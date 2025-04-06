package com.se.sample.models;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class CustomDTO6 {

    @ApiModelProperty(
            name = "count",
            value = "Количество записей на странице результата поиска (пагинация). Рекомендуемый диапазон: 1-100, "
                    + "но пользователь может задать любое значение по своему усмотрению.",
            dataType = "integer",
            example = "5")
    private int count;

    @ApiModelProperty(
            name = "q",
            value = "Поисковая строка. Одна или несколько фраз для поиска."
            ,example = "[\"Господарський\", \"кодекс\"]"
            ,dataType = "java.util.List"
    )
    private List<String> q;

    @ApiModelProperty(name = "search_group",
            value = "500-0000.0001.0001: База податкових знань, <br><br>" +
                    "500-0000.0002.0001: Судова практика, <br><br>" +
                    "500-0000.0002.0005: Практика ЄСПЛ, <br><br>" +
                    "500-0000.0003.0000: Міжнародне законодавство, <br><br>" +
                    "500-0000.0003.0001: Загальне законодавство, <br><br>" +
                    "500-0000.0003.0002: Регіональне законодавство, <br><br>" +
                    "500-0000.0003.0003: Столичне законодавство, <br><br>" +
                    "500-0000.0003.0005: Європейське законодавство, <br><br>" +
                    "500-0000.0003.0006: Внесені до ЄДРНПА, <br><br>" +
                    "500-0000.0009.0001: Мистецтво оборони, <br><br>" +
                    "500-0000.0009.0003: Алгоритми дій, <br><br>" +
                    "500-0000.0011.0001: Інструкції та шаблони для кадровика, <br><br>" +
                    "500-0000.0012.0000: Ситуації для бізнесу,<br><br> " +
                    "500-0000.0012.0001: Ситуації для бухгалтера, <br><br>" +
                    "500-0000.0012.0002: Ситуації для юриста, <br><br>" +
                    "500-0000.0012.0003: Ситуації для кадровика, <br><br>" +
                    "500-0000.0012.0004: Ситуації для комплаєнса<br><br>"
            ,example = "[\"500-0000.0001.0001\", \"500-0000.0003.0001\"]"
            ,dataType = "java.util.List"
    )
    private List<String> search_group;



    public CustomDTO6() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getQ() {
        return q;
    }

    public void setQ(List<String> q) {
        this.q = q;
    }

    public List<String> getSearch_group() {
        return search_group;
    }

    public void setSearch_group(List<String> search_group) {
        this.search_group = search_group;
    }
}
