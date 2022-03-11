package at.fhv.ss22.ea.f.customerDbService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    Optional<CustomerDTO> customerById(UUID uuid);

    List<CustomerDTO> customerListByIds(List<UUID> uuidList);
}
