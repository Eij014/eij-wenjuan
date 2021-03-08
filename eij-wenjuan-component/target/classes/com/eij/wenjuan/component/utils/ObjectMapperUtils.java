package com.eij.wenjuan.component.utils;

import java.io.IOException;
import java.io.UncheckedIOException;

import javax.annotation.Nullable;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-02-09
 */
public class ObjectMapperUtils {
    private static final String EMPTY_JSON = "{}";
    private static final String EMPTY_ARRAY_JSON = "[]";
    private static final ObjectMapper MAPPER;

    public ObjectMapperUtils() {
    }

    public static String toJson(@Nullable Object obj) {
        if (obj == null) {
            return null;
        } else {
            try {
                return MAPPER.writeValueAsString(obj);
            } catch (JsonProcessingException var) {
                throw new UncheckedIOException(var);
            }
        }
    }

    public static <T> T fromJson(@Nullable String json, Class<T> valueType) {
        if (json == null) {
            return null;
        } else {
            try {
                return MAPPER.readValue(json, valueType);
            } catch (IOException var2) {
                throw new RuntimeException(var2);
            }
        }
    }

    static {
        MAPPER = (new ObjectMapper((new JsonFactory().disable(JsonFactory.Feature.INTERN_FIELD_NAMES))).registerModule(new GuavaModule()));
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        MAPPER.enable(new JsonParser.Feature[]{JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS});
        MAPPER.enable(new JsonParser.Feature[]{JsonParser.Feature.ALLOW_COMMENTS});
        MAPPER.registerModule(new com.fasterxml.jackson.module.paramnames.ParameterNamesModule());
        MAPPER.registerModule(new com.hubspot.jackson.datatype.protobuf.ProtobufModule());
    }
}
