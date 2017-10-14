package br.com.creche.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import modelo.IRetrofitCreche;
import modelo.Pessoa;
import modelo.PessoaDesc;
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

        turmas = new ArrayList<>();
        new GsonBuilder().registerTypeAdapter(Pessoa.class, new PessoaDesc()).create();



        turmas.add("Pré-01");
        turmas.add("Pré-02");
        turmas.add("Pré-03");

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
