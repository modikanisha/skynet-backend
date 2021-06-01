package com.skynet.commons.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class jsonParseUtil {

    public static ObjectMapper mapper = new ObjectMapper();

    public static String getJsonString(String path) throws IOException {

        InputStream in = TypeReference.class.getResourceAsStream(path);
        JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
        return mapper.writeValueAsString(jsonNode);
    }
}
