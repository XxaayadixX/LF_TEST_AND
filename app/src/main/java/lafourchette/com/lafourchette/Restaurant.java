package lafourchette.com.lafourchette;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Ali on 10/02/2016.
 */

@Table(name = "Restaurant")
public class Restaurant extends Model {

    @Column(name = "restaurantId")
    private String  id_restaurant;

    @Column(name = "name")
    private String  name;

    @Column(name = "speciality")
    private String  speciality;

    @Column(name = "email")
    private String  email;

    @Column(name = "result")
    private String  result;

    @Column(name = "gps_lat")
    private double  gps_lat;

    @Column(name = "gps_long")
    private double  gps_long;

    @Column(name = "address")
    private String  address;

    @Column(name = "zipcode")
    private String  zipcode;

    @Column(name = "city")
    private String  city;

    @Column(name = "phone")
    private String  phone;

    @Column(name = "pictureNb")
    private int     nb_picture;

    @Column(name = "picture")
    private String  picture;

    @Column(name = "description")
    private String  description;

    @Column(name = "favourite")
    private boolean favourite;

    public Restaurant() {}

    public static Restaurant getRestaurantByID(String restaurantId) {
        return new Select()
                .from(Restaurant.class)
                .where("restaurantId = ?", restaurantId)
                .executeSingle();
    }

    public static List<Restaurant> getFavoriteRestaurant() {
        return new Select()
                .from(Restaurant.class)
                .where("favourite = ?", true)
                .execute();
    }

    public String getId_restaurant() {
        return id_restaurant;
    }

    public void setId_restaurant(String id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public double getGps_lat() {
        return gps_lat;
    }

    public void setGps_lat(double gps_lat) {
        this.gps_lat = gps_lat;
    }

    public double getGps_long() {
        return gps_long;
    }

    public void setGps_long(double gps_long) {
        this.gps_long = gps_long;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getNb_picture() {
        return nb_picture;
    }

    public void setNb_picture(int nb_picture) {
        this.nb_picture = nb_picture;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

}
