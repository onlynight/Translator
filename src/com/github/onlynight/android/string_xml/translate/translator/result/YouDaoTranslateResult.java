package com.github.onlynight.android.string_xml.translate.translator.result;

import java.util.List;

/**
 * Created by lion on 2016/9/23.
 */
public class YouDaoTranslateResult {
    /**
     * translation : ["The test translation"]
     * query : 测试翻译
     * errorCode : 0
     */

    private String query;
    private int errorCode;
    private List<String> translation;

    public void setQuery(String query) {
        this.query = query;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setTranslation(List<String> translation) {
        this.translation = translation;
    }

    public String getQuery() {
        return query;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public List<String> getTranslation() {
        return translation;
    }
}
