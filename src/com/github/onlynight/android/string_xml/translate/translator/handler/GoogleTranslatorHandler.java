package com.github.onlynight.android.string_xml.translate.translator.handler;

import com.alibaba.fastjson.JSON;
import com.github.onlynight.android.string_xml.translate.translator.Language;
import com.github.onlynight.android.string_xml.translate.translator.result.GoogleTranslateResult;
import com.github.onlynight.android.string_xml.translate.utils.Constants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by lion on 2016/10/30.
 */
public class GoogleTranslatorHandler implements TranslatorHandler {

    private static final String GOOGLE_API_KEY = Constants.GOOGLE_API_KEY;
    private static final String GOOGLE_TRANSLATE_URL = "https://www.googleapis.com/language/translate/v2?key=" + GOOGLE_API_KEY;

    @Override
    public String onGenerateUrl(String content, Language src, Language target) {
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

    @Override
    public String handleJsonString(String result) {
        GoogleTranslateResult json = JSON.parseObject(result, GoogleTranslateResult.class);
        if (json != null && json.getData() != null &&
                json.getData().getTranslations() != null &&
                json.getData().getTranslations().size() > 0) {
            return json.getData().getTranslations().get(0).getTranslatedText();
        }

        return null;
    }

    @Override
    public String handleXMLString(String result) {
        return null;
    }
}
