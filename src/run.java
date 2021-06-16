import javax.swing.*;
import java.awt.*;

public class run
{
    public static void main(String[] args) throws InterruptedException {
        //ATRIBUTOS
        long taskTime = 0;
        long sleepTime = 1000 / 1;

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //Comienza el game
                CoinManager cm = null;
                try {
                    cm = new CoinManager();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cm.setVisible(true);
            }
        });



        while (true)
        {
            taskTime = System.currentTimeMillis();

            System.out.println("e");

            taskTime = System.currentTimeMillis() - taskTime;

            if (sleepTime - taskTime > 0)
            {
                Thread.sleep(sleepTime - taskTime);
            }
        }



    }
}
