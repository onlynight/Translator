package com.github.onlynight.android.string_xml.translate.translator.xml;

import com.alibaba.fastjson.JSON;
import com.github.onlynight.android.string_xml.translate.translator.Language;
import com.github.onlynight.android.string_xml.translate.translator.result.YouDaoTranslateResult;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by lion on 2016/10/28.
 */
public class YouDaoXMLTranslator extends XMLTranslator {

    private static final String YOUDAO_TRANSLATE_URL = "http://fanyi.youdao.com/openapi.do?keyfrom=FishMatser&key=1288189848&type=data&doctype=json&version=1.1&q=";

    public YouDaoXMLTranslator(String filePath) {
        super(filePath);
    }

    @Override
    protected String onGenerateUrl(String content, Language src, Language target) {
        String url = YOUDAO_TRANSLATE_URL;
        try {
            url += URLEncoder.encode(content, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }

    @Override
    protected String onTranslateFinished(String result) {
        YouDaoTranslateResult json = JSON.parseObject(result, YouDaoTranslateResult.class);
        if (json != null && json.getTranslation() != null &&
                json.getTranslation().size() > 0) {
            return json.getTranslation().get(0);
        }

        return null;
    }

}
