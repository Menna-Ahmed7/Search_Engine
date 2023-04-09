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
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.*;
import java.util.Vector;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        //printing a collection
        mongoDB DB1=new mongoDB("GoWell");
        // Access the "web_crawler" database


        //Seeds
        String Url1="https://www.wikipedia.org/";
        String Url2="https://www.education.com/";
        //Without Threading
        // Crawl C1=new Crawl(Url1);
        //With Threading
        Vector<String> vector =  DB1.getLink();
        int size = vector.size();
        ThreadedCrawler [] TC=new ThreadedCrawler[size];
        for (int i=0;i< vector.size();i++)
        {
            TC[i]=new ThreadedCrawler(i, vector.get(i));
        }


        for (int i=0;i< vector.size();i++)
        {
           TC[i].Get_thread().join();
        }


    }

}