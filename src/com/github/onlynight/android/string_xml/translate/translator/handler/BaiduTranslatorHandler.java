package com.github.onlynight.android.string_xml.translate.translator.handler;

import com.alibaba.fastjson.JSON;
import com.github.onlynight.android.string_xml.translate.translator.Language;
import com.github.onlynight.android.string_xml.translate.translator.result.BaiduTranslateResult;
import com.github.onlynight.android.string_xml.translate.utils.Constants;
import com.github.onlynight.android.string_xml.translate.utils.MD5Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;

/**
 * Created by lion on 2016/10/30.
 */
public class BaiduTranslatorHandler implements TranslatorHandler {

    private static final String BAIDU_APP_ID = Constants.BAIDU_APP_ID;
    private static final String BAIDU_APP_SECRET = Constants.BAIDU_APP_SECRET;
    private static final String BAIDU_TRANSLATE_URL = "http://api.fanyi.baidu.com/api/trans/vip/translate?";

    @Override
    public String onGenerateUrl(String content, Language src, Language target) {
        Random random = new Random();
        int randomInt = random.nextInt();
        String sign = sign(content, randomInt);
        try {
            return BAIDU_TRANSLATE_URL +
                    "from=" + convertLanguage(src) +
                    "&to=" + convertLanguage(target) +
                    "&appid=" + BAIDU_APP_ID +
                    "&salt=" + randomInt +
                    "&q=" + URLEncoder.encode(content, "utf-8") +
                    "&sign=" + sign;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String handleJsonString(String result) {
        BaiduTranslateResult jsonResult = JSON.parseObject(result, BaiduTranslateResult.class);
        if (jsonResult != null &&
                jsonResult.getTrans_result() != null &&
                jsonResult.getTrans_result().get(0) != null) {
            return jsonResult.getTrans_result().get(0).getDst();
        }
        return null;
    }

    @Override
    public String handleXMLString(String result) {
        return null;
    }

    private String sign(String content, int randomInt) {
        String signParams = BAIDU_APP_ID + content + randomInt + BAIDU_APP_SECRET;
        return MD5Utils.getMD5Code(signParams);
    }

    private String convertLanguage(Language language) {
        switch (language.getValue()) {
            case "zh-CN":
                return "zh";
            case "zh-TW":
                return "cht";
            case "ja":
                return "jp";
            default:
                return language.getValue();
        }
    }
}
