package br.com.creche.modelo;

/**
 * Created by u6390869 on 26/10/2017.
 */

public class TipoTurma {

    private int id;
    private String nome;
    private String descricao;

    public TipoTurma() {
    }

    public TipoTurma(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
