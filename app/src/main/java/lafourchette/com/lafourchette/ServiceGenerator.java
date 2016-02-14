package lafourchette.com.lafourchette;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Ali on 10/02/2016.
 */

public class ServiceGenerator {

    public static final String API_BASE_URL = "http://api.lafourchette.com";

    public static Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(new CustomConverter())
            .create();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson));

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(new OkHttpClient()).build();
        return retrofit.create(serviceClass);
    }
}