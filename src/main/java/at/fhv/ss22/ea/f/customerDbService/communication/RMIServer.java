package at.fhv.ss22.ea.f.customerDbService.communication;

import at.fhv.ss22.ea.f.communication.internal.CustomerInternalService;
import at.fhv.ss22.ea.f.customerDbService.InstanceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class RMIServer {
    private static final Logger logger = LogManager.getLogger(RMIServer.class);

    public static final int PORT = Integer.parseInt(System.getenv("RMI_PORT"));
    private static final String RMI_HOSTNAME = System.getenv("RMI_HOSTNAME");

    public void start() {
        logger.info("Starting Rmi-Server on {}:{}", RMI_HOSTNAME, PORT);
        System.setProperty("java.rmi.server.hostname", RMI_HOSTNAME);

        Registry registry = null;
        if (Boolean.parseBoolean(System.getenv("CREATE_RMI_REGISTRY"))) {
            try {
                registry = LocateRegistry.createRegistry(PORT);
            } catch (RemoteException e) {
                e.printStackTrace();
                logger.fatal("Failed to create registry {}", e.getMessage());
                System.exit(1);
            }
        } else {
            String RMI_REGISTRY_HOSTNAME = System.getenv("RMI_REGISTRY_HOSTNAME");
            int RMI_REGISTRY_PORT = Integer.parseInt(System.getenv("RMI_REGISTRY_PORT"));
            try {
                registry = LocateRegistry.getRegistry(RMI_REGISTRY_HOSTNAME, RMI_REGISTRY_PORT);
            } catch (RemoteException e) {
                logger.fatal("Failed to get registry at {}:{} error: {}", RMI_REGISTRY_HOSTNAME, RMI_REGISTRY_PORT, e);
                System.exit(1);
            }
        }

        CustomerInternalService service = InstanceProvider.getCustomerService();
        try {
            String customerServiceName = "CustomerInternalService";
            registry.rebind(customerServiceName, service);
            logger.info("Bound customerService with name: {} in registry", customerServiceName);
        } catch (RemoteException e) {
            logger.fatal("Failed to bind customerService.", e);
            System.exit(1);
        }
    }
}
