package data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Part;

//import org.apache.commons.lang3.builder.ToStringBuilder;

public class AddGame {
    public int getGameid() {
        return gameid;
    }

    public void setGameid(int gameid) {
        this.gameid = gameid;
    }
    @SerializedName("gameid")

    private int gameid;
    @SerializedName("icon")

    private RequestBody icon;
    @SerializedName("title")

    private RequestBody title;
    @SerializedName("decription")

    private RequestBody description;
    @SerializedName("launch_date")
    
    private RequestBody launchDate;
    @SerializedName("publisher")
    
    private RequestBody publisher;
    @SerializedName("screen")
    
    private MultipartBody.Part screen;
    @SerializedName("platform")
    
    private RequestBody platform;
    @SerializedName("genre")
    
    private RequestBody genre;



    public RequestBody getIcon() {
        return icon;
    }

    public void setIcon(RequestBody icon) {
        this.icon = icon;
    }

    public RequestBody getTitle() {
        return title;
    }

    public void setTitle(RequestBody title) {
        this.title = title;
    }

    public RequestBody getDescription() {
        return description;
    }

    public void setDescription(RequestBody description) {
        this.description = description;
    }

    public RequestBody getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(RequestBody launchDate) {
        this.launchDate = launchDate;
    }

    public RequestBody getPublisher() {
        return publisher;
    }

    public void setPublisher(RequestBody publisher) {
        this.publisher = publisher;
    }

    public MultipartBody.Part getScreen() {
        return screen;
    }

    public void setScreen(MultipartBody.Part screen) {
        this.screen = screen;
    }

    public RequestBody getPlatform() {
        return platform;
    }

    public void setPlatform(RequestBody platform) {
        this.platform = platform;
    }

    public RequestBody getGenre() {
        return genre;
    }

    public void setGenre(RequestBody genre) {
        this.genre = genre;
    }

//    @Override
//    public String toString() {
//        return new ToStringBuilder(this).append("icon", icon).append("title", title).append("description", description).append("launchDate", launchDate).append("publisher", publisher).append("screen", screen).append("platform", platform).append("genre", genre).toString();
//    }


}