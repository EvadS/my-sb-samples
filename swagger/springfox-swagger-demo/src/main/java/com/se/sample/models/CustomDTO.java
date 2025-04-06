package com.se.sample.models;

import com.se.sample.models.enums.SearchGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "my base value", description = "default description" , reference = "CustomDTO")
public class CustomDTO {

    @ApiModelProperty(value = "base search group value",
    notes = "my search group notes",
    example = "example",
    dataType = "SearchGroup",
    allowableValues = "allowableValues")
    private SearchGroup searchGroup;

    public SearchGroup getSearchGroup() {
        return searchGroup;
    }

    public void setSearchGroup(SearchGroup searchGroup) {
        this.searchGroup = searchGroup;
    }
}
