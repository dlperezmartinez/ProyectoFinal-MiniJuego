import javax.swing.*;
import java.awt.*;

public class CoinManager extends JFrame
{
    //ATRIBUTOS
    JMenuBar menuBar;
    JMenuItem miner, tienda;

    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); //Se utiliza para saber el tamaño de la pantalla


    public CoinManager()
    {
        setTitle("Bitcoin Miner");
        setSize(800, 600);
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setLayout(null);
        setVisible(true);

        setJMenuBar(new JMenuBar());
        menuBar.add(miner = new JMenuItem("Miner"));
    }
}
