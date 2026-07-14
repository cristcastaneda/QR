package modelo;

public class DetalleDespacho {
    private String codDesp;
    private String codProd;
    private int cantidadDesp;

    public DetalleDespacho() {
    }

    public DetalleDespacho(String codDesp, String codProd, int cantidadDesp) {
        this.codDesp = codDesp;
        this.codProd = codProd;
        this.cantidadDesp = cantidadDesp;
    }

    public String getCodDesp() { return codDesp; }
    public void setCodDesp(String codDesp) { this.codDesp = codDesp; }

    public String getCodProd() { return codProd; }
    public void setCodProd(String codProd) { this.codProd = codProd; }

    public int getCantidadDesp() { return cantidadDesp; }
    public void setCantidadDesp(int cantidadDesp) { this.cantidadDesp = cantidadDesp; }
}
