package main.java;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.ArrayList;
public class ThreadedCrawler implements Runnable {
    private  int ID;
    private String First_Link;
    private Thread thread;
    ThreadedCrawler(int id ,String url)
    {
        ID=id;
        First_Link = url;
        thread =new Thread(this);
        thread.start();
    }
    @Override
    public void run()
    {
        crawl(1,First_Link,new ArrayList<String>());
    }
    private  void crawl(int level,String Url,ArrayList<String> visited)
    {
        if(level <=5 )
        {
            Document Doc = request(Url,visited);
            if(Doc != null)
            {
                for(Element link:Doc.select("a[href]"))
                {
                    String newLink=link.absUrl("href");
                    if(visited.contains(newLink)==false)
                    {
                      //  level+=1;
                        crawl(level++,newLink,visited);

                    }
                }
            }
        }
    }
    private  Document request(String Url,ArrayList<String>visited)  {
        try {
            Connection con = Jsoup.connect(Url);
            Document Doc = con.get();
            if (con.response().statusCode() == 200) {
                System.out.println("Thread ID # "+ID+"  Link :" + Url+"  "+Doc.title());
                visited.add(Url);
                return Doc;
            }
            else
                return null;
        }

        catch (IOException e)
        {
            return null;
        }
    }
    public Thread Get_thread(){
        return thread;
    }
}
