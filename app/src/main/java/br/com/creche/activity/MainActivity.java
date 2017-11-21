package br.com.creche.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.creche.modelo.IRetrofitCreche;
import br.com.creche.modelo.Pessoa;
import br.com.creche.modelo.PessoaDesc;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import testedelayout.cursoandroid.com.creche.R;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://192.168.0.115:8080/WebServiceCreche/webresources/Creches/";

    EditText editTextEmail, editTextSenha;
    Button btnLogar;
    String email = "";
    String senha = "";
    public Pessoa pessoaLogin = new Pessoa();
    public int idLogin;

            Gson g = new Gson();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(g))
            .build();

    IRetrofitCreche service = retrofit.create(IRetrofitCreche.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = (EditText) findViewById(R.id.editText_EmailId);
        editTextSenha = (EditText) findViewById(R.id.editText_SenhaId);
        btnLogar = (Button) findViewById(R.id.btnLogarId);


        //CÓDIGO ESTADOS



       /*
        final Call<List<Estado>> estados = service.getEstados();

        estados.enqueue(new Callback<List<Estado>>() {
            @Override
            public void onResponse(Call<List<Estado>> call, Response<List<Estado>> response) {
                if (response.isSuccessful()) {
                    List<Estado> estadoList = response.body();

                    for (Estado estado : estadoList) {
                        Log.i("ESTADO", estado.getId() + "----" + estado.getNome());
                        Log.i("ESTADO", "-------------------------------");
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Erro: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Estado>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erros: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });*/


        //CÓDIGO PESSOA - LISTAR



        /*final Call<List<Pessoa>> pessoas = service.getPessoas();

        pessoas.enqueue(new Callback<List<Pessoa>>() {
            @Override
            public void onResponse(Call<List<Pessoa>> call, Response<List<Pessoa>> response) {
                if (response.isSuccessful()) {
                    List<Pessoa> pessoaList = response.body();

                    for (Pessoa pessoa : pessoaList) {
                        Log.i("PESSOA", pessoa.getId() + "----" + pessoa.getNome());
                        Log.i("PESSOA", "-------------------------------");
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Erro: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Pessoa>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erros: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });*/


        //CÓDIGO PESSOA - LOGIN

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new GsonBuilder().registerTypeAdapter(Pessoa.class, new PessoaDesc()).create();


                email = editTextEmail.getText().toString();
                senha = editTextSenha.getText().toString();

                final Call<Pessoa> pessoaCall = service.login(email, senha);

                pessoaCall.enqueue(new Callback<Pessoa>() {


                    @Override
                    public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                        if (response.isSuccessful()) {

                            pessoaLogin = response.body();
                            Intent intent = new Intent(MainActivity.this, ProfessorActivity.class);
                            Bundle bundle = new Bundle();
                            String email = "";
                            String senha = "";


                            //
                            if (pessoaLogin == null) {
                                Toast.makeText(getApplicationContext(), "Login ou email com erro", Toast.LENGTH_LONG).show();

                                //TESTA PARA VER O TIPO DE PESSOA:
                                // 1 Administrador,
                                // 2 Professor,
                                // 3 Aluno,
                                // 4 Funcionário,
                                // 5 Responsável
                            } else if (pessoaLogin.getTipoPessoa() == 2) {


                                email = editTextEmail.getText().toString();

                                senha = editTextSenha.getText().toString();

                                idLogin = pessoaLogin.getId();

                                bundle.putString("email", email);
                                bundle.putString("senha", senha);
                                bundle.putInt("id",idLogin);

                                intent.putExtras(bundle);

                                startActivity(intent);


                            }else{
                                Toast.makeText(getApplicationContext(), "Não é Professor", Toast.LENGTH_LONG).show();
                            }


                        } else {
                            Toast.makeText(getApplicationContext(), "Erro:" + response.code(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Pessoa> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Erros: " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }


        });


    }


}

