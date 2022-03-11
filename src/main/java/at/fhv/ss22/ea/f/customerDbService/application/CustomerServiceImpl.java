package at.fhv.ss22.ea.f.customerDbService.application;

import at.fhv.ss22.ea.f.customerDbService.CustomerDTO;
import at.fhv.ss22.ea.f.customerDbService.CustomerRepository;
import at.fhv.ss22.ea.f.customerDbService.CustomerService;
import at.fhv.ss22.ea.f.customerDbService.infrastructure.CustomerRepositoryMongoDb;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        List<CustomerDTO> customers = new LinkedList<>();
        for (UUID id : uuidList) {
            Optional<CustomerDTO> customerOpt = customerRepository.customerById(id);
            customerOpt.ifPresent(customers::add);
        }
        return customers;
    }
}