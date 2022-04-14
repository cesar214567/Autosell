package com.example.autosell.utils.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.json.Json;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

public class Parser {
    static private final ObjectMapper objectMapper = new ObjectMapper();
    static private final JSONParser jsonParser = new JSONParser();
    static public Object parse(Object temp) throws JsonProcessingException, ParseException {
        return jsonParser.parse(objectMapper.writeValueAsString(temp));
    }
}
