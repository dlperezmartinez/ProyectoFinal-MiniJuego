import Objects.BitcoinButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Ventana extends JFrame implements ActionListener
{
    //ATRIBUTOS
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); //Se utiliza para saber el tamaño de la pantalla
    BufferedImage bitcoinIcon = ImageIO.read(new File("/home/nocend/IdeaProjects/Proyecto final - Mini juego/src/Sprites/BitcoinIcon.png")); //Imagen para el icono del Bitcoin

    public Ventana() throws IOException {
        setTitle("Bitcoin Miner");
        setSize(800, 600);
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        setContentPane(panel);

        JButton bitcoin = new JButton(new ImageIcon(bitcoinIcon));


        
        bitcoin.setBounds(40, 40, 100, 40);

        bitcoin.setVisible(true);
        panel.add(bitcoin);




    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
