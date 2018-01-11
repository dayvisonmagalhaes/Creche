package br.com.creche.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import br.com.creche.Adapter.AlunosAlunosAdapter;
import br.com.creche.Adapter.AlunosPresencaAdapter;
import br.com.creche.Deserializer.AlunoDesc;
import br.com.creche.interfacce.IRetrofitCreche;
import br.com.creche.modelo.Aluno;
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
    private ArrayAdapter<Aluno> adapter;
    //private ArrayList<String> alunos;
    private ArrayList<Aluno> alunos;
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

        if (extras != null){
            idTurmaSelecionada = extras.getInt("idTurma");
        }


        alunos = new ArrayList<>();

        new GsonBuilder().registerTypeAdapter(Aluno.class, new AlunoDesc()).create();


        final Call<List<Aluno>> alunosCall = service.getListarAlunos(idTurmaSelecionada);


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alunos_presenca, container, false);

        //Monta ListView e adapter
        listView = (ListView) view.findViewById(R.id.lv_alunos);

        adapter = new AlunosPresencaAdapter(getActivity(), alunos);

        listView.setAdapter(adapter);

        alunosCall.enqueue(new Callback<List<Aluno>>() {
            @Override
            public void onResponse(Call<List<Aluno>> call, Response<List<Aluno>> response) {

                if (response.isSuccessful()) {

                    List<Aluno> alunosList = response.body();

                    //Limpa lista
                    alunos.clear();

                    listaAlunos = new ArrayList<>();

                    if (alunosList != null) {

                        for (Aluno aluno : alunosList) {


                            // Aluno item = new Aluno();
                            //item.setPessoaId(aluno.getPessoaId());
                            //item.setNome(aluno.getNome());

                            //listaAlunos.add(item);
                            //alunos.add(aluno.getNome());
                            alunos.add(aluno);

                        }

                    } else if (alunos.isEmpty()){
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
        return view;
    }

}
