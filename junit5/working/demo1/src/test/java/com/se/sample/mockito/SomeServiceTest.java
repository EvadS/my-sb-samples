package com.se.sample.mockito;



import com.se.sample.service.SomeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SomeServiceTest {

    @Mock
    private SomeService someServiceMock;

    //  Настройка поведения моков
    @Test
    void testMockBehavior() {
        // задать его поведение
//        when(someServiceMock.greet("John")).thenReturn("Hello, mocked John!");
//
//        String result = someServiceMock.greet("John");
//
//        Assertions.assertEquals("Hello, mocked John!", result);
    }

    // Проверка вызовов
    @Test
    void testMethodInvocation() {
   //     someServiceMock.greet("John");

       // Mockito.verify(someServiceMock).greet("John"); // Проверка: вызвался ли метод greet с параметром "John"
       // Mockito.verify(someServiceMock, Mockito.times(1)).greet("John"); // Проверка: вызвался ровно один раз
    }
}