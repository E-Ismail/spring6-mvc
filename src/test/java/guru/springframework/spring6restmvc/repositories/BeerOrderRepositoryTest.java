package guru.springframework.spring6restmvc.repositories;

import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.entities.BeerOrder;
import guru.springframework.spring6restmvc.entities.BeerOrderShipment;
import guru.springframework.spring6restmvc.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
/*
 * @author Ech-Cherrate Ismail
 * @project spring-6-rest-mvc
 * @create 30/08/2025 - 16:03
 */

//@DataJpaTest//Again a splice
//Full test suite
@SpringBootTest
class BeerOrderRepositoryTest {

    @Autowired
    private BeerOrderRepository beerOrderRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BeerRepository beerRepository;

    Customer testCustomer;
    Beer testBeer;

    @BeforeEach
    void setUp() {

        testCustomer = customerRepository.findAll().getFirst();
        testBeer = beerRepository.findAll().getFirst();
    }

    @Transactional
    //Without the annotation: Unable to evaluate the expression Method threw 'org.hibernate.LazyInitializationException' exception.
    @Test
    void testBeerOrders() {
//        assertThat(beerOrderRepository.count()).isEqualTo(0L);
//        assertThat(customerRepository.count()).isEqualTo(3L);
//        assertThat(beerRepository.count()).isEqualTo(2413L);
//        System.out.println(testCustomer.getName());
//        System.out.println(testBeer.getBeerName());
        BeerOrder beerOrder = BeerOrder.builder()
                .customerRef("Test Order")
                .customer(testCustomer)
                .beerOrderShipment(BeerOrderShipment.builder()
                        .trackingNumber("1234r")
                        .build())
                .build();

        //BeerOrder savedBeerOrder = beerOrderRepository.saveAndFlush(beerOrder);
        //beerOrders list size is 0, has not been flushed to the database
        // Lazy loaded

        BeerOrder savedBeerOrder = beerOrderRepository.save(beerOrder);
        System.out.println("savedBeerOder" + savedBeerOrder.getCustomerRef());
    }
}