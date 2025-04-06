package com.se.sample.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value = "CustomDTO4", description = "line with hyphenation ")
public class CustomDTO4 {

    private static final String item1 = "500-0000.0002.0001\tСудова практика\n";

    @ApiModelProperty(value = "500-0000.0002.0005\tПрактика ЄСПЛ <br><br>" +
            "500-0000.0003.0000 Міжнародне законодавство<br><br>" +
            "500-0000.0003.0000 Міжнародне законодавство"

    ,allowableValues = "\"500-0000.0003.0000 Міжнародне законодавство <br><br>" +
            "500-0000.0003.0000\tМіжнародне законодавство\n"
    ,dataType = "string")
    private String item2;


    public CustomDTO4() {
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }
}
