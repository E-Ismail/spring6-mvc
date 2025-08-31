package guru.springframework.spring6restmvc.repositories;

import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.entities.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
/*
 * @author Ech-Cherrate Ismail
 * @project spring-6-rest-mvc
 * @create 31/08/2025 - 12:21
 */

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BeerRepository beerRepository;

    Beer testBeer;

    @BeforeEach
    void setUp() {
        testBeer = beerRepository.findAll().getFirst();
    }

    @Transactional
    @Test
    void testAddCategory() {
        Category saveCat = categoryRepository.save(Category.builder()
                .description("Ales")
                .build());
        testBeer.addCategory(saveCat);
        Beer savedBeer = beerRepository.save(testBeer);

        System.out.println(savedBeer.getBeerName());

    }
}