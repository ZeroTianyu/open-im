package com.openim.auth.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
public abstract class PageModel<T> {
    @NotNull(message = "start 不可为null")
    @Min(value = 0, message = "start 不可小于0")
    @Max(value = Integer.MAX_VALUE, message = "start 最大值超限")
    private Integer start = 1;
    @NotNull(message = "limit 不可为null")
    @Min(value = 1, message = "limit 不可小于1")
    @Max(value = Integer.MAX_VALUE, message = "limit 最大值超限")
    private Integer limit = 10;
    private String orderBy;
    private Boolean isAsc;
}
