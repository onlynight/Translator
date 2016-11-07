package com.github.onlynight.android.string_xml.translate.translator;

/**
 * Created by lion on 2016/10/28.
 * 翻译平台
 */
public enum TranslatePlatform {
    YOUDAO("youdao"),//有道
    GOOGLE("google"),//google
    BAIDU("baidu"); //百度

    private String value;

    TranslatePlatform(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
