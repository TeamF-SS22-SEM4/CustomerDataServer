package at.fhv.ss22.ea.f.customerDbService;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    Optional<CustomerDTO> customerById(UUID uuid);

}