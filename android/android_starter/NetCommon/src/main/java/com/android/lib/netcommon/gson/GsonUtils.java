package com.android.lib.netcommon.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Map;

public class GsonUtils {
    private static Gson gson;

    public synchronized static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .serializeNulls()
                    .registerTypeAdapterFactory(new NullStringAdapterFactory())
                    .registerTypeAdapterFactory(new NullMultiDateAdapterFactory())
                    .registerTypeAdapterFactory(new NullArrayTypeAdapterFactory())
                    .registerTypeAdapterFactory(new NullCollectionTypeAdapterFactory())
                    .create();
        }
        return gson;
    }

    public static String toJson(Object obj) {
        return getGson().toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return getGson().fromJson(json, clazz);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return getGson().fromJson(json, typeOfT);
    }

    public static <T> T fromJson(Reader reader, Type typeOfT) {
        return getGson().fromJson(reader, typeOfT);
    }

    public static <T> T fromMap(Map<String, Object> map, Class<T> type) {
        return getGson().fromJson(getGson().toJson(map), type);
    }

    public static boolean validate(String jsonStr) {
        JsonElement jsonElement;
        try {
            jsonElement = JsonParser.parseString(jsonStr);
        } catch (Exception e) {
            return false;
        }
        if (jsonElement == null) {
            return false;
        }
        return jsonElement.isJsonObject();
    }

    private GsonUtils() {
        throw new AssertionError("no instance");
    }
}
