package com.se.sample.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.se.sample.models.enums.SearchGroup;

import java.io.IOException;

public class CustomStageSerializer extends JsonSerializer<SearchGroup> {
    @Override
    public void serialize(SearchGroup group, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        // Serialize all stages except the one you want to hide
        if (group != SearchGroup.E) {
            System.out.println("HEERER-----------------------------");
            jsonGenerator.writeString(group.name() + "++++++" + group.getValue());
        }
    }
}
