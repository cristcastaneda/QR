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

public class PanelProveedor extends JPanel {

    public JTextField txtCodigo, txtNombre, txtNit, txtTelefono, txtDireccion, txtContacto;
    public JButton btnGuardar, btnEliminar;
    public JTable tablaProveedores;
    public DefaultTableModel modeloTabla;

    public PanelProveedor() {
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new BorderLayout());

        JLabel lblTitulo = new JLabel("Gestion de Proveedores", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panelSuperior.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(3, 4, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        panelFormulario.add(new JLabel("Codigo:"));
        txtCodigo = new JTextField();
        txtCodigo.setEditable(false);
        panelFormulario.add(txtCodigo);

        panelFormulario.add(new JLabel("Nombre Empresa:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("NIT:"));
        txtNit = new JTextField();
        panelFormulario.add(txtNit);

        panelFormulario.add(new JLabel("Telefono:"));
        txtTelefono = new JTextField();
        panelFormulario.add(txtTelefono);

        panelFormulario.add(new JLabel("Direccion:"));
        txtDireccion = new JTextField();
        panelFormulario.add(txtDireccion);

        panelFormulario.add(new JLabel("Nombre Contacto:"));
        txtContacto = new JTextField();
        panelFormulario.add(txtContacto);

        panelSuperior.add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel();
        btnGuardar = new JButton("Guardar Proveedor");
        btnEliminar = new JButton("Eliminar Proveedor Seleccionado");
        panelBoton.add(btnGuardar);
        panelBoton.add(btnEliminar);
        panelSuperior.add(panelBoton, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new String[]{"Codigo", "Empresa", "NIT", "Telefono", "Direccion", "Contacto"}, 0);
        tablaProveedores = new JTable(modeloTabla);

        JScrollPane scrollTabla = new JScrollPane(tablaProveedores);
        scrollTabla.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        add(scrollTabla, BorderLayout.CENTER);
    }
}
