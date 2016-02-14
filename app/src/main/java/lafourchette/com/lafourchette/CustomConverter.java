package lafourchette.com.lafourchette;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/**
 * Created by Ali on 10/02/2016.
 */

public class CustomConverter implements TypeAdapterFactory {

    public <T> TypeAdapter<T> create(Gson gson, final TypeToken<T> type) {

        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);

        return new TypeAdapter<T>() {

            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            public T read(JsonReader in) throws IOException {

                JsonElement jsonElement = elementAdapter.read(in);
                if (jsonElement.isJsonObject()) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject()
                            .get("data").getAsJsonObject();

                    if (jsonObject != null) {
                        Restaurant resto = Restaurant.getRestaurantByID(SplashScreen.restaurantID);
                        if (resto == null) {
                            Restaurant restaurant = new Restaurant();
                            restaurant.setId_restaurant(jsonObject.get("id_restaurant").getAsString());
                            restaurant.setName(jsonObject.get("name").getAsString());
                            restaurant.setGps_lat(jsonObject.get("gps_lat").getAsDouble());
                            restaurant.setGps_long(jsonObject.get("gps_long").getAsDouble());
                            restaurant.setAddress(jsonObject.get("address").getAsString());
                            restaurant.setCity(jsonObject.get("city").getAsString());
                            restaurant.setPhone(jsonObject.get("phone").getAsString());
                            restaurant.setEmail(jsonObject.get("email").getAsString());
                            restaurant.setNb_picture(jsonObject.get("pics_diaporama").getAsJsonArray().size());
                            restaurant.setDescription(jsonObject.get("description").getAsString());
                            restaurant.setPicture(jsonObject.get("pics_main").getAsJsonObject().get("612x344").getAsString());
                            restaurant.save(); // Save restaurant in database
                        } else {
                            resto.setId_restaurant(jsonObject.get("id_restaurant").getAsString());
                            resto.setName(jsonObject.get("name").getAsString());
                            resto.setGps_lat(jsonObject.get("gps_lat").getAsDouble());
                            resto.setGps_long(jsonObject.get("gps_long").getAsDouble());
                            resto.setAddress(jsonObject.get("address").getAsString());
                            resto.setCity(jsonObject.get("city").getAsString());
                            resto.setPhone(jsonObject.get("phone").getAsString());
                            resto.setEmail(jsonObject.get("email").getAsString());
                            resto.setNb_picture(jsonObject.get("pics_diaporama").getAsJsonArray().size());
                            resto.setDescription(jsonObject.get("description").getAsString());
                            resto.setPicture(jsonObject.get("pics_main").getAsJsonObject().get("612x344").getAsString());
                            resto.save(); // Update restaurant in database
                        }
                    }

                }

                return delegate.fromJsonTree(jsonElement);
            }
        }.nullSafe();
    }
}
