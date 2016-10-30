package com.github.onlynight.android.string_xml.translate.translator;

/**
 * Created by lion on 2016/10/28.
 * 语言简写配置，目前的几种语言使用的是google翻译的标准
 */
public enum Language {
    CN("zh-cn"), //简体中文
    TW("zh-tw"), //繁体中文
    JA("ja"), //日文
    EN("en"); //英文

    private String value;

    public String getValue() {
        return value;
    }

    Language(String value) {
        this.value = value;
    }
}