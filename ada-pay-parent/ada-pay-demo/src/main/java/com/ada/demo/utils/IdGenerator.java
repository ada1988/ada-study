package com.ada.demo.utils;

import java.util.UUID;


/**
 * Description: 全局ID生成器 <br>
 *
 * @Copyright: Copyright (c)2018 by Zhuzhao <br>
 */
public class IdGenerator {

    /**
     * 生成UUID
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
