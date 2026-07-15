package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.*;
import modelo.*;
import vista.VentanaPrincipal;

public class ControladorPrincipal implements ActionListener {

    private VentanaPrincipal vista;
    private ProductoDAO productoDAO;
    private ProveedorDAO proveedorDAO;
    private EmpleadoDAO empleadoDAO;
    private ClienteDAO clienteDAO;
    private MaquinaDAO maquinaDAO;
    private OrdenCompraDAO ordenDAO;
    private DetalleOrdenCompraDAO detalleOrdenDAO;
    private DespachoDAO despachoDAO;
    private DetalleDespachoDAO detalleDespachoDAO;
    private InventarioBodegaDAO inventarioBodegaDAO;
    private InventarioMaquinaDAO inventarioMaquinaDAO;
    private AlertaStockDAO alertaStockDAO;

    public ControladorPrincipal(VentanaPrincipal vista) {
        this.vista = vista;
        this.productoDAO = new ProductoDAO();
        this.proveedorDAO = new ProveedorDAO();
        this.empleadoDAO = new EmpleadoDAO();
        this.clienteDAO = new ClienteDAO();
        this.maquinaDAO = new MaquinaDAO();
        this.ordenDAO = new OrdenCompraDAO();
        this.detalleOrdenDAO = new DetalleOrdenCompraDAO();
        this.despachoDAO = new DespachoDAO();
        this.detalleDespachoDAO = new DetalleDespachoDAO();
        this.inventarioBodegaDAO = new InventarioBodegaDAO();
        this.inventarioMaquinaDAO = new InventarioMaquinaDAO();
        this.alertaStockDAO = new AlertaStockDAO();

        this.vista.btnProductos.addActionListener(this);
        this.vista.btnInventarioBodega.addActionListener(this);
        this.vista.btnInventarioMaquina.addActionListener(this);
        this.vista.btnCompras.addActionListener(this);
        this.vista.btnEmpleados.addActionListener(this);
        this.vista.btnClientes.addActionListener(this);
        this.vista.btnProveedores.addActionListener(this);
        this.vista.btnMaquinaria.addActionListener(this);
        this.vista.btnDespachos.addActionListener(this);
        this.vista.btnAlertas.addActionListener(this);
        this.vista.btnCambiarRol.addActionListener(this);

        this.vista.panelFormularioProducto.btnGuardar.addActionListener(this);
        this.vista.panelFormularioProducto.btnEliminar.addActionListener(this);
        this.vista.panelFormularioProveedor.btnGuardar.addActionListener(this);
        this.vista.panelFormularioProveedor.btnEliminar.addActionListener(this);
        this.vista.panelFormularioEmpleado.btnGuardar.addActionListener(this);
        this.vista.panelFormularioEmpleado.btnEliminar.addActionListener(this);
        this.vista.panelFormularioCliente.btnGuardar.addActionListener(this);
        this.vista.panelFormularioCliente.btnEliminar.addActionListener(this);
        this.vista.panelFormularioMaquina.btnGuardar.addActionListener(this);
        this.vista.panelFormularioMaquina.btnEliminar.addActionListener(this);
        this.vista.panelFormularioOrden.btnAgregarProducto.addActionListener(this);
        this.vista.panelFormularioOrden.btnGuardar.addActionListener(this);
        this.vista.panelFormularioOrden.btnCompletar.addActionListener(this);
        this.vista.panelFormularioDespacho.btnAgregarProducto.addActionListener(this);
        this.vista.panelFormularioDespacho.btnEliminarProducto.addActionListener(this);
        this.vista.panelFormularioDespacho.btnGuardar.addActionListener(this);
        this.vista.panelInventarioBodega.btnActualizar.addActionListener(this);
        this.vista.panelInventarioMaquina.btnActualizar.addActionListener(this);
        this.vista.panelAlertaStock.btnActualizar.addActionListener(this);

        new DatosInicialesDAO().crearDatosSiHaceFalta();
        cargarTodasLasTablas();
    }

    private void cargarTodasLasTablas() {
        cargarTablaProductos();
        cargarTablaInventarioBodega();
        cargarTablaInventarioMaquina();
        cargarTablaProveedores();
        cargarTablaEmpleados();
        cargarTablaClientes();
        cargarTablaMaquinas();
        cargarTablaOrdenes();
        cargarTablaDespachos();
        cargarTablaAlertas();
        cargarComboBoxesDinamicos();
        cargarCodigosAutomaticos();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnProductos) vista.getCardLayout().show(vista.getPanelCentral(), "Productos");
        else if (e.getSource() == vista.btnInventarioBodega) vista.getCardLayout().show(vista.getPanelCentral(), "InventarioBodega");
        else if (e.getSource() == vista.btnInventarioMaquina) vista.getCardLayout().show(vista.getPanelCentral(), "InventarioMaquina");
        else if (e.getSource() == vista.btnCompras) vista.getCardLayout().show(vista.getPanelCentral(), "Compras");
        else if (e.getSource() == vista.btnEmpleados) vista.getCardLayout().show(vista.getPanelCentral(), "Empleados");
        else if (e.getSource() == vista.btnClientes) vista.getCardLayout().show(vista.getPanelCentral(), "Clientes");
        else if (e.getSource() == vista.btnProveedores) vista.getCardLayout().show(vista.getPanelCentral(), "Proveedores");
        else if (e.getSource() == vista.btnMaquinaria) vista.getCardLayout().show(vista.getPanelCentral(), "Maquinaria");
        else if (e.getSource() == vista.btnDespachos) vista.getCardLayout().show(vista.getPanelCentral(), "Despachos");
        else if (e.getSource() == vista.btnAlertas) vista.getCardLayout().show(vista.getPanelCentral(), "Alertas");
        else if (e.getSource() == vista.btnCambiarRol) vista.cambiarInterfaz();

