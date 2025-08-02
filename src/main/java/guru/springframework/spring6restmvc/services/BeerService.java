package guru.springframework.spring6restmvc.services;

/*
 * @author Ech-Cherrate Ismail
 * @project spring-6-rest-mvc
 * @create 26/07/2025 - 22:20
 */

import guru.springframework.spring6restmvc.model.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerService {

    Beer getBeerById(UUID id);

    List<Beer> litBeers();

    Beer saveBeer(Beer beer);

    void updateBeerById(UUID beerId, Beer beer);

    void deleteById(UUID beerId);

    void patchBeerById(UUID beerId, Beer beer);
}
