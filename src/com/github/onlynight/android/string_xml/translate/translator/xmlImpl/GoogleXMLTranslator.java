package com.github.onlynight.android.string_xml.translate.translator.xmlImpl;

import com.github.onlynight.android.string_xml.translate.translator.Language;
import com.github.onlynight.android.string_xml.translate.translator.handler.GoogleTranslatorHandler;

/**
 * Created by lion on 2016/10/28.
 */
public class GoogleXMLTranslator extends XMLTranslator {

    private GoogleTranslatorHandler handler;

    public GoogleXMLTranslator(String filePath) {
        super(filePath);
        handler = new GoogleTranslatorHandler();
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
