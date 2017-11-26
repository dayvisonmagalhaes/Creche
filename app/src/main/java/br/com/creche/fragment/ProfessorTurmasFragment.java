package br.com.creche.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import br.com.creche.activity.AlunoActivity;
import br.com.creche.interfacce.IRetrofitCreche;
import br.com.creche.modelo.Aluno;
import br.com.creche.modelo.TipoTurma;
import br.com.creche.Deserializer.TipoTurmaDesc;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import testedelayout.cursoandroid.com.creche.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfessorTurmasFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    //private ArrayList<TipoTurma> listaTipoTurma;
    private ArrayList<String> turmas;
    private int idProfessorLogado = 0;
    public static int idTurmaSelecionada;


    private static final String BASE_URL = "http://192.168.0.115:8080/WebServiceCreche/webresources/Creches/";

    Gson g = new Gson();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(g))
            .build();

    IRetrofitCreche service = retrofit.create(IRetrofitCreche.class);



    public ProfessorTurmasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Recupera o ID da Pessoa (Professor) que logou no sistema.

        //idProfessorLogado = MainActivity.idLogin ;

        //SOMENTE PARA TESTES (AGILIZAR), PARA NÃO FICAR LOGANDO COM E-MAIL E SENHA
        idProfessorLogado = 3;

        turmas = new ArrayList<>();

        new GsonBuilder().registerTypeAdapter(TipoTurma.class, new TipoTurmaDesc()).create();

        final Call<List<TipoTurma>> tipoTurmaCall = service.getTipoTurmaProfessor(idProfessorLogado);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_turmas, container, false);

        //Monta ListView e adapter
        listView = (ListView) view.findViewById(R.id.lv_turmas);
        adapter = new ArrayAdapter<>(
                getActivity(),
                R.layout.lista_turma,
                turmas
        );

        listView.setAdapter( adapter );

        tipoTurmaCall.enqueue(new Callback<List<TipoTurma>>() {
            @Override
            public void onResponse(Call<List<TipoTurma>> call, Response<List<TipoTurma>> response) {

                if (response.isSuccessful()) {

                    List<TipoTurma> tipoTurmasList = response.body();

                    //Limpa lista
                    turmas.clear();

                    if (tipoTurmasList != null) {

                        for (TipoTurma dados : tipoTurmasList) {

                            turmas.add(dados.getNome());

                            //listaTipoTurma.add(dados);

                        }

                    }else{
                        Toast.makeText(getActivity(), "Erro: Não há turmas alocadas para o Professor", Toast.LENGTH_LONG).show();
                    }

                    adapter.notifyDataSetChanged();

                }else {
                    Toast.makeText(getActivity(), "Erro: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<TipoTurma>> call, Throwable t) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getActivity(),AlunoActivity.class);

                //recupera dados a serem passados

                idTurmaSelecionada = i;

                //TipoTurma tipoTurma = listaTipoTurma.get(i);

                String posicaoTurma = String.valueOf( adapterView.getItemAtPosition(i));

                //Enviando dados para a AlunoActivity

                intent.putExtra("posicaoTurma", posicaoTurma);
                //intent.putExtra("posicaoTurma", tipoTurma.getNome());

                startActivity(intent);
            }
        });

        return view;
    }



}
