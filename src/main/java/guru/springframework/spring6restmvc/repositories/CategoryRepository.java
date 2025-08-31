package guru.springframework.spring6restmvc.repositories;

/*
 * @author Ech-Cherrate Ismail
 * @project spring-6-rest-mvc
 * @create 31/08/2025 - 12:20
 */

import guru.springframework.spring6restmvc.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
