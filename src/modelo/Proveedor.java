package modelo;

public class Proveedor {
    private String codPro;
    private String nomPro;
    private String nitPro;
    private String telPro;
    private String dirPro;
    private String conPro;

    public Proveedor() {
    }

    public Proveedor(String codPro, String nomPro, String nitPro, String telPro, String dirPro, String conPro) {
        this.codPro = codPro;
        this.nomPro = nomPro;
        this.nitPro = nitPro;
        this.telPro = telPro;
        this.dirPro = dirPro;
        this.conPro = conPro;
    }

    public String getCodPro() { return codPro; }
    public void setCodPro(String codPro) { this.codPro = codPro; }

    public String getNomPro() { return nomPro; }
    public void setNomPro(String nomPro) { this.nomPro = nomPro; }

    public String getNitPro() { return nitPro; }
    public void setNitPro(String nitPro) { this.nitPro = nitPro; }

    public String getTelPro() { return telPro; }
    public void setTelPro(String telPro) { this.telPro = telPro; }

    public String getDirPro() { return dirPro; }
    public void setDirPro(String dirPro) { this.dirPro = dirPro; }

    public String getConPro() { return conPro; }
    public void setConPro(String conPro) { this.conPro = conPro; }
}
