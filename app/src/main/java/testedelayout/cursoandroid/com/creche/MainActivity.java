package testedelayout.cursoandroid.com.creche;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import modelo.Estado;
import modelo.EstadoDesc;
import modelo.IRetrofitCreche;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://192.168.0.115:8080/WebService/webresources/Creche/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String teste;

        Gson g = new GsonBuilder().registerTypeAdapter(Estado.class,new EstadoDesc()).create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        IRetrofitCreche service = retrofit.create(IRetrofitCreche.class);
        final Call<List<Estado>> estados = service.getEstados();

        estados.enqueue(new Callback<List<Estado>>() {
            @Override
            public void onResponse(Call<List<Estado>> call, Response<List<Estado>> response) {
                if (response.isSuccessful()){
                    List<Estado> estadoList = response.body();

                    for (Estado estado: estadoList){
                        Log.i("ESTADO", estado.getEstadoId()+"----"+estado.getEstadoNome());
                        Log.i("ESTADO","-------------------------------");
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "Erro: "+response.code(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Estado>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erros: "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
