package com.github.onlynight.android.string_xml.translate;

import com.github.onlynight.android.string_xml.translate.translator.Language;
import com.github.onlynight.android.string_xml.translate.translator.TranslateManager;
import com.github.onlynight.android.string_xml.translate.translator.TranslatePlatform;

import java.io.File;

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
//        TranslateManager.getInstance().translate(Language.CN, Language.JA);
//        TranslateManager.getInstance().translate(Language.CN, Language.TW);
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
//        TranslateManager.getInstance().translate(Language.CN, Language.EN);
        TranslateManager.getInstance().translate(Language.CN, Language.JA);
//        TranslateManager.getInstance().translate(Language.CN, Language.TW);
    }
}
