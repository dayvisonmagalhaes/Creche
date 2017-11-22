package br.com.creche.modelo;

/**
 * Created by u6390869 on 21/11/2017.
 */

public class Aluno {

    private int turmaId;
    private double escolaCNPJ;
    private int pessoaId;
    private double matricula;

    public Aluno() {
    }

    public Aluno(int turmaId, double escolaCNPJ, int pessoaId, double matricula) {
        this.turmaId = turmaId;
        this.escolaCNPJ = escolaCNPJ;
        this.pessoaId = pessoaId;
        this.matricula = matricula;
    }

    //ATALHO PARA GERAR GET E SET: Alt + Insert
    public int getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(int turmaId) {
        this.turmaId = turmaId;
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
