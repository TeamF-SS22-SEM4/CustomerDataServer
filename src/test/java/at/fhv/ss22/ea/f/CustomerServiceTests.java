package at.fhv.ss22.ea.f;

import at.fhv.ss22.ea.f.communication.dto.CustomerDTO;
import at.fhv.ss22.ea.f.customerDbService.InstanceProvider;
import at.fhv.ss22.ea.f.customerDbService.infrastructure.CustomerRepository;
import at.fhv.ss22.ea.f.customerDbService.application.CustomerApplicationService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerServiceTests {

    private CustomerApplicationService customerApplicationService = InstanceProvider.getTestingCustomerApplicationService();

    private CustomerRepository customerRepository = InstanceProvider.getMockedCustomerRepository();

    private CustomerDTO newTestCustomer() {
        return CustomerDTO.builder().id(UUID.randomUUID()).givenName("max").familyName("mustermann").build();
    }

    @Test
    void given_valid_id_get_by_id() {
        //given
        CustomerDTO customer = newTestCustomer();
        when(customerRepository.customerById(customer.getCustomerId())).thenReturn(java.util.Optional.of(customer));

        //when
        CustomerDTO customerActual = customerApplicationService.customerById(customer.getCustomerId()).orElseThrow();

        //then
        assertEquals(customerActual.getCustomerId(), customer.getCustomerId());
        assertEquals(customerActual.getGivenName(), customer.getGivenName());
        assertEquals(customerActual.getFamilyName(), customer.getFamilyName());
    }

    @Test
    void given_invalid_id_then_empty() {
        //given
        when(customerRepository.customerById(any())).thenReturn(Optional.empty());

        //when
        Optional<CustomerDTO> customerOpt = customerApplicationService.customerById(UUID.randomUUID());

        //then
        assertTrue(customerOpt.isEmpty());
    }

    @Test
    void given_list_of_mixed_ids_then_only_valid_customers_in_result() {
        //given
        CustomerDTO customer1 = newTestCustomer();
        CustomerDTO customer2 = newTestCustomer();
        CustomerDTO customer3 = newTestCustomer();
        CustomerDTO invalidCustomer = newTestCustomer();
        when(customerRepository.customerById(customer1.getCustomerId())).thenReturn(Optional.of(customer1));
        when(customerRepository.customerById(customer2.getCustomerId())).thenReturn(Optional.of(customer2));
        when(customerRepository.customerById(customer3.getCustomerId())).thenReturn(Optional.of(customer3));
        when(customerRepository.customerById(invalidCustomer.getCustomerId())).thenReturn(Optional.empty());

        //when
        List<CustomerDTO> listActual = customerApplicationService.customerListByIds(List.of(customer1.getCustomerId(), customer2.getCustomerId(), customer3.getCustomerId(), invalidCustomer.getCustomerId()));

        //then
        assertTrue(listActual.contains(customer1));
        assertTrue(listActual.contains(customer2));
        assertTrue(listActual.contains(customer3));

        assertFalse(listActual.contains(invalidCustomer));
    }
}
