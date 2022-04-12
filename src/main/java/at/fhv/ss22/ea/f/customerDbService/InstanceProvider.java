package at.fhv.ss22.ea.f.customerDbService;

import at.fhv.ss22.ea.f.communication.internal.CustomerInternalService;
import at.fhv.ss22.ea.f.customerDbService.application.CustomerApplicationService;
import at.fhv.ss22.ea.f.customerDbService.application.CustomerApplicationServiceImpl;
import at.fhv.ss22.ea.f.customerDbService.communication.CustomerServiceImpl;
import at.fhv.ss22.ea.f.customerDbService.infrastructure.CustomerRepository;
import at.fhv.ss22.ea.f.customerDbService.infrastructure.CustomerRepositoryMongoDb;

import java.rmi.RemoteException;

import static org.mockito.Mockito.mock;

public class InstanceProvider {
    private InstanceProvider(){}

    private static CustomerApplicationService customerApplicationService;
    private static CustomerRepository customerRepository;

    private static CustomerApplicationService testingCustomerApplicationService;
    private static CustomerRepository testingCustomerRepository;

    private static CustomerApplicationService mockedCustomerApplicationService;
    private static CustomerRepository mockedCustomerRepository;

    public static CustomerInternalService getCustomerService() {
        try {
            return new CustomerServiceImpl(getCustomerApplicationService());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static CustomerApplicationService getCustomerApplicationService() {
        if (null == customerApplicationService) {
            customerApplicationService = new CustomerApplicationServiceImpl(getCustomerRepository());
        }
        return customerApplicationService;
    }

    public static CustomerRepository getCustomerRepository() {
        if (null == customerRepository) {
            customerRepository = new CustomerRepositoryMongoDb();
        }
        return customerRepository;
    }

    public static CustomerApplicationService getTestingCustomerApplicationService() {
        if (null == testingCustomerApplicationService) {
            testingCustomerApplicationService = new CustomerApplicationServiceImpl(getMockedCustomerRepository());
        }
        return testingCustomerApplicationService;
    }

    public static CustomerRepository getTestingCustomerRepository() {
        //TODO
        return testingCustomerRepository;
    }

    public static CustomerApplicationService getMockedCustomerApplicationService() {
        if (null == mockedCustomerApplicationService) {
            mockedCustomerApplicationService = mock(CustomerApplicationService.class);
        }
        return mockedCustomerApplicationService;
    }

    public static CustomerRepository getMockedCustomerRepository() {
        if (null == mockedCustomerRepository) {
            mockedCustomerRepository = mock(CustomerRepository.class);
        }
        return mockedCustomerRepository;
    }
}
