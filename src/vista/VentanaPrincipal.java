package vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class VentanaPrincipal extends JFrame {

    private JPanel panelMenu;
    private JPanel panelCentral;
    private CardLayout cardLayout;

    public JButton btnProductos;
    public JButton btnInventarioBodega;
    public JButton btnInventarioMaquina;
    public JButton btnCompras;
    public JButton btnEmpleados;
    public JButton btnClientes;
    public JButton btnProveedores;
    public JButton btnMaquinaria;
    public JButton btnDespachos;
    public JButton btnAlertas;

    public PanelProducto panelFormularioProducto;
    public PanelInventarioBodega panelInventarioBodega;
    public PanelInventarioMaquina panelInventarioMaquina;
    public PanelProveedor panelFormularioProveedor;
    public PanelEmpleado panelFormularioEmpleado;
    public PanelCliente panelFormularioCliente;
    public PanelMaquina panelFormularioMaquina;
    public PanelOrdenCompra panelFormularioOrden;
    public PanelDespacho panelFormularioDespacho;
    public PanelAlertaStock panelAlertaStock;

    public VentanaPrincipal() {
        setTitle("Sistema de Gestion Logistica e Inventario");
        setSize(1150, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panelMenu = new JPanel();
        panelMenu.setLayout(new GridLayout(11, 1, 8, 8));
        panelMenu.setBackground(new Color(45, 52, 54));

        JLabel lblMenu = new JLabel("MENU PRINCIPAL", SwingConstants.CENTER);
        lblMenu.setForeground(Color.WHITE);
        lblMenu.setFont(new Font("Arial", Font.BOLD, 14));
        panelMenu.add(lblMenu);

        btnProductos = new JButton("Productos");
        btnInventarioBodega = new JButton("Inventario Bodega");
        btnInventarioMaquina = new JButton("Inventario Maquina");
        btnCompras = new JButton("Ordenes de Compra");
        btnEmpleados = new JButton("Empleados");
        btnClientes = new JButton("Clientes");
        btnProveedores = new JButton("Proveedores");
        btnMaquinaria = new JButton("Maquinaria");
        btnDespachos = new JButton("Despachos");
        btnAlertas = new JButton("Alertas Stock");

        panelMenu.add(btnProductos);
        panelMenu.add(btnInventarioBodega);
        panelMenu.add(btnInventarioMaquina);
        panelMenu.add(btnCompras);
        panelMenu.add(btnEmpleados);
        panelMenu.add(btnClientes);
        panelMenu.add(btnProveedores);
        panelMenu.add(btnMaquinaria);
        panelMenu.add(btnDespachos);
        panelMenu.add(btnAlertas);

        cardLayout = new CardLayout();
        panelCentral = new JPanel(cardLayout);

        panelFormularioProducto = new PanelProducto();
        panelInventarioBodega = new PanelInventarioBodega();
        panelInventarioMaquina = new PanelInventarioMaquina();
        panelFormularioProveedor = new PanelProveedor();
        panelFormularioEmpleado = new PanelEmpleado();
        panelFormularioCliente = new PanelCliente();
        panelFormularioMaquina = new PanelMaquina();
        panelFormularioOrden = new PanelOrdenCompra();
        panelFormularioDespacho = new PanelDespacho();
        panelAlertaStock = new PanelAlertaStock();

        panelCentral.add(panelFormularioProducto, "Productos");
        panelCentral.add(panelInventarioBodega, "InventarioBodega");
        panelCentral.add(panelInventarioMaquina, "InventarioMaquina");
        panelCentral.add(panelFormularioOrden, "Compras");
        panelCentral.add(panelFormularioEmpleado, "Empleados");
        panelCentral.add(panelFormularioCliente, "Clientes");
        panelCentral.add(panelFormularioProveedor, "Proveedores");
        panelCentral.add(panelFormularioMaquina, "Maquinaria");
        panelCentral.add(panelFormularioDespacho, "Despachos");
        panelCentral.add(panelAlertaStock, "Alertas");

        add(panelMenu, BorderLayout.WEST);
        add(panelCentral, BorderLayout.CENTER);
    }

    public CardLayout getCardLayout() { return cardLayout; }
    public JPanel getPanelCentral() { return panelCentral; }
}
