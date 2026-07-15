package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class PanelDespacho extends JPanel {

    public JTextField txtCodigo, txtFecha, txtCantidad;
    public JComboBox<String> cbEmpleados, cbMaquinas, cbProductos;
    public JButton btnAgregarProducto, btnEliminarProducto, btnGuardar;
    public JTable tablaDetalle, tablaDespachos;
    public DefaultTableModel modeloDetalle, modeloTabla;

    public PanelDespacho() {
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new BorderLayout());
        JLabel lblTitulo = new JLabel("Despachos a Maquinaria", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panelSuperior.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(3, 4, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        panelFormulario.add(new JLabel("Codigo Despacho:"));
        txtCodigo = new JTextField();
        txtCodigo.setEditable(false);
        panelFormulario.add(txtCodigo);

        panelFormulario.add(new JLabel("Fecha (YYYY-MM-DD):"));
        txtFecha = new JTextField();
        panelFormulario.add(txtFecha);

        panelFormulario.add(new JLabel("Empleado Responsable:"));
        cbEmpleados = new JComboBox<>();
        panelFormulario.add(cbEmpleados);

        panelFormulario.add(new JLabel("Maquina Destino:"));
        cbMaquinas = new JComboBox<>();
        panelFormulario.add(cbMaquinas);

        panelFormulario.add(new JLabel("Producto:"));
        cbProductos = new JComboBox<>();
        panelFormulario.add(cbProductos);

        panelFormulario.add(new JLabel("Cantidad:"));
        txtCantidad = new JTextField();
        panelFormulario.add(txtCantidad);

        panelSuperior.add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        btnAgregarProducto = new JButton("Agregar Producto");
        btnEliminarProducto = new JButton("Quitar Producto Seleccionado");
        btnGuardar = new JButton("Guardar Despacho");
        panelBotones.add(btnAgregarProducto);
        panelBotones.add(btnEliminarProducto);
        panelBotones.add(btnGuardar);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        JPanel panelCentro = new JPanel(new GridLayout(2, 1, 0, 10));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        modeloDetalle = new DefaultTableModel(new String[]{"Cod. Prod", "Producto", "Cantidad"}, 0);
        tablaDetalle = new JTable(modeloDetalle);
        panelCentro.add(new JScrollPane(tablaDetalle));

        modeloTabla = new DefaultTableModel(new String[]{"Cod. Despacho", "Fecha", "Cod. Emp", "Empleado", "Cod. Maq", "Maquina", "Cod. Prod", "Producto", "Cantidad"}, 0);
        tablaDespachos = new JTable(modeloTabla);
        panelCentro.add(new JScrollPane(tablaDespachos));

        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
    }
}
