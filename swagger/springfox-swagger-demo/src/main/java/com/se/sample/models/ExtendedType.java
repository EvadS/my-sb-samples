package com.se.sample.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.LinkedList;
import java.util.List;

@ApiModel(value = "VALUE")
public class ExtendedType {

    @ApiModelProperty(notes = "Доступные поисковые группы", required = true ,
            allowableValues = "{[\"500-0000.0001.0001\", \"База податкових знань\"], 111<br><br>" +                    "" +
                    "\"[\"500-0000.0002.0001\", \"Судова практика\"]\"<br><br>" +
                    "\"[\"500-0000.0002.0001\", \"Судова практика\"]\"}",
            dataType = "enum",
            name = "searchGroup")
    private List<String> search = new LinkedList<>();

    public ExtendedType() {
    }

    public List<String> getSearch() {
        return search;
    }

    public void setSearch(List<String> search) {
        this.search = search;
    }
}
