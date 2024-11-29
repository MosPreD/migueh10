import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MostrarImagen {
    public static void main(String[] args) {
        // Crear ventana principal
        JFrame frame = new JFrame("Información del Usuario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(4, 2)); // Ajustamos el layout para incluir el documento

        // Componentes para el nombre
        JLabel nombreLabel = new JLabel("Ingrese su nombre:");
        JTextField nombreField = new JTextField();
        frame.add(nombreLabel);
        frame.add(nombreField);

        // Componentes para la edad
        JLabel edadLabel = new JLabel("Ingrese su edad:");
        JTextField edadField = new JTextField();
        frame.add(edadLabel);
        frame.add(edadField);

        // Componentes para el número de documento
        JLabel docLabel = new JLabel("Ingrese su documento:");
        JTextField docField = new JTextField();
        frame.add(docLabel);
        frame.add(docField);

        // Botón para mostrar imagen
        JButton mostrarButton = new JButton("Mostrar Imagen");
        frame.add(new JLabel()); // Espacio vacío en el grid
        frame.add(mostrarButton);

        // Configurar eventos de teclado para pasar al siguiente campo con Enter
        nombreField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    edadField.requestFocus();
                }
            }
        });

        edadField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    docField.requestFocus();
                }
            }
        });

        docField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    mostrarButton.doClick();
                }
            }
        });

        // Acción del botón
        mostrarButton.addActionListener(e -> {
            String nombre = nombreField.getText();
            String edad = edadField.getText();
            String documento = docField.getText();

            // Mostrar un mensaje con los datos ingresados
            JOptionPane.showMessageDialog(frame, 
                "¡Hola " + nombre + "!\nTienes " + edad + " años.\nDocumento: " + documento);

            // Intentar cargar y mostrar una imagen aleatoria
            try {
                // Rutas de las imágenes (modifica esto con tus rutas)
                String[] rutasImagenes = {
                    "/C://Users//NOTEBOOK//Downloads//file.png/", 
                    "C:/Users/NOTEBOOK/Downloads/f1.jpg" 
                };

                // Selección aleatoria de la imagen
                Random random = new Random();
                int indiceAleatorio = random.nextInt(rutasImagenes.length);
                String rutaSeleccionada = rutasImagenes[indiceAleatorio];

                // Cargar y verificar la imagen seleccionada
                ImageIcon imageIcon = new ImageIcon(rutaSeleccionada);
                if (imageIcon.getIconWidth() <= 0) { // Si la imagen no carga correctamente
                    throw new Exception("No se pudo cargar la imagen seleccionada. Verifica las rutas.");
                }

                // Redimensionar la imagen para que encaje en el JFrame
                Image imagenEscalada = imageIcon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
                ImageIcon imagenFinal = new ImageIcon(imagenEscalada);

                // Crear un JLabel con la imagen y mostrarlo en una nueva ventana
                JLabel imageLabel = new JLabel(imagenFinal);
                JFrame imageFrame = new JFrame("Este eres tu");
                imageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                imageFrame.setSize(500, 500);
                imageFrame.add(imageLabel);
                imageFrame.setVisible(true);
            } catch (Exception ex) {
                // Mostrar mensaje de error si no se encuentra la imagen o hay otro problema
                JOptionPane.showMessageDialog(frame, 
                    "Error al cargar la imagen: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Mostrar la ventana principal
        frame.setVisible(true);
    }
}
