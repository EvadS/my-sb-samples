package com.se.sample.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "option with allowable values", description = "serializer description")
public class CustomDTO3 {

    @ApiModelProperty(notes = "Доступные поисковые группы", required = true ,
            allowableValues = "{[\"500-0000.0001.0001\", \"База податкових знань\"], 111<br><br>" +                    "" +
                    "\"[\"500-0000.0002.0001\", \"Судова практика\"]\"<br><br>" +
                    "\"[\"500-0000.0002.0001\", \"Судова практика\"]\"}",
            dataType = "enum",
            name = "searchGroup")
    private String searchGroup;

    public CustomDTO3() {
    }

    public String getSearchGroup() {
        return searchGroup;
    }

    public void setSearchGroup(String searchGroup) {
        this.searchGroup = searchGroup;
    }
}
