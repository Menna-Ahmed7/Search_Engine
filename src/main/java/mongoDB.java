package main.java;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;
import java.util.Iterator;
import java.util.List;
public class mongoDB {
    MongoDatabase db;
    public MongoCollection<Document> k;
    public MongoDatabase get_db() {
        return db;
    }
    public mongoDB(String Database) { //constructor holds data base name

        try {
            //---------DataBase Connection
            //database connnection string
            String uri ;
            if(System.getenv("DB_URI")==null) {
                uri = "mongodb://localhost:27017/"; //database connection string
            }

            else
                uri=System.getenv("DB_URI");

            ConnectionString connection_string = new ConnectionString(uri);


            MongoClientSettings settings =
                    MongoClientSettings.builder().applyConnectionString(connection_string).retryWrites(true).build();
            //connect to server
            MongoClient Client = MongoClients.create(settings);

            // Create the database
            db = Client.getDatabase(Database);

            System.out.println(" بسم الله الرحمن الرحيم  \n");
            //---------Collection Creation
//            db.createCollection("try");
//            db.createCollection("try1");
            k = db.getCollection("try");

            Document document1 = new Document();
            document1.append("name", "Ram");
            db.getCollection("try").insertOne(document1);
        } catch (Exception e) {
            System.out.println("faild to connect to data base ");
            e.printStackTrace();
        }

    }
    public FindIterable<Document> getAllk() {
        return k.find(new org.bson.Document());
    }
    void insert_Seed()
    {
        db.createCollection("Seeds");
        Document S1 = new Document();

    }



}