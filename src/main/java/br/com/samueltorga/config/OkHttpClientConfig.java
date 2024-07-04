package br.com.samueltorga.config;

import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;
import okhttp3.OkHttpClient;

@Factory
public class OkHttpClientConfig {

    @Singleton
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

}
