package modelo;

import java.time.LocalDate;

public class InventarioBodega {
    private String codProd;
    private int cantDisp;
    private int stockMin;
    private LocalDate fechaUltAct;

    public InventarioBodega() {
    }

    public InventarioBodega(String codProd, int cantDisp, int stockMin, LocalDate fechaUltAct) {
        this.codProd = codProd;
        this.cantDisp = cantDisp;
        this.stockMin = stockMin;
        this.fechaUltAct = fechaUltAct;
    }

    public String getCodProd() { return codProd; }
    public void setCodProd(String codProd) { this.codProd = codProd; }

    public int getCantDisp() { return cantDisp; }
    public void setCantDisp(int cantDisp) { this.cantDisp = cantDisp; }

    public int getStockMin() { return stockMin; }
    public void setStockMin(int stockMin) { this.stockMin = stockMin; }

    public LocalDate getFechaUltAct() { return fechaUltAct; }
    public void setFechaUltAct(LocalDate fechaUltAct) { this.fechaUltAct = fechaUltAct; }
}
