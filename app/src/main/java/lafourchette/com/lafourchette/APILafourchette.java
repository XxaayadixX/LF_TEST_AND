package lafourchette.com.lafourchette;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Ali on 10/02/2016.
 */

public interface APILafourchette {

    @GET("/api?key=IPHONEPRODEDCRFV&method=restaurant_get_info&")
    Call<Restaurant>    getRestaurant(@Query("id_restaurant") String id);
}