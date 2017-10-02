package modelo;

/**
 * Created by u6390869 on 01/10/2017.
 */

public class Pessoa {

    private int id;
    private double matricula;
    private String nome;
    private String rua;
    private String complemento;
    private int numero;
    private int cep;
    private String cpf;
    private byte[] foto;
    private String celular;
    private String sexo;
    private String email;
    private String senha;
    private int TipoPessoa;
    private int bairroId;

    public Pessoa() {
    }

    public Pessoa(int id, double matricula, String nome, String rua, String complemento, int numero, int cep, String cpf, byte[] foto, String celular, String sexo, String email, String senha, int TipoPessoa, int bairroId) {
        this.id = id;
        this.matricula = matricula;
        this.nome = nome;
        this.rua = rua;
        this.complemento = complemento;
        this.numero = numero;
        this.cep = cep;
        this.cpf = cpf;
        this.foto = foto;
        this.celular = celular;
        this.sexo = sexo;
        this.email = email;
        this.senha = senha;
        this.TipoPessoa = TipoPessoa;
        this.bairroId = bairroId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMatricula() {
        return matricula;
    }

    public void setMatricula(double matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTipoPessoa() {
        return TipoPessoa;
    }

    public void setTipoPessoa(int TipoPessoa) {
        this.TipoPessoa = TipoPessoa;
    }

    public int getBairroId() {
        return bairroId;
    }

    public void setBairroId(int bairroId) {
        this.bairroId = bairroId;
    }

}
