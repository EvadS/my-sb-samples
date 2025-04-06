package com.se.sample;

import com.se.entity.Item;
import com.se.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ApplicationTests {

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

    /**
     * data.slq будет загружен при старте
     *  @Sql(scripts = {"classpath:data.sql"}) - будет загружен для данного теста
     */
    @Test
    @Sql(scripts = {"classpath:data.sql"})
    void testDataSqlRecords() {
        // здесь еще одна загрузка данных
        List<Item> item = itemRepository.findByName("Laptops-test1");

        Assertions.assertNotNull(item);
        Assertions.assertEquals("Laptops-test1", item.get(0).getName());
    }

    @Test
    @Sql(scripts = {"classpath:data.sql"})
    void testDataSqlRecords2() {

        Optional<Item> item = itemRepository.findById(1L);

        Assertions.assertNotNull(item.get().getId());
        Assertions.assertEquals("Mobiles-test1", item.get().getName());
    }

    // each @Sql instruction -  one data load from file
    @Test
    @Sql(scripts = {"classpath:data.sql"})
    void testDataSqlRecords3() {

        List<Item> item = itemRepository.findByName("Laptops-test1");

        Assertions.assertNotNull(item.get(0).getId());
        Assertions.assertEquals("Laptops-test1", item.get(0).getName());
        Assertions.assertEquals(3, item.size());
    }


    /*
         источник: sb-data-init-h2\src\test\resources\com\se\sample>ApplicationTests.testMethodNameScript.sql
     */
    @Test
    @Sql
    void testMethodNameScript() {
        List<Item> item = itemRepository.findByName("Spices");

        Assertions.assertNotNull(item.get(0).getId());
        Assertions.assertEquals("Spices", item.get(0).getName());
    }
}