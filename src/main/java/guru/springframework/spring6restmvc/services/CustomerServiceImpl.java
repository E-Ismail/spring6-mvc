package guru.springframework.spring6restmvc.services;

/*
 * @author Ech-Cherrate Ismail
 * @project spring-6-rest-mvc
 * @create 29/07/2025 - 22:34
 */

import guru.springframework.spring6restmvc.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    Map<UUID, CustomerDTO> customerMap;

    public CustomerServiceImpl() {
        this.customerMap = new HashMap<>();
        CustomerDTO customer1 = CustomerDTO.builder()
                .id(UUID.randomUUID()).name("Customer 1").createdDate(LocalDateTime.now()).lastModifiedDate(LocalDateTime.now().plusDays(1))
                .build();
        CustomerDTO customer2 = CustomerDTO.builder()
                .id(UUID.randomUUID()).name("Customer 2").createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now().plusDays(1))
                .build();
        CustomerDTO customer3 = CustomerDTO.builder()
                .id(UUID.randomUUID()).name("Customer 3").createdDate(LocalDateTime.now()).lastModifiedDate(LocalDateTime.now().plusDays(1))
                .build();
        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
        customerMap.put(customer3.getId(), customer3);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        log.info("[SERVICE] Retrieving customers]");
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID uuid) {
        log.info("[SERVICE] Retrieving customer by uuid");
        return Optional.of(customerMap.get(uuid));
    }

    @Override
    public void patchCustomerById(UUID customerId, CustomerDTO customer) {

        Optional<CustomerDTO> existingCustomer = this.getCustomerById(customerId);
        if(StringUtils.hasText(existingCustomer.get().getName())) {
            existingCustomer.get().setName(customer.getName());
        }
    }

    @Override
    public void deleteCustomerById(UUID customerId) {
        customerMap.remove(customerId);
        log.info("[SERVICE] Successfully removed customer with id: {}", customerId);
    }

    @Override
    public void updateCustomerById(UUID customerId, CustomerDTO customer) {
        Optional<CustomerDTO> existingCustomer = this.getCustomerById(customerId);
        existingCustomer.get().setName(customer.getName());
        existingCustomer.get().setLastModifiedDate(LocalDateTime.now());
        existingCustomer.get().setVersion(existingCustomer.get().getVersion() + 1);
        //object ref in the customers map will be updated no need for the put method
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customer) {
        log.info("[SERVICE] post customer by name");
        CustomerDTO savedCustomer = CustomerDTO
                .builder()
                .id(UUID.randomUUID())
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now().plusDays(1))
                .name(customer.getName())
                .build();
        this.customerMap.put(customer.getId(), savedCustomer);
        return savedCustomer;
    }
}
