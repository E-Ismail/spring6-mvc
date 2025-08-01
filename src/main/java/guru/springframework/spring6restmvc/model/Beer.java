package guru.springframework.spring6restmvc.model;

/*
 * @author Ech-Cherrate Ismail
 * @project spring-6-rest-mvc
 * @create 26/07/2025 - 22:14
 */

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Beer {

    private UUID id;
    private Integer version;
    private String beerName;
    private BeerStyle beerStyle;
    private String upc;
    private Integer quantityOnHand;
    private BigDecimal price;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
