package at.fhv.ss22.ea.f.customerDbService.infrastructure;

import at.fhv.ss22.ea.f.customerDbService.CustomerDTO;
import at.fhv.ss22.ea.f.customerDbService.CustomerRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public class CustomerRepositoryMongoDb implements CustomerRepository {
    private MongoCollection<Document> customerCollection;

    public CustomerRepositoryMongoDb() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("config")) {
            props.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO set localhost in config
        MongoClient mongoClient = MongoClients.create("mongodb://" + props.getProperty("mongodb.address"));
        MongoDatabase db = mongoClient.getDatabase(props.getProperty("mongodb.database"));
        this.customerCollection = db.getCollection(props.getProperty("mongodb.collection"));

        if (Boolean.parseBoolean(props.getProperty("mongodb.generateUUID"))) {
            generateDomainIdForAllRecords();
            props.setProperty("mongodb.generateUUID", "false");
            try (FileOutputStream out = new FileOutputStream("database.config")) {
                props.store(out, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Optional<CustomerDTO> customerById(UUID uuid) {
        //TODO implement
        return Optional.empty();
    }

    /**Utility function to create uuid's for existing customer data
     * Warning running this takes a long as time >30min
     */
    private void generateDomainIdForAllRecords() {
        List<ObjectId> mongoIdList = new LinkedList<>();
        int j = 0;
        this.customerCollection.find(new BasicDBObject()).forEach(document -> mongoIdList.add(document.getObjectId("_id")));

        // setting ID for each separate document, because else each record has the same UUID
        for (ObjectId id : mongoIdList) {
            BasicDBObject idQuery = new BasicDBObject();
            idQuery.put("_id", id);

            BasicDBObject idField = new BasicDBObject();
            idField.put("domain_id", UUID.randomUUID().toString());

            BasicDBObject updateObject = new BasicDBObject();
            updateObject.put("$set", idField);

            this.customerCollection.updateOne(idQuery, updateObject);
        }
    }
}