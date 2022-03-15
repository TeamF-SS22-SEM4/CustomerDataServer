package at.fhv.ss22.ea.f.customerDbService;

import at.fhv.ss22.ea.f.customerDbService.application.CustomerService;
import at.fhv.ss22.ea.f.customerDbService.application.CustomerServiceImpl;

public class CustomerDbMain {

    public static void main(String[] args) {
        CustomerService customerService = new CustomerServiceImpl();

        // TODO stuff related to rmi
    }

}