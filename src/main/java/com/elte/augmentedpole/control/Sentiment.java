package com.elte.augmentedpole.control;

import com.elte.augmentedpole.model.Tweets;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Eric
 */
public class Sentiment {

    public static int findSentiment(String line) {

        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        int mainSentiment = 0;
        if (line != null && line.length() > 0) {
            int longest = 0;
            Annotation annotation = pipeline.process(line);
            for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
                Tree tree = sentence.get(SentimentCoreAnnotations.AnnotatedTree.class);
                int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
                String partText = sentence.toString();
                if (partText.length() > longest) {
                    mainSentiment = sentiment;
                    longest = partText.length();
                }

            }
        }
        if (mainSentiment == 2 || mainSentiment > 4 || mainSentiment < 0) {
            return 0;
        } else {
            return mainSentiment;
        }

    }

    /**
     *
     * @param lista
     * @return
     */
    public static int findListSentiment(List<Tweets> lista) {
        //começa com 0??
        int sentimentoGeral = 0, i = 0;
        int sentimentos[] = new int[lista.size()];
        for (Tweets frase : lista) {
            sentimentos[i] = findSentiment(frase.getText());
            i++;
        }
        Arrays.sort(sentimentos);
        return moda(sentimentos);

    }

    private static int moda(int[] array) {
        int number = array[0];
        int mode = number;
        int count = 1;
        int countMode = 1;

        for (int i = 1; i < array.length; i++) {
            if (array[i] == number) {
                count++;
            } else {
                if (count > countMode) {
                    countMode = count;
                    mode = number;
                }
                count = 1;
                number = array[i];
            }
        }
        return mode;
    }

}
