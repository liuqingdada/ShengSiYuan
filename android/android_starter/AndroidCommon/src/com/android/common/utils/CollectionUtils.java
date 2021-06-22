package com.android.common.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("rawtypes")
public final class CollectionUtils {

    private CollectionUtils() {
    }

    public static List avoidNull(List list) {
        return list != null ? list : Collections.EMPTY_LIST;
    }

    public static Set avoidNull(Set set) {
        return set != null ? set : Collections.EMPTY_SET;
    }

    public static Map avoidNull(Map map) {
        return map != null ? map : Collections.EMPTY_MAP;
    }

    public static int getSize(Collection c) {
        return c != null ? c.size() : 0;
    }

    public static <T> int getSize(T[] array) {
        return array != null ? array.length : 0;
    }

    public static int getSize(Map map) {
        return map != null ? map.size() : 0;
    }

    public static int getSize(JSONArray ja) {
        return ja != null ? ja.length() : 0;
    }

    public static int getSize(JSONObject ja) {
        return ja != null ? ja.length() : 0;
    }

    public static <T> T get(List<T> list, int index) {
        if (list == null || index < 0) {
            return null;
        }
        return getSize(list) > index ? list.get(index) : null;
    }

    public static <K, V> V get(Map<K, V> map, K key) {
        if (map == null) {
            return null;
        }
        return map.get(key);
    }

    public static <T> T get(T[] array, int index) {
        if (array == null || index < 0) {
            return null;
        }
        return getSize(array) > index ? array[index] : null;
    }

    public static boolean isEmpty(Collection c) {
        return getSize(c) == 0;
    }

    public static boolean isEmpty(Map map) {
        return getSize(map) == 0;
    }

    public static <T> boolean isEmpty(T[] array) {
        return getSize(array) == 0;
    }

    public static boolean isEmpty(JSONArray ja) {
        return getSize(ja) == 0;
    }

    public static boolean isEmpty(JSONObject ja) {
        return getSize(ja) == 0;
    }

    public static boolean isNotEmpty(Collection c) {
        return !isEmpty(c);
    }

    public static <T> boolean isNotEmpty(T[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    public static boolean isNotEmpty(JSONArray ja) {
        return !isEmpty(ja);
    }

    public static boolean isNotEmpty(JSONObject ja) {
        return !isEmpty(ja);
    }
}
