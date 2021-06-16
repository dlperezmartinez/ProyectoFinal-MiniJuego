import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class ItemsTienda extends JButton
{
    //ATRIBUTOS
    private AtomicInteger contador;
    private String nombre;
    private int precio;
    private final double multiplicador;
    private final ImageIcon icon;
    private JButton button;
    Datos datos = new Datos();

    //CONSTRUCTOR
    public ItemsTienda(String nombre, int precio, double multiplicador, ImageIcon icon) {
        this.contador = new AtomicInteger();
        this.nombre = nombre;
        this.precio = precio;
        this.multiplicador = multiplicador;
        this.icon = icon;

        this.button = new JButton(this.nombre, this.icon); //(Nombre, Icono);
        this.button.setPreferredSize(new Dimension(200, 200));
        this.button.setHorizontalTextPosition(JButton.CENTER); //Establece la posición horizontal del texto (CENTRO en este caso)
        this.button.setVerticalTextPosition(JButton.BOTTOM); //Establece la posición vertical del texto (ABAJO en este caso)
        this.button.setContentAreaFilled(false); //Quitar el fondo del botón
        this.button.setText(this.nombre + " " + this.contador + " Precio: " + this.precio + " B");
        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addContador(datos.getContadorBitcoin());
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        this.button.setVisible(true);

    }

    //SETTERS Y GETTERS
    public AtomicInteger getContador() {
        return contador;
    }

    public int addContador(int bitcoins) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (bitcoins >= this.precio)
        {
            Sonido.cachin();
            return contador.addAndGet(1);
        }
        else
        {
            Sonido.error();
            return contador.addAndGet(0);
        }
    }

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

    public JButton getButton()
    {
        return this.button;
    }

    //MÉTODOS


}

