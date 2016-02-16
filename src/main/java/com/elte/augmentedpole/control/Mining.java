package com.elte.augmentedpole.control;

import com.elte.augmentedpole.model.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Eric
 */
public class Mining implements Runnable {

    @Override
    public void run() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setOAuthConsumerKey("YJ4sLgy2FAndchk7dpSUSg");
        cb.setOAuthConsumerSecret("iwGmyahjUjzz9QrzFVtQMQsdfv4fQhInfptuAD5U9o");
        cb.setOAuthAccessToken("30504978-AmzpG8IqT2Hz48kn0D499Ne3UlcfIzlKrE76Ayn3E");
        cb.setOAuthAccessTokenSecret("X1kZdtrgZxOTRlNPjqyiDNw1pMeSXF40r2Oj3M3GecPKs");

        TwitterFactory twitterFactory = new TwitterFactory(cb.build());
        twitter4j.Twitter twitter = twitterFactory.getInstance();

        Query query = new Query();
        GeoLocation location = new GeoLocation(47.472654, 19.061580);
        query.setGeoCode(location, 1, Query.MILES);

        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory("com.elte_AugmentedPole_jar_1.0");

        try {

            //Busca tweets
            QueryResult result;
            result = twitter.search(query);

            for (Status status : result.getTweets()) {
                String tweet = status.getText().replaceAll("\\r|\\n", " ");
                long uniqueID = status.getId();
                Cleaner tweetLimpo = new Cleaner(tweet);
                Tradutor trans = new Tradutor();
                String tweetTraduzido = trans.translator(tweetLimpo.conteudo);
                java.util.Date date = new java.util.Date();
                
                try {

                    EntityManager em = emf.createEntityManager();
                    em.getTransaction().begin();

                    //escreve o tweet limpo e o traduzido no banco caso nao exista
                    javax.persistence.Query tweetExistente
                            = em.createNamedQuery("Tweets.findBySnowflake").setParameter("snowflake", uniqueID);
                    List<Integer> encontrados = tweetExistente.getResultList();

                    if (encontrados.isEmpty()) {

                        Tweets tweetBanco = new Tweets();
                        tweetBanco.setText(tweetLimpo.conteudo);
                        tweetBanco.setSnowflake(uniqueID);
                        tweetBanco.setTranslatedText(tweetTraduzido);
                        tweetBanco.setTimestamp(date.getTime());
                        em.persist(tweetBanco);

                        em.getTransaction().commit();
                        em.close();
                    } else {
                        System.out.println("Nothing to do, really.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } catch (TwitterException ex) {
        }
        
        emf.close();

    }

}
