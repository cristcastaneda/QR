package modelo;

import java.time.LocalDate;

public class Maquina {
    private String codMaq;
    private String codSerie;
    private String ubicMaq;
    private String estadoMaq;
    private LocalDate fechaInsMaq;
    private String codCli;

    public Maquina() {
    }

    public Maquina(String codMaq, String codSerie, String ubicMaq, String estadoMaq, LocalDate fechaInsMaq, String codCli) {
        this.codMaq = codMaq;
        this.codSerie = codSerie;
        this.ubicMaq = ubicMaq;
        this.estadoMaq = estadoMaq;
        this.fechaInsMaq = fechaInsMaq;
        this.codCli = codCli;
    }

    public String getCodMaq() { return codMaq; }
    public void setCodMaq(String codMaq) { this.codMaq = codMaq; }

    public String getCodSerie() { return codSerie; }
    public void setCodSerie(String codSerie) { this.codSerie = codSerie; }

    public String getUbicMaq() { return ubicMaq; }
    public void setUbicMaq(String ubicMaq) { this.ubicMaq = ubicMaq; }

    public String getEstadoMaq() { return estadoMaq; }
    public void setEstadoMaq(String estadoMaq) { this.estadoMaq = estadoMaq; }

    public LocalDate getFechaInsMaq() { return fechaInsMaq; }
    public void setFechaInsMaq(LocalDate fechaInsMaq) { this.fechaInsMaq = fechaInsMaq; }

    public String getCodCli() { return codCli; }
    public void setCodCli(String codCli) { this.codCli = codCli; }
}
