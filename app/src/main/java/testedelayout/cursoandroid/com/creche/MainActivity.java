package testedelayout.cursoandroid.com.creche;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import modelo.Estado;
import modelo.EstadoDesc;
import modelo.IRetrofitCreche;
import modelo.Pessoa;
import modelo.PessoaDesc;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://10.7.50.162:8080/WebServiceCreche/webresources/Creches/";

    EditText editTextEmail, editTextSenha;
    Button btnLogar;

    String url = "";
    String parametros = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = (EditText) findViewById(R.id.editText_EmailId);
        editTextSenha = (EditText) findViewById(R.id.editText_SenhaId);
        btnLogar = (Button) findViewById(R.id.btnLogarId);

/*
        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {

                    String email = editTextEmail.getText().toString();
                    String senha = editTextSenha.getText().toString();

                    if (email.isEmpty() || senha.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Nenhum campo pode estar vazio", Toast.LENGTH_LONG).show();
                    } else {
                        url = "http://10.7.50.162:8080/";
                        parametros = "email=" + email + "&senha=" + senha;
                        new solicitaDados().execute(url);

                    }

                } else {

                    Toast.makeText(getApplicationContext(), "Nenhuma conexão detectada", Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    private class solicitaDados extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            return Conexao.postDados(urls[0], parametros);

        }

        @Override
        protected void onPostExecute(String resultado) {
            //textView.setText(result);

            editTextEmail.setText(resultado);
        }
    }
*/


        //CÓDIGO ESTADO



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



        //CÓDIGO PESSOA - LOGIN







        //CÓDIGO PESSOA - LISTAR



       /* final Call<List<Pessoa>> pessoas = service.getPessoas();

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
        }); */

       btnLogar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Gson g = new GsonBuilder().registerTypeAdapter(Pessoa.class, new PessoaDesc()).create();

               Retrofit retrofit = new Retrofit.Builder()
                       .baseUrl(BASE_URL)
                       .addConverterFactory(GsonConverterFactory.create(g))
                       .build();

               IRetrofitCreche service = retrofit.create(IRetrofitCreche.class);


               String email = editTextEmail.getText().toString();
               String senha = editTextSenha.getText().toString();

               final Call<Pessoa> pessoaCall = service.login(email,senha);

               pessoaCall.enqueue(new Callback<Pessoa>()

               {
                   @Override
                   public void onResponse(Call<Pessoa> call, Response <Pessoa> response) {
                       if (response.isSuccessful()) {

                           Pessoa pessoaLogin;
                           pessoaLogin = response.body();

                           //editTextEmail.setText(pessoaLogin.getEmail());


                           //Toast.makeText(getApplicationContext(), pessoaLogin.getNome().toString(),Toast.LENGTH_LONG).show();


                           Log.i("PES", pessoaLogin.getId() + "----" + pessoaLogin.getNome());
                           Log.i("PES", "-------------------------------");


                       } else {
                           Toast.makeText(getApplicationContext(), "Erro: " + response.code(), Toast.LENGTH_LONG).show();
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
