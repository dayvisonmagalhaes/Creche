package modelo;


import java.io.Serializable;

public class Estado implements Serializable{

    private int estadoId;
    private String estadoNome;
    private String estadoSigla;

    public Estado() {
    }

    public Estado(int estadoId, String estadoNome, String estadoSigla) {
        this.estadoId = estadoId;
        this.estadoNome = estadoNome;
        this.estadoSigla = estadoSigla;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public String getEstadoNome() {
        return estadoNome;
    }

    public void setEstadoNome(String estadoNome) {
        this.estadoNome = estadoNome;
    }

    public String getEstadoSigla() {
        return estadoSigla;
    }

    public void setEstadoSigla(String estadoSigla) {
        this.estadoSigla = estadoSigla;
    }

}
