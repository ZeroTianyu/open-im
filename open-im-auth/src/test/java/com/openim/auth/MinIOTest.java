package com.openim.auth;


import com.openim.common.utils.MinIOUtils;
import io.minio.ObjectWriteResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;

@SpringBootTest
@Slf4j
public class MinIOTest {
    @Test
    public void minioTest() throws Exception {

        ObjectWriteResponse avatar = MinIOUtils.uploadFile("avatar", "321321111.png", new FileInputStream("D:\\Pictures\\MSI Wallpaper\\MSI.png"));


//        String avatar1 = MinIOUtils.getPresignedObjectUrl("avatar", "321321111.png");
//        log.info("上传头像：{}", avatar1);
    }

}
