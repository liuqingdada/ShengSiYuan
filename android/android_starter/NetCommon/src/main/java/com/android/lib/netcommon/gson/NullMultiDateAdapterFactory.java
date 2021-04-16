package com.android.lib.netcommon.gson;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NullMultiDateAdapterFactory implements TypeAdapterFactory {
    private static final String[] DEFAULT_FORMATS = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd"};
    private final List<DateFormat> dateFormats;

    public NullMultiDateAdapterFactory() {
        this(DEFAULT_FORMATS);
    }

    public NullMultiDateAdapterFactory(String... stringDateFormats) {
        int size = stringDateFormats.length;
        dateFormats = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            dateFormats.add(new SimpleDateFormat(stringDateFormats[i], Locale.getDefault()));
        }
    }

    public NullMultiDateAdapterFactory(List<DateFormat> dateFormats) {
        this.dateFormats = new ArrayList<>(dateFormats);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<? super T> rawType = type.getRawType();
        if (!Date.class.isAssignableFrom(rawType)) {
            return null;
        }
        return (TypeAdapter<T>) new NullMultiDateAdapter(dateFormats);
    }


    private static class NullMultiDateAdapter extends TypeAdapter<Date> {
        private final List<DateFormat> dateFormats;

        private NullMultiDateAdapter(List<DateFormat> dateFormats) {
            this.dateFormats = dateFormats;
        }

        @Override
        public void write(JsonWriter out, Date value) throws IOException {
            synchronized (dateFormats) {
                if (value == null) {
                    out.nullValue();
                } else {
                    String dateString = dateFormats.get(0).format(value);
                    out.value(dateString);
                }
            }
        }

        @Override
        public synchronized Date read(JsonReader in) throws IOException {
            JsonToken token = in.peek();
            if (token == JsonToken.NULL) {
                in.nextNull();
                return new Date();
            }
            if (token != JsonToken.STRING) {
                throw new JsonParseException("The date should be a string value");
            }
            String dateString = in.nextString();
            Date result = null;
            for (int i = 0, size = dateFormats.size(); result == null && i < size; ++i) {
                try {
                    result = dateFormats.get(i).parse(dateString);
                } catch (ParseException ignore) {
                }
            }
            if (result == null) {
                throw new JsonParseException("Bad date format, the resource = " + dateString);
            }
            return result;
        }
    }
}
