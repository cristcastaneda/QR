package vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    public JButton btnCambiarRol;

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
    private String interfazSeleccionada;

    public VentanaPrincipal() {
        Estilos.configurarAparienciaGlobal();
        interfazSeleccionada = seleccionarInterfaz();
        setTitle("Sistema de Gestion Logistica e Inventario - " + interfazSeleccionada);
        setSize(1150, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panelMenu = new JPanel();
        Estilos.estilizarMenu(panelMenu);

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
        btnCambiarRol = new JButton("Cambiar Rol");

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

        configurarMenuPorInterfaz();
        add(panelMenu, BorderLayout.WEST);
        add(panelCentral, BorderLayout.CENTER);
        Estilos.aplicar(this);
        Estilos.estilizarMenu(panelMenu);
        mostrarPantallaInicial();
    }

    private String seleccionarInterfaz() {
        String[] opciones = {"Jefe Comercial", "Repartidor", "Operador de Bodega"};
        int seleccion = JOptionPane.showOptionDialog(
            null,
            "Selecciona la interfaz con la que vas a trabajar:",
            "Seleccion de interfaz",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );
        if (seleccion < 0) {
            return opciones[2];
        }
        return opciones[seleccion];
    }

    private void configurarMenuPorInterfaz() {
        panelMenu.removeAll();

        if ("Jefe Comercial".equals(interfazSeleccionada)) {
            panelMenu.setLayout(new GridLayout(5, 1, 8, 8));
            agregarTituloMenu("JEFE COMERCIAL");
            panelMenu.add(btnClientes);
            panelMenu.add(btnMaquinaria);
            panelMenu.add(btnInventarioMaquina);
            panelMenu.add(btnCambiarRol);
        } else if ("Repartidor".equals(interfazSeleccionada)) {
            panelMenu.setLayout(new GridLayout(6, 1, 8, 8));
            agregarTituloMenu("REPARTIDOR");
            panelMenu.add(btnDespachos);
            panelMenu.add(btnMaquinaria);
            panelMenu.add(btnInventarioMaquina);
            panelMenu.add(btnProductos);
            panelMenu.add(btnCambiarRol);
        } else {
            panelMenu.setLayout(new GridLayout(7, 1, 8, 8));
            agregarTituloMenu("OPERADOR BODEGA");
            panelMenu.add(btnCompras);
            panelMenu.add(btnInventarioBodega);
            panelMenu.add(btnAlertas);
            panelMenu.add(btnProductos);
            panelMenu.add(btnProveedores);
            panelMenu.add(btnCambiarRol);
        }
        panelMenu.revalidate();
        panelMenu.repaint();
    }

    private void agregarTituloMenu(String titulo) {
        JLabel lblMenu = new JLabel(titulo, SwingConstants.CENTER);
        lblMenu.setFont(new Font("Arial", Font.BOLD, 14));
        Estilos.estilizarTituloMenu(lblMenu);
        panelMenu.add(lblMenu);
    }

    private void mostrarPantallaInicial() {
        if ("Jefe Comercial".equals(interfazSeleccionada)) {
            cardLayout.show(panelCentral, "Clientes");
        } else if ("Repartidor".equals(interfazSeleccionada)) {
            cardLayout.show(panelCentral, "Despachos");
        } else {
            cardLayout.show(panelCentral, "Compras");
        }
    }

    public CardLayout getCardLayout() { return cardLayout; }
    public JPanel getPanelCentral() { return panelCentral; }
    public String getInterfazSeleccionada() { return interfazSeleccionada; }

    public void cambiarInterfaz() {
        interfazSeleccionada = seleccionarInterfaz();
        setTitle("Sistema de Gestion Logistica e Inventario - " + interfazSeleccionada);
        configurarMenuPorInterfaz();
        Estilos.aplicar(this);
        Estilos.estilizarMenu(panelMenu);
        mostrarPantallaInicial();
    }
}
