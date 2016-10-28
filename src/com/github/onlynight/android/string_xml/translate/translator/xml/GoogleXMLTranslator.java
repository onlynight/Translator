package com.github.onlynight.android.string_xml.translate.translator.xml;

import com.alibaba.fastjson.JSON;
import com.github.onlynight.android.string_xml.translate.translator.Language;
import com.github.onlynight.android.string_xml.translate.translator.result.GoogleTranslateResult;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by lion on 2016/10/28.
 */
public class GoogleXMLTranslator extends XMLTranslator {

    private static final String GOOGLE_API_KEY = "AIzaSyBf5PSylPMHNfCGlzAFJ-8pNOh-0TKuZnE";
    private static final String GOOGLE_TRANSLATE_URL = "https://www.googleapis.com/language/translate/v2?key=" + GOOGLE_API_KEY;

    public GoogleXMLTranslator(String filePath) {
        super(filePath);
    }

    @Override
    protected String onGenerateUrl(String content, Language src, Language target) {
        return getGoogleTranslateUrl(content, src, target);
    }

    @Override
    protected String onTranslateFinished(String result) {
        GoogleTranslateResult json = JSON.parseObject(result, GoogleTranslateResult.class);
        if (json != null && json.getData() != null &&
                json.getData().getTranslations() != null &&
                json.getData().getTranslations().size() > 0) {
            return json.getData().getTranslations().get(0).getTranslatedText();
        }

        return null;
    }

    private String getGoogleTranslateUrl(String content, Language src, Language target) {
        StringBuilder sb = new StringBuilder();
        sb.append(GOOGLE_TRANSLATE_URL);
        try {
            sb.append("&source=").append(src.getValue());
            sb.append("&target=").append(target.getValue());
            sb.append("&q=").append(URLEncoder.encode(content, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
