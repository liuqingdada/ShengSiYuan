package com.android.lib.netcommon.http.cookie;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class CookieJarImpl implements CookieJar {

    private final CookieStore cookieStore;

    public CookieJarImpl(CookieStore cookieStore) {
        if (cookieStore == null) {
            throw new IllegalArgumentException("cookieStore can not be null.");
        }
        this.cookieStore = cookieStore;
    }

    @Override
    public synchronized void saveFromResponse(@NotNull HttpUrl url, @NotNull List<Cookie> cookies) {
        this.cookieStore.add(url, cookies);
    }

    @NotNull
    @Override
    public synchronized List<Cookie> loadForRequest(@NotNull HttpUrl url) {
        return this.cookieStore.get(url);
    }

    public CookieStore getCookieStore() {
        return this.cookieStore;
    }
}
