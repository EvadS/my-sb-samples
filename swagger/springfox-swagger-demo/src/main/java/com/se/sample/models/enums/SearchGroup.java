package com.se.sample.models.enums;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;


@ApiModel(value = "api model <br><br> value",
        description = "my description",
        reference = ""
)
public enum SearchGroup {
    @ApiModelProperty(name = "api model name", notes = "my api model notes")
    A("500-0000.0001.0001", "База податкових знань"),
    B("500-0000.0002.0001", "Судова практика"),
    C("500-0000.0002.0005", "Практика ЄСПЛ"),
    D("500-0000.0003.0000", "Міжнародне законодавство"),
    E("500-0000.0003.0001", "Загальне законодавство"),
    F("500-0000.0003.0002", "Регіональне законодавство"),
    G("500-0000.0003.0003", "Столичне законодавство"),
    H("500-0000.0003.0005", "Європейське законодавство"),
    I("500-0000.0003.0006", "Внесені до ЄДРНПА"),
    J("500-0000.0009.0001", "Мистецтво оборони"),
    K("500-0000.0009.0003", "Алгоритми дій"),
    L("500-0000.0011.0001", "Інструкції та шаблони для кадровика"),
    M("500-0000.0012.0000", "Ситуації для бізнесу"),
    N("500-0000.0012.0001", "Ситуації для бухгалтера"),
    O("500-0000.0012.0002", "Ситуації для юриста"),
    P("500-0000.0012.0003", "Ситуації для кадровика"),
    Q("500-0000.0012.0004", "Ситуації для комплаєнса");

    private String name;
    private String value;

    SearchGroup(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public final static Map<String, String> colors = new HashMap<>();

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "[" + name + ":" + value + "]";
    }
}

