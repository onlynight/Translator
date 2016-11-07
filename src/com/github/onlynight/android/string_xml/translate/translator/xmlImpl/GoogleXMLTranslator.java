package com.github.onlynight.android.string_xml.translate.translator.xmlImpl;

import com.github.onlynight.android.string_xml.translate.translator.Language;
import com.github.onlynight.android.string_xml.translate.translator.handler.GoogleTranslatorHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lion on 2016/10/28.
 */
public class GoogleXMLTranslator extends XMLTranslator {

    private static final List<Language> languages = new ArrayList<>();

    static {
        languages.add(Language.AF);
        languages.add(Language.SQ);
        languages.add(Language.AR);
        languages.add(Language.HY);
        languages.add(Language.AZ);
        languages.add(Language.EU);
        languages.add(Language.BE);
        languages.add(Language.BN);
        languages.add(Language.BS);
        languages.add(Language.BG);
        languages.add(Language.CA);
        languages.add(Language.CEB);
        languages.add(Language.NY);
        languages.add(Language.ZH_CN);
        languages.add(Language.ZH_TW);
        languages.add(Language.HR);
        languages.add(Language.CS);
        languages.add(Language.DA);
        languages.add(Language.NL);
        languages.add(Language.EN);
        languages.add(Language.EO);
        languages.add(Language.ET);
        languages.add(Language.TL);
        languages.add(Language.FI);
        languages.add(Language.FR);
        languages.add(Language.GL);
        languages.add(Language.KA);
        languages.add(Language.DE);
        languages.add(Language.EL);
        languages.add(Language.GU);
        languages.add(Language.HT);
        languages.add(Language.HA);
        languages.add(Language.IW);
        languages.add(Language.HI);
        languages.add(Language.HMN);
        languages.add(Language.HU);
        languages.add(Language.IS);
        languages.add(Language.IG);
        languages.add(Language.ID);
        languages.add(Language.GA);
        languages.add(Language.IT);
        languages.add(Language.JA);
        languages.add(Language.JW);
        languages.add(Language.KN);
        languages.add(Language.KK);
        languages.add(Language.KM);
        languages.add(Language.KO);
        languages.add(Language.LO);
        languages.add(Language.LA);
        languages.add(Language.LV);
        languages.add(Language.LT);
        languages.add(Language.MK);
        languages.add(Language.MG);
        languages.add(Language.MS);
        languages.add(Language.ML);
        languages.add(Language.MT);
        languages.add(Language.MI);
        languages.add(Language.MR);
        languages.add(Language.MN);
        languages.add(Language.MY);
        languages.add(Language.NE);
        languages.add(Language.NO);
        languages.add(Language.FA);
        languages.add(Language.PL);
        languages.add(Language.PT);
        languages.add(Language.RO);
        languages.add(Language.RU);
        languages.add(Language.SR);
        languages.add(Language.ST);
        languages.add(Language.SI);
        languages.add(Language.SK);
        languages.add(Language.SL);
        languages.add(Language.SO);
        languages.add(Language.ES);
        languages.add(Language.SU);
        languages.add(Language.SW);
        languages.add(Language.SV);
        languages.add(Language.TG);
        languages.add(Language.TA);
        languages.add(Language.TE);
        languages.add(Language.TH);
        languages.add(Language.TR);
        languages.add(Language.UK);
        languages.add(Language.UR);
        languages.add(Language.UZ);
        languages.add(Language.VI);
        languages.add(Language.CY);
        languages.add(Language.YI);
        languages.add(Language.YO);
        languages.add(Language.ZU);
    }

    private GoogleTranslatorHandler handler;

    public GoogleXMLTranslator(String filePath) {
        super(filePath);
        handler = new GoogleTranslatorHandler();
    }

    @Override
    public String onGenerateUrl(String content, Language src, Language target) {
        return handler.onGenerateUrl(content, src, target);
    }

    @Override
    public String onTranslateFinished(String result) {
        return handler.handleJsonString(result);
    }

    @Override
    public List<Language> getSupportLanguage() {
        return languages;
    }
}
