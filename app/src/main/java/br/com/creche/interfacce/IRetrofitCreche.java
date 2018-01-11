package br.com.creche.interfacce;

import java.util.List;

import br.com.creche.modelo.Aluno;
import br.com.creche.modelo.AlunoPresenca;
import br.com.creche.modelo.Estado;
import br.com.creche.modelo.Pessoa;
import br.com.creche.modelo.TipoTurma;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by u6390869 on 10/09/2017.
 */

public interface IRetrofitCreche {

    @GET("Estado/listar")
    Call <List<Estado>> getEstados();

    @GET("Pessoa/login/{email},{senha}")
    Call<Pessoa> login(@Path("email") String email, @Path("senha") String senha);

    //@GET("Pessoa/verificaLogin/{email},{senha}")
    //Call<Boolean> verificaLogin(@Path("email") String email, @Path("senha") String senha);


    @GET("TipoTurma/listarPorProfessor/{id}")
    Call <List<TipoTurma>> getTipoTurmaProfessor(@Path("id") int id);

    @GET("Alunos/listarAlunos/{id}")
    Call <List<Aluno>> getListarAlunos(@Path("id") int id);

    @GET("Alunos/listarAlunosPresentes/{id}")
    Call <List<AlunoPresenca>> getAlunosPresentesId(@Path("id") int id);

    @GET("Pessoa/listar")
    Call <List<Pessoa>> getPessoas();


    /*(@Path("estados") String estados);
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();*/
}
