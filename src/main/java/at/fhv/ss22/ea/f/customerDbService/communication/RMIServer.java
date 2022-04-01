package at.fhv.ss22.ea.f.customerDbService.communication;

import at.fhv.ss22.ea.f.communication.api.CustomerService;
import at.fhv.ss22.ea.f.customerDbService.InstanceProvider;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static final int PORT = Integer.parseInt(System.getenv("RMI_PORT"));

    public void start() {
        System.out.println("Starting server on port " + PORT);
        System.setProperty("java.rmi.server.hostname", System.getenv("RMI_HOSTNAME"));

        Registry registry = null;
        if (Boolean.parseBoolean(System.getenv("CREATE_RMI_REGISTRY"))) {
            try {
                registry = LocateRegistry.createRegistry(PORT);
            } catch (RemoteException e) {
                e.printStackTrace();
                System.err.println("Failed to create registry");
                System.exit(1);
            }
        } else {
            try {
                registry = LocateRegistry.getRegistry(
                        System.getenv("RMI_REGISTRY_HOSTNAME"),
                        Integer.parseInt(System.getenv("RMI_REGISTRY_PORT")));
            } catch (RemoteException e) {
                e.printStackTrace();
                System.err.println("Failed to get registry");
                System.exit(1);
            }
        }

        CustomerService customerService = InstanceProvider.getCustomerService();
        try {
            registry.rebind("CustomerService", customerService);
            System.out.println("CustomerService bound in registry on port " + PORT);
        } catch (RemoteException e) {
            e.printStackTrace();
            System.err.println("Failed to bind customer service");
            System.exit(1);
        }
    }
}
