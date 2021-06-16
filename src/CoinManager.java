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

    //INSTANCIAS


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
        setLocationRelativeTo(null);

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
                //actualiza el contador de bitcoin
                datos.addContadorBitcoin(1);
                contadorBitCoin.setText(String.valueOf(datos.getContadorBitcoin()));

                //reproduce sonido
                try { //PLIN
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
                if (datos.getContadorBitcoin() >= 5)
                {
                    try { //PLIN
                        soundPlin(ruta, barra);
                    } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                        unsupportedAudioFileException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (LineUnavailableException lineUnavailableException) {
                        lineUnavailableException.printStackTrace();
                    }
                    datos.addContadorRack(1);
                    rack.setText("Armario rack " + datos.getContadorRack() + " Price: 5 B");
                    contadorBitCoin.setText(String.valueOf(datos.getContadorBitcoin()));
                }
                else
                {
                    try { //ERROR
                        soundError(ruta, barra);
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

        //g1
        g1 = new JButton("Gráfica 1" + String.valueOf(datos.getContadorG1()) + " Price: 10 B", iconG1 = new ImageIcon(ruta + "Sprites" + barra + "g1.png")); //(Nombre del botón, imagen)
        g1.setSize(200, 200);
        g1.setHorizontalTextPosition(JButton.CENTER); //Establece la posición horizontal del texto (CENTRO en este caso)
        g1.setVerticalTextPosition(JButton.BOTTOM); //Establece la posición vertical del texto (ABAJO en este caso)
        g1.setContentAreaFilled(false); //Quitar el fondo del botón
        g1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (datos.getContadorBitcoin() >= 10)
                {
                    try { //PLIN
                        soundPlin(ruta, barra);
                    } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                        unsupportedAudioFileException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (LineUnavailableException lineUnavailableException) {
                        lineUnavailableException.printStackTrace();
                    }
                    datos.addContadorG1(1);
                    g1.setText("Gráfica 1 " + datos.getContadorG1() + " Price: 10 B");
                    contadorBitCoin.setText(String.valueOf(datos.getContadorBitcoin()));
                }
                else
                {
                    try { //ERROR
                        soundError(ruta, barra);
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

        //g2
        g2 = new JButton("Gráfica 2" + String.valueOf(datos.getContadorG2()) + " Price: 15 B", iconG2 = new ImageIcon(ruta + "Sprites" + barra + "g2.png")); //(Nombre del botón, imagen)
        g2.setSize(200, 200);
        g2.setHorizontalTextPosition(JButton.CENTER); //Establece la posición horizontal del texto (CENTRO en este caso)
        g2.setVerticalTextPosition(JButton.BOTTOM); //Establece la posición vertical del texto (ABAJO en este caso)
        g2.setContentAreaFilled(false); //Quitar el fondo del botón
        g2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (datos.getContadorBitcoin() >= 15)
                {
                    try { //PLIN
                        soundPlin(ruta, barra);
                    } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                        unsupportedAudioFileException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (LineUnavailableException lineUnavailableException) {
                        lineUnavailableException.printStackTrace();
                    }
                    datos.addContadorG2(1);
                    g2.setText("Gráfica 2 " + datos.getContadorG2() + " Price: 15 B");
                    contadorBitCoin.setText(String.valueOf(datos.getContadorBitcoin()));
                }
                else
                {
                    try { //ERROR
                        soundError(ruta, barra);
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


        //g3
        g3 = new JButton("Gráfica 3" + String.valueOf(datos.getContadorG3()) + " Price: 20 B", iconG3 = new ImageIcon(ruta + "Sprites" + barra + "g3.png")); //(Nombre del botón, imagen)
        g3.setSize(200, 200);
        g3.setHorizontalTextPosition(JButton.CENTER); //Establece la posición horizontal del texto (CENTRO en este caso)
        g3.setVerticalTextPosition(JButton.BOTTOM); //Establece la posición vertical del texto (ABAJO en este caso)
        g3.setContentAreaFilled(false); //Quitar el fondo del botón
        rack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (datos.getContadorBitcoin() >= 20)
                {
                    try { //PLIN
                        soundPlin(ruta, barra);
                    } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                        unsupportedAudioFileException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (LineUnavailableException lineUnavailableException) {
                        lineUnavailableException.printStackTrace();
                    }
                    datos.addContadorG3(1);
                    g3.setText("Gráfica 3 " + datos.getContadorG3() + " Price: 20 B");
                    contadorBitCoin.setText(String.valueOf(datos.getContadorBitcoin()));
                }
                else
                {
                    try { //ERROR
                        soundError(ruta, barra);
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


        //g4
        ItemsTienda g4I = new ItemsTienda("Gráfica 4", 25, 5, new ImageIcon(ruta + "Sprites" + barra + "g4.png"));
        g4I.getActionListeners();

        /*g4 = new JButton(g4I.getNombre() + " " + String.valueOf(g4I.getContador()) + " Price: " + String.valueOf(g4I.getPrecio()) + "B", iconG4 = new ImageIcon(ruta + "Sprites" + barra + "g4.png")); //(Nombre del botón, imagen)
        g4.setSize(200, 200);
        g4.setHorizontalTextPosition(JButton.CENTER); //Establece la posición horizontal del texto (CENTRO en este caso)
        g4.setVerticalTextPosition(JButton.BOTTOM); //Establece la posición vertical del texto (ABAJO en este caso)
        g4.setContentAreaFilled(false); //Quitar el fondo del botón
        rack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (datos.getContadorBitcoin() >= 25)
                {
                    try { //PLIN
                        soundPlin(ruta, barra);
                    } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                        unsupportedAudioFileException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (LineUnavailableException lineUnavailableException) {
                        lineUnavailableException.printStackTrace();
                    }
                    datos.addContadorG4(1);
                    g4.setText("Gráfica 4 " + datos.getContadorG4() + " Price: 25 B");
                    contadorBitCoin.setText(String.valueOf(datos.getContadorBitcoin()));
                }
                else
                {
                    try { //ERROR
                        soundError(ruta, barra);
                    } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                        unsupportedAudioFileException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (LineUnavailableException lineUnavailableException) {
                        lineUnavailableException.printStackTrace();
                    }
                }
            }
        });*/


        //label tienda
        JLabel labelTienda = new JLabel("Tienda"); //TODO

        tienda.add(rack, BorderLayout.NORTH);
        tienda.add(g1, BorderLayout.NORTH);
        tienda.add(g2, BorderLayout.NORTH);
        tienda.add(g3, BorderLayout.NORTH);
        tienda.add(g4I.getButton(), BorderLayout.NORTH);

        pack();
        setVisible(true);
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

    private static void soundError(String ruta, String barra) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream plin = AudioSystem.getAudioInputStream(new File(ruta + "Sounds" + barra + "error.wav"));
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

    public String getBarra() {
        return barra;
    }

    public String getSistema() {
        return sistema;
    }

    public String getRuta() {
        return ruta;
    }
}
