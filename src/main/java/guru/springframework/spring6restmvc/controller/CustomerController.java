package guru.springframework.spring6restmvc.controller;

/*
 * @author Ech-Cherrate Ismail
 * @project spring-6-rest-mvc
 * @create 29/07/2025 - 22:37
 */

import guru.springframework.spring6restmvc.model.CustomerDTO;
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
@RequestMapping(CustomerController.CUSTOMER_PATH)
public class CustomerController {

    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = "/{customerId}";

    private final CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<CustomerDTO> listAllCustomers() {
        log.info("[CONTROLLER] Getting customers]");
        return customerService.getAllCustomers();
    }

    @RequestMapping(value = CustomerController.CUSTOMER_PATH_ID, method = RequestMethod.GET)
    public CustomerDTO getCustomerById(@PathVariable("customerId") UUID customerId) {
        log.info("[CONTROLLER] Getting customer with id: {}", customerId);
        return customerService.getCustomerById(customerId).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> createCustomer(@RequestBody CustomerDTO customer) {

        CustomerDTO savedCustomer = customerService.saveNewCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + savedCustomer.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(CustomerController.CUSTOMER_PATH_ID)
    public ResponseEntity<HttpStatus> updateCustomerById(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDTO customer) {
        log.info("[CONTROLLER] Updating customer with id: {}", customerId);
        if(customerService.updateCustomerById(customerId, customer).isEmpty()){
            throw new NotFoundException();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = CustomerController.CUSTOMER_PATH_ID)
    public ResponseEntity<HttpStatus> deleteCustomerById(@PathVariable("customerId") UUID customerId) {
        if (!customerService.deleteCustomerById(customerId)){
            throw new NotFoundException();
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = CustomerController.CUSTOMER_PATH_ID)
    public ResponseEntity<HttpStatus> patchCustomerById(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDTO customer) {
        log.info("[CONTROLLER] Patching customer with id: {}", customerId);
        customerService.patchCustomerById(customerId, customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
