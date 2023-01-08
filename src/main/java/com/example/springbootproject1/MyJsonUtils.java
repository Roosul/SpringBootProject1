package com.example.springbootproject1;

import com.google.gson.JsonObject;
import org.springframework.http.ResponseEntity;

public class MyJsonUtils {

    public static String getString(JsonObject jo, String key) {
        try {
            return jo.get(key).getAsString();
        } catch (Exception e) {
            return null;
        }

    }
    public static int getInteger(JsonObject jo, String key) {
        try {
            return jo.get(key).getAsInt();
        } catch (Exception e) {
            return 0;
        }

    }

}
