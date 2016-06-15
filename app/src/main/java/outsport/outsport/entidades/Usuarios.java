package outsport.outsport.entidades;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by Daniel on 25-05-2016.
 */
@SuppressWarnings("serial")
public class Usuarios implements Serializable {
    private String  nombre;
    private String  apellido;
    private String  correo;
    private String  password;
    private int     id_nivel;
    private int     id_foto;
    static Drawable fondo;

    public static Drawable getFondo() {
        return fondo;
    }

    public static void setFondo(Drawable Fondo) {
        fondo = Fondo;
    }

    public Usuarios() {}

    public Usuarios(String nombre, String apellido, String correo, String password, int id_nivel, int id_foto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;
        this.id_nivel = id_nivel;
        this.id_foto = id_foto;
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", password='" + password + '\'' +
                ", id_nivel=" + id_nivel +
                ", id_foto=" + id_foto +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPassword() {
        return password;
    }

    public int getId_nivel() {
        return id_nivel;
    }

    public int getId_foto() {
        return id_foto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId_nivel(int id_nivel) {
        this.id_nivel = id_nivel;
    }

    public void setId_foto(int id_foto) {
        this.id_foto = id_foto;
    }
}
