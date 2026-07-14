package modelo;

public class Cliente {
    private String codCli;
    private String nomCli;
    private String dirCli;
    private String nit;
    private String conCli;
    private String telCli;

    public Cliente() {
    }

    public Cliente(String codCli, String nomCli, String dirCli, String nit, String conCli, String telCli) {
        this.codCli = codCli;
        this.nomCli = nomCli;
        this.dirCli = dirCli;
        this.nit = nit;
        this.conCli = conCli;
        this.telCli = telCli;
    }

    public String getCodCli() { return codCli; }
    public void setCodCli(String codCli) { this.codCli = codCli; }

    public String getNomCli() { return nomCli; }
    public void setNomCli(String nomCli) { this.nomCli = nomCli; }

    public String getDirCli() { return dirCli; }
    public void setDirCli(String dirCli) { this.dirCli = dirCli; }

    public String getNit() { return nit; }
    public void setNit(String nit) { this.nit = nit; }

    public String getConCli() { return conCli; }
    public void setConCli(String conCli) { this.conCli = conCli; }

    public String getTelCli() { return telCli; }
    public void setTelCli(String telCli) { this.telCli = telCli; }
}
