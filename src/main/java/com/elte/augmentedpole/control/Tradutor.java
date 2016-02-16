package com.elte.augmentedpole.control;

import com.gtranslate.Translator;
import com.memetix.mst.detect.Detect;
import com.memetix.mst.language.Language;

public class Tradutor {

    public String languageDetection(String tweet) {
        String language;
        Detect.setClientId("augmented_pole");
        Detect.setClientSecret("CKu85Ai2di5qnh4fG3u7vo4Pistxyc9mBRlBH1dWH00=");

        try {
            Language detectedLanguage = Detect.execute(tweet);
            language = detectedLanguage.getName(Language.ENGLISH).toUpperCase();

        } catch (Exception ex) {
            language = "ENGLISH";
        }
        return language;
    }

    public String translator(String text) {
        Tradutor t = new Tradutor();
        Translator translate = Translator.getInstance();
        String languageInput = t.languageDetection(text);
        String translatedText = translate.translate(text, languageInput, com.gtranslate.Language.ENGLISH);
        return translatedText;
    }
}
