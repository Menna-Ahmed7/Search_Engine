package main.java;//package all;
import java.io.IOException;
import java.util.*;
import java.util.Iterator;
import java.lang.Object;
import main.java.mongoDB;
import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //printing a collection
        mongoDB DB1=new mongoDB("Seeds");
        Iterator it=DB1.getAllk().iterator();
        while (it.hasNext()) {

            System.out.println(it.next());
        }
        //Seeds
        String Url1="https://www.wikipedia.org/";
        String Url2="https://www.education.com/";
        //Without Threading
        // Crawl C1=new Crawl(Url1);
        //With Threading
        String title = DB1.getLink();
        ThreadedCrawler TC1=new ThreadedCrawler(1,title);

        ThreadedCrawler TC2=new ThreadedCrawler(2,Url2);

        TC1.Get_thread().join();
        TC2.Get_thread().join();

    }

}