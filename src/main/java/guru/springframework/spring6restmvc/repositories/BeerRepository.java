package guru.springframework.spring6restmvc.repositories;

/*
 * @author Ech-Cherrate Ismail
 * @project spring-6-rest-mvc
 * @create 04/08/2025 - 23:27
 */

import guru.springframework.spring6restmvc.entities.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface BeerRepository extends JpaRepository<Beer, UUID> {
}
