package data.model.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import okhttp3.RequestBody;

public class Review {

    @SerializedName("title")
    @Expose
    private RequestBody title;
    @SerializedName("content")
    @Expose
    private RequestBody content;
    @SerializedName("userID")
    @Expose
    private RequestBody userID;
    @SerializedName("gameID")
    @Expose
    private Integer gameID;
    @SerializedName("rate")
    @Expose
    private Integer rate;

    public RequestBody getTitle() {
        return title;
    }

    public void setTitle(RequestBody title) {
        this.title = title;
    }

    public RequestBody getContent() {
        return content;
    }

    public void setContent(RequestBody content) {
        this.content = content;
    }

    public RequestBody getUserID() {
        return userID;
    }

    public void setUserID(RequestBody userID) {
        this.userID = userID;
    }

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

}

