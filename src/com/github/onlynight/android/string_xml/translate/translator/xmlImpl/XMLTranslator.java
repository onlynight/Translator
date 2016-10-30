package com.github.onlynight.android.string_xml.translate.translator.xmlImpl;

import com.github.onlynight.android.string_xml.translate.translator.Language;
import com.github.onlynight.android.string_xml.translate.translator.Translator;
import com.github.onlynight.android.string_xml.translate.utils.Constants;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

/**
 * Created by lion on 2016/10/28.
 * android string.xmlImpl 字符串资源翻译器读写管理，并且调用翻译REST_API进行翻译。
 * 基本逻辑如下：
 * 1. 读取xml文件
 * 2. 遍历xml标签，逐一进行翻译
 * 3. 将翻译完成的xml保存的新的文件中
 */
public abstract class XMLTranslator implements Translator {

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
        return Constants.valuesFolders.get(target);
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
