import java.util.concurrent.atomic.AtomicInteger;

public class Datos
{
    //ATRIBUTOS
    private AtomicInteger bitcoin, item; //Dato sincronizado

    //CONSTRUCTOR
    public Datos() {
        this.bitcoin = new AtomicInteger(); //Inicializa a 0
        this.item = new AtomicInteger();
    }

    //GETTERS Y SETTERS
    //getters
    public int getBitcoin() {
        return bitcoin.get();
    }

    public int getItem() {
        return item.get();
    }

    //setters
    public int addBitcoin(int x) {
            return bitcoin.addAndGet(x);
    }

    public int addItem(int x) {
        return this.item.addAndGet(x);
    }

    //MÉTODOS

}
