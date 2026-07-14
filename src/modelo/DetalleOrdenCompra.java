package modelo;

public class DetalleOrdenCompra {
    private String codOrdCom;
    private String codProd;
    private int cantSol;
    private double precioUni;
    private double subTotal;

    public DetalleOrdenCompra() {
    }

    public DetalleOrdenCompra(String codOrdCom, String codProd, int cantSol, double precioUni, double subTotal) {
        this.codOrdCom = codOrdCom;
        this.codProd = codProd;
        this.cantSol = cantSol;
        this.precioUni = precioUni;
        this.subTotal = subTotal;
    }

    public String getCodOrdCom() { return codOrdCom; }
    public void setCodOrdCom(String codOrdCom) { this.codOrdCom = codOrdCom; }

    public String getCodProd() { return codProd; }
    public void setCodProd(String codProd) { this.codProd = codProd; }

    public int getCantSol() { return cantSol; }
    public void setCantSol(int cantSol) { this.cantSol = cantSol; }

    public double getPrecioUni() { return precioUni; }
    public void setPrecioUni(double precioUni) { this.precioUni = precioUni; }

    public double getSubTotal() { return subTotal; }
    public void setSubTotal(double subTotal) { this.subTotal = subTotal; }
}
