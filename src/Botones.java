import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Botones extends JButton
{
    public static int bitcoin; //cantidad de bitcoins

    //ATRIBUTOS
    private String nombre;
    private int precio;
    private long sumador;
    private final ImageIcon icon;
    private JButton button;
    private final JLabel labelBitcoin = new JLabel("Bitcoins");
    private JTextArea cantidadBitcoin = new JTextArea();

    private static Datos datos = new Datos();
    private Datos cantidad;

    //CONSTRUCTOR
    //BITCOIN BUTTON
    public Botones (String nombre, ImageIcon icon, ImageIcon iconPressed)
    {
        this.nombre = nombre;
        this.icon = icon;

        this.button = new JButton(null, this.icon); //(Nombre, Icono);
        this.button.setPreferredSize(new Dimension(200, 200));
        this.button.setContentAreaFilled(false); //Quitar el fondo del botón
        this.button.setBorderPainted(false); //Quitar el borde
        this.button.setFocusPainted(false); //Quitar el indicador de que ha sido pulsado
        this.button.setPressedIcon(iconPressed);


        this.labelBitcoin.setFont(labelBitcoin.getFont().deriveFont(Font.BOLD, 64.0f));
        this.labelBitcoin.setPreferredSize(new Dimension(350, 200));

        this.cantidadBitcoin.setBackground(Color.getColor("beige")); //Color
        this.cantidadBitcoin.setFont(cantidadBitcoin.getFont().deriveFont(Font.BOLD, 64.0f));

        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cantidadBitcoin.setText(String.valueOf(datos.addBitcoin(1)));

                try {
                    Sonido.plin();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
            }
        });
        this.button.setVisible(true);
    }

    //ITEMS TIENDA
    public Botones(String nombre, int precio, long sumador, ImageIcon icon, ImageIcon iconPressed) {
        this.nombre = nombre;
        this.precio = precio;
        this.sumador = sumador;
        this.icon = icon;
        this.cantidad = new Datos();

        this.button = new JButton(this.nombre, this.icon); //(Nombre, Icono);
        this.button.setPreferredSize(new Dimension(200, 200));
        this.button.setHorizontalTextPosition(JButton.CENTER); //Establece la posición horizontal del texto (CENTRO en este caso)
        this.button.setVerticalTextPosition(JButton.BOTTOM); //Establece la posición vertical del texto (ABAJO en este caso)
        this.button.setContentAreaFilled(false); //Quitar el fondo del botón
        this.button.setBorderPainted(false); //Quitar el borde
        this.button.setFocusPainted(false); //Quitar el indicador de que ha sido pulsado
        this.button.setPressedIcon(iconPressed);
        this.button.setText(this.nombre + " " + cantidad.getItem() + " Precio: " + this.precio + "B");

        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (datos.getBitcoin() >= getPrecio())
                {
                    datos.addBitcoin(-getPrecio());
                    setPrecio(getPrecio()*2);
                    button.setText(getNombre() + " " + cantidad.addItem(1) + " Precio: " + getPrecio() + " B");

                    try {
                        Sonido.cachin();
                    } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                        unsupportedAudioFileException.printStackTrace();
                    } catch (LineUnavailableException lineUnavailableException) {
                        lineUnavailableException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                else
                {
                    try {
                        Sonido.error();
                    } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                        unsupportedAudioFileException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (LineUnavailableException lineUnavailableException) {
                        lineUnavailableException.printStackTrace();
                    }
                }
            }
        });
        this.button.setVisible(true);
    }

    //SETTERS Y GETTERS
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public long getSumador() {
        return sumador;
    }

    public JButton getButton()
    {
        return this.button;
    }

    public JLabel getLabelBitcoin()
    {
        return this.labelBitcoin;
    }

    public JTextArea getCantidadBitcoin()
    {
        return this.cantidadBitcoin;
    }

    public long getDatos()
    {
        return datos.getBitcoin();
    }

    public void addDatos(long x)
    {
        datos.addBitcoin(x);
    }

    public int getItem()
    {
        return cantidad.getItem();
    }

    public void addItem(int x)
    {
        cantidad.addItem(x);
    }

    public void setTextDesdeFuera(int item)
    {
        button.setText(this.getNombre() + " " + item + " Precio: " + this.getPrecio() + " B");
    }
}

