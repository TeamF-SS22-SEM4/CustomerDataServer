package at.fhv.ss22.ea.f.customerDbService.application;

import at.fhv.ss22.ea.f.communication.dto.CustomerDTO;
import at.fhv.ss22.ea.f.customerDbService.infrastructure.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class CustomerApplicationServiceImpl implements CustomerApplicationService {

    private CustomerRepository customerRepository;

    public CustomerApplicationServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<CustomerDTO> customerById(UUID uuid) {
        return customerRepository.customerById(uuid);
    }

    @Override
    public List<CustomerDTO> search(String query) {
        return customerRepository.search(query);
    }

    @Override
    public List<CustomerDTO> customerListByIds(List<UUID> uuidList) {
        return uuidList.stream().map(id -> customerRepository.customerById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}