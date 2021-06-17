import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Scanner;

public class run extends JFrame
{
    public static void main(String[] args) throws InterruptedException {
        //ATRIBUTOS
        long taskTime = 0;
        long sleepTime = 1000 / 1;
        long bitcoinsPorSegundo;
        int cantidadG1, cantidadG2, cantidadG3, cantidadG4;

        //ATRIBUTOS
        JTabbedPane tabPane;
        JPanel miner, tienda;
        Botones bitcoinButton, rack, g1, g2, g3, g4; //g Graficas
        JTextArea bitcoinsPorSegundoTextArea;
        JLabel porSegundo = new JLabel("Por segundo: ");

        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize(); //Se utiliza para saber el tamaño de la pantalla

        //INSTANCIAS
        Sistema sistema = new Sistema();

        JFrame frame = new JFrame();

        File save = new File("save.txt");

        //Comienza música
        Thread music = new Thread() {
            public void run() {
                Clip clip;
                try {
                    AudioInputStream input = AudioSystem.getAudioInputStream(new File("Sounds" + sistema.getBarra() + "arcadeLoop.wav"));
                    clip = AudioSystem.getClip();
                    clip.open(input);
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                    clip.start();
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
            }
        };
        music.start();

        //Comienza el frame
        frame.setTitle("Bitcoin Miner");
        frame.setSize(950, 270);
        frame.setLocation(pantalla.width/2-frame.getSize().width/2, pantalla.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("Sprites" + sistema.getBarra() + "bitcoin.png");
        frame.setIconImage(icon.getImage());

        //PESTAÑAS
        tabPane = new JTabbedPane();
        frame.setContentPane(tabPane);

        //Pestaña MINER
        miner = new JPanel();
        miner.setLayout(new BorderLayout());

        JPanel panelWEST = new JPanel();
        JPanel panelCENTER = new JPanel();
        JPanel panelSOUTH = new JPanel();

        tabPane.addTab("Minado", miner); //Añade el panel miner a la pestaña

        //Pestaña TIENDA
        tienda = new JPanel();
        tienda.setLayout(new BorderLayout());
        tabPane.addTab("Tienda", tienda); //Añade el panel tienda a la pestaña

        //CONTENIDO MINER
        //botón bitCoin
        bitcoinButton = new Botones("Bitcoins",
                new ImageIcon("Sprites" + sistema.getBarra() + "bitcoin.png"),
                new ImageIcon("Sprites" + sistema.getBarra() + "bitcoinPressed.png"));

        JPanel panelGraficas = new JPanel();
        JPanel panelDescripcionGraficas = new JPanel();
        JPanel panelBitcoinsTienda = new JPanel();

        bitcoinButton.getActionListeners();

        //arreglos
        bitcoinsPorSegundoTextArea = new JTextArea();
        bitcoinsPorSegundoTextArea.setBackground(Color.getColor("beige"));

        //adds
        panelWEST.add(bitcoinButton.getButton());
        panelCENTER.add(bitcoinButton.getLabelBitcoin());
        panelCENTER.add(bitcoinButton.getCantidadBitcoin());
        panelSOUTH.add(porSegundo);
        panelSOUTH.add(bitcoinsPorSegundoTextArea);

        miner.add(panelWEST, BorderLayout.WEST);
        miner.add(panelCENTER, BorderLayout.CENTER);
        miner.add(panelSOUTH, BorderLayout.SOUTH);

        //CONTENIDO TIENDA
        //rack
        /*rack =  new Botones("Armario rack", 5, 5,
                new ImageIcon(sistema.getRuta() + "Sprites" + sistema.getBarra() + "rack.png"),
                new ImageIcon(sistema.getRuta() + "Sprites" + sistema.getBarra() + "rack.png"));
        rack.getActionListeners();*/

        //g1
        g1 =    new Botones("GT 220", 10, 1,
                new ImageIcon("Sprites" + sistema.getBarra() + "g1.png"),
                new ImageIcon("Sprites" + sistema.getBarra() + "g1Pressed.png"));
        g1.getActionListeners();

        //g2
        g2 =    new Botones("GTX 970", 100, 10,
                new ImageIcon("Sprites" + sistema.getBarra() + "g2.png"),
                new ImageIcon("Sprites" + sistema.getBarra() + "g2Pressed.png"));
        g2.getActionListeners();

        //g3
        g3 =    new Botones("Radeon VII", 1000, 50,
                new ImageIcon("Sprites" + sistema.getBarra() + "g3.png"),
                new ImageIcon("Sprites" + sistema.getBarra() + "g3Pressed.png"));
        g3.getActionListeners();

        //g4
        g4 =    new Botones("RTX 3090", 10000, 100,
                new ImageIcon("Sprites" + sistema.getBarra() + "g4.png"),
                new ImageIcon("Sprites" + sistema.getBarra() + "g4Pressed.png"));
        g4.getActionListeners();

        //MÓDULO GUARDAR PARTIDA
        frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                try {
                    FileWriter saveWrite = new FileWriter("save.txt");
                    saveWrite.write(String.valueOf((int) bitcoinButton.getDatos()));

                    saveWrite.write("\n" + String.valueOf(g1.getItem()));
                    saveWrite.write("\n" + String.valueOf(g2.getItem()));
                    saveWrite.write("\n" + String.valueOf(g3.getItem()));
                    saveWrite.write("\n" + String.valueOf(g4.getItem()));

                    saveWrite.write("\n" + String.valueOf(g1.getPrecio()));
                    saveWrite.write("\n" + String.valueOf(g2.getPrecio()));
                    saveWrite.write("\n" + String.valueOf(g3.getPrecio()));
                    saveWrite.write("\n" + String.valueOf(g4.getPrecio()));

                    saveWrite.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                System.out.println("Guardado con éxito");
                e.getWindow().dispose();
            }
        });

        //MÓDULO CARGAR PARTIDA
        int listaItems[] = new int[9];

        if (save.exists())
        {
            System.out.println("Cargado con éxito");

            Scanner in = null;
            try {
                in = new Scanner(save);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            for (int i = 0; i <= 8; i++)
            {
                listaItems[i] = Integer.valueOf(in.nextLine());
            }

            bitcoinButton.addDatos(listaItems[0]);

            g1.addItem(listaItems[1]);
            g1.setPrecio(listaItems[5]); //La posición 5 de la lista es el precio del item 1, item 2 es la posición 6 y así sucesivamente
            g1.setTextDesdeFuera(listaItems[1]);

            g2.addItem(listaItems[2]);
            g2.setPrecio(listaItems[6]);
            g2.setTextDesdeFuera(listaItems[2]);

            g3.addItem(listaItems[3]);
            g3.setPrecio(listaItems[7]);
            g3.setTextDesdeFuera(listaItems[3]);

            g4.addItem(listaItems[4]);
            g4.setPrecio(listaItems[8]);
            g4.setTextDesdeFuera(listaItems[4]);
        }
        else
        {
            JOptionPane.showMessageDialog(frame, "Dale a la monedita!");
        }

        //adds
        //panelGraficas
        //tienda.add(rack.getButton(), BorderLayout.NORTH);
        panelGraficas.add(g1.getButton());
        panelGraficas.add(g2.getButton());
        panelGraficas.add(g3.getButton());
        panelGraficas.add(g4.getButton());

        //panelDescripcionGraficas
        JLabel descG1 = new JLabel("Genera " + g1.getSumador() + "B/s");
        descG1.setPreferredSize(new Dimension(200, 20));
        descG1.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        panelDescripcionGraficas.add(descG1);

        JLabel descG2 = new JLabel("Genera " + g2.getSumador() + "B/s");
        descG2.setPreferredSize(new Dimension(200, 20));
        descG2.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        panelDescripcionGraficas.add(descG1);

        JLabel descG3 = new JLabel("Genera " + g3.getSumador() + "B/s");
        descG3.setPreferredSize(new Dimension(200, 20));
        descG3.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        panelDescripcionGraficas.add(descG1);

        JLabel descG4 = new JLabel("Genera " + g4.getSumador() + "B/s");
        descG4.setPreferredSize(new Dimension(200, 20));
        descG4.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        panelDescripcionGraficas.add(descG1);

        panelDescripcionGraficas.add(descG1);
        panelDescripcionGraficas.add(descG2);
        panelDescripcionGraficas.add(descG3);
        panelDescripcionGraficas.add(descG4);

        //panelBitcoinsTienda
        JLabel labelTiendaBitcoin = new JLabel("Bitcoins: ");
        panelBitcoinsTienda.add(labelTiendaBitcoin);

        JTextArea textareaBitcoins = new JTextArea();
        textareaBitcoins.setBackground(Color.getColor("beige"));
        panelBitcoinsTienda.add(textareaBitcoins);

        tienda.add(panelGraficas, BorderLayout.NORTH);
        tienda.add(panelDescripcionGraficas, BorderLayout.CENTER);
        tienda.add(panelBitcoinsTienda, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);

        //GAME LOOP
        int i = 0; //Para el timer

        while (true)
        {
            taskTime = System.currentTimeMillis();

            //TIMER
            System.out.println(i++); //timer

            //GENERADOR DE BITCOINS
            bitcoinsPorSegundo =    ((g1.getItem() * g1.getSumador())) +
                                    ((g2.getItem() * g2.getSumador())) +
                                    ((g3.getItem() * g3.getSumador())) +
                                    ((g4.getItem() * g4.getSumador()));
            bitcoinButton.addDatos (bitcoinsPorSegundo);

            bitcoinsPorSegundoTextArea.setText(String.valueOf(bitcoinsPorSegundo));

            //UPDATE CONTADOR DE BITCOINS
            bitcoinButton.getCantidadBitcoin().setText(String.valueOf(bitcoinButton.getDatos()));
            textareaBitcoins.setText(String.valueOf(bitcoinButton.getDatos()));

            taskTime = System.currentTimeMillis() - taskTime;

            if (sleepTime - taskTime > 0)
            {
                Thread.sleep(sleepTime - taskTime);
            }
        }
    }
}
