package at.fhv.ss22.ea.f.customerDbService.application;

import at.fhv.ss22.ea.f.customerDbService.CustomerDTO;
import at.fhv.ss22.ea.f.customerDbService.infrastructure.CustomerRepository;
import at.fhv.ss22.ea.f.customerDbService.infrastructure.CustomerRepositoryMongoDb;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl() {
        this.customerRepository = new CustomerRepositoryMongoDb();
    }

    public static CustomerService newTestInstance(CustomerRepository mockedCustomerRepo) {
        CustomerServiceImpl service = new CustomerServiceImpl();
        service.customerRepository = mockedCustomerRepo;
        return service;
    }

    @Override
    public Optional<CustomerDTO> customerById(UUID uuid) {
        return customerRepository.customerById(uuid);
    }

    @Override
    public List<CustomerDTO> customerListByIds(List<UUID> uuidList) {
        return uuidList.stream().map(id -> customerRepository.customerById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}