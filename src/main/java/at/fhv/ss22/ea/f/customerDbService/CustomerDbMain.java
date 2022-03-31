package at.fhv.ss22.ea.f.customerDbService;

import at.fhv.ss22.ea.f.customerDbService.application.CustomerService;
import at.fhv.ss22.ea.f.customerDbService.application.CustomerServiceImpl;

import java.util.UUID;

public class CustomerDbMain {

    public static void main(String[] args) {
        System.out.println("starting");
        CustomerService customerService = new CustomerServiceImpl();

        // TODO stuff related to rmi



        // TODO for me
        // - remove _id from raw data
        // - add data to mongo-init.sh
        // - adjust SETUP.md
        // - .conf files
        // - .env.dist.file
        // -
        // - delete this
        CustomerDTO customer = customerService.customerById(UUID.fromString("4a7d1fda-e46a-4d14-b03a-60ebe8d70c63")).get();
        System.out.println(customer.getGivenName() + " " + customer.getFamilyName());
    }

}