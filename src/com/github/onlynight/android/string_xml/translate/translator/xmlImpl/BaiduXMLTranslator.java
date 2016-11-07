package com.github.onlynight.android.string_xml.translate.translator.xmlImpl;

import com.github.onlynight.android.string_xml.translate.translator.Language;
import com.github.onlynight.android.string_xml.translate.translator.handler.BaiduTranslatorHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lion on 2016/10/28.
 * 百度翻译
 */
public class BaiduXMLTranslator extends XMLTranslator {

    private static final List<Language> languages = new ArrayList<>();

    static {
        languages.add(Language.ZH_CN);
        languages.add(Language.ZH_TW);
        languages.add(Language.EN);
        languages.add(Language.JA);
    }

    private BaiduTranslatorHandler handler;

    public BaiduXMLTranslator(String filePath) {
        super(filePath);
        handler = new BaiduTranslatorHandler();
    }

    @Override
    public String onGenerateUrl(String content, Language src, Language target) {
        return handler.onGenerateUrl(content, src, target);
    }

    @Override
    public String onTranslateFinished(String result) {
        return handler.handleJsonString(result);
    }

    @Override
    public List<Language> getSupportLanguage() {
        return languages;
    }
}
