package lafourchette.com.lafourchette;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.widget.Toast;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class SplashScreen extends Activity {

    private Handler handler = new Handler();
    public static   String restaurantID = "14163";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Check if connected
        if (isOnline()) {
            // Get restaurant
            getRestaurant(restaurantID);
            handler.postDelayed(runnable, 3000);
        }
        else {
            // Check if the restaurant ID is save in Database
            Restaurant restaurant = Restaurant.getRestaurantByID(restaurantID);
            if (restaurant == null) {
                Toast.makeText(getApplicationContext(),
                        getResources().getString(R.string.pleaseConnection), Toast.LENGTH_LONG).show();
                finish();
            } else
                handler.postDelayed(runnable, 3000);
        }

    }

    private void getRestaurant(String restaurantId) {
        APILafourchette restaurant = ServiceGenerator.createService(APILafourchette.class);

        Call<Restaurant> call = restaurant.getRestaurant(restaurantId);
        call.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Response<Restaurant> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    // -- //
                } else {
                    Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.pbGetData), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),
                        getResources().getString(R.string.failedToConnect), Toast.LENGTH_SHORT).show();
                System.out.println(t);
            }
        });

    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(myIntent);
            finish();
        }
    };

}
