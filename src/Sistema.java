public class Sistema {

    private String barra, sistema, ruta; //Se útiliza para saber sobre que sistema operativo está corriendo y cambiar el tipo de barra para las rutas.

    public Sistema()
    {
        //Establecer barra
        barra = establecerBarra(sistema = System.getProperty("os.name"));
        //ruta
        ruta = System.getProperty("user.dir") + barra + "src" + barra;

    }

    public String getBarra() {
        return barra;
    }

    public String getSistema() {
        return sistema;
    }

    public String getRuta() {
        return ruta;
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
