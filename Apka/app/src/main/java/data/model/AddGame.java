package data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    private String icon;
    @SerializedName("title")

    private String title;
    @SerializedName("decription")

    private String description;
    @SerializedName("launch_date")
    
    private String launchDate;
    @SerializedName("publisher")
    
    private String publisher;
    @SerializedName("screen")
    
    private String screen;
    @SerializedName("platform")
    
    private String platform;
    @SerializedName("genre")
    
    private String genre;



    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

//    @Override
//    public String toString() {
//        return new ToStringBuilder(this).append("icon", icon).append("title", title).append("description", description).append("launchDate", launchDate).append("publisher", publisher).append("screen", screen).append("platform", platform).append("genre", genre).toString();
//    }


}