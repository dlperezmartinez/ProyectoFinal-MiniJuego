import javax.sound.sampled.*;
import javax.sound.sampled.spi.AudioFileReader;
import javax.swing.*;
import javax.swing.text.JTextComponent;
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
    protected JButton bitCoin;
    protected JLabel numeroBitCoin;
    protected JTextArea contadorBitCoin;
    protected Icon bitcoinIcon, bitcoinIconPressed; //Imágenes
    protected Clip plin, cachin; //Sonidos
    protected int contador = 0;

    private String barra, sistema, ruta; //Se útiliza para saber sobre que sistema operativo está corriendo y cambiar el tipo de barra para las rutas.

    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize(); //Se utiliza para saber el tamaño de la pantalla


    public CoinManager()
    {
        //Establecer barra
        barra = establecerBarra(sistema = System.getProperty("os.name"));
        //ruta
        ruta = System.getProperty("user.dir") + barra + "src" + barra;

        //Comienza el frame
        setTitle("Bitcoin Miner");
        setSize(800, 600);
        setLocation(pantalla.width/2-this.getSize().width/2, pantalla.height/2-this.getSize().height/2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        //PESTAÑAS
        tabPane = new JTabbedPane();
        setContentPane(tabPane);

        //Pestaña miner
        miner = new JPanel();
        miner.setLayout(null);
        tabPane.addTab("miner", miner); //Añade el panel miner a la pestaña

        //Pestaña tienda
        tienda = new JPanel();
        tienda.setLayout(null);
        tabPane.addTab("Tienda", tienda); //Añade el panel tienda a la pestaña

        //CONTENIDO MINER
        //botón bitCoin
        System.out.println(System.getProperty("user.dir"));
        bitCoin = new JButton(bitcoinIcon = new ImageIcon(ruta + "Sprites" + barra + "bitcoin.png"));
        bitCoin.setSize(200, 200);
        bitCoin.setLocation(this.getWidth()/2-bitCoin.getWidth()/2, this.getHeight()/2-bitCoin.getHeight()/2); //TODO no está en el centro
        bitCoin.setContentAreaFilled(false); //Quitar el fondo del botón
        bitCoin.setBorderPainted(false); //Quitar el borde del botón
        bitCoin.setPressedIcon(bitcoinIconPressed = new ImageIcon(ruta + "Sprites" + barra + "bitcoinPressed.png"));
        bitCoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    contador += 1;
                    contadorBitCoin.setText(String.valueOf(contador));

                    //reproduce sonido
                    Sound(ruta + "Sounds" + barra + "plin.wav");
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
            }
        });
        miner.add(bitCoin);

        //contador de bitCoin
        numeroBitCoin = new JLabel("Bit Coins");
        numeroBitCoin.setSize(100, 20);
        numeroBitCoin.setLocation(5, 5);
        miner.add(numeroBitCoin);

        contadorBitCoin = new JTextArea();
        contadorBitCoin.setSize(50, 20);
        contadorBitCoin.setLocation(numeroBitCoin.getX()+numeroBitCoin.getWidth()+5, numeroBitCoin.getY());
        contadorBitCoin.setBackground(Color.getColor("beige"));
        contadorBitCoin.setForeground(Color.orange);
        contadorBitCoin.setText(String.valueOf(contador));
        miner.add(contadorBitCoin);




    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {

    }

    //SONIDOS necesita la ruta del archivo a reproducir
    private static void Sound(String ruta) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream plin = AudioSystem.getAudioInputStream(new File(ruta));
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
