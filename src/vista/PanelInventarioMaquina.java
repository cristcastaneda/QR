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

public class PanelInventarioMaquina extends JPanel {

    public JButton btnActualizar;
    public JTable tablaInventario;
    public DefaultTableModel modeloTabla;

    public PanelInventarioMaquina() {
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new BorderLayout());
        JLabel lblTitulo = new JLabel("Inventario por Maquina", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panelSuperior.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBoton = new JPanel();
        btnActualizar = new JButton("Actualizar Inventario");
        panelBoton.add(btnActualizar);
        panelSuperior.add(panelBoton, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new String[]{"Cod. Maq", "Maquina", "Cod. Prod", "Producto", "Cantidad Maquina", "Stock Min.", "Fecha Ult. Rec."}, 0);
        tablaInventario = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaInventario);
        scrollTabla.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        add(scrollTabla, BorderLayout.CENTER);
    }
}
