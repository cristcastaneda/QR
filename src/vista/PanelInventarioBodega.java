package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class PanelInventarioBodega extends JPanel {

    public JButton btnActualizar;
    public JTable tablaInventario;
    public DefaultTableModel modeloTabla;

    public PanelInventarioBodega() {
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new BorderLayout());
        JLabel lblTitulo = new JLabel("Inventario de Bodega", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panelSuperior.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBoton = new JPanel();
        btnActualizar = new JButton("Actualizar Inventario");
        panelBoton.add(btnActualizar);
        panelSuperior.add(panelBoton, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new String[]{"Cod. Prod", "Producto", "Cantidad Bodega", "Stock Min.", "Fecha Ult. Act."}, 0);
        tablaInventario = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaInventario);
        scrollTabla.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        add(scrollTabla, BorderLayout.CENTER);
    }
}