        if (e.getSource() == vista.panelFormularioProducto.btnGuardar) guardarProducto();
        if (e.getSource() == vista.panelFormularioProducto.btnEliminar) eliminarProductoSeleccionado();
        if (e.getSource() == vista.panelFormularioProveedor.btnGuardar) guardarProveedor();
        if (e.getSource() == vista.panelFormularioProveedor.btnEliminar) eliminarProveedorSeleccionado();
        if (e.getSource() == vista.panelFormularioEmpleado.btnGuardar) guardarEmpleado();
        if (e.getSource() == vista.panelFormularioEmpleado.btnEliminar) eliminarEmpleadoSeleccionado();
        if (e.getSource() == vista.panelFormularioCliente.btnGuardar) guardarCliente();
        if (e.getSource() == vista.panelFormularioCliente.btnEliminar) eliminarClienteSeleccionado();
        if (e.getSource() == vista.panelFormularioMaquina.btnGuardar) guardarMaquina();
        if (e.getSource() == vista.panelFormularioMaquina.btnEliminar) eliminarMaquinaSeleccionada();
        if (e.getSource() == vista.panelFormularioOrden.btnAgregarProducto) agregarProductoAOrden();
        if (e.getSource() == vista.panelFormularioOrden.btnGuardar) guardarOrdenConDetalles();
        if (e.getSource() == vista.panelFormularioOrden.btnCompletar) completarOrdenSeleccionada();
        if (e.getSource() == vista.panelFormularioDespacho.btnAgregarProducto) agregarProductoADespacho();
        if (e.getSource() == vista.panelFormularioDespacho.btnEliminarProducto) eliminarProductoDetalleDespacho();
        if (e.getSource() == vista.panelFormularioDespacho.btnGuardar) guardarDespachoConDetalles();
        if (e.getSource() == vista.panelInventarioBodega.btnActualizar) cargarTablaInventarioBodega();
        if (e.getSource() == vista.panelInventarioMaquina.btnActualizar) cargarTablaInventarioMaquina();
        if (e.getSource() == vista.panelAlertaStock.btnActualizar) cargarTablaAlertas();
    }

    private void guardarProducto() {
        try {
            validarTexto(vista.panelFormularioProducto.txtNombre.getText(), "nombre del producto");
            validarTexto(vista.panelFormularioProducto.txtCategoria.getText(), "categoria del producto");
            validarTexto(vista.panelFormularioProducto.txtUnidad.getText(), "unidad del producto");
            Producto producto = new Producto(
                vista.panelFormularioProducto.txtCodigo.getText(),
                vista.panelFormularioProducto.txtNombre.getText().trim(),
                vista.panelFormularioProducto.txtCategoria.getText().trim(),
                validarDecimalPositivo(vista.panelFormularioProducto.txtPrecio.getText(), "precio"),
                vista.panelFormularioProducto.txtUnidad.getText().trim()
            );

            if (productoDAO.registrarProducto(producto)) {
                JOptionPane.showMessageDialog(null, "Producto registrado.");
                limpiarCajas(vista.panelFormularioProducto.txtNombre, vista.panelFormularioProducto.txtCategoria, vista.panelFormularioProducto.txtPrecio, vista.panelFormularioProducto.txtUnidad);
                cargarTablaProductos();
                cargarComboBoxesDinamicos();
                cargarCodigosAutomaticos();
            }
        } catch (Exception ex) {
            mostrarError(ex, "Error en los datos del producto.");
        }
    }

    private void guardarProveedor() {
        try {
            validarTexto(vista.panelFormularioProveedor.txtNombre.getText(), "nombre del proveedor");
            validarTexto(vista.panelFormularioProveedor.txtNit.getText(), "NIT del proveedor");
            validarTexto(vista.panelFormularioProveedor.txtTelefono.getText(), "telefono del proveedor");
            validarTexto(vista.panelFormularioProveedor.txtDireccion.getText(), "direccion del proveedor");
            validarTexto(vista.panelFormularioProveedor.txtContacto.getText(), "contacto del proveedor");
            Proveedor proveedor = new Proveedor(
                vista.panelFormularioProveedor.txtCodigo.getText(),
                vista.panelFormularioProveedor.txtNombre.getText().trim(),
                vista.panelFormularioProveedor.txtNit.getText().trim(),
                vista.panelFormularioProveedor.txtTelefono.getText().trim(),
                vista.panelFormularioProveedor.txtDireccion.getText().trim(),
                vista.panelFormularioProveedor.txtContacto.getText().trim()
            );

            if (proveedorDAO.registrarProveedor(proveedor)) {
                JOptionPane.showMessageDialog(null, "Proveedor registrado.");
                limpiarCajas(vista.panelFormularioProveedor.txtNombre, vista.panelFormularioProveedor.txtNit, vista.panelFormularioProveedor.txtTelefono, vista.panelFormularioProveedor.txtDireccion, vista.panelFormularioProveedor.txtContacto);
                cargarTablaProveedores();
                cargarComboBoxesDinamicos();
                cargarCodigosAutomaticos();
            }
        } catch (Exception ex) {
            mostrarError(ex, "Error en los datos del proveedor.");
        }
    }

    private void guardarEmpleado() {
        try {
            validarTexto(vista.panelFormularioEmpleado.txtNombre.getText(), "nombre del empleado");
            validarTexto(vista.panelFormularioEmpleado.txtTelefono.getText(), "telefono del empleado");
            Empleado empleado = new Empleado(
                vista.panelFormularioEmpleado.txtCodigo.getText(),
                vista.panelFormularioEmpleado.txtNombre.getText().trim(),
                vista.panelFormularioEmpleado.cbCargo.getSelectedItem().toString(),
                vista.panelFormularioEmpleado.txtTelefono.getText().trim(),
                validarFecha(vista.panelFormularioEmpleado.txtFechaIngreso.getText(), "fecha de ingreso")
            );

            if (empleadoDAO.registrarEmpleado(empleado)) {
                JOptionPane.showMessageDialog(null, "Empleado registrado.");
                limpiarCajas(vista.panelFormularioEmpleado.txtNombre, vista.panelFormularioEmpleado.txtTelefono, vista.panelFormularioEmpleado.txtFechaIngreso);
                cargarTablaEmpleados();
                cargarComboBoxesDinamicos();
                cargarCodigosAutomaticos();
            }
        } catch (Exception ex) {
            mostrarError(ex, "Error en los datos del empleado.");
        }
    }

    private void guardarCliente() {
        try {
            validarTexto(vista.panelFormularioCliente.txtNombre.getText(), "nombre del cliente");
            validarTexto(vista.panelFormularioCliente.txtDireccion.getText(), "direccion del cliente");
            validarTexto(vista.panelFormularioCliente.txtNit.getText(), "NIT del cliente");
            validarTexto(vista.panelFormularioCliente.txtContacto.getText(), "contacto del cliente");
            validarTexto(vista.panelFormularioCliente.txtTelefono.getText(), "telefono del cliente");
            Cliente cliente = new Cliente(
                vista.panelFormularioCliente.txtCodigo.getText(),
                vista.panelFormularioCliente.txtNombre.getText().trim(),
                vista.panelFormularioCliente.txtDireccion.getText().trim(),
                vista.panelFormularioCliente.txtNit.getText().trim(),
                vista.panelFormularioCliente.txtContacto.getText().trim(),
                vista.panelFormularioCliente.txtTelefono.getText().trim()
            );

            if (clienteDAO.registrarCliente(cliente)) {
                JOptionPane.showMessageDialog(null, "Cliente registrado.");
                limpiarCajas(vista.panelFormularioCliente.txtNombre, vista.panelFormularioCliente.txtDireccion, vista.panelFormularioCliente.txtNit, vista.panelFormularioCliente.txtContacto, vista.panelFormularioCliente.txtTelefono);
                cargarTablaClientes();
                cargarComboBoxesDinamicos();
                cargarCodigosAutomaticos();
            }
        } catch (Exception ex) {
            mostrarError(ex, "Error en los datos del cliente.");
        }
    }

    private void guardarMaquina() {
        try {
            validarCombo(vista.panelFormularioMaquina.cbClientes.getSelectedItem(), "cliente");
            validarTexto(vista.panelFormularioMaquina.txtSerie.getText(), "serie de la maquina");
            validarTexto(vista.panelFormularioMaquina.txtUbicacion.getText(), "ubicacion de la maquina");
            String codCli = obtenerCodigoSeleccionado(vista.panelFormularioMaquina.cbClientes.getSelectedItem().toString());
            Maquina maquina = new Maquina(
                vista.panelFormularioMaquina.txtCodigo.getText(),
                vista.panelFormularioMaquina.txtSerie.getText().trim(),
                vista.panelFormularioMaquina.txtUbicacion.getText().trim(),
                vista.panelFormularioMaquina.cbEstado.getSelectedItem().toString(),
                validarFecha(vista.panelFormularioMaquina.txtFechaIns.getText(), "fecha de instalacion"),
                codCli
            );

            if (maquinaDAO.registrarMaquina(maquina)) {
                JOptionPane.showMessageDialog(null, "Maquina registrada.");
                limpiarCajas(vista.panelFormularioMaquina.txtSerie, vista.panelFormularioMaquina.txtUbicacion, vista.panelFormularioMaquina.txtFechaIns);
                cargarTablaMaquinas();
                cargarComboBoxesDinamicos();
                cargarCodigosAutomaticos();
            }
        } catch (Exception ex) {
            mostrarError(ex, "Error en los datos de la maquina.");
        }
    }

    private void agregarProductoAOrden() {
        try {
            validarCombo(vista.panelFormularioOrden.cbProductos.getSelectedItem(), "producto");
            String item = vista.panelFormularioOrden.cbProductos.getSelectedItem().toString();
            String codProd = obtenerCodigoSeleccionado(item);
            String nomProd = obtenerNombreSeleccionado(item);
            validarProductoNoRepetido(vista.panelFormularioOrden.modeloDetalle, codProd, nomProd);
            LocalDate fechaVencLote = validarFecha(vista.panelFormularioOrden.txtFechaVencLote.getText(), "fecha de vencimiento del lote");
            int cantidad = validarEnteroPositivo(vista.panelFormularioOrden.txtCantidad.getText(), "cantidad");
            double precio = validarDecimalPositivo(vista.panelFormularioOrden.txtPrecioUnitario.getText(), "precio unitario");
            double subtotal = cantidad * precio;

            vista.panelFormularioOrden.modeloDetalle.addRow(new Object[]{codProd, nomProd, fechaVencLote, cantidad, precio, subtotal});
            limpiarCajas(vista.panelFormularioOrden.txtFechaVencLote, vista.panelFormularioOrden.txtCantidad, vista.panelFormularioOrden.txtPrecioUnitario);
        } catch (Exception ex) {
            mostrarError(ex, "Error al agregar producto a la orden.");
        }
    }

    private void guardarOrdenConDetalles() {
        try {
            validarCombo(vista.panelFormularioOrden.cbProveedores.getSelectedItem(), "proveedor");
            validarCombo(vista.panelFormularioOrden.cbEmpleados.getSelectedItem(), "empleado");
            if (vista.panelFormularioOrden.modeloDetalle.getRowCount() == 0) {
                throw new IllegalArgumentException("Agrega al menos un producto a la orden.");
            }

            String codOrden = vista.panelFormularioOrden.txtCodigo.getText();
            String codPro = obtenerCodigoSeleccionado(vista.panelFormularioOrden.cbProveedores.getSelectedItem().toString());
            String codEmp = obtenerCodigoSeleccionado(vista.panelFormularioOrden.cbEmpleados.getSelectedItem().toString());
            OrdenCompra orden = new OrdenCompra(
                codOrden,
                validarFecha(vista.panelFormularioOrden.txtFecha.getText(), "fecha de orden"),
                vista.panelFormularioOrden.cbEstado.getSelectedItem().toString(),
                parseFechaOpcional(vista.panelFormularioOrden.txtFechaRecepcion.getText()),
                codPro,
                codEmp
            );

            List<DetalleOrdenCompra> detalles = new ArrayList<>();
            for (int i = 0; i < vista.panelFormularioOrden.modeloDetalle.getRowCount(); i++) {
                String codProd = vista.panelFormularioOrden.modeloDetalle.getValueAt(i, 0).toString();
                LocalDate fechaVencLote = LocalDate.parse(vista.panelFormularioOrden.modeloDetalle.getValueAt(i, 2).toString());
                int cantidad = Integer.parseInt(vista.panelFormularioOrden.modeloDetalle.getValueAt(i, 3).toString());
                double precio = Double.parseDouble(vista.panelFormularioOrden.modeloDetalle.getValueAt(i, 4).toString());
                double subtotal = Double.parseDouble(vista.panelFormularioOrden.modeloDetalle.getValueAt(i, 5).toString());
                detalles.add(new DetalleOrdenCompra(codOrden, codProd, fechaVencLote, cantidad, precio, subtotal));
            }

            if (ordenDAO.registrarOrdenConDetalles(orden, detalles)) {
                JOptionPane.showMessageDialog(null, "Orden registrada.");
                limpiarCajas(vista.panelFormularioOrden.txtFecha, vista.panelFormularioOrden.txtFechaRecepcion);
                vista.panelFormularioOrden.modeloDetalle.setRowCount(0);
                cargarTablaOrdenes();
                cargarTablaInventarioBodega();
                cargarTablaAlertas();
                cargarCodigosAutomaticos();
            }
        } catch (Exception ex) {
            mostrarError(ex, "Error en los datos de la orden.");
        }
    }

    private void completarOrdenSeleccionada() {
        int fila = vista.panelFormularioOrden.tablaOrdenes.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Selecciona una orden en la tabla.");
            return;
        }
        String codOrden = vista.panelFormularioOrden.modeloTabla.getValueAt(fila, 0).toString();
        if (ordenDAO.completarOrden(codOrden)) {
            JOptionPane.showMessageDialog(null, "Orden completada y sumada al inventario de bodega.");
            cargarTablaOrdenes();
            cargarTablaInventarioBodega();
            cargarTablaAlertas();
        } else {
            JOptionPane.showMessageDialog(null, "La orden no se pudo completar. Revisa que este en proceso.");
        }
    }

    private void agregarProductoADespacho() {
        try {
            validarCombo(vista.panelFormularioDespacho.cbProductos.getSelectedItem(), "producto");
            String item = vista.panelFormularioDespacho.cbProductos.getSelectedItem().toString();
            String codProd = obtenerCodigoSeleccionado(item);
            String nomProd = obtenerNombreSeleccionado(item);
            validarProductoNoRepetido(vista.panelFormularioDespacho.modeloDetalle, codProd, nomProd);
            int cantidad = validarEnteroPositivo(vista.panelFormularioDespacho.txtCantidad.getText(), "cantidad");
            validarStockDisponibleParaDespacho(codProd, cantidad);
            vista.panelFormularioDespacho.modeloDetalle.addRow(new Object[]{codProd, nomProd, cantidad});
            limpiarCajas(vista.panelFormularioDespacho.txtCantidad);
        } catch (Exception ex) {
            mostrarError(ex, "Error al agregar producto al despacho.");
        }
    }

    private void guardarDespachoConDetalles() {
        try {
            validarCombo(vista.panelFormularioDespacho.cbEmpleados.getSelectedItem(), "empleado");
            validarCombo(vista.panelFormularioDespacho.cbMaquinas.getSelectedItem(), "maquina");
            if (vista.panelFormularioDespacho.modeloDetalle.getRowCount() == 0) {
                throw new IllegalArgumentException("Agrega al menos un producto al despacho.");
            }

            String codDesp = vista.panelFormularioDespacho.txtCodigo.getText();
            String codEmp = obtenerCodigoSeleccionado(vista.panelFormularioDespacho.cbEmpleados.getSelectedItem().toString());
            String codMaq = obtenerCodigoSeleccionado(vista.panelFormularioDespacho.cbMaquinas.getSelectedItem().toString());
            Despacho despacho = new Despacho(codDesp, validarFecha(vista.panelFormularioDespacho.txtFecha.getText(), "fecha de despacho"), codEmp, codMaq);

            List<DetalleDespacho> detalles = new ArrayList<>();
            for (int i = 0; i < vista.panelFormularioDespacho.modeloDetalle.getRowCount(); i++) {
                String codProd = vista.panelFormularioDespacho.modeloDetalle.getValueAt(i, 0).toString();
                int cantidad = Integer.parseInt(vista.panelFormularioDespacho.modeloDetalle.getValueAt(i, 2).toString());
                detalles.add(new DetalleDespacho(codDesp, codProd, cantidad));
            }

            if (despachoDAO.registrarDespachoConDetalles(despacho, detalles)) {
                JOptionPane.showMessageDialog(null, "Despacho registrado y movimiento de inventario aplicado.");
                limpiarCajas(vista.panelFormularioDespacho.txtFecha);
                vista.panelFormularioDespacho.modeloDetalle.setRowCount(0);
                cargarTablaDespachos();
                cargarTablaInventarioBodega();
                cargarTablaInventarioMaquina();
                cargarTablaAlertas();
                cargarCodigosAutomaticos();
            } else {
                JOptionPane.showMessageDialog(null, "No se registro el despacho. Revisa el stock de bodega.");
            }
        } catch (Exception ex) {
            mostrarError(ex, "Error en los datos del despacho.");
        }
    }

    private void eliminarProductoDetalleDespacho() {
        int fila = vista.panelFormularioDespacho.tablaDetalle.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Selecciona un producto del despacho actual.");
            return;
        }
        vista.panelFormularioDespacho.modeloDetalle.removeRow(fila);
    }

    private void eliminarProductoSeleccionado() {
        int fila = vista.panelFormularioProducto.tablaProductos.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Selecciona un producto en la tabla.");
            return;
        }
        String codigo = vista.panelFormularioProducto.modeloTabla.getValueAt(fila, 0).toString();
        String nombre = vista.panelFormularioProducto.modeloTabla.getValueAt(fila, 1).toString();
        if (confirmarEliminacion("producto", codigo, nombre) && productoDAO.eliminarProducto(codigo)) {
            cargarTablaProductos();
            cargarComboBoxesDinamicos();
            cargarCodigosAutomaticos();
            JOptionPane.showMessageDialog(null, "Producto eliminado.");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar. Revisa si tiene ordenes, despachos o inventario asociado.");
        }
    }

    private void eliminarProveedorSeleccionado() {
        int fila = vista.panelFormularioProveedor.tablaProveedores.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Selecciona un proveedor en la tabla.");
            return;
        }
        String codigo = vista.panelFormularioProveedor.modeloTabla.getValueAt(fila, 0).toString();
        String nombre = vista.panelFormularioProveedor.modeloTabla.getValueAt(fila, 1).toString();
        if (confirmarEliminacion("proveedor", codigo, nombre) && proveedorDAO.eliminarProveedor(codigo)) {
            cargarTablaProveedores();
            cargarComboBoxesDinamicos();
            cargarCodigosAutomaticos();
            JOptionPane.showMessageDialog(null, "Proveedor eliminado.");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar. Revisa si tiene ordenes asociadas.");
        }
    }

    private void eliminarEmpleadoSeleccionado() {
        int fila = vista.panelFormularioEmpleado.tablaEmpleados.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Selecciona un empleado en la tabla.");
            return;
        }
        String codigo = vista.panelFormularioEmpleado.modeloTabla.getValueAt(fila, 0).toString();
        String nombre = vista.panelFormularioEmpleado.modeloTabla.getValueAt(fila, 1).toString();
        if (confirmarEliminacion("empleado", codigo, nombre) && empleadoDAO.eliminarEmpleado(codigo)) {
            cargarTablaEmpleados();
            cargarComboBoxesDinamicos();
            cargarCodigosAutomaticos();
            JOptionPane.showMessageDialog(null, "Empleado eliminado.");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar. Revisa si tiene ordenes o despachos asociados.");
        }
    }

    private void eliminarClienteSeleccionado() {
        int fila = vista.panelFormularioCliente.tablaClientes.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Selecciona un cliente en la tabla.");
            return;
        }
        String codigo = vista.panelFormularioCliente.modeloTabla.getValueAt(fila, 0).toString();
        String nombre = vista.panelFormularioCliente.modeloTabla.getValueAt(fila, 1).toString();
        if (confirmarEliminacion("cliente", codigo, nombre) && clienteDAO.eliminarCliente(codigo)) {
            cargarTablaClientes();
            cargarComboBoxesDinamicos();
            cargarCodigosAutomaticos();
            JOptionPane.showMessageDialog(null, "Cliente eliminado.");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar. Revisa si tiene maquinas asociadas.");
        }
    }

    private void eliminarMaquinaSeleccionada() {
        int fila = vista.panelFormularioMaquina.tablaMaquinas.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Selecciona una maquina en la tabla.");
            return;
        }
        String codigo = vista.panelFormularioMaquina.modeloTabla.getValueAt(fila, 0).toString();
        String nombre = vista.panelFormularioMaquina.modeloTabla.getValueAt(fila, 1).toString() + " - " + vista.panelFormularioMaquina.modeloTabla.getValueAt(fila, 2).toString();
        if (confirmarEliminacion("maquina", codigo, nombre) && maquinaDAO.eliminarMaquina(codigo)) {
            cargarTablaMaquinas();
            cargarComboBoxesDinamicos();
            cargarCodigosAutomaticos();
            JOptionPane.showMessageDialog(null, "Maquina eliminada.");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar. Revisa si tiene despachos o inventario asociado.");
        }
    }

    private boolean confirmarEliminacion(String tipo, String codigo, String nombre) {
        int opcion = JOptionPane.showConfirmDialog(
            null,
            "Deseas eliminar " + tipo + " " + codigo + " - " + nombre + "?",
            "Confirmar eliminacion",
            JOptionPane.YES_NO_OPTION
        );
        return opcion == JOptionPane.YES_OPTION;
    }

    private void mostrarError(Exception ex, String mensajeGenerico) {
        String mensaje = ex instanceof IllegalArgumentException ? ex.getMessage() : mensajeGenerico;
        JOptionPane.showMessageDialog(null, mensaje);
    }

    private void validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo " + campo + " es obligatorio.");
        }
    }

    private void validarCombo(Object valor, String campo) {
        if (valor == null) {
            throw new IllegalArgumentException("Debes seleccionar " + campo + ".");
        }
    }

    private void validarProductoNoRepetido(DefaultTableModel modelo, String codProd, String nomProd) {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (codProd.equals(modelo.getValueAt(i, 0).toString())) {
                throw new IllegalArgumentException("El producto " + codProd + " - " + nomProd + " ya fue agregado a esta operacion.");
            }
        }
    }

    private void validarStockDisponibleParaDespacho(String codProd, int cantidadNueva) {
        int cantidadDisponible = inventarioBodegaDAO.obtenerCantidadDisponible(codProd);
        int cantidadYaAgregada = 0;
        for (int i = 0; i < vista.panelFormularioDespacho.modeloDetalle.getRowCount(); i++) {
            if (codProd.equals(vista.panelFormularioDespacho.modeloDetalle.getValueAt(i, 0).toString())) {
                cantidadYaAgregada += Integer.parseInt(vista.panelFormularioDespacho.modeloDetalle.getValueAt(i, 2).toString());
            }
        }
        if (cantidadDisponible < cantidadYaAgregada + cantidadNueva) {
            throw new IllegalArgumentException("No hay existencias suficientes en bodega. Disponible: " + cantidadDisponible + ".");
        }
    }

    private LocalDate validarFecha(String valor, String campo) {
        validarTexto(valor, campo);
        try {
            return LocalDate.parse(valor.trim());
        } catch (Exception ex) {
            throw new IllegalArgumentException("El campo " + campo + " debe tener formato YYYY-MM-DD.");
        }
    }

    private int validarEnteroPositivo(String valor, String campo) {
        validarTexto(valor, campo);
        try {
            int numero = Integer.parseInt(valor.trim());
            if (numero <= 0) {
                throw new NumberFormatException();
            }
            return numero;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("El campo " + campo + " debe ser un numero entero positivo.");
        }
    }

    private double validarDecimalPositivo(String valor, String campo) {
        validarTexto(valor, campo);
        try {
            double numero = Double.parseDouble(valor.trim());
            if (numero <= 0) {
                throw new NumberFormatException();
            }
            return numero;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("El campo " + campo + " debe ser un numero positivo.");
        }
    }

    private void limpiarCajas(javax.swing.JTextField... cajas) {
        for (javax.swing.JTextField caja : cajas) {
            caja.setText("");
        }
    }

    private String obtenerCodigoSeleccionado(String item) {
        return item.split(" - ")[0];
    }

    private String obtenerNombreSeleccionado(String item) {
        String[] partes = item.split(" - ", 2);
        return partes.length > 1 ? partes[1] : item;
    }

    private LocalDate parseFechaOpcional(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return null;
        }
        return validarFecha(texto, "fecha de recepcion");
    }

    private void cargarCodigosAutomaticos() {
        vista.panelFormularioProducto.txtCodigo.setText(productoDAO.obtenerSiguienteCodigo());
        vista.panelFormularioProveedor.txtCodigo.setText(proveedorDAO.obtenerSiguienteCodigo());
        vista.panelFormularioEmpleado.txtCodigo.setText(empleadoDAO.obtenerSiguienteCodigo());
        vista.panelFormularioCliente.txtCodigo.setText(clienteDAO.obtenerSiguienteCodigo());
        vista.panelFormularioMaquina.txtCodigo.setText(maquinaDAO.obtenerSiguienteCodigo());
        vista.panelFormularioOrden.txtCodigo.setText(ordenDAO.obtenerSiguienteCodigo());
        vista.panelFormularioDespacho.txtCodigo.setText(despachoDAO.obtenerSiguienteCodigo());
    }

    private void cargarTablaProductos() {
        DefaultTableModel modelo = vista.panelFormularioProducto.modeloTabla;
        modelo.setRowCount(0);
        for (Producto p : productoDAO.obtenerProductos()) {
            modelo.addRow(new Object[]{p.getCodProd(), p.getNomProd(), p.getCategProd(), p.getPrecioVenProd(), p.getUnidadMedProd()});
        }
    }

    private void cargarTablaProveedores() {
        DefaultTableModel modelo = vista.panelFormularioProveedor.modeloTabla;
        modelo.setRowCount(0);
        for (Proveedor p : proveedorDAO.obtenerProveedores()) {
            modelo.addRow(new Object[]{p.getCodPro(), p.getNomPro(), p.getNitPro(), p.getTelPro(), p.getDirPro(), p.getConPro()});
        }
    }

    private void cargarTablaEmpleados() {
        DefaultTableModel modelo = vista.panelFormularioEmpleado.modeloTabla;
        modelo.setRowCount(0);
        for (Empleado emp : empleadoDAO.obtenerEmpleados()) {
            modelo.addRow(new Object[]{emp.getCodEmp(), emp.getNomEmp(), emp.getCargoEmp(), emp.getTelEmp(), emp.getFechaIngEmp()});
        }
    }

    private void cargarTablaClientes() {
        DefaultTableModel modelo = vista.panelFormularioCliente.modeloTabla;
        modelo.setRowCount(0);
        for (Cliente c : clienteDAO.obtenerClientes()) {
            modelo.addRow(new Object[]{c.getCodCli(), c.getNomCli(), c.getDirCli(), c.getNit(), c.getConCli(), c.getTelCli()});
        }
    }

    private void cargarTablaMaquinas() {
        DefaultTableModel modelo = vista.panelFormularioMaquina.modeloTabla;
        modelo.setRowCount(0);
        Map<String, String> clientes = mapaClientes();
        for (Maquina m : maquinaDAO.obtenerMaquinas()) {
            modelo.addRow(new Object[]{m.getCodMaq(), m.getCodSerie(), m.getUbicMaq(), m.getEstadoMaq(), m.getFechaInsMaq(), m.getCodCli(), clientes.getOrDefault(m.getCodCli(), "")});
        }
    }

    private void cargarTablaOrdenes() {
        DefaultTableModel modelo = vista.panelFormularioOrden.modeloTabla;
        modelo.setRowCount(0);
        Map<String, OrdenCompra> ordenes = new HashMap<>();
        Map<String, String> proveedores = mapaProveedores();
        Map<String, String> empleados = mapaEmpleados();
        Map<String, String> productos = mapaProductos();
        for (OrdenCompra oc : ordenDAO.obtenerOrdenes()) {
            ordenes.put(oc.getCodOrdCom(), oc);
        }

        List<DetalleOrdenCompra> detalles = detalleOrdenDAO.obtenerDetalles();
        for (DetalleOrdenCompra detalle : detalles) {
            OrdenCompra oc = ordenes.get(detalle.getCodOrdCom());
            if (oc != null) {
                modelo.addRow(new Object[]{
                    oc.getCodOrdCom(),
                    oc.getFechaOrdCom(),
                    oc.getEstadoOrdCom(),
                    oc.getFechaRecOrdCom(),
                    oc.getCodPro(),
                    proveedores.getOrDefault(oc.getCodPro(), ""),
                    oc.getCodEmp(),
                    empleados.getOrDefault(oc.getCodEmp(), ""),
                    detalle.getCodProd(),
                    productos.getOrDefault(detalle.getCodProd(), ""),
                    detalle.getFechaVencLote(),
                    detalle.getCantSol(),
                    detalle.getPrecioUni(),
                    detalle.getSubTotal()
                });
            }
        }
    }

    private void cargarTablaDespachos() {
        DefaultTableModel modelo = vista.panelFormularioDespacho.modeloTabla;
        modelo.setRowCount(0);
        Map<String, Despacho> despachos = new HashMap<>();
        Map<String, String> empleados = mapaEmpleados();
        Map<String, String> maquinas = mapaMaquinas();
        Map<String, String> productos = mapaProductos();
        for (Despacho d : despachoDAO.obtenerDespachos()) {
            despachos.put(d.getCodDesp(), d);
        }

        List<DetalleDespacho> detalles = detalleDespachoDAO.obtenerDetalles();
        for (DetalleDespacho detalle : detalles) {
            Despacho d = despachos.get(detalle.getCodDesp());
            if (d != null) {
                modelo.addRow(new Object[]{
                    d.getCodDesp(),
                    d.getFechaDesp(),
                    d.getCodEmp(),
                    empleados.getOrDefault(d.getCodEmp(), ""),
                    d.getCodMaq(),
                    maquinas.getOrDefault(d.getCodMaq(), ""),
                    detalle.getCodProd(),
                    productos.getOrDefault(detalle.getCodProd(), ""),
                    detalle.getCantidadDesp()
                });
            }
        }
    }

    private void cargarTablaInventarioBodega() {
        DefaultTableModel modelo = vista.panelInventarioBodega.modeloTabla;
        modelo.setRowCount(0);
        Map<String, String> productos = mapaProductos();
        for (InventarioBodega inv : inventarioBodegaDAO.obtenerInventarios()) {
            modelo.addRow(new Object[]{inv.getCodProd(), productos.getOrDefault(inv.getCodProd(), ""), inv.getCantDisp(), inv.getStockMin(), inv.getFechaUltAct()});
        }
    }

    private void cargarTablaInventarioMaquina() {
        DefaultTableModel modelo = vista.panelInventarioMaquina.modeloTabla;
        modelo.setRowCount(0);
        Map<String, String> maquinas = mapaMaquinas();
        Map<String, String> productos = mapaProductos();
        for (InventarioMaquina inv : inventarioMaquinaDAO.obtenerInventarios()) {
            modelo.addRow(new Object[]{inv.getCodMaq(), maquinas.getOrDefault(inv.getCodMaq(), ""), inv.getCodProd(), productos.getOrDefault(inv.getCodProd(), ""), inv.getCantAct(), inv.getStockMin(), inv.getFechaUltRec()});
        }
    }

    private void cargarTablaAlertas() {
        DefaultTableModel modelo = vista.panelAlertaStock.modeloTabla;
        modelo.setRowCount(0);
        for (AlertaStock alerta : alertaStockDAO.obtenerAlertas()) {
            modelo.addRow(new Object[]{alerta.getTipoAlerta(), alerta.getCodProd(), alerta.getNomProd(), alerta.getUbicacion(), alerta.getCantidadActual(), alerta.getStockMin(), alerta.getFechaVenc()});
        }
    }

    private Map<String, String> mapaProductos() {
        Map<String, String> mapa = new HashMap<>();
        for (Producto p : productoDAO.obtenerProductos()) {
            mapa.put(p.getCodProd(), p.getNomProd());
        }
        return mapa;
    }

    private Map<String, String> mapaProveedores() {
        Map<String, String> mapa = new HashMap<>();
        for (Proveedor p : proveedorDAO.obtenerProveedores()) {
            mapa.put(p.getCodPro(), p.getNomPro());
        }
        return mapa;
    }

    private Map<String, String> mapaEmpleados() {
        Map<String, String> mapa = new HashMap<>();
        for (Empleado emp : empleadoDAO.obtenerEmpleados()) {
            mapa.put(emp.getCodEmp(), emp.getNomEmp());
        }
        return mapa;
    }

    private Map<String, String> mapaClientes() {
        Map<String, String> mapa = new HashMap<>();
        for (Cliente c : clienteDAO.obtenerClientes()) {
            mapa.put(c.getCodCli(), c.getNomCli());
        }
        return mapa;
    }

    private Map<String, String> mapaMaquinas() {
        Map<String, String> mapa = new HashMap<>();
        for (Maquina m : maquinaDAO.obtenerMaquinas()) {
            mapa.put(m.getCodMaq(), m.getCodSerie() + " - " + m.getUbicMaq());
        }
        return mapa;
    }

    private void cargarComboBoxesDinamicos() {
        vista.panelFormularioMaquina.cbClientes.removeAllItems();
        for (Cliente c : clienteDAO.obtenerClientes()) {
            vista.panelFormularioMaquina.cbClientes.addItem(c.getCodCli() + " - " + c.getNomCli());
        }

        vista.panelFormularioOrden.cbProveedores.removeAllItems();
        for (Proveedor p : proveedorDAO.obtenerProveedores()) {
            vista.panelFormularioOrden.cbProveedores.addItem(p.getCodPro() + " - " + p.getNomPro());
        }

        vista.panelFormularioOrden.cbEmpleados.removeAllItems();
        vista.panelFormularioDespacho.cbEmpleados.removeAllItems();
        for (Empleado emp : empleadoDAO.obtenerEmpleados()) {
            String item = emp.getCodEmp() + " - " + emp.getNomEmp();
            vista.panelFormularioOrden.cbEmpleados.addItem(item);
            vista.panelFormularioDespacho.cbEmpleados.addItem(item);
        }

        vista.panelFormularioOrden.cbProductos.removeAllItems();
        vista.panelFormularioDespacho.cbProductos.removeAllItems();
        for (Producto p : productoDAO.obtenerProductos()) {
            String item = p.getCodProd() + " - " + p.getNomProd();
            vista.panelFormularioOrden.cbProductos.addItem(item);
            vista.panelFormularioDespacho.cbProductos.addItem(item);
        }

        vista.panelFormularioDespacho.cbMaquinas.removeAllItems();
        for (Maquina m : maquinaDAO.obtenerMaquinas()) {
            vista.panelFormularioDespacho.cbMaquinas.addItem(m.getCodMaq() + " - Serie: " + m.getCodSerie());
        }
    }
}
