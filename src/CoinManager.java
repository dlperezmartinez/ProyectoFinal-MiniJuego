import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class CoinManager extends JFrame implements ActionListener
{
    //ATRIBUTOS
    protected JTabbedPane tabPane;
    protected JPanel miner, tienda;
    protected JButton bitCoin, rack, g1, g2, g3, g4; //g Graficas
    protected JLabel numeroBitCoin;
    protected JTextArea contadorBitCoin, contadorRack, contadorG1, contadorG2, contadorG3, contadorG4;
    protected Icon iconBitcoin, iconBitcoinPressed, iconRack, iconG1, iconG2, iconG3, iconG4; //Imágenes
    protected Clip plin, cachin; //Sonidos

    private String barra, sistema, ruta; //Se útiliza para saber sobre que sistema operativo está corriendo y cambiar el tipo de barra para las rutas.

    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize(); //Se utiliza para saber el tamaño de la pantalla
    Datos datos = new Datos();

    public CoinManager() throws InterruptedException {
        //Establecer barra
        barra = establecerBarra(sistema = System.getProperty("os.name"));
        //ruta
        ruta = System.getProperty("user.dir") + barra + "src" + barra;

        //Comienza el frame
        setTitle("Bitcoin Miner");
        setSize(900, 270);
        setLocation(pantalla.width/2-this.getSize().width/2, pantalla.height/2-this.getSize().height/2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        //PESTAÑAS
        tabPane = new JTabbedPane();
        setContentPane(tabPane);

        //Pestaña MINER
        miner = new JPanel();
        //miner.setLayout(null);
        tabPane.addTab("miner", miner); //Añade el panel miner a la pestaña

        //Pestaña TIENDA
        tienda = new JPanel();
        //tienda.setLayout(null);
        tabPane.addTab("Tienda", tienda); //Añade el panel tienda a la pestaña

        //CONTENIDO MINER
        //botón bitCoin
        bitCoin = new JButton(iconBitcoin = new ImageIcon(ruta + "Sprites" + barra + "bitcoin.png"));
        bitCoin.setSize(200, 200);
        //bitCoin.setLocation(this.getWidth()/2-bitCoin.getWidth()/2, this.getHeight()/2-bitCoin.getHeight()/2); //TODO no está en el centro
        bitCoin.setContentAreaFilled(false); //Quitar el fondo del botón
        bitCoin.setBorderPainted(false); //Quitar el borde del botón
        bitCoin.setPressedIcon(iconBitcoinPressed = new ImageIcon(ruta + "Sprites" + barra + "bitcoinPressed.png"));
        bitCoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //actualiza el contador de bitcoin
                    datos.addContadorBitcoin(1);
                    contadorBitCoin.setText(String.valueOf(datos.getContadorBitcoin()));

                    //reproduce sonido
                    soundPlin(ruta, barra);
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
            }
        });

        //contador de bitCoin
        numeroBitCoin = new JLabel("Bit Coins");
        numeroBitCoin.setSize(100, 20);
        numeroBitCoin.setLocation(5, 5);

        contadorBitCoin = new JTextArea();
        contadorBitCoin.setSize(50, 20);
        contadorBitCoin.setLocation(numeroBitCoin.getX()+numeroBitCoin.getWidth()+5, numeroBitCoin.getY());
        contadorBitCoin.setBackground(Color.getColor("beige"));
        contadorBitCoin.setForeground(Color.orange);

        //adds
        miner.add(bitCoin, BorderLayout.WEST);
        miner.add(contadorBitCoin, BorderLayout.SOUTH);
        miner.add(numeroBitCoin, BorderLayout.SOUTH);

        //CONTENIDO TIENDA
        //rack
        rack = new JButton("Armario rack " + String.valueOf(datos.getContadorRack()) + " Price: 10 B", iconRack = new ImageIcon(ruta + "Sprites" + barra + "rack.png")); //(Nombre del botón, imagen)
        rack.setSize(200, 200);
        rack.setHorizontalTextPosition(JButton.CENTER); //Establece la posición horizontal del texto (CENTRO en este caso)
        rack.setVerticalTextPosition(JButton.BOTTOM); //Establece la posición vertical del texto (ABAJO en este caso)
        rack.setContentAreaFilled(false); //Quitar el fondo del botón
        rack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    datos.addContadorRack(1);
                    rack.setText("Armario rack " + datos.getContadorRack() + " Price: 10 B");

                    soundCachin(ruta, barra);
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
            }
        });

        /*contadorRack = new JTextArea();
        contadorRack.setSize(20, 20);
        contadorRack.setText(String.valueOf(datos.getContadorRack())); //Valor inicial 0*/

        //g1
        g1 = new JButton("Gráfica 1", iconG1 = new ImageIcon(ruta + "Sprites" + barra + "g1.png")); //(Nombre del botón, imagen)
        g1.setSize(200, 200);
        g1.setHorizontalTextPosition(JButton.CENTER); //Establece la posición horizontal del texto (CENTRO en este caso)
        g1.setVerticalTextPosition(JButton.BOTTOM); //Establece la posición vertical del texto (ABAJO en este caso)
        g1.setContentAreaFilled(false); //Quitar el fondo del botón

        contadorG1 = new JTextArea();
        contadorG1.setText(String.valueOf(datos.getContadorG1())); //Valor inicial 0

        //g2
        g2 = new JButton("Gráfica 2", iconG2 = new ImageIcon(ruta + "Sprites" + barra + "g2.png")); //(Nombre del botón, imagen)
        g2.setSize(200, 200);
        g2.setHorizontalTextPosition(JButton.CENTER); //Establece la posición horizontal del texto (CENTRO en este caso)
        g2.setVerticalTextPosition(JButton.BOTTOM); //Establece la posición vertical del texto (ABAJO en este caso)
        g2.setContentAreaFilled(false); //Quitar el fondo del botón

        contadorG2 = new JTextArea();
        contadorG2.setText(String.valueOf(datos.getContadorG2())); //Valor inicial 0

        //g3
        g3 = new JButton("Gráfica 3", iconG3 = new ImageIcon(ruta + "Sprites" + barra + "g3.png")); //(Nombre del botón, imagen)
        g3.setSize(200, 200);
        g3.setHorizontalTextPosition(JButton.CENTER); //Establece la posición horizontal del texto (CENTRO en este caso)
        g3.setVerticalTextPosition(JButton.BOTTOM); //Establece la posición vertical del texto (ABAJO en este caso)
        g3.setContentAreaFilled(false); //Quitar el fondo del botón

        contadorG3 = new JTextArea();
        contadorG3.setText(String.valueOf(datos.getContadorG3())); //Valor inicial 0

        //g4
        g4 = new JButton("Gráfica 4", iconG4 = new ImageIcon(ruta + "Sprites" + barra + "g4.png")); //(Nombre del botón, imagen)
        g4.setSize(200, 200);
        g4.setHorizontalTextPosition(JButton.CENTER); //Establece la posición horizontal del texto (CENTRO en este caso)
        g4.setVerticalTextPosition(JButton.BOTTOM); //Establece la posición vertical del texto (ABAJO en este caso)
        g4.setContentAreaFilled(false); //Quitar el fondo del botón

        contadorG4 = new JTextArea();
        contadorG4.setText(String.valueOf(datos.getContadorG4())); //Valor inicial 0

        //label tienda
        JLabel labelTienda = new JLabel("Tienda");

        tienda.add(rack, BorderLayout.NORTH);
        tienda.add(g1, BorderLayout.NORTH);
        tienda.add(g2, BorderLayout.NORTH);
        tienda.add(g3, BorderLayout.NORTH);
        tienda.add(g4, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {

    }

    //SONIDOS necesita la ruta del archivo a reproducir
    private static void soundPlin(String ruta, String barra) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream plin = AudioSystem.getAudioInputStream(new File(ruta + "Sounds" + barra + "plin.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(plin);
        clip.start();
    }

    private static void soundCachin(String ruta, String barra) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream plin = AudioSystem.getAudioInputStream(new File(ruta + "Sounds" + barra + "cachin.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(plin);
        clip.start();
    }

    private static String establecerBarra(String sistema) //Método para establecer la barra según el SO.
    {
        if (sistema == "Windows 10") //En Windows 10 "\"
        {
            return "\\";
        }
        else //He decidido suponer que si no es Windows 10 es cualquier tipo de linux.
        {
            return "/";
        }
    }
}
