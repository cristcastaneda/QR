package modelo;

import java.time.LocalDate;

public class Despacho {
    private String codDesp;
    private LocalDate fechaDesp;
    private String codEmp;
    private String codMaq;

    public Despacho() {
    }

    public Despacho(String codDesp, LocalDate fechaDesp, String codEmp, String codMaq) {
        this.codDesp = codDesp;
        this.fechaDesp = fechaDesp;
        this.codEmp = codEmp;
        this.codMaq = codMaq;
    }

    public String getCodDesp() { return codDesp; }
    public void setCodDesp(String codDesp) { this.codDesp = codDesp; }

    public LocalDate getFechaDesp() { return fechaDesp; }
    public void setFechaDesp(LocalDate fechaDesp) { this.fechaDesp = fechaDesp; }

    public String getCodEmp() { return codEmp; }
    public void setCodEmp(String codEmp) { this.codEmp = codEmp; }

    public String getCodMaq() { return codMaq; }
    public void setCodMaq(String codMaq) { this.codMaq = codMaq; }
}
