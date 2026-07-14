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

public class PanelEmpleado extends JPanel {

    public JTextField txtCodigo, txtNombre, txtTelefono, txtFechaIngreso;
    public JComboBox<String> cbCargo;
    public JButton btnGuardar;
    public JTable tablaEmpleados;
    public DefaultTableModel modeloTabla;

    public PanelEmpleado() {
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new BorderLayout());
        JLabel lblTitulo = new JLabel("Gestion de Personal", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panelSuperior.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(3, 4, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        panelFormulario.add(new JLabel("Codigo Empleado:"));
        txtCodigo = new JTextField();
        txtCodigo.setEditable(false);
        panelFormulario.add(txtCodigo);

        panelFormulario.add(new JLabel("Nombre Completo:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Cargo:"));
        cbCargo = new JComboBox<>(new String[]{"Empleado de bodega", "Operador comercial"});
        panelFormulario.add(cbCargo);

        panelFormulario.add(new JLabel("Telefono:"));
        txtTelefono = new JTextField();
        panelFormulario.add(txtTelefono);

        panelFormulario.add(new JLabel("Fecha Ingreso (YYYY-MM-DD):"));
        txtFechaIngreso = new JTextField();
        panelFormulario.add(txtFechaIngreso);

        panelFormulario.add(new JLabel(""));
        panelFormulario.add(new JLabel(""));

        panelSuperior.add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel();
        btnGuardar = new JButton("Guardar Empleado");
        panelBoton.add(btnGuardar);
        panelSuperior.add(panelBoton, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new String[]{"Codigo", "Nombre", "Cargo", "Telefono", "Fecha Ingreso"}, 0);
        tablaEmpleados = new JTable(modeloTabla);

        JScrollPane scrollTabla = new JScrollPane(tablaEmpleados);
        scrollTabla.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        add(scrollTabla, BorderLayout.CENTER);
    }
}
