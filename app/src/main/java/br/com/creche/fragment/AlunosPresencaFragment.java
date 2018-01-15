package br.com.creche.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import br.com.creche.Adapter.AlunosPresencaAdapter;
import br.com.creche.Deserializer.AlunoDesc;
import br.com.creche.Deserializer.AlunoPresencaDesc;
import br.com.creche.interfacce.IRetrofitCreche;
import br.com.creche.modelo.Aluno;
import br.com.creche.modelo.AlunoPresenca;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import testedelayout.cursoandroid.com.creche.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlunosPresencaFragment extends Fragment {


    private ListView listView;
    private ArrayAdapter<AlunoPresenca> adapter;
    //private ArrayList<String> alunos;
    private ArrayList<AlunoPresenca> alunosPresentes;
    private ArrayList<Aluno> alunosFaltantes;
    List<Aluno> listaAlunos;
    private int idTurmaSelecionada = 0;


    private static final String BASE_URL = "http://192.168.0.115:8080/WebServiceCreche/webresources/Creches/";

    Gson g = new Gson();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(g))
            .build();

    IRetrofitCreche service = retrofit.create(IRetrofitCreche.class);

    public AlunosPresencaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Recupera o ID da Pessoa (Professor) que logou no sistema.

        //idProfessorLogado = MainActivity.idLogin ;

        //SOMENTE PARA TESTES (AGILIZAR), PARA NÃO FICAR LOGANDO COM E-MAIL E SENHA
        // idTurmaSelecionada = ProfessorTurmasFragment.idTurmaSelecionada;

        Bundle extras = getActivity().getIntent().getExtras();

        if (extras != null) {
            idTurmaSelecionada = extras.getInt("idTurma");
        }

        alunosPresentes = new ArrayList<>();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alunos_alunos, container, false);

        //Monta ListView e adapter
        listView = (ListView) view.findViewById(R.id.lv_alunos);

        adapter = new AlunosPresencaAdapter(getActivity(), alunosPresentes);

        listView.setAdapter(adapter);

        verificaPresenca();

        return view;
    }

    public void verificaPresenca(){

        new GsonBuilder().registerTypeAdapter(AlunoPresenca.class, new AlunoPresencaDesc()).create();

        final Call<List<AlunoPresenca>> alunosCall = service.getAlunosPresentesId(idTurmaSelecionada);



        alunosCall.enqueue(new Callback<List<AlunoPresenca>>() {
            @Override
            public void onResponse(Call<List<AlunoPresenca>> call, Response<List<AlunoPresenca>> response) {

                if (response.isSuccessful()) {

                    List<AlunoPresenca> alunosList = response.body();

                    //Limpa lista
                    alunosPresentes.clear();

                    //listaAlunos = new ArrayList<>();

                    if (alunosList.size() != 0) {

                        for (AlunoPresenca aluno : alunosList) {

                            alunosPresentes.add(aluno);

                        }

                    } else {
                        Toast.makeText(getActivity(), "Erro: A Presença ainda não foi realizada nesta data", Toast.LENGTH_LONG).show();

                        //lancarFaltas();

                        //new AlunosChamadaFragment();
                    }

                    adapter.notifyDataSetChanged();

                } else {

                    Toast.makeText(getActivity(), "Erro: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<AlunoPresenca>> call, Throwable t) {

            }
        });
    }

    public void lancarFaltas(){

        new GsonBuilder().registerTypeAdapter(Aluno.class, new AlunoDesc()).create();

        final Call<List<Aluno>> alunosCall = service.getListarAlunos(idTurmaSelecionada);

        alunosFaltantes = new ArrayList<>();

        alunosCall.enqueue(new Callback<List<Aluno>>() {
            @Override
            public void onResponse(Call<List<Aluno>> call, Response<List<Aluno>> response) {

                if (response.isSuccessful()) {

                    List<Aluno> alunosList = response.body();

                    //Limpa lista
                    alunosFaltantes.clear();

                    if (alunosList.size() != 0) {

                        for (Aluno aluno : alunosList) {

                            alunosFaltantes.add(aluno);

                        }

                    } else {
                        Toast.makeText(getActivity(), "Erro: Não há ALUNOS alocados para esta Turma", Toast.LENGTH_LONG).show();
                    }

                    adapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(getActivity(), "Erro: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Aluno>> call, Throwable t) {

            }
        });
    }




}
