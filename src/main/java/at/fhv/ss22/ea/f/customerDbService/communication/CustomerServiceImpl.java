package at.fhv.ss22.ea.f.customerDbService.communication;

import at.fhv.ss22.ea.f.communication.api.CustomerService;
import at.fhv.ss22.ea.f.communication.dto.CustomerDTO;
import at.fhv.ss22.ea.f.customerDbService.application.CustomerApplicationService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.UUID;

public class CustomerServiceImpl extends UnicastRemoteObject implements CustomerService {

    private CustomerApplicationService customerApplicationService;

    public CustomerServiceImpl(CustomerApplicationService customerApplicationService) throws RemoteException {
        super(RMIServer.PORT);
        this.customerApplicationService = customerApplicationService;
    }

    @Override
    public CustomerDTO customerById(UUID uuid) {
        CustomerDTO customerDTO =customerApplicationService.customerById(uuid).orElse(null);
        try {
            System.out.println(getClientHost());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerDTO;
    }

    @Override
    public List<CustomerDTO> customerListByIds(List<UUID> uuidList) {
        return customerApplicationService.customerListByIds(uuidList);
    }

    @Override
    public List<CustomerDTO> search(String query) {
        return customerApplicationService.search(query);
    }
}
