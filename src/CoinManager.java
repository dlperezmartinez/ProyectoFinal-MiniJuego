import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoinManager extends JFrame implements ActionListener
{
    //ATRIBUTOS
    protected JTabbedPane tabPane;
    protected JPanel miner, tienda;
    protected Botones bitcoinButton, rack, g1, g2, g3, g4; //g Graficas
    protected JLabel labelBitcoin;
    protected JTextArea cantidadBitcoin, contadorRack, contadorG1, contadorG2, contadorG3, contadorG4;
    protected Icon iconBitcoin, iconBitcoinPressed, iconRack, iconG1, iconG2, iconG3, iconG4; //Imágenes
    protected Clip plin, cachin; //Sonidos

    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize(); //Se utiliza para saber el tamaño de la pantalla

    //INSTANCIAS
    Sistema sistema = new Sistema();

    public CoinManager() throws InterruptedException {

        //Comienza el frame
        setTitle("Bitcoin Miner");
        setSize(900, 270);
        setLocation(pantalla.width/2-this.getSize().width/2, pantalla.height/2-this.getSize().height/2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //PESTAÑAS
        tabPane = new JTabbedPane();
        setContentPane(tabPane);

        //Pestaña MINER
        miner = new JPanel();
        tabPane.addTab("miner", miner); //Añade el panel miner a la pestaña

        //Pestaña TIENDA
        tienda = new JPanel();
        tabPane.addTab("Tienda", tienda); //Añade el panel tienda a la pestaña

        //CONTENIDO MINER
        //botón bitCoin
        bitcoinButton = new Botones("Bitcoins", new ImageIcon(sistema.getRuta() + "Sprites" + sistema.getBarra() + "bitcoin.png"));
        bitcoinButton.getActionListeners();

        //adds
        miner.add(bitcoinButton.getButton(), BorderLayout.CENTER);
        miner.add(bitcoinButton.getLabelBitcoin(), BorderLayout.WEST);
        miner.add(bitcoinButton.getCantidadBitcoin(), BorderLayout.WEST);

        //CONTENIDO TIENDA
        //rack
        rack = new Botones("Armario rack", 5, 5, new ImageIcon(sistema.getRuta() + "Sprites" + sistema.getBarra() + "rack.png"));
        rack.getActionListeners();

        //g1
        g1 = new Botones("Gráfica 1", 7, 5, new ImageIcon(sistema.getRuta() + "Sprites" + sistema.getBarra() + "g1.png"));
        g1.getActionListeners();

        //g2
        g2 = new Botones("Gráfica 2", 10, 5, new ImageIcon(sistema.getRuta() + "Sprites" + sistema.getBarra() + "g2.png"));
        g2.getActionListeners();

        //g3
        g3 = new Botones("Gráfica 3", 13, 5, new ImageIcon(sistema.getRuta() + "Sprites" + sistema.getBarra() + "g3.png"));
        g3.getActionListeners();

        //g4
        g4 = new Botones("Gráfica 4", 15, 5, new ImageIcon(sistema.getRuta() + "Sprites" + sistema.getBarra() + "g4.png"));
        g4.getActionListeners();

        //label tienda
        JLabel labelTienda = new JLabel("Tienda"); //TODO

        tienda.add(rack.getButton(), BorderLayout.NORTH);
        tienda.add(g1.getButton(), BorderLayout.NORTH);
        tienda.add(g2.getButton(), BorderLayout.NORTH);
        tienda.add(g3.getButton(), BorderLayout.NORTH);
        tienda.add(g4.getButton(), BorderLayout.NORTH);

        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {

    }
}
