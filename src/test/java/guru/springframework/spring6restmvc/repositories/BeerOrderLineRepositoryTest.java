package guru.springframework.spring6restmvc.repositories;

import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.entities.BeerOrderLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
/*
 * @author Ech-Cherrate Ismail
 * @project spring-6-rest-mvc
 * @create 30/08/2025 - 19:14
 */

@SpringBootTest
class BeerOrderLineRepositoryTest {

    Beer testBeer;
    @Autowired
    private BeerRepository beerRepository;
    @Autowired
    private BeerOrderLineRepository beerOrderLineRepository;

    @BeforeEach
    void setUp() {
        testBeer = beerRepository.findAll().getFirst();

    }

    @Test
    void testBeerOrderLines(){
        BeerOrderLine beerOrderLine = BeerOrderLine.builder()
                .beer(testBeer)
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        BeerOrderLine savedBeerOrderLine =beerOrderLineRepository.save(beerOrderLine);
        assertThat(savedBeerOrderLine.getBeer().getBeerName()).isEqualTo(testBeer.getBeerName());
    }
}