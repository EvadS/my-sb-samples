package com.se.sample.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Met request")
public class MetRequest {

    @ApiModelProperty(name = "seach_group",
            value = "\"500-0000.0002.0001\":\"Судова практика\"<br><br>" +
            "                    \"500-0000.0002.0001\": \"Судова практика\""
                    , dataType = "")
    private String[] seach_group;




    public String[] getSeach_group() {
        return seach_group;
    }

    public void setSeach_group(String[] seach_group) {
        this.seach_group = seach_group;
    }
}
