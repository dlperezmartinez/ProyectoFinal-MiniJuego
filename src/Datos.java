import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntBinaryOperator;

public class Datos
{
    //ATRIBUTOS
    private AtomicInteger contadorBitcoin, contadorRack, contadorG1, contadorG2, contadorG3, contadorG4; //Dato sincronizado

    //CONSTRUCTOR
    public Datos() {
        this.contadorBitcoin = new AtomicInteger(); //Inicializa a 0
        this.contadorRack = new AtomicInteger();
        this.contadorG1 = new AtomicInteger();
        this.contadorG2 = new AtomicInteger();
        this.contadorG3 = new AtomicInteger();
        this.contadorG4 = new AtomicInteger();
    }

    //GETTERS Y SETTERS
    //getters
    public int getContadorBitcoin() {
        return contadorBitcoin.get();
    }

    public int getContadorRack() {
        return contadorRack.get();
    }

    public int getContadorG1() {
        return contadorG1.get();
    }

    public int getContadorG2() {
        return contadorG2.get();
    }

    public int getContadorG3() {
        return contadorG3.get();
    }

    public int getContadorG4() {
        return contadorG4.get();
    }

    //setters
    public int addContadorBitcoin(int x) {
            return contadorBitcoin.addAndGet(x);
    }

    public int addContadorRack(int x) {
        if (contadorBitcoin.get() < 10)
        {
            return this.contadorRack.addAndGet(0);
        }
        else
        {
            contadorBitcoin.addAndGet(-10);
            CoinManager.contadorBitcoin.setText(contadorBitcoin.get());
            return this.contadorRack.addAndGet(x);
        }
    }

    public int addContadorG1(int x) {
        return this.contadorG1.addAndGet(x);
    }

    public int addContadorG2(int x) {
        return this.contadorG2.addAndGet(x);
    }

    public int addContadorG3(int x) {
        return this.contadorG3.addAndGet(x);
    }

    public int addContadorG4(int x) {
        return this.contadorG4.addAndGet(x);
    }

    //MÉTODOS

}
