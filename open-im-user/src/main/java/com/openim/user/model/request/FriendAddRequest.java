package com.openim.user.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FriendAddRequest {
    /**
     * 目标id
     */
    @NotNull(message = "要添加的好友id不能为空")
    private Long friendId;
}
