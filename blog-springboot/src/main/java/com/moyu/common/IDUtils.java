package com.moyu.common;

import java.util.UUID;

public class IDUtils {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
