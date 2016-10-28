package com.github.onlynight.android.string_xml.translate.translator.xml;

import com.github.onlynight.android.string_xml.translate.translator.Language;
import com.github.onlynight.android.string_xml.translate.translator.Translator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by lion on 2016/10/28.
 */
public abstract class XMLTranslator extends Translator {

    private static final String VALUES_EN = "values-en";
    private static final String VALUES_JA = "values-ja";
    private static final String VALUES_CN = "values-zh-rCN";
    private static final String VALUES_TW = "values-zh-rTW";

    private static final Map<Language, String> folders = new HashMap<>();

    static {
        folders.put(Language.CN, VALUES_CN);
        folders.put(Language.TW, VALUES_TW);
        folders.put(Language.JA, VALUES_JA);
        folders.put(Language.EN, VALUES_EN);
    }

    private String filePath;

    public XMLTranslator(String filePath) {
        this.filePath = filePath;
    }

    private Document openDocument(File currentPath) {
        String xmlFileName = currentPath.getAbsolutePath();
        String xmlContent = loadXmlFile(xmlFileName);
        return getDocument(xmlContent);
    }

    private void writeDocument(String parentPath, Document document, Language target) {
        if (document == null) {
            return;
        }

        File dir = new File(parentPath + File.separator + getValuesFolderName(target));
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String xml = document.asXML();
        try {
            FileOutputStream fos = new FileOutputStream(new File(dir.getAbsolutePath() +
                    File.separator + new File(filePath).getName()));
            fos.write(xml.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getValuesFolderName(Language target) {
        return folders.get(target);
    }

    private Document getDocument(String xml) {
        if (xml != null) {
            try {
                return DocumentHelper.parseText(xml);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private String loadXmlFile(String path) {
        try {
            FileInputStream fis = new FileInputStream(new File(path));
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String result = "";
            String line;
            while ((line = reader.readLine()) != null) {
                result += line + "\n";
            }

            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String innerTranslate(String sourceString, Language src, Language target) {
        if (sourceString == null) {
            return null;
        }

        String url = onGenerateUrl(sourceString, src, target);

        try {
            URLConnection connection = new URL(url).openConnection();
            InputStream is = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
            String result = "";
            String line = "";
            while ((line = reader.readLine()) != null) {
                result += line;
            }

            return onTranslateFinished(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract String onGenerateUrl(String content, Language src, Language target);

    protected abstract String onTranslateFinished(String result);

    @Override
    public void translate(Language src, Language target) {
        File currentPath = new File(filePath);
        Document document = openDocument(currentPath);
        parseXmlAndTranslate(document, src, target);
        writeDocument(currentPath.getParentFile().getAbsolutePath(), document, target);
    }

    private void parseXmlAndTranslate(Document document, Language src, Language target) {
        if (document == null) {
            System.out.println("document is null");
            return;
        }
        Element rootElement = document.getRootElement();
        Iterator<Element> iterator = rootElement.elementIterator();
        while (iterator.hasNext()) {
            Element element = iterator.next();
//            System.out.println(element.attribute("name").getValue());
//            System.out.println(element.getText());
            String result = innerTranslate(element.getText(), src, target);
            if (result != null) {
                System.out.println(element.getText() + " TRANSLATE TO ====> " + result);
                element.setText(result);
            }
        }
    }
}
