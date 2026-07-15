package modelo;

public class Producto {
    private String codProd;
    private String nomProd;
    private String categProd;
    private double precioVenProd;
    private String unidadMedProd;

    public Producto() {
    }

    public Producto(String codProd, String nomProd, String categProd, double precioVenProd, String unidadMedProd) {
        this.codProd = codProd;
        this.nomProd = nomProd;
        this.categProd = categProd;
        this.precioVenProd = precioVenProd;
        this.unidadMedProd = unidadMedProd;
    }

    public String getCodProd() { return codProd; }
    public void setCodProd(String codProd) { this.codProd = codProd; }

    public String getNomProd() { return nomProd; }
    public void setNomProd(String nomProd) { this.nomProd = nomProd; }

    public String getCategProd() { return categProd; }
    public void setCategProd(String categProd) { this.categProd = categProd; }

    public double getPrecioVenProd() { return precioVenProd; }
    public void setPrecioVenProd(double precioVenProd) { this.precioVenProd = precioVenProd; }

    public String getUnidadMedProd() { return unidadMedProd; }
    public void setUnidadMedProd(String unidadMedProd) { this.unidadMedProd = unidadMedProd; }
}
