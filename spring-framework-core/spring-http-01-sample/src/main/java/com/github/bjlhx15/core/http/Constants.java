/*
 * @(#)Constants  1.0 2019/11/4
 *
 * Copyright 2014-2019 jd jr All Rights Reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * Author Email: wyjiangchunan@jd.com
 */

package com.github.bjlhx15.core.http;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    /**
     * 公共部分开始
     */
    // 编码格式。发送编码格式统一用UTF-8
    public static final String ENCODING_CHAR = "UTF-8";
    public static final String HEADER_CONTENT_TYPE_JSON = "application/json;charset=UTF-8";
    public static final String BODY_CONTENT_TYPE_JSON = "application/json";

    /**
     * httpClient， 设置访问server获取配置超时时间配置
     */
    public static final Integer HTTPCLIENT_SOCKET_TIMEOUT = 30000;
    public static final Integer HTTPCLIENT_CONNECTION_TIMEOUT = 5000;
    public static final Integer HTTPCLIENT_REQUEST_TIMEOUT = 5000;
    public static final Integer HTTPCLIENT_MAX_TOTAL = 100;
    public static final Integer HTTPCLIENT_DEFAULT_MAX_PER_ROUTE = 100;


    /**
     * 网关报文中通用字段.
     */
    public static final String JRGW_REQUEST_TIME = "jrgw-request-time";
    public static final String JRGW_ENTERPRISE_USER_ID = "jrgw-enterprise-user-id";
    public static final String JRGW_USER_ID_TYPE = "jrgw-user-id-type";
    public static final String GW_ENCRYPT_TYPE = "gw-encrypt-type";
    public static final String GW_SIGN_TYPE = "gw-sign-type";
    public static final String GW_SIGN = "gw-sign";
    public static final String BIZ_CONTENT = "biz-content";
    public static final String ENCRYPT = "encrypt";
    public static final String JOIN_FLAG = "&";
    public static final String EQUAL_FLAG = "=";

    public static final String JRGW_RESP_CODE = "jrgw-resp-code";
    public static final String JRGW_RESP_MSG = "jrgw-resp-msg";
    public static final String JRGW_RESPOND_TIME = "jrgw-respond-time";

    /**
     * 通用的Key值等，正式环境需要单独申请.
     */
    public static final String APP_ID = "wenli";
    public static final String OPEN_KEYS_PLAT_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDAi58dFnrtnc6WOBEvnabT9pfhnckiT10fJUHNq0oHTbfJsrxRjwhq6z76zP0Sab7rhXvHwT3o60AwXarvD3aq+TXAUfohl6irNWVgOW6VGdh5gwyJewD0ar/oL0eW1KABrxwr8KKPo0yX6ol5NWvVPlUS6jTUW913PQ7rWokd6wIDAQAB";
    public static final String OPEN_KEYS_APP_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMZftHdtEZypS3ucX4oeHVGRTbaBnK0sNcK88m1JQxNCl9Ocpx3wDcpuTectn+nvjZcOmjUpqBv0cIV2Zs6z6Qytb5NtPkIuNNyI4QTlKfTU7umeuTer59HX51FtcVtS7g9mz2PzULXVYpqQJbUv8hLkKm6YPjyz+FVSx79uq4SrAgMBAAECgYBp3/aPrvSxamHRDXqoWr5HJIgXmkGm42umDfZNgcZ85xPlazx+a/rQSeLmOaGZ17L4JmEDcsI4eVP8x17Df54npzFkk6l5jd5buGrBZ9mvgd9CLDWSaloVJWanobQmVRKpi6lyTbQDgVTnl+OAjcZNbHrcLLph6uR+q9ntiXGJsQJBAOwbDo93mP4k0gUkjP4/g643TsX57e/LI4diO9VdHG53kq12GqU5jrt/l3FzzBGkVDzLALTZRvnkOvFZXKGGl/8CQQDXFr/RzUwrZNN4jKmmwB1AMiqm7A92X8E+tQe2/uOO9NJACc2x/ayWd0G8B0KedzvrCoZ/U5SCEkbJ/8IVTPNVAkAltXCO41gRBwpv/dVy6ev7ECRJpFAnIzeKcNsDibp3visz/w/kYZv1QDyMxyTJkIhPqVB0kdsvIA916oLL3XY7AkEAnmcxo4hnRTn/B7PIKXco06NeiAonpQ0GG3FlGh8DZb/lq6XMp/3jgfiYH6rVJDE5nnLk9JaQM27UVWWXAOrn9QJBAOpVrTvN5d7zqsn7zUWiCizr01BySX7Ow8EIvlsFdcLzShvs61kT9Tz3ZoW4+LTHaWB5fJWaO8ykxYf69BHXU7o=";
    public static final String MD5_SALT = "12345678";
}
