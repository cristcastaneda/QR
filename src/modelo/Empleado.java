package modelo;

import java.time.LocalDate;

public class Empleado {
    private String codEmp;
    private String nomEmp;
    private String cargoEmp;
    private String telEmp;
    private LocalDate fechaIngEmp;

    public Empleado() {
    }

    public Empleado(String codEmp, String nomEmp, String cargoEmp, String telEmp, LocalDate fechaIngEmp) {
        this.codEmp = codEmp;
        this.nomEmp = nomEmp;
        this.cargoEmp = cargoEmp;
        this.telEmp = telEmp;
        this.fechaIngEmp = fechaIngEmp;
    }

    public String getCodEmp() { return codEmp; }
    public void setCodEmp(String codEmp) { this.codEmp = codEmp; }

    public String getNomEmp() { return nomEmp; }
    public void setNomEmp(String nomEmp) { this.nomEmp = nomEmp; }

    public String getCargoEmp() { return cargoEmp; }
    public void setCargoEmp(String cargoEmp) { this.cargoEmp = cargoEmp; }

    public String getTelEmp() { return telEmp; }
    public void setTelEmp(String telEmp) { this.telEmp = telEmp; }

    public LocalDate getFechaIngEmp() { return fechaIngEmp; }
    public void setFechaIngEmp(LocalDate fechaIngEmp) { this.fechaIngEmp = fechaIngEmp; }
}
