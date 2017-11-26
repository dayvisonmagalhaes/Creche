package br.com.creche.modelo;

/**
 * Created by u6390869 on 21/11/2017.
 */

public class Aluno {

    private int turmaId;
    private String nome;
    private double escolaCNPJ;
    private int pessoaId;
    private double matricula;

    public Aluno() {
    }

    public Aluno(int turmaId, String nome, double escolaCNPJ, int pessoaId, double matricula) {
        this.turmaId = turmaId;
        this.nome = nome;
        this.escolaCNPJ = escolaCNPJ;
        this.pessoaId = pessoaId;
        this.matricula = matricula;
    }

    public int getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(int turmaId) {
        this.turmaId = turmaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getEscolaCNPJ() {
        return escolaCNPJ;
    }

    public void setEscolaCNPJ(double escolaCNPJ) {
        this.escolaCNPJ = escolaCNPJ;
    }

    public int getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(int pessoaId) {
        this.pessoaId = pessoaId;
    }

    public double getMatricula() {
        return matricula;
    }

    public void setMatricula(double matricula) {
        this.matricula = matricula;
    }

}
