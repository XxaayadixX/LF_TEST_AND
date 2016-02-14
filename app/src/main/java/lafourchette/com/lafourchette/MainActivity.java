package lafourchette.com.lafourchette;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.restaurant_name)
    TextView restaurant_name;
    @Bind(R.id.restaurant_address)
    TextView restaurant_address;
    @Bind(R.id.restaurant_nb_picture)
    TextView restaurant_nb_picture;
    @Bind(R.id.restaurant_descr)
    TextView restaurant_descr;
    @Bind(R.id.mapView)
    MapView mapView;
    @Bind(R.id.toolbar_image)
    ImageView toolbar_image;
    @Bind(R.id.icon_hearts)
    ImageView icon_hearts;
    @Bind(R.id.nav_view)
    NavigationView nav_view;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer_layout;
    private GoogleMap   googleMap;
    private Bundle      bundle;
    private Restaurant  restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bundle = savedInstanceState;

        // Get restaurant in database
        restaurant = Restaurant.getRestaurantByID(SplashScreen.restaurantID);

        // Initialise toolbar
        initToolbar(toolbar, restaurant);

        // Initialise menu
        initMenu();

        // Display restaurant
        restaurant_name.setText(restaurant.getName());
        restaurant_address.setText(restaurant.getAddress() + ", " + restaurant.getCity());
        restaurant_nb_picture.setText(
                restaurant.getNb_picture() + " " + getResources().getString(R.string.picture));
        restaurant_descr.setText(restaurant.getDescription());
        Picasso.with(getApplicationContext()).load(restaurant.getPicture()).into(toolbar_image);
        if (restaurant.isFavourite())
            Picasso.with(getApplicationContext()).load(R.drawable.icon_hearth_filled).into(icon_hearts);
        else
            Picasso.with(getApplicationContext()).load(R.drawable.icon_hearts).into(icon_hearts);

        // Initialise map
        initMap(savedInstanceState, restaurant);

    }

    private void initMenu() {
        nav_view.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawer_layout.closeDrawers();

                switch (menuItem.getItemId()) {
                    case R.id.navigation_item_1:
                        Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.my_profil), Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation_item_2:
                        Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.my_favorite), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, Favorite.class));
                        return true;
                    case R.id.navigation_item_3:
                        System.exit(1);
                        return true;
                    default:
                        return true;
                }
            }
        });
    }

    private void initToolbar(Toolbar toolbar, Restaurant restaurant) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            CollapsingToolbarLayout collapsingToolbarLayout =
                    (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
            collapsingToolbarLayout.setTitle(restaurant.getName());
            collapsingToolbarLayout.setExpandedTitleColor(
                    ContextCompat.getColor(getBaseContext(), R.color.green));
            collapsingToolbarLayout.setCollapsedTitleTextColor(
                    ContextCompat.getColor(getApplicationContext(), R.color.white));
        }
    }

    private void initMap(Bundle savedInstanceState, final Restaurant restaurant) {
        mapView.onCreate(savedInstanceState);
        mapView.onResume(); // Needed to get the map to display immediately

        googleMap = mapView.getMap();
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        googleMap.setMyLocationEnabled(true);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        try {
            MapsInitializer.initialize(getApplicationContext());
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

        // Add marker in MapView
        googleMap.addMarker(
                new MarkerOptions()
                        .title(restaurant.getName())
                        .position(new LatLng(restaurant.getGps_lat(), restaurant.getGps_long()))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_marker))
        );

        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View view = getLayoutInflater().inflate(
                        R.layout.custom_marker, null);

                TextView resto_name = ((TextView) view.findViewById(R.id.restaurant_name_marker));
                TextView resto_phone = ((TextView) view.findViewById(R.id.restaurant_phone_marker));
                resto_name.setText(restaurant.getName());
                resto_phone.setText("tel: " + restaurant.getPhone());

                return view;
            }

        });

        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                new LatLng(restaurant.getGps_lat(), restaurant.getGps_long()), 15);
        googleMap.animateCamera(cameraUpdate);

    }

    @OnClick(R.id.icon_share)
    public void share() {
        Intent sharing = new Intent(android.content.Intent.ACTION_SEND);
        sharing.setType("text/plain");
        String body = restaurant.getName() +
                " au " + restaurant.getAddress() + " à " + restaurant.getCity();
        sharing.putExtra(
                android.content.Intent.EXTRA_SUBJECT, getResources().getString(R.string.title_share));
        sharing.putExtra(android.content.Intent.EXTRA_TEXT, body);
        startActivity(Intent.createChooser(sharing, getResources().getString(R.string.share_with)));
    }

    @OnClick(R.id.icon_hearts)
    public void favorite() {
        if (restaurant.isFavourite()) {
            restaurant.setFavourite(false);
            Picasso.with(getApplicationContext()).load(R.drawable.icon_hearts).into(icon_hearts);
        } else {
            restaurant.setFavourite(true);
            Picasso.with(getApplicationContext()).load(R.drawable.icon_hearth_filled).into(icon_hearts);
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.add_to_your_favoris), Toast.LENGTH_SHORT).show();
        }
        restaurant.save();
    }

    @OnClick(R.id.img_email)
    public void email() {
        Intent mail = new Intent(android.content.Intent.ACTION_SEND,
                Uri.parse("mailto:" + restaurant.getEmail()));
        mail.setType("text/plain");
        try {
            startActivity(Intent.createChooser(mail, getResources().getString(R.string.title_email)));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.error_no_client_message),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.img_phone)
    public void phone() {
        alertDial(getResources().getString(R.string.contact_restaurant),
                getResources().getString(R.string.contact_restaurant_question));
    }

    private void alertDial(String title, String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title
        alertDialogBuilder.setTitle(title);

        // set dialog message
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(getResources().getString(
                        R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent call = new Intent(Intent.ACTION_CALL,
                                Uri.parse("tel:" + restaurant.getPhone()));
                        try {
                            startActivity(call);
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(getApplicationContext(),
                                    getResources().getString(
                                            R.string.error_call), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton(getResources().getString(
                        R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

}