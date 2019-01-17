package data.model.remote;

import java.util.List;

import data.model.AddGame;
import data.model.Games;
import data.model.GamesList;
import data.model.Post;


import data.model.PostLogin;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Multipart;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface APIService {
    @POST("/register")
    @Multipart
    Call<Post> savePost(@Part("login") String login,
                        @Part("password") String password);


    @POST("/addgame")
    @Multipart
    Call<AddGame> addgame(@Part("title") String dgametitle,
                          @Part("description") String dgamedescription,
                          @Part("launch_date") String dgamelaunchdate,
                          @Part("publisher") String dgamepublisher,
                          @Part("platform") String dgameplatform,
                          @Part("genre") String dgamegenre,
                          @Part MultipartBody.Part dgamescreen);


    @POST("/login")
    @FormUrlEncoded
    Call<PostLogin> postLogin(@Field("login") String login,
                              @Field("password") String password);

    @GET("/games")
    Call<List<Games>> getGamesData();

    @GET("games/{gameid}")
    Call<Games> getGameDetail(@Path("gameid") String gameid);

    @GET("/loggedUsername")
    Call<ResponseBody> getLoggedUsername();



//    @GET("/loggedUsername")
//    Call<User> getLoggedUser();
//
//    @GET("/users")
//    Call<ResponseBody> getLoggedUsers();
//
//    @GET("/logout")
//    Call<ResponseBody> logout();
    
}

//binary (non-alphanumeric) data (or a significantly sized payload) to transmit, use multipart. Otherwise, use urlencoded

/*
public interface APIService {
    @POST("/register")
    @FormUrlEncoded
    Call<Post> savePost(@Field("login") String login,
                        @Field"password") String password);
}
*/