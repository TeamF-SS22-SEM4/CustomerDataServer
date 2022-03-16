package at.fhv.ss22.ea.f.customerDbService.infrastructure;

import at.fhv.ss22.ea.f.customerDbService.CustomerDTO;
import at.fhv.ss22.ea.f.customerDbService.util.Config;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;

import org.bson.Document;

import java.util.*;

public class CustomerRepositoryMongoDb implements CustomerRepository {

    private static final String DATABASE_IP_ADDRESS = Config.getProperty("mongodb.address");
    private static final String DATABASE_PORT = Config.getProperty("mongodb.port");
    private static final String MONGODB_DATABASE = Config.getProperty("mongodb.database");
    private static final String MONGODB_COLLECTION = Config.getProperty("mongodb.customerCollection");

    private MongoCollection<Document> customerCollection;

    public CustomerRepositoryMongoDb() {
        MongoClient mongoClient = MongoClients.create("mongodb://" + DATABASE_IP_ADDRESS + ":" + DATABASE_PORT);
        MongoDatabase db = mongoClient.getDatabase(MONGODB_DATABASE);
        this.customerCollection = db.getCollection(MONGODB_COLLECTION);
    }

    @Override
    public Optional<CustomerDTO> customerById(UUID uuid) {
        BasicDBObject query = new BasicDBObject();
        query.put("customerId", uuid.toString());
        return customerDTOFromDocument(this.customerCollection.find(query).first());
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