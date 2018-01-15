package br.com.creche.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import br.com.creche.Adapter.AlunosAlunosAdapter;
import br.com.creche.Adapter.AlunosChamadaAdapter;
import br.com.creche.Deserializer.AlunoDesc;
import br.com.creche.activity.AlunoActivity;
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
public class AlunosChamadaFragment extends Fragment {

    private ArrayList<String> itensSelecionados;
    private Button btnSalvarChamada;
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

    public AlunosChamadaFragment() {
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


        alunos = new ArrayList<>();

        new GsonBuilder().registerTypeAdapter(Aluno.class, new AlunoDesc()).create();


        final Call<List<Aluno>> alunosCall = service.getListarAlunos(idTurmaSelecionada);

//        btnSalvarChamada = listView.findViewById(R.id.btnSalvarChamada);

        // Inflate the layout for this fragment

        //View view = inflater.inflate(R.layout.fragment_alunos_alunos, container, false);
        View view = inflater.inflate(R.layout.fragment_alunos_chamada, container, false);

        //Monta ListView e adapter

        //listView = (ListView) view.findViewById(R.id.lv_alunos);
        listView = (ListView) view.findViewById(R.id.lv_alunos_chamada);

        //2ª configuração
        //listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        //vincula botão Salvar da ListView


        adapter = new AlunosChamadaAdapter(getActivity(), alunos);

        listView.setAdapter(adapter);

        alunosCall.enqueue(new Callback<List<Aluno>>() {
            @Override
            public void onResponse(Call<List<Aluno>> call, Response<List<Aluno>> response) {

                if (response.isSuccessful()) {

                    List<Aluno> alunosList = response.body();

                    //Limpa lista
                    alunos.clear();

                    listaAlunos = new ArrayList<>();

                    if (alunosList.size() != 0) {

                        for (Aluno aluno : alunosList) {

                            alunos.add(aluno);

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

        //listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                adapterView.setBackgroundColor();

                Aluno aluno = alunos.get(i);

                Toast.makeText(getActivity(),"Clicou em: "+ aluno.getNome() + " ID: "+ aluno.getPessoaId(),Toast.LENGTH_LONG).show();


                /*String itemSelecionado = ((TextView) view).getText().toString();

                if (itensSelecionados.contains(itemSelecionado)){

                    itensSelecionados.remove(itemSelecionado);
                }else{
                    itensSelecionados.add(itemSelecionado);*//**//*

                    Toast.makeText(getActivity(),itemSelecionado,Toast.LENGTH_LONG).show();
                }



                Intent intent = new Intent(getActivity(), AlunoActivity.class);

                Aluno aluno = alunos.get(i);

                startActivity(intent);
*/
            }
        });



       /* btnSalvarChamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String meusItens = "";

                for (String item:itensSelecionados){
                    if (meusItens == ""){
                        meusItens = item;
                    }else{
                        meusItens += "/" + item;
                    }
                    Toast.makeText(getActivity(),meusItens,Toast.LENGTH_LONG).show();

                }

            }
        });*/

        return view;


    }

}
