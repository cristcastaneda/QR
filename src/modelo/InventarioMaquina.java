package modelo;

import java.time.LocalDate;

public class InventarioMaquina {
    private String codMaq;
    private String codProd;
    private int cantAct;
    private int stockMin;
    private LocalDate fechaUltRec;

    public InventarioMaquina() {
    }

    public InventarioMaquina(String codMaq, String codProd, int cantAct, int stockMin, LocalDate fechaUltRec) {
        this.codMaq = codMaq;
        this.codProd = codProd;
        this.cantAct = cantAct;
        this.stockMin = stockMin;
        this.fechaUltRec = fechaUltRec;
    }

    public String getCodMaq() { return codMaq; }
    public void setCodMaq(String codMaq) { this.codMaq = codMaq; }

    public String getCodProd() { return codProd; }
    public void setCodProd(String codProd) { this.codProd = codProd; }

    public int getCantAct() { return cantAct; }
    public void setCantAct(int cantAct) { this.cantAct = cantAct; }

    public int getStockMin() { return stockMin; }
    public void setStockMin(int stockMin) { this.stockMin = stockMin; }

    public LocalDate getFechaUltRec() { return fechaUltRec; }
    public void setFechaUltRec(LocalDate fechaUltRec) { this.fechaUltRec = fechaUltRec; }
}
