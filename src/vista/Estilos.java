package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;

public final class Estilos {

    public static final Color AZUL = new Color(0x60, 0x82, 0xC4);
    public static final Color FONDO = Color.WHITE;
    public static final Color TEXTO = new Color(35, 40, 50);
    public static final Color BORDE = new Color(222, 229, 242);

    private Estilos() {
    }

    public static void configurarAparienciaGlobal() {
        UIManager.put("OptionPane.background", FONDO);
        UIManager.put("Panel.background", FONDO);
        UIManager.put("Button.focus", AZUL);
        UIManager.put("ComboBox.background", FONDO);
    }

    public static void aplicar(Component componente) {
        if (componente instanceof JPanel) {
            JPanel panel = (JPanel) componente;
            panel.setBackground(FONDO);
            if (panel.getBorder() instanceof TitledBorder) {
                TitledBorder borde = (TitledBorder) panel.getBorder();
                borde.setTitleColor(AZUL);
                borde.setBorder(new LineBorder(BORDE, 1, true));
            }
        } else if (componente instanceof JButton) {
            estilizarBoton((JButton) componente);
        } else if (componente instanceof JLabel) {
            estilizarEtiqueta((JLabel) componente);
        } else if (componente instanceof JTextField) {
            estilizarCampo((JTextField) componente);
        } else if (componente instanceof JComboBox) {
            estilizarCombo((JComboBox<?>) componente);
        } else if (componente instanceof JTable) {
            estilizarTabla((JTable) componente);
        } else if (componente instanceof JScrollPane) {
            ((JScrollPane) componente).getViewport().setBackground(FONDO);
        } else if (componente instanceof JSplitPane) {
            ((JSplitPane) componente).setBackground(FONDO);
        }

        if (componente instanceof Container) {
            for (Component hijo : ((Container) componente).getComponents()) {
                aplicar(hijo);
            }
        }
    }

    public static void estilizarMenu(JPanel panelMenu) {
        panelMenu.setBackground(FONDO);
        panelMenu.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 0, 3, AZUL),
            BorderFactory.createEmptyBorder(18, 14, 18, 14)
        ));
    }

    public static void estilizarTituloMenu(JLabel etiqueta) {
        etiqueta.setOpaque(true);
        etiqueta.setBackground(AZUL);
        etiqueta.setForeground(Color.WHITE);
        etiqueta.setFont(new Font("Segoe UI", Font.BOLD, 14));
        etiqueta.setBorder(new EmptyBorder(12, 10, 12, 10));
    }

    private static void estilizarBoton(JButton boton) {
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setBackground(AZUL);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        boton.setBorder(new EmptyBorder(8, 14, 8, 14));
    }

    private static void estilizarEtiqueta(JLabel etiqueta) {
        if (AZUL.equals(etiqueta.getBackground()) && etiqueta.isOpaque()) {
            etiqueta.setForeground(Color.WHITE);
            return;
        }
        etiqueta.setForeground(etiqueta.getFont().isBold() && etiqueta.getFont().getSize() >= 18 ? AZUL : TEXTO);
        etiqueta.setFont(new Font("Segoe UI", etiqueta.getFont().getStyle(), etiqueta.getFont().getSize()));
    }

    private static void estilizarCampo(JTextField campo) {
        campo.setBackground(FONDO);
        campo.setForeground(TEXTO);
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        campo.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(BORDE, 1, true),
            new EmptyBorder(6, 8, 6, 8)
        ));
    }

    private static void estilizarCombo(JComboBox<?> combo) {
        combo.setBackground(FONDO);
        combo.setForeground(TEXTO);
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        combo.setBorder(new LineBorder(BORDE, 1, true));
    }

    private static void estilizarTabla(JTable tabla) {
        tabla.setBackground(FONDO);
        tabla.setForeground(TEXTO);
        tabla.setGridColor(BORDE);
        tabla.setRowHeight(26);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tabla.setSelectionBackground(new Color(228, 235, 250));
        tabla.setSelectionForeground(TEXTO);

        JTableHeader encabezado = tabla.getTableHeader();
        encabezado.setBackground(AZUL);
        encabezado.setForeground(Color.WHITE);
        encabezado.setFont(new Font("Segoe UI", Font.BOLD, 12));
        encabezado.setBorder(new LineBorder(AZUL));
    }
}
