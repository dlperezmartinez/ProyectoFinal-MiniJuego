import java.awt.*;
import java.io.IOException;

public class run
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                CoinManager ventana = new CoinManager();
                ventana.setVisible(true);
            }
        });
    }
}
