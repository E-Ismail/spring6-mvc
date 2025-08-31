package guru.springframework.spring6restmvc.repositories;

/*
 * @author Ech-Cherrate Ismail
 * @project spring-6-rest-mvc
 * @create 30/08/2025 - 16:02
 */

import guru.springframework.spring6restmvc.entities.BeerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerOrderRepository extends JpaRepository<BeerOrder, UUID> {
}
