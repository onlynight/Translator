package com.github.onlynight.android.string_xml.translate.translator;

import com.github.onlynight.android.string_xml.translate.translator.xmlImpl.XMLTranslator;
import com.github.onlynight.android.string_xml.translate.utils.Constants;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by lion on 2016/10/28.
 * 翻译管理器，统一调度管理翻译器
 */
public class TranslateManager {

    //翻译扫描路径
    private String translatePath = "";
    //是否翻译扫描路径下的所有xml文件
    private boolean translateAllXml = false;
    //翻译平台
    private TranslatePlatform platform;

    //如果translateAllXml==false则默认翻译strings.xml文件
    private static final String XML_FILE_NAME = "strings.xml";

    private static TranslateManager instance;

    public static TranslateManager getInstance() {
        if (instance == null) {
            instance = new TranslateManager();
        }
        return instance;
    }

    public void init(String translatePath, boolean translateAllXml, TranslatePlatform platform) {
        this.translatePath = translatePath;
        this.translateAllXml = translateAllXml;
        this.platform = platform;
    }

    public void init(String translatePath, boolean translateAllXml) {
        this.translatePath = translatePath;
        this.translateAllXml = translateAllXml;
        this.platform = TranslatePlatform.GOOGLE;
    }

    public void translate(Language src, Language dest) {
        if (translateAllXml) {
            File file = new File(translatePath);
            File[] files = file.listFiles();
            if (files != null) {
                for (File temp : files) {
                    if (temp.getName().endsWith(".xml")) {
                        innerTranslate(temp.getAbsolutePath(), src, dest);
                    }
                }
            }
        } else {
            innerTranslate(translatePath + File.separator + XML_FILE_NAME, src, dest);
        }
    }

    private void innerTranslate(String path, Language src, Language target) {
        Class<? extends XMLTranslator> translatorClass = Constants.platformConfig.get(platform);
        try {
            Constructor<? extends XMLTranslator> constructor = translatorClass.getDeclaredConstructor(String.class);
            XMLTranslator translator = constructor.newInstance(path);
            translator.translate(src, target);
        } catch (NoSuchMethodException | IllegalAccessException |
                InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void setTranslatePath(String translatePath) {
        this.translatePath = translatePath;
    }

    public String getTranslatePath() {
        return translatePath;
    }

    public void setTranslateAllXml(boolean translateAllXml) {
        this.translateAllXml = translateAllXml;
    }

    public boolean isTranslateAllXml() {
        return translateAllXml;
    }

    public TranslatePlatform getPlatform() {
        return platform;
    }

    public void setPlatform(TranslatePlatform platform) {
        this.platform = platform;
    }
}
