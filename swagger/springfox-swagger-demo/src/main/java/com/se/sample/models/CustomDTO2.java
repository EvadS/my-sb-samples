package com.se.sample.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.se.sample.models.enums.SearchGroup;
import com.se.sample.serializers.CustomStageSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "option with serializer", description = "serializer description")
public class CustomDTO2 {

    @JsonSerialize(using = CustomStageSerializer.class)

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
