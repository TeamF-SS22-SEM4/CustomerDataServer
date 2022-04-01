package at.fhv.ss22.ea.f.customerDbService.infrastructure;

import at.fhv.ss22.ea.f.communication.dto.CustomerDTO;
import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.MongoCredential;
import org.bson.Document;

import java.util.*;

public class CustomerRepositoryMongoDb implements CustomerRepository {

    private static final String DATABASE_IP_ADDRESS = "local_mongo";
    private static final String DATABASE_PORT = "27017";
    private static final String MONGODB_DATABASE = System.getenv("MONGO_INITDB_DATABASE");
    private static final String MONGODB_COLLECTION = "customers";

    private MongoCollection<Document> customerCollection;

    public CustomerRepositoryMongoDb() {
        MongoCredential credential = MongoCredential.createCredential(
                System.getenv("MONGO_USERNAME"),
                System.getenv("MONGO_INITDB_DATABASE"),
                System.getenv("MONGO_PASSWORD").toCharArray());
        MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        .credential(credential)
                        .applyConnectionString(new ConnectionString("mongodb://" + DATABASE_IP_ADDRESS + ":" + DATABASE_PORT))
                        .build());
        MongoDatabase db = mongoClient.getDatabase(MONGODB_DATABASE);
        this.customerCollection = db.getCollection(MONGODB_COLLECTION);
    }

    @Override
    public Optional<CustomerDTO> customerById(UUID uuid) {
        BasicDBObject query = new BasicDBObject();
        query.put("customerId", uuid.toString());
        return customerDTOFromDocument(this.customerCollection.find(query).first());
    }

    @Override
    public List<CustomerDTO> search(String query) {
        return new LinkedList<>();
    }

    private Optional<CustomerDTO> customerDTOFromDocument(Document doc) {
        if (doc==null) { return Optional.empty(); }

        Document address = doc.get("address", Document.class);

        return Optional.of(CustomerDTO.builder()
                .id(UUID.fromString(doc.get("customerId").toString()))
                .givenName(doc.getString("givenName"))
                .familyName(doc.getString("familyName"))
                .email(doc.getString("email"))
                .street(address.getString("streetAddress"))
                .houseNumber(address.getString("houseNumber"))
                .city(address.getString("addressLocality"))
                .postalCode(address.getString("postalCode"))
                .country(address.getString("addressCountry"))
                .phoneNumber(doc.getString("phoneNo"))
                .mobileNumber(doc.getString("mobileNo"))
                .build());
    }
}