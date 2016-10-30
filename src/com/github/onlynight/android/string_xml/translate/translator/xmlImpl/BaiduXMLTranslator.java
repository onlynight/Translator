package com.github.onlynight.android.string_xml.translate.translator.xmlImpl;

import com.github.onlynight.android.string_xml.translate.translator.Language;
import com.github.onlynight.android.string_xml.translate.translator.handler.BaiduTranslatorHandler;

/**
 * Created by lion on 2016/10/28.
 * 百度翻译
 */
public class BaiduXMLTranslator extends XMLTranslator {

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
}
