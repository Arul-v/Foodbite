package com.foodbite.grabfood.dal;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.springframework.stereotype.Repository;

/**
 * Java MongoDB : Save image example
 *
 */

@Repository
public class UploadImage {


    Mongo mongo = new Mongo("localhost", 27017);
    DB db = mongo.getDB("imagedb");
    DBCollection collection = db.getCollection("dummyColl");

//    public void upload() {
//
//        try {
//            Mongo mongo = new Mongo("localhost", 27017);
//            DB db = mongo.getDB("imagedb");
//            DBCollection collection = db.getCollection("dummyColl");
//
//
//            String newFileName = "mkyong-java-image";
//
//            File imageFile = new File("c:\\JavaWebHosting.png");
//
//            // create a "photo" namespace
//            GridFS gfsPhoto = new GridFS(db, "photo");
//
//            // get image file from local drive
//            GridFSInputFile gfsFile = gfsPhoto.createFile(imageFile);
//
//            // set a new filename for identify purpose
//            gfsFile.setFilename(newFileName);
//
//            // save the image file into mongoDB
//            gfsFile.save();
//
//
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (MongoException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    public void saveImage()
    {
        String newFileName = "mkyong-java-image";
        File imageFile = new File("c:\\JavaWebHosting.png");
        GridFS gfsPhoto;
        gfsPhoto = new GridFS(db, "photo");
        GridFSInputFile gfsFile = null;
        try {
            gfsFile = gfsPhoto.createFile(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        gfsFile.setFilename(newFileName);
        gfsFile.save();
    }

    public void getImage()
    {
        String newFileName = "mkyong-java-image";
        GridFS gfsPhoto = new GridFS(db, "photo");
        GridFSDBFile imageForOutput = gfsPhoto.findOne(newFileName);
        System.out.println(imageForOutput);
    }
}