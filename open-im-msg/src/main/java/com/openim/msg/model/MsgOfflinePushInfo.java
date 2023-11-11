
package com.openim.msg.model;


import lombok.Data;

@Data
public class MsgOfflinePushInfo {
    /**
     * 推送的标题
     */
    private String title;
    /**
     * 推送的具体描述
     */
    private String desc;
    /**
     * 扩展字段
     */
    private String ex;
    /**
     * IOS的推送声音
     */
    private String iOSPushSound;
    /**
     * IOS推送消息是否计入桌面图标未读数
     */
    private Boolean iOSBadgeCount;

}