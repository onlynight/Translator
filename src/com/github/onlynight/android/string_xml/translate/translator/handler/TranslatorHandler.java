package com.github.onlynight.android.string_xml.translate.translator.handler;

import com.github.onlynight.android.string_xml.translate.translator.Language;

/**
 * Created by lion on 2016/10/30.
 * 将rest_api处理和具体的translator分离方便拓展
 */
public interface TranslatorHandler {

    /**
     * 在不同的平台中实现，返回最终的请求url
     *
     * @param content 要翻译的内容，utf8需要encode一次
     * @param src     原语言
     * @param target  要翻译的目标语言
     * @return
     */
    String onGenerateUrl(String content, Language src, Language target);

    /**
     * REST_API调用返回结果
     *
     * @param result json字符串
     * @return 翻译的最终结果
     */
    String handleJsonString(String result);

    /**
     * REST_API调用返回结果
     *
     * @param result xml字符串
     * @return 翻译的最终结果
     */
    String handleXMLString(String result);
}
