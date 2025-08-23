package guru.springframework.spring6restmvc.services;

/*
 * @author Ech-Cherrate Ismail
 * @project spring-6-rest-mvc
 * @create 22/08/2025 - 17:03
 */

import guru.springframework.spring6restmvc.model.BeerCSVRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface BeerCsvService {

    List<BeerCSVRecord> convertCSV(File csvFile) throws FileNotFoundException;
}
