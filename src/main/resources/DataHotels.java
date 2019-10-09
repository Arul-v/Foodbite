import java.net.UnknownHostException;
 
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
 
public class DataHotels
{
    public static void main(String[] args) throws UnknownHostException
    {
        Mongo mongo = new Mongo("localhost", 27017);
        DB db = mongo.getDB("foodbite");
        
        DBCollection collection = db.getCollection("hotels");
    
			
        System.out.println("BasicDBObject example...");

    String json1 = " { 'name' : 'Saravuana Bhauan', " +
                        "'items' :[ { 'id' : '1' , " + 
                                "'itemName' : 'Veg Briayani' , " +
                                "'quantity' : '200' , " +
                                "'price' : '185' } ," +
                                "{ 'id' : '2' , " + 
                                "'itemName' : 'Chick grill' , " +
                                "'quantity' : '200' , " +
                                "'price' : '235' } ," +
                                "{ 'id' : '3' , " + 
                                "'itemName' : 'Chick BBQ' , " +
                                "'quantity' : '200' , " +
                                "'price' : '280' } ," +
                                "{ 'id' : '4' , " + 
                                "'itemName' : 'Dosa' , " +
                                "'quantity' : '200' , " +
                                "'price' : '65' } ," +
                                "{ 'id' : '5' , " + 
                                "'itemName' : 'Fried Rice' , " +
                                "'quantity' : '200' , " +
                                "'price' : '122' } ," +
                                "{ 'id' : '6' , " + 
                                "'itemName' : 'Chick grill' , " +
                                "'quantity' : '200' , " +
                                "'price' : '235' } ]" +

                    " }";  

              
    DBObject dbObject = (DBObject)JSON.parse(json1);
      
    collection.insert(dbObject);

    String json2 = " { 'name' : 'Punjuab Griill', " +
                    "'items' :[ { 'id' : '1' , " + 
                            "'itemName' : 'BBQ Chops ' , " +
                            "'quantity' : '200' , " +
                            "'price' : '185' } ," +
                            "{ 'id' : '2' , " + 
                            "'itemName' : 'Noodles' , " +
                            "'quantity' : '200' , " +
                            "'price' : '235' } ," +
                            "{ 'id' : '3' , " + 
                            "'itemName' : 'Milk Shakes' , " +
                            "'quantity' : '200' , " +
                            "'price' : '280' } ," +
                            "{ 'id' : '4' , " + 
                            "'itemName' : 'Naan Roti' , " +
                            "'quantity' : '200' , " +
                            "'price' : '65' } ," +
                            "{ 'id' : '5' , " + 
                            "'itemName' : 'Pizza' , " +
                            "'quantity' : '200' , " +
                            "'price' : '122' } ," +
                            "{ 'id' : '6' , " + 
                            "'itemName' : 'Chicken Rolls' , " +
                            "'quantity' : '200' , " +
                            "'price' : '235' } ]" +

                " }";  
    dbObject = (DBObject)JSON.parse(json2);
      
     collection.insert(dbObject); 
    
     String json3 = " { 'name' : 'Hotel Mainland', " +
                    "'items' :[ { 'id' : '1' , " + 
                            "'itemName' : 'Allo Roll' , " +
                            "'quantity' : '200' , " +
                            "'price' : '185' } ," +
                            "{ 'id' : '2' , " + 
                            "'itemName' : 'Paneer Tikka Roll' , " +
                            "'quantity' : '200' , " +
                            "'price' : '235' } ," +
                            "{ 'id' : '3' , " + 
                            "'itemName' : 'Pasta Lunch Box' , " +
                            "'quantity' : '200' , " +
                            "'price' : '280' } ," +
                            "{ 'id' : '4' , " + 
                            "'itemName' : 'Chicken Salad' , " +
                            "'quantity' : '200' , " +
                            "'price' : '65' } ," +
                            "{ 'id' : '5' , " + 
                            "'itemName' : 'Broccoli Tacos' , " +
                            "'quantity' : '200' , " +
                            "'price' : '122' } ," +
                            "{ 'id' : '6' , " + 
                            "'itemName' : 'Ranch Chicken' , " +
                            "'quantity' : '200' , " +
                            "'price' : '235' } ]" +

                " }";  
    dbObject = (DBObject)JSON.parse(json3);
      
    collection.insert(dbObject); 
    
    String json4 = " { 'name' : 'Good Bowls', " +
                    "'items' :[ { 'id' : '1' , " + 
                            "'itemName' : 'Kadai veg' , " +
                            "'quantity' : '200' , " +
                            "'price' : '185' } ," +
                            "{ 'id' : '2' , " + 
                            "'itemName' : 'Garlic Beef' , " +
                            "'quantity' : '200' , " +
                            "'price' : '235' } ," +
                            "{ 'id' : '3' , " + 
                            "'itemName' : 'Alfredo Pasta' , " +
                            "'quantity' : '200' , " +
                            "'price' : '280' } ," +
                            "{ 'id' : '4' , " + 
                            "'itemName' : 'Salmon Maki' , " +
                            "'quantity' : '200' , " +
                            "'price' : '65' } ," +
                            "{ 'id' : '5' , " + 
                            "'itemName' : 'Green Curry' , " +
                            "'quantity' : '200' , " +
                            "'price' : '122' } ," +
                            "{ 'id' : '6' , " + 
                            "'itemName' : 'Spiced Rice' , " +
                            "'quantity' : '200' , " +
                            "'price' : '235' } ]" +

                " }";  
    dbObject = (DBObject)JSON.parse(json4);
      
    collection.insert(dbObject);      

    String json5 = " { 'name' : 'French Loafe', " +
                    "'items' :[ { 'id' : '1' , " + 
                            "'itemName' : 'Veg Briayani' , " +
                            "'quantity' : '200' , " +
                            "'price' : '185' } ," +
                            "{ 'id' : '2' , " + 
                            "'itemName' : 'Chick grill' , " +
                            "'quantity' : '200' , " +
                            "'price' : '235' } ," +
                            "{ 'id' : '3' , " + 
                            "'itemName' : 'Chick BBQ' , " +
                            "'quantity' : '200' , " +
                            "'price' : '280' } ," +
                            "{ 'id' : '4' , " + 
                            "'itemName' : 'Dosa' , " +
                            "'quantity' : '200' , " +
                            "'price' : '65' } ," +
                            "{ 'id' : '5' , " + 
                            "'itemName' : 'Fried Rice' , " +
                            "'quantity' : '200' , " +
                            "'price' : '122' } ," +
                            "{ 'id' : '6' , " + 
                            "'itemName' : 'Chick grill' , " +
                            "'quantity' : '200' , " +
                            "'price' : '235' } ]" +

                " }";  
    dbObject = (DBObject)JSON.parse(json5);
      
    collection.insert(dbObject);   
    
    String json6 = " { 'name' : 'The bowls factories', " +
                    "'items' :[ { 'id' : '1' , " + 
                            "'itemName' : 'Veechy Parotta' , " +
                            "'quantity' : '200' , " +
                            "'price' : '185' } ," +
                            "{ 'id' : '2' , " + 
                            "'itemName' : 'Pasta' , " +
                            "'quantity' : '200' , " +
                            "'price' : '235' } ," +
                            "{ 'id' : '3' , " + 
                            "'itemName' : 'Mutton Briyani' , " +
                            "'quantity' : '200' , " +
                            "'price' : '280' } ," +
                            "{ 'id' : '4' , " + 
                            "'itemName' : 'Tandoori Guchchi' , " +
                            "'quantity' : '200' , " +
                            "'price' : '65' } ," +
                            "{ 'id' : '5' , " + 
                            "'itemName' : 'Bhatti Murgh' , " +
                            "'quantity' : '200' , " +
                            "'price' : '122' } ," +
                            "{ 'id' : '6' , " + 
                            "'itemName' : 'Chick grill' , " +
                            "'quantity' : '200' , " +
                            "'price' : '235' } ]" +

                " }";  
    dbObject = (DBObject)JSON.parse(json6);
      
    collection.insert(dbObject);    
    }
     
}