import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Datos
{
    //ATRIBUTOS
    private AtomicLong bitcoin;
    private AtomicInteger item; //Dato sincronizado


    //CONSTRUCTOR
    public Datos() {
        this.bitcoin = new AtomicLong(); //Inicializa a 0
        this.item = new AtomicInteger();
    }

    //GETTERS Y SETTERS
    //getters
    public long getBitcoin() {
        return bitcoin.get();
    }

    public int getItem() {
        return item.get();
    }

    //setters
    public long addBitcoin(long x) {
            return bitcoin.addAndGet(x);
    }

    public int addItem(int x) {
        return this.item.addAndGet(x);
    }

    //MÉTODOS

}
