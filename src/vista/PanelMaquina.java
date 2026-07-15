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

public class PanelMaquina extends JPanel {

    public JTextField txtCodigo, txtSerie, txtUbicacion, txtFechaIns;
    public JComboBox<String> cbEstado, cbClientes;
    public JButton btnGuardar, btnEliminar;
    public JTable tablaMaquinas;
    public DefaultTableModel modeloTabla;

    public PanelMaquina() {
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new BorderLayout());
        JLabel lblTitulo = new JLabel("Gestion de Maquinaria", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panelSuperior.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(3, 4, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        panelFormulario.add(new JLabel("Codigo Maquina:"));
        txtCodigo = new JTextField();
        txtCodigo.setEditable(false);
        panelFormulario.add(txtCodigo);

        panelFormulario.add(new JLabel("Codigo Serie:"));
        txtSerie = new JTextField();
        panelFormulario.add(txtSerie);

        panelFormulario.add(new JLabel("Ubicacion:"));
        txtUbicacion = new JTextField();
        panelFormulario.add(txtUbicacion);

        panelFormulario.add(new JLabel("Estado:"));
        cbEstado = new JComboBox<>(new String[]{"Activo", "Desactivado"});
        panelFormulario.add(cbEstado);

        panelFormulario.add(new JLabel("Fecha Inst. (YYYY-MM-DD):"));
        txtFechaIns = new JTextField();
        panelFormulario.add(txtFechaIns);

        panelFormulario.add(new JLabel("Asignar a Cliente:"));
        cbClientes = new JComboBox<>();
        panelFormulario.add(cbClientes);

        panelSuperior.add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel();
        btnGuardar = new JButton("Guardar Maquina");
        btnEliminar = new JButton("Eliminar Maquina Seleccionada");
        panelBoton.add(btnGuardar);
        panelBoton.add(btnEliminar);
        panelSuperior.add(panelBoton, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new String[]{"Codigo", "Serie", "Ubicacion", "Estado", "Fecha", "Cod. Cliente", "Cliente"}, 0);
        tablaMaquinas = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaMaquinas);
        scrollTabla.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        add(scrollTabla, BorderLayout.CENTER);
    }
}
