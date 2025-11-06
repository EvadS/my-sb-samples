package com.se.sample.memoryleaks.equalshashcode;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

public class BadPersonMemoryLeakUnitTest {
    @Test
    @Ignore // Test deliberately ignored as memory leak tests consume lots of resources
    public void givenMap_whenEqualsAndHashCodeNotOverridden_thenMemoryLeak() {
        Map<BadPerson, Integer> map = new HashMap<BadPerson, Integer>();
        for(int i=0; i<10000000; i++) {
            map.put(new BadPerson("jon"), 1);
        }
        assertTrue(map.size() > 1);
        System.out.print("Debug Point - VisuaLVM");
    }
    
    @Test
    @Ignore // Test deliberately ignored as memory leak tests consume lots of resources
    public void givenMap_whenEqualsAndHashCodeOverridden_thenNoMemoryLeak() {
        Map<Person, Integer> map = new HashMap<Person, Integer>();
        for(int i=0; i<10000; i++) {
            map.put(new Person("jon"), 1);
        }
        assertTrue(map.size() == 1);
        System.out.print("Debug Point - VisuaLVM");
    }
}
