package at.fhv.ss22.ea.f.customerDbService.application;

import at.fhv.ss22.ea.f.customerDbService.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    Optional<CustomerDTO> customerById(UUID uuid);

    List<CustomerDTO> customerListByIds(List<UUID> uuidList);

    //TODO search query
}
