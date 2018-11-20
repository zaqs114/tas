package data.model.remote;

import data.model.Post;

import data.model.PostLogin;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Multipart;
import retrofit2.http.Part;

public interface APIService {
    @POST("/register")
    @Multipart
    Call<Post> savePost(@Part("login") String login,
                        @Part("password") String password);

    @POST("/login")
    @FormUrlEncoded
    Call<PostLogin> postLogin(@Field("login") String login,
                              @Field("password") String password);

    @GET("/loggedUsername")
    Call<ResponseBody> getLoggedUser();

    @GET("/users")
    Call<ResponseBody> getLoggedUsers();

    @GET("/logout")
    Call<ResponseBody> logout();
    
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