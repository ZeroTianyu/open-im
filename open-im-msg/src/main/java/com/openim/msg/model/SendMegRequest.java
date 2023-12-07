
package com.openim.msg.model;


import lombok.Data;

import java.io.Serializable;

@Data
public class SendMegRequest implements Serializable {
    /**
     * 接收者ID，sessionType1或者4时必填，如果是群聊则不填
     */
    private String recvID;
    /**
     * 群ID，sessionType2或者3时必填，如果为单聊则不填
     */
    private String groupID;
    /**
     * 消息发送者昵称
     */
    private String senderNickname;
    /**
     * 消息发送者头像
     */
    private String senderFaceURL;
    /**
     * 发送者平台号，模拟用户发送时填写， 1：IOS，2：Android，3：Windows，4：OSX，5：Web，6：MiniWeb，7：Linux，8：Android Pad，9：IPad，10：admin
     */
    private Integer senderPlatformID;
    /**
     * 消息的具体内容，内部是json 对象，其他消息的详细字段请参考消息类型格式描述文档
     */
    private MsgContent content;
    /**
     * 消息类型，目前支持的消息类型有：
     * 101	文本消息
     * 102	图片消息
     * 103	音频消息
     * 104	视频消息
     * 105	文件消息
     * 110	自定义消息
     * 1400	系统通知类型消息
     */
    private Integer contentType;
    /**
     * 	会话类型,1：单聊，3：群聊，4：系统通知
     */
    private Integer sessionType;
    /**
     * 接收者在线才能收到，否则将会丢失
     */
    private Boolean isOnlineOnly;
    /**
     * 不进行离线推送
     */
//    private Boolean notOfflinePush;
    /**
     * 消息发送时间，仅导入消息时填写，单位毫秒
     */
    private Long sendTime;
    /**
     * 离线推送的具体内容，如果不填写，使用服务器默认推送标题
     */
//    private MsgOfflinePushInfo offlinePushInfo;

}