//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.openim.auth.enums;

import java.io.Serializable;

public interface ResultCode extends Serializable {
    int getCode();

    String getMessage();
    Boolean getSuccess();
}
