package data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import okhttp3.RequestBody;

public class Post {

        @SerializedName("login")
        @Expose
        private RequestBody login;
        @SerializedName("password")
        @Expose
        private RequestBody password;
        @SerializedName("avatar")
        @Expose
        private String avatar;

        public RequestBody getLogin() {
            return login;
        }

        public void setLogin(RequestBody login) {
            this.login = login;
        }

        public RequestBody getPassword() {
            return password;
        }

        public void setPassword(RequestBody password) {
            this.password = password;
        }

        public String getAvatar() {
        return avatar;
    }

        public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


//    @Override
//    public String toString() {
//        return "Post{" +
//                "login='" + login + '\'' +
//                ", password='" + password + '\'' +
//                '}';
//    }
}
