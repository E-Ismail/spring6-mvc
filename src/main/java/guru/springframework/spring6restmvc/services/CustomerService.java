package guru.springframework.spring6restmvc.services;

/*
 * @author Ech-Cherrate Ismail
 * @project spring-6-rest-mvc
 * @create 29/07/2025 - 22:33
 */

import guru.springframework.spring6restmvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    List<Customer> customers();

    Customer getCustomerById(UUID id);

    Customer saveCustomer(Customer customer);

    void updateCustomerById(UUID customerId, Customer customer);

    void deleteCustomerById(UUID customerId);

    void patchCustomerById(UUID customerId, Customer customer);
}
