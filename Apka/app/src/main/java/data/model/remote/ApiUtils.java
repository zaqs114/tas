package data.model.remote;

public class ApiUtils {
    private ApiUtils(){}

    public static final String BASE_URL = "https://projekttasy.herokuapp.com/";

    public static APIService getAPIService(){
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
