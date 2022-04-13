package at.fhv.ss22.ea.f;

import at.fhv.ss22.ea.f.communication.dto.CustomerDTO;
import at.fhv.ss22.ea.f.customerDbService.InstanceProvider;
import at.fhv.ss22.ea.f.customerDbService.infrastructure.CustomerRepository;
import org.junit.jupiter.api.TestInstance;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MongoDbCustomerRepoTests {

    CustomerRepository customerRepository;

//    @BeforeAll
    void setup() {
        this.customerRepository = InstanceProvider.getCustomerRepository();
    }

//    @Test
    void get_customer_with_valid_id() {
        // copied valid id directly from mongoDb, not inserting tests records because this application doesn't have
        // to support updates, thus no easy methods for inserting records has been implemented yet
        UUID id = UUID.fromString("3def4360-82e6-4b4b-948f-4d3b88bdc039");

        Optional<CustomerDTO> customerOpt = customerRepository.customerById(id);

        assertTrue(customerOpt.isPresent());
        assertEquals("Ina", customerOpt.get().getGivenName());
        assertEquals("Ludwigsfelde", customerOpt.get().getCity());
    }

//    @Test
    void get_customer_with_invalid_id() {
        UUID id = UUID.randomUUID();
        assertTrue(customerRepository.customerById(id).isEmpty());
    }
}
