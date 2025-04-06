package com.se.sample;

import com.se.sample.entity.Item;
import com.se.sample.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@SpringBootTest
class SbApplicationTests {

    @Autowired
    @Lazy
    ItemRepository itemRepository;

    @Test
    void contextLoads() {
    }

    @Test
        // данные загружаются из data.sql
    void testImportSqlRecords() {

        List<Item> item = itemRepository.findByName("Mobiles-test1");

        Assertions.assertNotNull(item.get(0));
        Assertions.assertEquals("Mobiles-test1", item.get(0).getName());
    }


}
