package com.github.onlynight.android.string_xml.translate;

import com.github.onlynight.android.string_xml.translate.translator.Language;
import com.github.onlynight.android.string_xml.translate.translator.TranslateManager;
import com.github.onlynight.android.string_xml.translate.translator.TranslatePlatform;

import java.io.File;

/**
 * 翻译主入口
 * 不只到为什么打包成jar就中有就是又乱码，暂时没有解决所有不能提供工具包。
 * <p>
 * 你可以根据我的规则自己添加任意翻译平台，目前只支持有道翻译，百度翻译以及google翻译。
 * <p>
 * 使用方法：
 * 1. TranslateManager.getInstance().init();初始化翻译管理器
 * 2. TranslateManager.getInstance().translate();翻译
 * 3. 当你要切换平台时需要重新初始化TranslateManager
 * <p>
 * 拓展规则：
 * 1. 首先新建一个类继承自
 * {@link com.github.onlynight.android.string_xml.translate.translator.xmlImpl.XMLTranslator}
 * 并且实现它的抽象方法。
 * 2. 在你的类的#onGenerateUrl方法中返回你添加的翻译平台的REST_API的完整请求url，
 * 其中有可能包含平台的的appid以及secret更具不同的平台的参数说明填写完整请求url即可。
 * 3. 在{@link com.github.onlynight.android.string_xml.translate.translator.result}包中添加平台REST_API
 * 请求返回结果Gson类（你可以使用GsonFormat插件快速生成）。
 * 4. 在你的类的#onTranslateFinished方法中处理REST_API返回的json为jsonobject，我这里使用的是fastjson，
 * 你同样可以使用Gson处理。处理完成后返回最终的翻译结果。
 * 5. 更加抽象的，我们不在具体的{Translator}中实现所有的操作，为了方便拓展，我们抽象出Handler将所有的操作都在
 * {Handler}中实现，具体代码参见{@link com.github.onlynight.android.string_xml.translate.translator.handler}
 * <p>
 * 注意：
 * 目前只添加了简体中文，繁体中文，英文，日文集中语言，如果你需要添加其他语言你需要做以下几件事：
 * 1. 在{@link com.github.onlynight.android.string_xml.translate.translator.TranslatePlatform}中
 * 新建一个平台，并且将新添加的平台配置到
 * {@link com.github.onlynight.android.string_xml.translate.utils.Constants}
 * 类中的platformConfig中去，具体方法参考其他平台配置。
 * 2. {@link com.github.onlynight.android.string_xml.translate.translator.Language}中添加新的语言
 * 这里使用的是google翻译平台标准简称，如果你使用其他平台需要将简称转换以下，参考
 * {@link com.github.onlynight.android.string_xml.translate.translator.xmlImpl.BaiduXMLTranslator}
 * 中的转换方法。
 * 3. 你需要在{@link com.github.onlynight.android.string_xml.translate.utils.Constants}
 * 中配置valuesFolders，添加不同语言对应的文件夹。
 */
public class Main {

    public Main() {
        super();
    }

    public static void main(String[] args) {
        File currentPath = new File("");
        baidu(currentPath, true);
//        google(currentPath, true);
//        youdao(currentPath, true);
    }

    private static void youdao(File currentPath, boolean translateAllXml) {
        TranslateManager.getInstance().
                init(currentPath.getAbsolutePath(), translateAllXml, TranslatePlatform.YOUDAO);
        TranslateManager.getInstance().translate(Language.CN, Language.EN);
        TranslateManager.getInstance().translate(Language.CN, Language.JA);
        TranslateManager.getInstance().translate(Language.CN, Language.TW);
    }

    private static void google(File currentPath, boolean translateAllXml) {
        TranslateManager.getInstance().
                init(currentPath.getAbsolutePath(), translateAllXml, TranslatePlatform.GOOGLE);
        TranslateManager.getInstance().translate(Language.CN, Language.EN);
        TranslateManager.getInstance().translate(Language.CN, Language.JA);
        TranslateManager.getInstance().translate(Language.CN, Language.TW);
    }

    private static void baidu(File currentPath, boolean translateAllXml) {
        TranslateManager.getInstance().
                init(currentPath.getAbsolutePath(), translateAllXml, TranslatePlatform.BAIDU);
        TranslateManager.getInstance().translate(Language.CN, Language.EN);
        TranslateManager.getInstance().translate(Language.CN, Language.JA);
        TranslateManager.getInstance().translate(Language.CN, Language.TW);
    }
}
