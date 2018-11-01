package data.model.remote;
import data.model.Post;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Multipart;
import retrofit2.http.Part;

public interface APIService {
    @POST("/register")
    @Multipart
    Call<Post> savePost(@Part("login") String login,
                        @Part("password") String password);
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