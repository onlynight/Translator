package com.github.onlynight.android.string_xml.translate.utils;

import com.github.onlynight.android.string_xml.translate.translator.Language;
import com.github.onlynight.android.string_xml.translate.translator.TranslatePlatform;
import com.github.onlynight.android.string_xml.translate.translator.xmlImpl.BaiduXMLTranslator;
import com.github.onlynight.android.string_xml.translate.translator.xmlImpl.GoogleXMLTranslator;
import com.github.onlynight.android.string_xml.translate.translator.xmlImpl.XMLTranslator;
import com.github.onlynight.android.string_xml.translate.translator.xmlImpl.YouDaoXMLTranslator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lion on 2016/10/29.
 * 常用变量集中配置管理
 */
public class Constants {

    //百度翻译平台账号信息
    public static final String BAIDU_APP_ID = "20161028000030981";
    public static final String BAIDU_APP_SECRET = "6SluTL5sbgymRn0IWZfd";
    //google翻译平台所有api key
    public static final String GOOGLE_API_KEY = "AIzaSyBf5PSylPMHNfCGlzAFJ-8pNOh-0TKuZnE";
    //有道翻译平台api key
    public static final String YOUDAO_API_KEY = "1288189848";

    //翻译平台配置，方便拓展所以放在这里
    public static final Map<TranslatePlatform, Class<? extends XMLTranslator>>
            platformConfig = new HashMap<>();

    static {
        platformConfig.put(TranslatePlatform.BAIDU, BaiduXMLTranslator.class);
        platformConfig.put(TranslatePlatform.GOOGLE, GoogleXMLTranslator.class);
        platformConfig.put(TranslatePlatform.YOUDAO, YouDaoXMLTranslator.class);
    }

    //不同语言对应的文件夹名称，如果你需要其他的语言你需要在这里添加
    private static final String VALUES_EN = "values-en";
    private static final String VALUES_JA = "values-ja";
    private static final String VALUES_CN = "values-zh-rCN";
    private static final String VALUES_TW = "values-zh-rTW";

    //Language对应的文件夹名称，当你需要拓展的时候你必须在这里配置
    public static final Map<Language, String> valuesFolders = new HashMap<>();

    static {
        valuesFolders.put(Language.CN, VALUES_CN);
        valuesFolders.put(Language.TW, VALUES_TW);
        valuesFolders.put(Language.JA, VALUES_JA);
        valuesFolders.put(Language.EN, VALUES_EN);
    }
}
