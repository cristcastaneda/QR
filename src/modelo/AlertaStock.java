package modelo;

public class AlertaStock {
    private String codProd;
    private String nomProd;
    private String ubicacion;
    private int cantidadActual;
    private int stockMin;

    public AlertaStock() {
    }

    public AlertaStock(String codProd, String nomProd, String ubicacion, int cantidadActual, int stockMin) {
        this.codProd = codProd;
        this.nomProd = nomProd;
        this.ubicacion = ubicacion;
        this.cantidadActual = cantidadActual;
        this.stockMin = stockMin;
    }

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
}
