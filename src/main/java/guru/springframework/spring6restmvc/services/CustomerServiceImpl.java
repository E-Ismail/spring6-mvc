package guru.springframework.spring6restmvc.services;

/*
 * @author Ech-Cherrate Ismail
 * @project spring-6-rest-mvc
 * @create 29/07/2025 - 22:34
 */

import guru.springframework.spring6restmvc.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    Map<UUID, Customer> customerMap;

    public CustomerServiceImpl() {
        this.customerMap = new HashMap<>();
        Customer customer1 = Customer.builder()
                .id(UUID.randomUUID()).name("Customer 1").createdDate(LocalDateTime.now()).lastModifiedDate(LocalDateTime.now().plusDays(1))
                .build();
        Customer customer2 = Customer.builder()
                .id(UUID.randomUUID()).name("Customer 2").createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now().plusDays(1))
                .build();
        Customer customer3 = Customer.builder()
                .id(UUID.randomUUID()).name("Customer 3").createdDate(LocalDateTime.now()).lastModifiedDate(LocalDateTime.now().plusDays(1))
                .build();
        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
        customerMap.put(customer3.getId(), customer3);
    }

    @Override
    public List<Customer> getCustomers() {
        log.info("[SERVICE] Retrieving customers]");
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer getCustomerById(UUID id) {
        log.info("[SERVICE] Retrieving customer by id");
        return this.customerMap.get(id);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        log.info("[SERVICE] post customer by name");
        Customer savedCustomer = Customer
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

    @Override
    public void updateCustomerById(UUID customerId, Customer customer) {
        Customer existingCustomer = this.getCustomerById(customerId);
        existingCustomer.setName(customer.getName());
        existingCustomer.setLastModifiedDate(LocalDateTime.now());
        existingCustomer.setVersion(existingCustomer.getVersion() + 1);
        //object ref in the customers map will be updated no need for the put method
    }

    @Override
    public void deleteCustomerById(UUID customerId) {
        customerMap.remove(customerId);
        log.info("[SERVICE] Successfully removed customer with id: {}", customerId);
    }

    @Override
    public void patchCustomerById(UUID customerId, Customer customer) {
        Customer existingCustomer = this.getCustomerById(customerId);

        if(StringUtils.hasText(existingCustomer.getName())) {
            existingCustomer.setName(customer.getName());
        }

    }
}
