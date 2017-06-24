package org.eneko.test.unit.cart.utils;

import jdk.nashorn.internal.parser.JSONParser;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

/**
 * Created by eneko on 20/06/17.
 */
public class JsonValidator {

    private final Schema schema;

    public JsonValidator(String schemaFileName){
        try {
            schema = initSchema(schemaFileName);
        } catch (IOException e) {
            throw new RuntimeException("Could not create schema");
        }
    }

    private Schema initSchema(String jsonSchemaFile) throws IOException {
        try (InputStream inputStream = getClass().getResourceAsStream(jsonSchemaFile)) {
            JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
            return SchemaLoader.load(rawSchema);
        }
    }


    public Object readFromFile(String jsonFilename) throws IOException {
        try (InputStream is = getClass().getResourceAsStream(jsonFilename)) {
            String jsonTxt = IOUtils.toString(is);
            return jsonTxt.trim().startsWith("[")?new JSONArray(jsonTxt):new JSONObject(jsonTxt);
        }
    }

    public boolean isValid(String jsonFile) throws IOException {
        schema.validate(readFromFile(jsonFile));
        return true;
    }

}
