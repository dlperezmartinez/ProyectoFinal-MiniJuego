import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoinManager extends JFrame implements ActionListener
{
    //ATRIBUTOS
    protected JTabbedPane tabPane;
    protected JPanel miner, tienda;
    protected JButton bitCoin;
    protected JLabel numeroBitCoin;
    protected JTextComponent contadorBitCoin;
    protected Icon bitcoinIcon, bitcoinIconPressed;

    private String barra, sistema; //Se útiliza para saber sobre que sistema operativo está corriendo y cambiar el tipo de barra para las rutas.

    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize(); //Se utiliza para saber el tamaño de la pantalla


    public CoinManager()
    {
        //Establecer barra
        barra = establecerBarra(sistema = System.getProperty("os.name"));

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
        bitCoin = new JButton(bitcoinIcon = new ImageIcon(System.getProperty("user.dir") + barra + "src" + barra + "Sprites" + barra + "bitcoin.png"));
        bitCoin.setSize(200, 200);
        bitCoin.setLocation(this.getWidth()/2-bitCoin.getWidth()/2, this.getHeight()/2-bitCoin.getHeight()/2); //TODO no está en el centro
        bitCoin.setContentAreaFilled(false); //Quitar el fondo del botón
        bitCoin.setBorderPainted(false); //Quitar el borde del botón
        bitCoin.setPressedIcon(bitcoinIconPressed = new ImageIcon(System.getProperty("user.dir") + barra + "src" + barra + "Sprites" + barra + "bitcoinPressed.png"));
        miner.add(bitCoin);

        //contador de bitCoin
        numeroBitCoin = new JLabel("Bit Coins");



    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    private static String establecerBarra(String sistema) //Método para establecer la barra según el SO.
    {
        if (sistema == "Windows 10") //En Windows 10 \
        {
            return "\\";
        }
        else //He decidido suponer que si no es Windows 10 es cualquier tipo de linux.
        {
            return "/";
        }
    }
}
