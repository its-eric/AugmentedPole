
package com.elte.augmentedpole.control;

/**
 *
 * @author Eric
 */
import org.apache.commons.lang3.StringUtils;

public class Cleaner {
    
    String conteudo = null;
    
    Cleaner(String tweet) {
        String palavras[] = StringUtils.splitPreserveAllTokens(tweet, ' ');
        int contador = 0;
        for (String palavra : palavras) {
            if(palavra.compareTo("RT") == 0 || 
                        (StringUtils.contains(palavra, "@") && palavra.charAt(0) == '@') ||
                         StringUtils.contains(palavra, "#") ||
                          StringUtils.contains(palavra, "http")) {
                    palavras[contador] = "";
            }
            contador++;
        }
        this.conteudo = StringUtils.strip(StringUtils.join(palavras, ' '));
    }   
    
}
