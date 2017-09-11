package modelo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by u6390869 on 10/09/2017.
 */

public interface IRetrofitCreche {

    @GET("get/estados")
    Call <List<Estado>> getEstados();

    /*(@Path("estados") String estados);
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();*/
}