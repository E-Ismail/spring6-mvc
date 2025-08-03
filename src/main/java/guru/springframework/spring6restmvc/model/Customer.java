package guru.springframework.spring6restmvc.model;

/*
 * @author Ech-Cherrate Ismail
 * @project spring-6-rest-mvc
 * @create 29/07/2025 - 22:32
 */

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Customer {

    private UUID id;
    private String name;
    private Integer version;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

}
