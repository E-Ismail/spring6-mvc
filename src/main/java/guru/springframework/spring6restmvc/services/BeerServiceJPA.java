package guru.springframework.spring6restmvc.services;

/*
 * @author Ech-Cherrate Ismail
 * @project spring-6-rest-mvc
 * @create 05/08/2025 - 22:16
 */

import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.mappers.BeerMapper;
import guru.springframework.spring6restmvc.model.BeerDTO;
import guru.springframework.spring6restmvc.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Primary
@RequiredArgsConstructor
@Slf4j
public class BeerServiceJPA implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;
    private final static String WILDCARD = "%";

    @Override
    public List<BeerDTO> listBeers(String beerName) {
        List<Beer> beerList;
        if (StringUtils.hasText(beerName)) {
            beerList = listBeersByName(beerName);
        } else {
            beerList = beerRepository.findAll();
        }

        return beerList.stream()
                .map(beerMapper::beerToBeerDTO)
                .toList();
    }

    List<Beer> listBeersByName(String name) {
        return beerRepository.findAllByBeerNameIsLikeIgnoreCase(WILDCARD + name + WILDCARD);
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID uuid) {
        return Optional.ofNullable(beerMapper.beerToBeerDTO(beerRepository.findById(uuid).orElse(null)));
    }

    @Override
    public BeerDTO saveBeer(BeerDTO beer) {
        return beerMapper.beerToBeerDTO(beerRepository.save(beerMapper.beerDTOToBeer(beer)));
    }

    @Override
    public Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beerDTO) {
        AtomicReference<Optional<BeerDTO>> beerDTOAtomicReference = new AtomicReference<>();

        beerRepository.findById(beerId).ifPresentOrElse(foundBeer -> {
            foundBeer.setBeerName(beerDTO.getBeerName());
            foundBeer.setBeerStyle(beerDTO.getBeerStyle());
            foundBeer.setUpc(beerDTO.getUpc());
            foundBeer.setPrice(beerDTO.getPrice());
            beerDTOAtomicReference.set(Optional.of(beerMapper
                    .beerToBeerDTO(beerRepository.save(foundBeer))));
        }, () -> beerDTOAtomicReference.set(Optional.empty()));

        return beerDTOAtomicReference.get();
    }

    @Override
    public Boolean deleteById(UUID beerId) {
        if (beerRepository.existsById(beerId)) {
            beerRepository.deleteById(beerId);
            return true;
        }
        return false;
    }

    @Override
    public Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beerDTO) {
        AtomicReference<Optional<BeerDTO>> beerDTOAtomicReference = new AtomicReference<>();

        beerRepository.findById(beerId).ifPresentOrElse(foundBeer -> {
            if (StringUtils.hasText(beerDTO.getBeerName())) {
                foundBeer.setBeerName(beerDTO.getBeerName());
            }

            if (beerDTO.getBeerStyle() != null) {
                foundBeer.setBeerStyle(beerDTO.getBeerStyle());
            }
            if (StringUtils.hasText(beerDTO.getUpc())) {
                foundBeer.setUpc(beerDTO.getUpc());
            }

            if (beerDTO.getPrice() != null) {
                foundBeer.setPrice(beerDTO.getPrice());
            }

            if (beerDTO.getQuantityOnHand() != null) {
                foundBeer.setQuantityOnHand(beerDTO.getQuantityOnHand());
            }

            beerDTOAtomicReference.set(Optional.of(beerMapper
                    .beerToBeerDTO(beerRepository.save(foundBeer))));
        }, () -> beerDTOAtomicReference.set(Optional.empty()));

        return beerDTOAtomicReference.get();
    }
}
