package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.Beer;
import guru.springframework.spring6restmvc.services.BeerService;
import guru.springframework.spring6restmvc.services.BeerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/*
 * @author Ech-Cherrate Ismail
 * @project spring-6-rest-mvc
 * @create 26/07/2025 - 22:29
 */

//Test splice, just for BeerController
@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    BeerService beerService;

    BeerServiceImpl beerServiceImpl= new BeerServiceImpl();

    @Test
    void getBeerById() throws Exception {
        Beer testBeer = beerServiceImpl.litBeers().get(0);
        given(beerService.getBeerById(any(UUID.class))).willReturn(testBeer);
        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void listBeers() {
        //assertThat(beerController.litBeers().size()).isEqualTo(3);
    }
}