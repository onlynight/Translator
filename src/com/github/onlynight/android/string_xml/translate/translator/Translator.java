package com.github.onlynight.android.string_xml.translate.translator;

/**
 * Created by lion on 2016/9/23.
 * 翻译公共接口，以后拓展就可以不局限为xml翻译
 */
public interface Translator {

    /**
     * 翻译统一调用入口
     *
     * @param src    原语言
     * @param target 要翻译的目标语言
     */
    void translate(Language src, Language target);

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
     * 请求REST_API后返回string处理这个结果，
     * 你需要在请求REST_API后调用该函数。
     *
     * @param result 请求REST_API返回string
     * @return 最终处理翻译请求的结果
     */
    String onTranslateFinished(String result);
}
