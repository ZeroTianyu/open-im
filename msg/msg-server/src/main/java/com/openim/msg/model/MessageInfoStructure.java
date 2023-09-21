package com.openim.msg.model;

import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@ToString
public class MessageInfoStructure {
    //消息类型
    private String msgType;
    //消息内容
    private byte[] msgContent;

    public String getMsgType() {
        return msgType;
    }
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
    public byte[] getMsgContent() {
        return msgContent;
    }
    public void setMsgContent(byte[] msgContent) {
        this.msgContent = msgContent;
    }
}