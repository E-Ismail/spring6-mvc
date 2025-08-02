package guru.springframework.spring6restmvc.controller;

/*
 * @author Ech-Cherrate Ismail
 * @project spring-6-rest-mvc
 * @create 29/07/2025 - 22:37
 */

import guru.springframework.spring6restmvc.model.Customer;
import guru.springframework.spring6restmvc.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> customers() {
        log.info("[CONTROLLER] Getting customers]");
        return customerService.customers();
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable("customerId") UUID customerId) {
        log.info("[CONTROLLER] Getting customer with id: {}", customerId);
        return customerService.getCustomerById(customerId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {

        Customer savedCustomer = customerService.saveCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + savedCustomer.getId().toString());
        return new ResponseEntity<>(savedCustomer, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<HttpStatus> updateCustomer(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer) {
        log.info("[CONTROLLER] Updating customer with id: {}", customerId);
        customerService.updateCustomerById(customerId, customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("customerId") UUID customerId) {
        log.info("[CONTROLLER] Deleting customer with id: {}", customerId);
        customerService.deleteCustomerById(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/{customerId}")
    public ResponseEntity<HttpStatus> patchCustomerById(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer) {
        log.info("[CONTROLLER] Patching customer with id: {}", customerId);
        customerService.patchCustomerById(customerId, customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
