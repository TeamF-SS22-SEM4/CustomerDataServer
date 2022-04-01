package at.fhv.ss22.ea.f.customerDbService.infrastructure;


import at.fhv.ss22.ea.f.communication.dto.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    Optional<CustomerDTO> customerById(UUID uuid);

    List<CustomerDTO> search(String query);

}