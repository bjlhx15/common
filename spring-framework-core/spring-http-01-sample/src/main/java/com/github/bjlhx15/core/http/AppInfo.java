/*
 * @(#)AppInfo  1.0 2019/11/5
 *
 * Copyright 2014-2019 jd jr All Rights Reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * Author Email: wyjiangchunan@jd.com
 */

package com.github.bjlhx15.core.http;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppInfo {

    /**
     * 应用ID.
     */
    private String appId;

    /**
     * 应用ID类型, 默认直接 "0".
     */
    private String appIdType = "0";

    /**
     * 平台公钥.
     */
    private String openPlatPublicKey;

    /**
     * 应用私钥.
     */
    private String appPrivateKey;

    /**
     * MD5盐.
     */
    private String md5Salt;

    /**
     * 加解密方式.
     */
    private String encryptType = "RSA";

    /**
     * 验签方式.
     */
    private String signType = "MD5_RSA";
}
