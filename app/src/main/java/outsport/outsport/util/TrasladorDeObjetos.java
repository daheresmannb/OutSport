package outsport.outsport.util;

/**
 * Created by Daniel on 28-05-2016.
 */
public class TrasladorDeObjetos {
    private static Object objeto = null;
    private static Object face = null;
    private static Object google = null;

    public static void setObjeto(Object Objeto) {
        objeto = Objeto;
    }

    public static Object getObjeto() {
        return objeto;
    }

    public static Object getFace() {
        return face;
    }

    public static Object getGoogle() {
        return google;
    }

    public static void setFace(Object Face) {
        face = Face;
    }

    public static void setGoogle(Object Google) {
        google = Google;
    }
}

