package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class PanelProducto extends JPanel {
    
    // Componentes del formulario
    public JTextField txtCodigo, txtNombre, txtCategoria, txtFechaVenc, txtPrecio, txtUnidad;
    public JButton btnGuardar;
    
    // ¡NUEVO! Componentes para la tabla
    public JTable tablaProductos;
    public DefaultTableModel modeloTabla;

    public PanelProducto() {
        setLayout(new BorderLayout());
        
        // --- 1. PANEL SUPERIOR: Formulario ---
        JPanel panelSuperior = new JPanel(new BorderLayout());
        
        JLabel lblTitulo = new JLabel("Gestión de Productos", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panelSuperior.add(lblTitulo, BorderLayout.NORTH);

        // Cuadrícula más compacta (3 filas, 4 columnas)
        JPanel panelFormulario = new JPanel(new GridLayout(3, 4, 10, 10)); 
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        panelFormulario.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        txtCodigo.setEditable(false);
        panelFormulario.add(txtCodigo);

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Categoría:"));
        txtCategoria = new JTextField();
        panelFormulario.add(txtCategoria);

        panelFormulario.add(new JLabel("Vencimiento (YYYY-MM-DD):"));
        txtFechaVenc = new JTextField();
        panelFormulario.add(txtFechaVenc);

        panelFormulario.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        panelFormulario.add(txtPrecio);

        panelFormulario.add(new JLabel("Unidad:"));
        txtUnidad = new JTextField();
        panelFormulario.add(txtUnidad);

        panelSuperior.add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel();
        btnGuardar = new JButton("Guardar Producto");
        panelBoton.add(btnGuardar);
        panelSuperior.add(panelBoton, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);

        // --- 2. PANEL INFERIOR: Tabla de Inventario ---
        // Definimos las columnas de la tabla
        modeloTabla = new DefaultTableModel(new String[]{"Código", "Nombre", "Categoría", "Vencimiento", "Precio", "Unidad"}, 0);
        tablaProductos = new JTable(modeloTabla);
        
        // Metemos la tabla en un ScrollPane por si hay muchos productos
        JScrollPane scrollTabla = new JScrollPane(tablaProductos);
        scrollTabla.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        
        add(scrollTabla, BorderLayout.CENTER);
    }
}
