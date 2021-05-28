package com.skynet.initdata.changelog;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public abstract class AbstractChangeLog {

    public static ObjectMapper mapper = new ObjectMapper();

    public static String getJsonString(String path) throws JsonParseException, JsonMappingException, IOException {
        log.info("Reading Json file with path" + path);
        InputStream in = TypeReference.class.getResourceAsStream(path);
        JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
        return mapper.writeValueAsString(jsonNode);
    }

}
