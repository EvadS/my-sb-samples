package com.se.sample;

import com.se.sample.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql({"/item/import_item.sql", "/item/import_item2.sql"})
public class SpringBootInitialLoadIntegrationTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testLoadDataForTestClass() {
        assertEquals(6, itemRepository.findAll().spliterator().getExactSizeIfKnown());
    }
}
