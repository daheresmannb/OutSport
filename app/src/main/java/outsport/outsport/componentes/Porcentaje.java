package outsport.outsport.componentes;
/**
 * Created by Daniel on 01-05-2016.
 */
public class Porcentaje {
    public Porcentaje() {}
    public static int result(int num, double porcentaje) {
        return (int) Math.round(((porcentaje * num) / 100.0));
    }
}

