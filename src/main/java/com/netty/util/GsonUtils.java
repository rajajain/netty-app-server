package com.netty.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * Created by Ashwin on 20/01/16.
 */
public class GsonUtils {

    public static Gson getInstance() {
        return new Gson();
    }

    public static Gson getInstanceWithExclusion() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeHierarchyAdapter(String.class, new GsonStringAdapter());
        return builder.create();
    }

    static class GsonStringAdapter extends TypeAdapter<String> {

        @Override
        public void write(JsonWriter jsonWriter, String value) throws IOException {
            if(StringUtils.isEmpty(value)) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.value(value);
        }

        @Override
        public String read(JsonReader jsonReader) throws IOException {
            if(jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return jsonReader.nextString();
        }
    }

}
