import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoinManager extends JFrame implements ActionListener
{
    //ATRIBUTOS
    protected JTabbedPane minerPane;
    protected JMenuBar menuBar;
    protected JButton miner, tienda;

    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); //Se utiliza para saber el tamaño de la pantalla


    public CoinManager()
    {
        setTitle("Bitcoin Miner");
        setSize(800, 600);
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        JPanel panel = new JPanel();
        setContentPane(panel);

        minerPane = new JTabbedPane(); //TODO investigar el JTabbedPane
        panel.add(BorderLayout.NORTH, minerPane);

        miner = new JButton("Miner");
        panel.add(BorderLayout.SOUTH, miner);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
