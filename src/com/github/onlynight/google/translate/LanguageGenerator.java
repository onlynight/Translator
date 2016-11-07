package com.github.onlynight.google.translate;

import com.github.onlynight.android.string_xml.translate.translator.TranslatePlatform;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lion on 2016/11/7.
 */
public class LanguageGenerator {

    public static void main(String args[]) {
        String currentPath = new File("").getAbsolutePath();
        generate(currentPath, TranslatePlatform.GOOGLE);
    }

    private static void generate(String currentPath, TranslatePlatform platform) {
        List<String> languages = readGoogleSupportPlatform(currentPath +
                File.separator + platform.getValue() + "_support_language.txt");
        genLanguagePlatform(currentPath, languages, platform);
    }

    private static List<String> readGoogleSupportPlatform(String path) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(new File(path))));
            List<String> countries = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                countries.add(line);
            }

            return countries;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void genLanguagePlatform(String path, List<String> languages,
                                            TranslatePlatform platform) {
        try {
            List<String> languagesPlatform = new ArrayList<>();
            List<String> values = new ArrayList<>();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(new File(path +
                            File.separator + platform.getValue() +
                            "LanguageGen.java")), "utf-8"));
            String title = "/**\n" +
                    " * 这个是自动生成类，你把自动生成的代码复制到需要的地方就好了\n" +
                    " */";
            writer.write(title);
            writer.write("\n");
            writer.write("\n");

            for (String language : languages) {
                String temp = language.toUpperCase().replace('-', '_');
                languagesPlatform.add(temp);
                writer.write(temp + "(\"" + platform + "\"),\n");
            }

            writer.write("\n");

            for (String language : languages) {
                String valuesName;
                if (language.contains("-")) {
                    valuesName = "VALUES_" + language.toUpperCase().replace("-", "_");
                    String temp[] = language.split("-");
                    writer.write("public static final String "
                            + valuesName
                            + " = \"values-" + temp[0] + "-r" + temp[1] + "\";\n");
                } else {
                    valuesName = "VALUES_" + language.toUpperCase();
                    writer.write("public static final String "
                            + "VALUES_" + language.toUpperCase()
                            + " = \"values-" + platform + "\";\n");
                }
                values.add(valuesName);
            }

            writer.write("\n");

            for (int i = 0; i < languagesPlatform.size(); i++) {
                writer.write("valuesFolders.put(Language." +
                        languagesPlatform.get(i) + ", " +
                        values.get(i) + ");\n");
            }
            writer.write("\n");

            for (String lan : languagesPlatform) {
                writer.write("languages.add(Language." + lan + ");\n");
            }

            writer.close();

            System.out.println("Generate Finish!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
