package guru.springframework.spring6restmvc.repositories;

import guru.springframework.spring6restmvc.bootstrap.BootstrapData;
import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.model.BeerStyle;
import guru.springframework.spring6restmvc.services.BeerCsvServiceImpl;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
/*
 * @author Ech-Cherrate Ismail
 * @project spring-6-rest-mvc
 * @create 04/08/2025 - 23:29
 */

@DataJpaTest//splice
@Import({BootstrapData.class, BeerCsvServiceImpl.class})
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testGetBeerListByName() {
        List<Beer> list = beerRepository.findAllByBeerNameIsLikeIgnoreCase("%IPA%");
        assertThat(list.size()).isEqualTo(336);
    }

    @Test
    void testSaveBeerNameTooLong() {
        assertThrows(ConstraintViolationException.class, () -> {
            Beer beer = Beer.builder()
                    .beerName("Very Long Name But that is returning a data DataIntegrityViolationException")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .upc("12345")
                    .price(BigDecimal.valueOf(12.34))
                    .build();
            beerRepository.save(beer);
            beerRepository.flush();
        });
    }

    @Test
    void testSaveBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                .beerName("Badweiser")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12345")
                .price(new BigDecimal("11.99"))
                .build());
        //Running so quickly returning back beer object and not rushing to db
        //Sometimes hibernate will flush and batch, but the session is ending so quickly
        //explicitly flush through the repository
        beerRepository.flush();

        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
    }

}