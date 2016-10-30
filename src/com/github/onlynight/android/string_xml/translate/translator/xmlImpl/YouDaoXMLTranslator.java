package com.github.onlynight.android.string_xml.translate.translator.xmlImpl;

import com.github.onlynight.android.string_xml.translate.translator.Language;
import com.github.onlynight.android.string_xml.translate.translator.handler.YouDaoTranslatorHandler;

/**
 * Created by lion on 2016/10/28.
 */
public class YouDaoXMLTranslator extends XMLTranslator {

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

}
