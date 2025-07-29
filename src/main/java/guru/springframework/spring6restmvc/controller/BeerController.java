package guru.springframework.spring6restmvc.controller;

/*
 * @author Ech-Cherrate Ismail
 * @project spring-6-rest-mvc
 * @create 26/07/2025 - 22:25
 */

import guru.springframework.spring6restmvc.model.Beer;
import guru.springframework.spring6restmvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
@AllArgsConstructor
@Slf4j
public class BeerController {
    private final BeerService beerService;


    @RequestMapping(method = RequestMethod.GET)
    public List<Beer> litBeers() {
        return beerService.litBeers();
    }

    @RequestMapping(value = "/{beerId}", method = RequestMethod.GET)
    public Beer getBeerById(@PathVariable("beerId") UUID beerId) {
        log.debug("[CONTROLLER] Get Beer or something with id: {}", beerId);
        return beerService.getBeerById(beerId);
    }
}
