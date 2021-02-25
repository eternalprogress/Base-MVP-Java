package com.joker.basemvp.net.error;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.joker.basemvp.net.bean.BaseNetBean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;


/**
 * Created by @ZhangBo
 *
 */

public class MyGsonConverterFactory extends Converter.Factory {
    private final Gson gson;
    private Class baseNetClass;

    private MyGsonConverterFactory(Gson gson,Class baseNetClass) {
        this.baseNetClass = baseNetClass;
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    public static MyGsonConverterFactory create() {
        return create(new Gson(),BaseNetBean.class);
    }

    public static MyGsonConverterFactory create(Gson gson,Class baseNetClass) {
        return new MyGsonConverterFactory(gson,baseNetClass);
    }


    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new MyGsonResponseBodyConverter<>(gson, adapter, baseNetClass);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new MyGsonRequestBodyConverter<>(gson, adapter);
    }
}