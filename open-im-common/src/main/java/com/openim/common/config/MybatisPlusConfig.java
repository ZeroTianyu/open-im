package com.openim.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.openim.common.entity.BaseEntity;
import com.openim.common.model.jwt.JwtPayload;
import com.openim.common.utils.JwtPayLoadUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * MybatisPlus分页插件
 *
 * @author vains
 */
@Configuration
// Mapper接口的包路径，记得替换为自己的mapper接口包路径
public class MybatisPlusConfig {

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,
     * 需要设置 MybatisConfiguration#useDeprecatedExecutor = false
     * 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * 这里对应的是实体类中的`@TableField(fill = FieldFill.INSERT_UPDATE)`注解
     * fill的值可以是INSERT、UPDATE和INSERT_UPDATE
     * INSERT：插入时填充字段
     * UPDATE：修改时填充字段
     * INSERT_UPDATE：插入与修改时都触发
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {

            @Override
            public void insertFill(MetaObject metaObject) {
                if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity baseEntity) {

                    LocalDateTime current = LocalDateTime.now();
                    // 创建时间为空，则以当前时间为插入时间
                    if (Objects.isNull(baseEntity.getCreateTime())) {
                        baseEntity.setCreateTime(current);
                    }
                    // 更新时间为空，则以当前时间为更新时间
                    if (Objects.isNull(baseEntity.getUpdateTime())) {
                        baseEntity.setUpdateTime(current);
                    }

                    JwtPayload jwtPayLoad = JwtPayLoadUtils.getJwtPayLoad();

                    // 当前登录用户不为空，创建人为空，则当前登录用户为创建人
                    if (Objects.nonNull(jwtPayLoad) && Objects.isNull(baseEntity.getCreaterId())) {
                        baseEntity.setCreaterId(jwtPayLoad.getUserId());
                    }

                    if (Objects.nonNull(jwtPayLoad) && Objects.isNull(baseEntity.getCreaterName())) {
                        baseEntity.setCreaterName(jwtPayLoad.getNikeName());
                    }

                    // 当前登录用户不为空，更新人为空，则当前登录用户为更新人
                    if (Objects.nonNull(jwtPayLoad) && Objects.isNull(baseEntity.getUpdaterId())) {
                        baseEntity.setUpdaterId(jwtPayLoad.getUserId());
                    }
                    if (Objects.nonNull(jwtPayLoad) && Objects.isNull(baseEntity.getUpdaterName())) {
                        baseEntity.setUpdaterName(jwtPayLoad.getNikeName());
                    }
                }
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity baseEntity) {
                    JwtPayload jwtPayLoad = JwtPayLoadUtils.getJwtPayLoad();
                    // 当前登录用户不为空，更新人为空，则当前登录用户为更新人
                    if (Objects.nonNull(jwtPayLoad) && Objects.isNull(baseEntity.getUpdaterId())) {
                        baseEntity.setUpdaterId(jwtPayLoad.getUserId());
                    }
                    if (Objects.nonNull(jwtPayLoad) && Objects.isNull(baseEntity.getUpdaterName())) {
                        baseEntity.setUpdaterName(jwtPayLoad.getNikeName());
                    }
                    // 更新时间为空，则以当前时间为更新时间
                    if (Objects.isNull(baseEntity.getUpdateTime())) {
                        baseEntity.setUpdateTime(LocalDateTime.now());
                    }
                }
            }

        };
    }

}
