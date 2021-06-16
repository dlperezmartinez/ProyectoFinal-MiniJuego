import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

public class run extends JFrame
{
    public static void main(String[] args) throws InterruptedException {
        //ATRIBUTOS
        long taskTime = 0;
        long sleepTime = 1000 / 1;

        //ATRIBUTOS
        JTabbedPane tabPane;
        JPanel miner, tienda;
        Botones bitcoinButton, rack, g1, g2, g3, g4; //g Graficas
        JLabel labelBitcoin;
        JTextArea cantidadBitcoin, contadorRack, contadorG1, contadorG2, contadorG3, contadorG4;
        Icon iconBitcoin, iconBitcoinPressed, iconRack, iconG1, iconG2, iconG3, iconG4; //Imágenes
        Clip plin, cachin; //Sonidos

        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize(); //Se utiliza para saber el tamaño de la pantalla

        //INSTANCIAS
        Sistema sistema = new Sistema();

        JFrame frame = new JFrame();

        //Comienza el frame
        frame.setTitle("Bitcoin Miner");
        frame.setSize(900, 270);
        frame.setLocation(pantalla.width/2-frame.getSize().width/2, pantalla.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        //PESTAÑAS
        tabPane = new JTabbedPane();
        frame.setContentPane(tabPane);

        //Pestaña MINER
        miner = new JPanel();
        tabPane.addTab("Minado", miner); //Añade el panel miner a la pestaña

        //Pestaña TIENDA
        tienda = new JPanel();
        tabPane.addTab("Tienda", tienda); //Añade el panel tienda a la pestaña

        //CONTENIDO MINER
        //botón bitCoin
        bitcoinButton = new Botones("Bitcoins",
                new ImageIcon(sistema.getRuta() + "Sprites" + sistema.getBarra() + "bitcoin.png"),
                new ImageIcon(sistema.getRuta() + "Sprites" + sistema.getBarra() + "bitcoinPressed.png"));
        bitcoinButton.getActionListeners();

        //adds
        miner.add(bitcoinButton.getButton(), BorderLayout.CENTER);
        miner.add(bitcoinButton.getLabelBitcoin(), BorderLayout.WEST);
        miner.add(bitcoinButton.getCantidadBitcoin(), BorderLayout.WEST);

        //CONTENIDO TIENDA
        //rack
        rack =  new Botones("Armario rack", 5, 5,
                new ImageIcon(sistema.getRuta() + "Sprites" + sistema.getBarra() + "rack.png"),
                new ImageIcon(sistema.getRuta() + "Sprites" + sistema.getBarra() + "rack.png"));
        rack.getActionListeners();

        //g1
        g1 =    new Botones("Gráfica 1", 7, 5,
                new ImageIcon(sistema.getRuta() + "Sprites" + sistema.getBarra() + "g1.png"),
                new ImageIcon(sistema.getRuta() + "Sprites" + sistema.getBarra() + "g1.png"));
        g1.getActionListeners();

        //g2
        g2 =    new Botones("Gráfica 2", 10, 5,
                new ImageIcon(sistema.getRuta() + "Sprites" + sistema.getBarra() + "g2.png"),
                new ImageIcon(sistema.getRuta() + "Sprites" + sistema.getBarra() + "g2.png"));
        g2.getActionListeners();

        //g3
        g3 =    new Botones("Gráfica 3", 13, 5,
                new ImageIcon(sistema.getRuta() + "Sprites" + sistema.getBarra() + "g3.png"),
                new ImageIcon(sistema.getRuta() + "Sprites" + sistema.getBarra() + "g3.png"));
        g3.getActionListeners();

        //g4
        g4 =    new Botones("Gráfica 4", 15, 5,
                new ImageIcon(sistema.getRuta() + "Sprites" + sistema.getBarra() + "g4.png"),
                new ImageIcon(sistema.getRuta() + "Sprites" + sistema.getBarra() + "g4.png"));
        g4.getActionListeners();

        //label tienda
        JLabel labelTienda = new JLabel("Tienda"); //TODO

        tienda.add(rack.getButton(), BorderLayout.NORTH);
        tienda.add(g1.getButton(), BorderLayout.NORTH);
        tienda.add(g2.getButton(), BorderLayout.NORTH);
        tienda.add(g3.getButton(), BorderLayout.NORTH);
        tienda.add(g4.getButton(), BorderLayout.NORTH);

        frame.pack();
        frame.setVisible(true);

        int i = 0;        while (true)
        {
            taskTime = System.currentTimeMillis();

            System.out.println(i++);



            taskTime = System.currentTimeMillis() - taskTime;

            if (sleepTime - taskTime > 0)
            {
                Thread.sleep(sleepTime - taskTime);
            }
        }



    }
}
