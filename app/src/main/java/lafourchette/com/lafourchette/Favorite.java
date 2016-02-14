package lafourchette.com.lafourchette;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Favorite extends AppCompatActivity {

    private List<Restaurant>    restaurantList;
    private RVAdapter           adapter;
    private RecyclerView        rv;
    private LinearLayoutManager llm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        // Get all favorite restaurant
        restaurantList = new ArrayList<>();
        restaurantList = Restaurant.getFavoriteRestaurant();

        rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        adapter = new RVAdapter(getApplication(), restaurantList);
        rv.setAdapter(adapter);
    }

}
