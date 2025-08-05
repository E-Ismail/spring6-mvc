package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.BeerDTO;
import guru.springframework.spring6restmvc.repositories.BeerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
/*
 * @author Ech-Cherrate Ismail
 * @project spring-6-rest-mvc
 * @create 05/08/2025 - 22:25
 */

//Full spring boot context, Complete test
@SpringBootTest
class BeerControllerIT {

    @Autowired
    BeerController beerController;
    @Autowired
    BeerRepository beerRepository;


    @Test
    void testListBeers() {
        List<BeerDTO> dtos = beerController.litBeers();
        assertThat(dtos).hasSize(3);
    }

    @Rollback
    @Transactional
    @Test
    void testEmptyList() {
        beerRepository.deleteAll();
        List<BeerDTO> dtos = beerController.litBeers();
        assertThat(dtos.size()).isEqualTo(0);
    }
}