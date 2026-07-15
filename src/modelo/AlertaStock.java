package modelo;

import java.time.LocalDate;

public class AlertaStock {
    private String tipoAlerta;
    private String codProd;
    private String nomProd;
    private String ubicacion;
    private int cantidadActual;
    private int stockMin;
    private LocalDate fechaVenc;

    public AlertaStock() {
    }

    public AlertaStock(String tipoAlerta, String codProd, String nomProd, String ubicacion, int cantidadActual, int stockMin, LocalDate fechaVenc) {
        this.tipoAlerta = tipoAlerta;
        this.codProd = codProd;
        this.nomProd = nomProd;
        this.ubicacion = ubicacion;
        this.cantidadActual = cantidadActual;
        this.stockMin = stockMin;
        this.fechaVenc = fechaVenc;
    }

    public String getTipoAlerta() { return tipoAlerta; }
    public void setTipoAlerta(String tipoAlerta) { this.tipoAlerta = tipoAlerta; }

    public String getCodProd() { return codProd; }
    public void setCodProd(String codProd) { this.codProd = codProd; }

    public String getNomProd() { return nomProd; }
    public void setNomProd(String nomProd) { this.nomProd = nomProd; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public int getCantidadActual() { return cantidadActual; }
    public void setCantidadActual(int cantidadActual) { this.cantidadActual = cantidadActual; }

    public int getStockMin() { return stockMin; }
    public void setStockMin(int stockMin) { this.stockMin = stockMin; }

    public LocalDate getFechaVenc() { return fechaVenc; }
    public void setFechaVenc(LocalDate fechaVenc) { this.fechaVenc = fechaVenc; }
}
