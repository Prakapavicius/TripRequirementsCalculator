package com.pprakapavicius.TripRequirementsCalculator.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.beans.PropertyEditorSupport;
import java.lang.reflect.Field;
import java.util.stream.Stream;

public class JsonPropertyEditor <T extends Enum<T>> extends PropertyEditorSupport {
    private final Class<T> typeParameterClass;

    public JsonPropertyEditor(Class<T> typeParameterClass) {
        super();
        this.typeParameterClass = typeParameterClass;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null) {
            throw new IllegalArgumentException();
        }

        Stream.of(typeParameterClass.getFields())
                .filter(Field::isEnumConstant)
                .filter(field -> field.getAnnotation(JsonProperty.class) != null)
                .filter(field -> field.getAnnotation(JsonProperty.class).value().equals(text))
                .map(Field::getName)
                .map(name -> T.valueOf(typeParameterClass, name))
                .findFirst()
                .ifPresent(this::setValue);
    }
}
