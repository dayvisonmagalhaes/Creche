package br.com.creche.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import modelo.IRetrofitCreche;
import modelo.Pessoa;
import modelo.PessoaDesc;
import modelo.TipoTurma;
import modelo.TipoTurmaDesc;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import testedelayout.cursoandroid.com.creche.R;

public class ProfessorActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<String> turmas;

    private static final String BASE_URL = "http://192.168.0.115:8080/WebServiceCreche/webresources/Creches/";

    Gson g = new Gson();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(g))
            .build();

    IRetrofitCreche service = retrofit.create(IRetrofitCreche.class);



    public ProfessorActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);

        Intent intent = getIntent();

        //Recupera o ID da Pessoa que foi passada atavés da Activity Main, caso não encontrado o valor default (-1) será atraibuído.
        int id = intent.getIntExtra("id",-1);

        turmas = new ArrayList<>();

        new GsonBuilder().registerTypeAdapter(TipoTurma.class, new TipoTurmaDesc()).create();

        final Call<List<TipoTurma>> tipoTurmaCall = service.getTipoTurmaProfessor(id);

        tipoTurmaCall.enqueue(new Callback<List<TipoTurma>>() {
            @Override
            public void onResponse(Call<List<TipoTurma>> call, Response<List<TipoTurma>> response) {

                if (response.isSuccessful()) {

                    List<TipoTurma> tipoTurmasList = response.body();

                    for (TipoTurma tipoTurma : tipoTurmasList) {
                        turmas.add(tipoTurma.getNome());
                    }

                }else {
                    Toast.makeText(getApplicationContext(), "Erro: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<TipoTurma>> call, Throwable t) {

            }
        });






        //turmas.add("Pré-01");
        //turmas.add("Pré-02");
        //turmas.add("Pré-03");

        //Monta Listview e adapter
        listView = (ListView) findViewById(R.id.lv_turmas);

        adapter = new ArrayAdapter(
                getApplicationContext(),
                R.layout.lista_turma,
                turmas

        );
        listView.setAdapter(adapter);

        //Recuperar turmas para o professor



    }
}
