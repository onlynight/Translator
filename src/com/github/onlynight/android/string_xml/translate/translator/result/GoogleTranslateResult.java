package com.github.onlynight.android.string_xml.translate.translator.result;

import java.util.List;

/**
 * Created by lion on 2016/10/28.
 */
public class GoogleTranslateResult {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * translatedText : 你好，世界
         */

        private List<TranslationsBean> translations;

        public List<TranslationsBean> getTranslations() {
            return translations;
        }

        public void setTranslations(List<TranslationsBean> translations) {
            this.translations = translations;
        }

        public static class TranslationsBean {
            private String translatedText;

            public String getTranslatedText() {
                return translatedText;
            }

            public void setTranslatedText(String translatedText) {
                this.translatedText = translatedText;
            }
        }
    }
}
