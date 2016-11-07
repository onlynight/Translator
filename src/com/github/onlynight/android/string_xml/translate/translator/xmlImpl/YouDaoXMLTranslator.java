package com.github.onlynight.android.string_xml.translate.translator.xmlImpl;

import com.github.onlynight.android.string_xml.translate.translator.Language;
import com.github.onlynight.android.string_xml.translate.translator.handler.YouDaoTranslatorHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lion on 2016/10/28.
 */
public class YouDaoXMLTranslator extends XMLTranslator {

    private static final List<Language> languages = new ArrayList<>();

    static {
        languages.add(Language.ZH_CN);
        languages.add(Language.EN);
    }

    private YouDaoTranslatorHandler handler;

    public YouDaoXMLTranslator(String filePath) {
        super(filePath);
        handler = new YouDaoTranslatorHandler();
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
