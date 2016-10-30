package com.github.onlynight.android.string_xml.translate.translator.handler;

import com.alibaba.fastjson.JSON;
import com.github.onlynight.android.string_xml.translate.translator.Language;
import com.github.onlynight.android.string_xml.translate.translator.result.YouDaoTranslateResult;
import com.github.onlynight.android.string_xml.translate.utils.Constants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by lion on 2016/10/30.
 */
public class YouDaoTranslatorHandler implements TranslatorHandler{

    private static final String YOUDAO_API_KEY = Constants.YOUDAO_API_KEY;
    private static final String YOUDAO_TRANSLATE_URL = "http://fanyi.youdao.com/openapi.do?keyfrom=FishMatser" +
            "&key=" + YOUDAO_API_KEY + "&type=data&doctype=json&version=1.1&q=";

    @Override
    public String onGenerateUrl(String content, Language src, Language target) {
        String url = YOUDAO_TRANSLATE_URL;
        try {
            url += URLEncoder.encode(content, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }

    @Override
    public String handleJsonString(String result) {
        YouDaoTranslateResult json = JSON.parseObject(result, YouDaoTranslateResult.class);
        if (json != null && json.getTranslation() != null &&
                json.getTranslation().size() > 0) {
            return json.getTranslation().get(0);
        }

        return null;
    }

    @Override
    public String handleXMLString(String result) {
        return null;
    }
}
