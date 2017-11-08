package almundotest.com.pruebahoteles.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by cc_ri on 06/11/2017.
 */

public class Hotel {

    @SerializedName("name")
    public String name;
    @SerializedName("stars")
    public int stars;
    @SerializedName("urlimages")
    public List<String> urlimages;
    @SerializedName("price")
    public String price;
    @SerializedName("description")
    public String description;
    @SerializedName("address")
    public String address;
    @SerializedName("country")
    public String country;
    @SerializedName("city")
    public String city;
    @SerializedName("state")
    public String state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public List<String> getUrlimages() {
        return urlimages;
    }

    public void setUrlimages(List<String> urlimages) {
        this.urlimages = urlimages;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
