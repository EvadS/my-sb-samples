package com.se.sample.repository;

import com.se.sample.model.CatalogueItem;
import com.se.sample.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CatalogueJPATest {
    @Autowired
    private TestEntityManager testEM;
    @Autowired
    private CatalogueRepository catalogueRepository;


    // SKU number we use for testing
    private static final String skuNumber = "SKUNUMBER-1234";


    @BeforeEach
    void cleanup() {
        catalogueRepository.deleteAll();
        catalogueRepository.flush();
        testEM.clear();
    }

    @Test
    public void testFindByPublishedDateAfter() {

        CatalogueItem item = prepareCatalogItem(skuNumber);
        item = catalogueRepository.save(item);

        Assertions.assertNotNull(item.getId());

    }
    private CatalogueItem prepareCatalogItem(String skuNumber) {
        CatalogueItem item
                = CatalogueItem.of(
                skuNumber,
                "Catalog Item -"+skuNumber,
                "Catalog Desc - "+skuNumber,
                Category.BOOKS.getValue(),
                10.00,
                10,
                new Date()
        );
        return item;
    }

}
