package com.elte.augmentedpole.control;

import com.elte.augmentedpole.model.Tweets;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Eric
 */
public class Main {

    /**
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String args[]) throws InterruptedException {

        Thread app;
        Date ultimaPesquisa = new Date();
        long lastRequest = 0;
        while (true) {
            app = new Thread(new Mining());
            app.start();
            app.join();


            EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.elte_AugmentedPole_jar_1.0");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            //refinar a pesquisa de tweets apenas com os mais novos
            if (ultimaPesquisa.getTime() > (lastRequest + 15000)) {
                javax.persistence.Query tweets = em.createQuery("SELECT t from Tweets t where t.timestamp > :lastRequest").setParameter("lastRequest", lastRequest);
                lastRequest = ultimaPesquisa.getTime();
                List<Tweets> list = tweets.getResultList();
                System.out.println(list.size() + " new tweets will be analyzed now.");
                int sentiment = Sentiment.findListSentiment(list);
                System.out.println("The general sentiment is: " + sentiment);
            }
            Thread.sleep(1500000);
            app = null;
        }

    }

}
