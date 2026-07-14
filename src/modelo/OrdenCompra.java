package modelo;

import java.time.LocalDate;

public class OrdenCompra {
    private String codOrdCom;
    private LocalDate fechaOrdCom;
    private String estadoOrdCom;
    private LocalDate fechaRecOrdCom;
    private String codPro;
    private String codEmp;

    public OrdenCompra() {
    }

    public OrdenCompra(String codOrdCom, LocalDate fechaOrdCom, String estadoOrdCom, LocalDate fechaRecOrdCom, String codPro, String codEmp) {
        this.codOrdCom = codOrdCom;
        this.fechaOrdCom = fechaOrdCom;
        this.estadoOrdCom = estadoOrdCom;
        this.fechaRecOrdCom = fechaRecOrdCom;
        this.codPro = codPro;
        this.codEmp = codEmp;
    }

    public String getCodOrdCom() { return codOrdCom; }
    public void setCodOrdCom(String codOrdCom) { this.codOrdCom = codOrdCom; }

    public LocalDate getFechaOrdCom() { return fechaOrdCom; }
    public void setFechaOrdCom(LocalDate fechaOrdCom) { this.fechaOrdCom = fechaOrdCom; }

    public String getEstadoOrdCom() { return estadoOrdCom; }
    public void setEstadoOrdCom(String estadoOrdCom) { this.estadoOrdCom = estadoOrdCom; }

    public LocalDate getFechaRecOrdCom() { return fechaRecOrdCom; }
    public void setFechaRecOrdCom(LocalDate fechaRecOrdCom) { this.fechaRecOrdCom = fechaRecOrdCom; }

    public String getCodPro() { return codPro; }
    public void setCodPro(String codPro) { this.codPro = codPro; }

    public String getCodEmp() { return codEmp; }
    public void setCodEmp(String codEmp) { this.codEmp = codEmp; }
}
