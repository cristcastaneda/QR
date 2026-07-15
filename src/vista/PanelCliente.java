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

public class PanelCliente extends JPanel {

    public JTextField txtCodigo, txtNombre, txtDireccion, txtNit, txtContacto, txtTelefono;
    public JButton btnGuardar, btnEliminar;
    public JTable tablaClientes;
    public DefaultTableModel modeloTabla;

    public PanelCliente() {
        setLayout(new BorderLayout());
        
        JPanel panelSuperior = new JPanel(new BorderLayout());
        JLabel lblTitulo = new JLabel("Gestión de Clientes", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panelSuperior.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(3, 4, 10, 10)); 
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        panelFormulario.add(new JLabel("Código Cliente:"));
        txtCodigo = new JTextField();
        txtCodigo.setEditable(false);
        panelFormulario.add(txtCodigo);

        panelFormulario.add(new JLabel("Nombre/Razón Social:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panelFormulario.add(txtDireccion);

        panelFormulario.add(new JLabel("NIT:"));
        txtNit = new JTextField();
        panelFormulario.add(txtNit);

        panelFormulario.add(new JLabel("Persona de Contacto:"));
        txtContacto = new JTextField();
        panelFormulario.add(txtContacto);

        panelFormulario.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelFormulario.add(txtTelefono);

        panelSuperior.add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel();
        btnGuardar = new JButton("Guardar Cliente");
        btnEliminar = new JButton("Eliminar Cliente Seleccionado");
        panelBoton.add(btnGuardar);
        panelBoton.add(btnEliminar);
        panelSuperior.add(panelBoton, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new String[]{"Código", "Nombre", "Dirección", "NIT", "Contacto", "Teléfono"}, 0);
        tablaClientes = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaClientes);
        scrollTabla.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        add(scrollTabla, BorderLayout.CENTER);
    }
}
