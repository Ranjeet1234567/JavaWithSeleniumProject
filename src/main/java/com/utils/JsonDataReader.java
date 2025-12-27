package com.utils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class JsonDataReader {

    private static final ThreadLocal<JsonNode> jsonData = new ThreadLocal<>();
    private static final ObjectMapper mapper = new ObjectMapper();

    private JsonDataReader() {}

    public static void load(String env, String fileName) {

        String filePath = env + "/" + fileName;

        try {
            InputStream input =
                    JsonDataReader.class
                            .getClassLoader()
                            .getResourceAsStream(filePath);

            if (input == null) {
                throw new RuntimeException("JSON file not found: " + filePath);
            }

            jsonData.set(mapper.readTree(input));

        } catch (Exception e) {
            throw new RuntimeException("Failed to load JSON: " + filePath, e);
        }
    }

    public static String get(String key) {
        return jsonData.get().get(key).asText();
    }

    public static void unload() {
        jsonData.remove();
    }
}
