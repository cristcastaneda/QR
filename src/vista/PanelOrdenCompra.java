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
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class PanelOrdenCompra extends JPanel {

    public JTextField txtCodigo, txtFecha, txtFechaRecepcion, txtFechaVencLote, txtCantidad, txtPrecioUnitario;
    public JComboBox<String> cbEstado, cbProveedores, cbEmpleados, cbProductos;
    public JButton btnAgregarProducto, btnGuardar, btnCompletar;
    public JTable tablaDetalle, tablaOrdenes;
    public DefaultTableModel modeloDetalle, modeloTabla;

    public PanelOrdenCompra() {
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new BorderLayout());
        JLabel lblTitulo = new JLabel("Ordenes de Compra", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panelSuperior.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(2, 1, 0, 8));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel panelCabecera = new JPanel(new GridLayout(3, 4, 10, 8));
        panelCabecera.setBorder(BorderFactory.createTitledBorder("Datos de la orden"));
        panelCabecera.add(new JLabel("Codigo Orden:"));
        txtCodigo = new JTextField();
        txtCodigo.setEditable(false);
        panelCabecera.add(txtCodigo);
        panelCabecera.add(new JLabel("Fecha Orden (YYYY-MM-DD):"));
        txtFecha = new JTextField();
        panelCabecera.add(txtFecha);
        panelCabecera.add(new JLabel("Estado:"));
        cbEstado = new JComboBox<>(new String[]{"En proceso", "Completado"});
        panelCabecera.add(cbEstado);
        panelCabecera.add(new JLabel("Fecha Recepcion (opcional):"));
        txtFechaRecepcion = new JTextField();
        panelCabecera.add(txtFechaRecepcion);
        panelCabecera.add(new JLabel("Proveedor:"));
        cbProveedores = new JComboBox<>();
        panelCabecera.add(cbProveedores);
        panelCabecera.add(new JLabel("Empleado Receptor:"));
        cbEmpleados = new JComboBox<>();
        panelCabecera.add(cbEmpleados);

        JPanel panelProducto = new JPanel(new GridLayout(2, 5, 10, 8));
        panelProducto.setBorder(BorderFactory.createTitledBorder("Producto a comprar"));
        panelProducto.add(new JLabel("Producto:"));
        panelProducto.add(new JLabel("Vencimiento lote:"));
        panelProducto.add(new JLabel("Cantidad:"));
        panelProducto.add(new JLabel("Precio Unitario:"));
        panelProducto.add(new JLabel("Accion:"));

        cbProductos = new JComboBox<>();
        panelProducto.add(cbProductos);
        txtFechaVencLote = new JTextField();
        panelProducto.add(txtFechaVencLote);
        txtCantidad = new JTextField();
        panelProducto.add(txtCantidad);
        txtPrecioUnitario = new JTextField();
        panelProducto.add(txtPrecioUnitario);
        btnAgregarProducto = new JButton("Agregar Producto");
        panelProducto.add(btnAgregarProducto);

        panelFormulario.add(panelCabecera);
        panelFormulario.add(panelProducto);
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        btnGuardar = new JButton("Guardar Orden");
        btnCompletar = new JButton("Completar Orden Seleccionada");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCompletar);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        modeloDetalle = new DefaultTableModel(new String[]{"Cod. Prod", "Producto", "Vence Lote", "Cantidad", "Precio", "Subtotal"}, 0);
        tablaDetalle = new JTable(modeloDetalle);
        JScrollPane scrollDetalle = new JScrollPane(tablaDetalle);
        scrollDetalle.setBorder(BorderFactory.createTitledBorder("Productos de la orden actual"));

        modeloTabla = new DefaultTableModel(new String[]{"Cod. Orden", "Fecha", "Estado", "Fecha Rec.", "Cod. Prov", "Proveedor", "Cod. Emp", "Empleado", "Cod. Prod", "Producto", "Vence Lote", "Cant.", "Precio", "Subtotal"}, 0);
        tablaOrdenes = new JTable(modeloTabla);
        JScrollPane scrollOrdenes = new JScrollPane(tablaOrdenes);
        scrollOrdenes.setBorder(BorderFactory.createTitledBorder("Ordenes registradas"));

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollDetalle, scrollOrdenes);
        splitPane.setResizeWeight(0.35);
        splitPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        add(panelSuperior, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
    }
}
