package com.github.onlynight.android.string_xml.translate.translator;

import com.github.onlynight.android.string_xml.translate.translator.xml.BaiduXMLTransaltor;
import com.github.onlynight.android.string_xml.translate.translator.xml.GoogleXMLTranslator;
import com.github.onlynight.android.string_xml.translate.translator.xml.XMLTranslator;
import com.github.onlynight.android.string_xml.translate.translator.xml.YouDaoXMLTranslator;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lion on 2016/10/28.
 */
public class TranslateManager {

    private static final Map<TranslatePlatform, Class<? extends XMLTranslator>> platformConfig =
            new HashMap<>();

    static {
        platformConfig.put(TranslatePlatform.BAIDU, BaiduXMLTransaltor.class);
        platformConfig.put(TranslatePlatform.GOOGLE, GoogleXMLTranslator.class);
        platformConfig.put(TranslatePlatform.YOUDAO, YouDaoXMLTranslator.class);
    }

    private String translatePath = "";
    private boolean translateAllXml = false;
    private TranslatePlatform platform;

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
        Class<? extends XMLTranslator> translatorClass = platformConfig.get(platform);
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
