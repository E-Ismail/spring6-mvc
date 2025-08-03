package guru.springframework.spring6restmvc.services;

/*
 * @author Ech-Cherrate Ismail
 * @project spring-6-rest-mvc
 * @create 26/07/2025 - 22:21
 */

import guru.springframework.spring6restmvc.model.Beer;
import guru.springframework.spring6restmvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;


@Service
@Slf4j
public class BeerServiceImpl implements BeerService {

    private Map<UUID, Beer> beerMap;

    public BeerServiceImpl() {
        this.beerMap = new HashMap<>();

        Beer beer1 = Beer.builder().id(UUID.randomUUID()).version(1).beerName("Galaxy Cat").beerStyle(BeerStyle.PALE_ALE).upc("12356").price(new BigDecimal("12.99")).quantityOnHand(122).createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).build();

        Beer beer2 = Beer.builder().id(UUID.randomUUID()).version(1).beerName("Crank").beerStyle(BeerStyle.PALE_ALE).upc("12356222").price(new BigDecimal("11.99")).quantityOnHand(392).createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).build();

        Beer beer3 = Beer.builder().id(UUID.randomUUID()).version(1).beerName("Sunshine City").beerStyle(BeerStyle.IPA).upc("12356").price(new BigDecimal("13.99")).quantityOnHand(144).createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).build();

        beerMap.put(beer1.getId(), beer1);
        beerMap.put(beer2.getId(), beer2);
        beerMap.put(beer3.getId(), beer3);
    }

    @Override
    public Optional<Beer> getBeerById(UUID id) {

        log.debug("[SERVICE] getBeerById");
        return Optional.of(beerMap.get(id));
    }

    @Override
    public List<Beer> listBeers() {
        log.debug("[SERVICE] Lit Beers");
        return new ArrayList<>(beerMap.values());
    }

    @Override
    public Beer saveBeer(Beer beer) {
        Beer savedBeer = Beer.builder()
                .id(UUID.randomUUID())
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .upc(beer.getUpc())
                .price(beer.getPrice())
                .quantityOnHand(beer.getQuantityOnHand())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        this.beerMap.put(savedBeer.getId(), savedBeer);
        return savedBeer;
    }

    @Override
    public void updateBeerById(UUID beerId, Beer beer) {
        Beer existingBeer = beerMap.get(beerId);
        existingBeer.setBeerName(beer.getBeerName());
        existingBeer.setBeerStyle(beer.getBeerStyle());
        existingBeer.setUpc(beer.getUpc());
        existingBeer.setPrice(beer.getPrice());
        existingBeer.setQuantityOnHand(beer.getQuantityOnHand());
        existingBeer.setUpdateDate(LocalDateTime.now());

        //beerMap.put(existingBeer.getId(), existingBeer); no need, test also in customerService

    }

    @Override
    public void deleteById(UUID beerId) {
        beerMap.remove(beerId);
        log.debug("[SERVICE] successfully removed Beer with id: {}", beerId);
    }

    @Override
    public void patchBeerById(UUID beerId, Beer beer) {
        Beer existingBeer = this.getBeerById(beerId).orElse(null);

        if (StringUtils.hasText(beer.getBeerName())) {
            existingBeer.setBeerName(beer.getBeerName());
        }

        if (beer.getBeerStyle() != null) {
            existingBeer.setBeerStyle(beer.getBeerStyle());
        }

        if (beer.getPrice() != null) {
            existingBeer.setPrice(beer.getPrice());
        }

        if (beer.getQuantityOnHand() != null) {
            existingBeer.setQuantityOnHand(beer.getQuantityOnHand());
        }

        if (StringUtils.hasText(beer.getUpc())) {
            existingBeer.setUpc(beer.getUpc());
        }
    }
}
