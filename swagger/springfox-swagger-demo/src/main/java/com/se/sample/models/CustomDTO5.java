package com.se.sample.models;

import com.se.sample.models.enums.SearchGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value = "CustomDTO5", description = "api model for enums")
public class CustomDTO5 {

    public CustomDTO5() {
    }
    @ApiModelProperty(
            notes = "The search group",
            name = "testAnootation",
            example = "ex1" +
                    "ex2",
            value = "1. Name <br><br>"+
                    "2. Teams <br><br>", dataType = "значения")
    private String testAnootation;

    @ApiModelProperty(
            notes = "The search group",
            name = "searchGroup",
            value = "1. Name <br><br>" +
                    "2. Teams  <br><br>")
    private SearchGroup searchGroup;

    public SearchGroup getSearchGroup() {
        return searchGroup;
    }

    public void setSearchGroup(SearchGroup searchGroup) {
        this.searchGroup = searchGroup;
    }
}
