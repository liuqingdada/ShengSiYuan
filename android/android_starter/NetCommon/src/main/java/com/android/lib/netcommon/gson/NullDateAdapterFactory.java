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
import java.util.Date;
import java.util.Locale;

public class NullDateAdapterFactory implements TypeAdapterFactory {
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final String format;

    public NullDateAdapterFactory() {
        this(DEFAULT_FORMAT);
    }

    public NullDateAdapterFactory(String format) {
        this.format = format;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<? super T> rawType = type.getRawType();
        if (!Date.class.isAssignableFrom(rawType)) {
            return null;
        }
        return (TypeAdapter<T>) new NullDateAdapter(format);
    }


    private static class NullDateAdapter extends TypeAdapter<Date> {
        private final DateFormat dateFormatter;

        private NullDateAdapter(String format) {
            dateFormatter = new SimpleDateFormat(format, Locale.getDefault());
        }

        @Override
        public void write(JsonWriter out, Date value) throws IOException {
            synchronized (dateFormatter) {
                if (value == null) {
                    out.nullValue();
                } else {
                    String dateString = dateFormatter.format(value);
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
            try {
                return dateFormatter.parse(in.nextString());
            } catch (ParseException e) {
                throw new JsonParseException(e);
            }
        }
    }
}
