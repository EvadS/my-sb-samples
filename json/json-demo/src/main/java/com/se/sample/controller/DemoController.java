package com.se.sample.controller;


import com.se.sample.models.MyDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @ResponseBody
    @GetMapping("/base")
    private MyDto demo1() {

        MyDto myDto = new MyDto();
        myDto.setIntValue(123);
        myDto.setStringValue("string value");

        return myDto;
    }

    @ResponseBody
    @GetMapping("/base-null")
    private MyDto demo2() {

        MyDto myDto = new MyDto();
        myDto.setIntValue(123);

        return myDto;
    }
}
