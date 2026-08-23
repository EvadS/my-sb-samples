package com.se.sample;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Person API",
        description = "API для управления данными")
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/process")
    @Operation(summary = "Process Orders", description = "Processes a list of order IDs asynchronously.")
    public String processOrders(@RequestBody List<String> orderIds) {
        orderService.processOrders(orderIds);
        return "Processing started!";
    }
}
