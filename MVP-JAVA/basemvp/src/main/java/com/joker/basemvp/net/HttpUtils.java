package com.joker.basemvp.net;

import android.text.TextUtils;
import android.util.Log;

import com.joker.basemvp.net.error.ApiException;
import com.joker.basemvp.net.error.MyGsonConverterFactory;
import com.joker.basemvp.utils.Factory;

import java.io.IOException;
import java.util.Map;

import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;

/**
 * @Author joker
 * @Date 2020/7/31-09:36
 */
public class HttpUtils {

    private Retrofit retrofitIO;
    private Retrofit retrofit;

    private HttpUtils() {}
    private static class HttpUtilsHolder {
        private static final HttpUtils INSTANCE = new HttpUtils();
    }

    public static HttpUtils getInstance() {
        return HttpUtilsHolder.INSTANCE;
    }

    public HttpUtils initRetrofitIO(String baseUrl,Class baseNetClass) {
        if (retrofitIO == null) {
            retrofitIO = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .addConverterFactory(MyGsonConverterFactory.create(Factory.getGson(),baseNetClass))
                    .client(okHttpClient(null))
                    .build();
            retrofit = null;
        }
        return this;
    }
    public HttpUtils initRetrofitIO(String baseUrl, Class baseNetClass, Map<String,String> headers) {

            retrofitIO = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .addConverterFactory(MyGsonConverterFactory.create(Factory.getGson(),baseNetClass))
                    .client(okHttpClient(headers))
                    .build();
            retrofit = null;
        return this;
    }
    public HttpUtils initRetrofit(String baseUrl,Class baseNetClass) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .addConverterFactory(MyGsonConverterFactory.create(Factory.getGson(),baseNetClass))
                    .client(okHttpClient(null))
                    .build();
            retrofitIO = null;
        }
        return this;
    }

    public HttpUtils initRetrofit(String baseUrl,Class baseNetClass,Map<String,String> headers) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .addConverterFactory(MyGsonConverterFactory.create(Factory.getGson(),baseNetClass))
                    .client(okHttpClient(headers))
                    .build();
            retrofitIO = null;
        return this;
    }

    public <T> T create(Class<T> service) {
        if (retrofit !=null || retrofitIO!=null ) {
            if (retrofit == null) {
                return retrofitIO.create(service);
            } else {
                return retrofit.create(service);
            }
        } else {
            throw new ApiException("0x10000","未初始化Retrofit");
        }
    }


    /**
     * 可以打log的okHttpClient
     * @return Client
     */

    private OkHttpClient okHttpClient(Map<String,String> headers) {
        final String tag = "MVPRetrofit";

        OkHttpClient.Builder client = new OkHttpClient
                .Builder();
        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request original = chain.request();

                //       String timestamp = String.valueOf(System.currentTimeMillis());
                //     String content = token + timestamp;
//                String sign = EncryptUtils.encryptMD5ToString(content).toLowerCase();
                if (headers !=null) {
                    original = original.newBuilder()
                           .headers(Headers.of(headers))
                            .build();
                }
                /*Request request = original.newBuilder()
                        .header("token", token)
                        .header("source", "")
                        .header("ver", "")
                        .header("eqid",GlobalContants.eqid)
//                        .header("sign", sign)
                        .build();*/
                logForRequest(original, tag);
                Response response = chain.proceed(original);

                return logForResponse(response,tag);
            }
        });

        return client.build();
    }

    private Response logForResponse(Response response, String tag) {
        try {
            Log.e(tag, "---------------------response log start---------------------");
            Response.Builder builder = response.newBuilder();
            Response clone = builder.build();
            Log.e(tag, "url : " + clone.request().url());
            Log.e(tag, "code : " + clone.code());
            Log.e(tag, "protocol : " + clone.protocol());
            Log.e(tag, "Cookie : " + clone.headers("Set-Cookie"));
            if (!TextUtils.isEmpty(clone.message())) Log.e(tag, "message : " + clone.message());

            if (true) {
                ResponseBody body = clone.body();
                if (body != null) {
                    MediaType mediaType = body.contentType();
                    if (mediaType != null) {
                        Log.e(tag, "contentType : " + mediaType.toString());
                        if (isText(mediaType)) {
                            String resp = body.string();
                            Log.e(tag, "content : " + resp);
                            body = ResponseBody.create(mediaType, resp);
                            return response.newBuilder().body(body).build();
                        } else {
                            Log.e(tag, "content : " + " maybe [file part] , too large too print , ignored!");
                        }
                    }
                }
            }
        } catch (Exception e) {
            Log.e(tag, e.getMessage());
        } finally {
            Log.e(tag, "---------------------response log end-----------------------");
        }

        return response;
    }

    private void logForRequest(Request request, String tag) {
        try {
            String url = request.url().toString();
            Headers headers = request.headers();
            Log.e(tag, "---------------------request log start---------------------");
            Log.e(tag, "method : " + request.method());
            Log.e(tag, "url : " + url);

            if (headers != null && headers.size() > 0) {
                Log.e(tag, "headers : \n");
                Log.e(tag, headers.toString());
            }
            RequestBody requestBody = request.body();
            if (requestBody != null) {
                MediaType mediaType = requestBody.contentType();
                if (mediaType != null) {
                    Log.e(tag, "contentType : " + mediaType.toString());
                    if (isText(mediaType)) {
                        Log.e(tag, "content : " + bodyToString(request));
                    } else {
                        Log.e(tag, "content : " + " maybe [file part] , too large too print , ignored!");
                    }
                }
            }

        }catch (Exception e) {
            Log.e(tag,e.getMessage());
        } finally {
            Log.e(tag, "---------------------request log end-----------------------");
        }

    }

    private boolean isText(MediaType mediaType) {
        if (mediaType.type() != null && mediaType.type().equals("text")) {
            return true;
        }
        if (mediaType.subtype() != null) {
            if (mediaType.toString().equals("application/x-www-form-urlencoded") ||
                    mediaType.subtype().equals("json") ||
                    mediaType.subtype().equals("xml") ||
                    mediaType.subtype().equals("html") ||
                    mediaType.subtype().equals("webviewhtml")) //
                return true;
        }
        return false;
    }


    @SuppressWarnings("ConstantConditions")
    private String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "something error when show requestBody.";
        }
    }




}
