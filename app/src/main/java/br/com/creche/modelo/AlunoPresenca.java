package br.com.creche.modelo;

/**
 * Created by u6390869 on 11/01/2018.
 */

public class AlunoPresenca {

    private String nome;
    private double matricula;
    private int status;

    public AlunoPresenca() {
    }



    public AlunoPresenca(String nome, double matricula, int status) {
        this.nome = nome;
        this.matricula = matricula;
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getMatricula() {
        return matricula;
    }

    public void setMatricula(double matricula) {
        this.matricula = matricula;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
