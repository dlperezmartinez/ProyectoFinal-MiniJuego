import java.util.concurrent.atomic.AtomicInteger;

public class Datos
{
    //ATRIBUTOS
    private AtomicInteger contadorBitcoin; //Dato sincronizado
    private int contadorRack, contadorG1, contadorG2, contadorG3, contadorG4;

    //CONSTRUCTOR
    public Datos() {
        this.contadorBitcoin = new AtomicInteger(); //Inicializa a 0
        this.contadorRack = 0;
        this.contadorG1 = 0;
        this.contadorG2 = 0;
        this.contadorG3 = 0;
        this.contadorG4 = 0;
    }

    //GETTERS Y SETTERS
    //getters
    public AtomicInteger getContadorBitcoin() {
        return contadorBitcoin;
    }

    public int getContadorRack() {
        return contadorRack;
    }

    public int getContadorG1() {
        return contadorG1;
    }

    public int getContadorG2() {
        return contadorG2;
    }

    public int getContadorG3() {
        return contadorG3;
    }

    public int getContadorG4() {
        return contadorG4;
    }

    //setters
    public void setContadorBitcoin(AtomicInteger contadorBitcoin) {
        this.contadorBitcoin = contadorBitcoin;
    }

    public void setContadorRack(int contadorRack) {
        this.contadorRack = contadorRack;
    }

    public void setContadorG1(int contadorG1) {
        this.contadorG1 = contadorG1;
    }

    public void setContadorG2(int contadorG2) {
        this.contadorG2 = contadorG2;
    }

    public void setContadorG3(int contadorG3) {
        this.contadorG3 = contadorG3;
    }

    public void setContadorG4(int contadorG4) {
        this.contadorG4 = contadorG4;
    }
}
