package main.java;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
public class mongoDB {
    MongoDatabase db;
    public MongoCollection<Document> k;
    public MongoDatabase get_db() {
        return db;
    }
    public mongoDB(String Database) { //constructor holds data base name

        try {
            //---------DataBase Connection
            //data base connnection string
            String uri ;
            if(System.getenv("DB_URI")==null)
                uri="mongodb://localhost:27017"; //database connection string
            else
                uri=System.getenv("DB_URI");

            ConnectionString connection_string = new ConnectionString(uri);


            MongoClientSettings settings =
                    MongoClientSettings.builder().applyConnectionString(connection_string).retryWrites(true).build();
            //connect to server
            MongoClient Client = MongoClients.create(settings);

            // Create the data base
            db = Client.getDatabase(Database);

            System.out.println("successfully connected to data base \n");
            //---------Collection Creation
//            db.createCollection("try");
//            db.createCollection("try1");
            k = db.getCollection("Seeds");

            Document document1 = new Document();
            document1.append("Name", "Wikipedia");
            document1.append("URL", "https://www.wikipedia.org/");
            Document document2 = new Document();
            document2.append("Name", "Education");
            document2.append("URL", "https://www.education.com/");
            Document document3 = new Document();
            document3.append("Name", "Github");
            document3.append("URL", "https://github.com/");
            Document document4 = new Document();
            document4.append("Name", "BBC News");
            document4.append("URL", "https://www.bbc.com/news");
            Document document5 = new Document();
            document5.append("Name", "National Institute of Health");
            document5.append("URL", " https://www.nih.gov/");
            Document document6 = new Document();
            document6.append("Name", "Geeks for Geeks");
            document6.append("URL", " https://www.geeksforgeeks.org/");


            db.getCollection("Seeds").insertOne(document1);
            db.getCollection("Seeds").insertOne(document2);
            db.getCollection("Seeds").insertOne(document3);
            db.getCollection("Seeds").insertOne(document4);
            db.getCollection("Seeds").insertOne(document5);
            db.getCollection("Seeds").insertOne(document6);
        } catch (Exception e) {
            System.out.println("faild to connect to data base ");
            e.printStackTrace();
        }

    }
    public FindIterable<Document> getAllk() {
        return k.find(new org.bson.Document());
    }
    Vector<String> getLink ()
    {
        // Access the "pages" collection
        Vector<String> Links = new Vector<String>();
        MongoCollection<Document> collection = db.getCollection("Seeds");
        // Retrieve the value of the "title" field from the first document in the collection
        MongoCursor<Document>firstDocument =  collection.find().iterator();
        try {
            while (firstDocument.hasNext()) {
              Document document = firstDocument.next();
                String title = document.getString("URL");
                Links.add(title);

                System.out.println(title);
            }
        } finally {
            firstDocument.close();
        }
        return Links;
    }





}