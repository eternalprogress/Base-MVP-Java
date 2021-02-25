package com.joker.basemvp.net.error;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.joker.basemvp.net.bean.BaseNetBean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by @ZhangBo
 *
 */

public class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final Gson mGson;
    private final TypeAdapter<T> adapter;
    private Class baseNetClass;

    public MyGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter,Class baseNetClass) {
        mGson = gson;
        this.adapter = adapter;
        this.baseNetClass = baseNetClass;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();

        BaseNetBean re = (BaseNetBean) mGson.fromJson(response, baseNetClass);
        //关注的重点，自定义响应码中非000000的情况，一律抛出ApiException异常。
        //这样，我们就成功的将该异常交给onError()去处理了。
        if (!re.isSuccess()) {
            value.close();
            throw new ApiException(re.getStatus(), re.getMsg());
        }
        response = mGson.toJson(re.getData());
        MediaType mediaType = value.contentType();
        Charset charset = mediaType != null ? mediaType.charset(UTF_8) : UTF_8;
        ByteArrayInputStream bis = new ByteArrayInputStream(response.getBytes());
        InputStreamReader reader = new InputStreamReader(bis,charset);
        JsonReader jsonReader = mGson.newJsonReader(reader);
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }

    }
}
