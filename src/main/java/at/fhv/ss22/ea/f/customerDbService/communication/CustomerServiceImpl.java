package at.fhv.ss22.ea.f.customerDbService.communication;

import at.fhv.ss22.ea.f.communication.dto.CustomerDTO;
import at.fhv.ss22.ea.f.communication.internal.CustomerInternalService;
import at.fhv.ss22.ea.f.customerDbService.application.CustomerApplicationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.UUID;

public class CustomerServiceImpl extends UnicastRemoteObject implements CustomerInternalService {
    private static final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);

    private CustomerApplicationService customerApplicationService;

    public CustomerServiceImpl(CustomerApplicationService customerApplicationService) throws RemoteException {
        super(RMIServer.PORT);
        this.customerApplicationService = customerApplicationService;
    }

    @Override
    public CustomerDTO customerById(UUID uuid) {
        String host = "HOST_UNKNOWN";
        try {
            host = getClientHost();
        } catch (ServerNotActiveException e) {}
        logger.info("customerById called from {}", host);

        return customerApplicationService.customerById(uuid).orElse(null);
    }

    @Override
    public List<CustomerDTO> customerListByIds(List<UUID> uuidList) {
        String host = "HOST_UNKNOWN";
        try {
            host = getClientHost();
        } catch (ServerNotActiveException e) {}
        logger.info("customerListByIds called from {}", host);

        return customerApplicationService.customerListByIds(uuidList);
    }

    @Override
    public List<CustomerDTO> search(String query) {
        String host = "HOST_UNKNOWN";
        try {
            host = getClientHost();
        } catch (ServerNotActiveException e) {}
        logger.info("search called from {}", host);

        return customerApplicationService.search(query);
    }
}
