package at.fhv.ss22.ea.f.customerDbService;

import at.fhv.ss22.ea.f.customerDbService.communication.RMIServer;

public class CustomerDbMain {

    public static void main(String[] args) {
        RMIServer server = new RMIServer();
        server.start();

        // TODO for me
        // - remove _id from raw data - DONE
        // - add ALL data to mongo-init.sh
        // - adjust SETUP.md - DONE
        // - .conf files - DONE
        // - .env.dist.file - DONE
        // - RMI-stuff - DONE
        // - mongo testing

    }

}