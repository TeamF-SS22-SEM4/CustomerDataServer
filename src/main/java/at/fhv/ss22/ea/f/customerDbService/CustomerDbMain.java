package at.fhv.ss22.ea.f.customerDbService;

import at.fhv.ss22.ea.f.customerDbService.communication.RMIServer;

public class CustomerDbMain {

    public static void main(String[] args) {
        RMIServer.start();
    }
}