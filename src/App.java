import java.io.FileNotFoundException;
import java.io.IOException;
import excepciones.CupoExcedidoException;
import modelo.actividad.Actividad;
import modelo.Estudiante;
import modelo.EventoUniversitario;
import modelo.Sala;

import java.util.ArrayList;

public class App {
    public static void main (String [] args ){

        Estudiante alumno1 = new Estudiante("53000", "Pedro");
        Estudiante alumno2 = new Estudiante("52000", "Marta");
        Estudiante alumno3 = new Estudiante("51000","Facundo");

        ArrayList <Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(alumno1);
        estudiantes.add(alumno2);
        estudiantes.add(alumno3);

        Sala sala1 = new Sala(1,"Laboratorio informatico");

        EventoUniversitario evento1 = new EventoUniversitario( "1", "Ciberseguridad", 1000.0, false);

        System.out.println("\nDATOS DE LOS EVENTOS");

        evento1.asignarSala(sala1);

        evento1.crearActividad(1,"Proteccion de sistemas",1,"charla","si",false);
        evento1.crearActividad(2,"Seguridad en internet",2,"taller","no",true);

        Actividad act1 =evento1.getActividad(1);
        Actividad act2 =evento1.getActividad(2);
        try {
            try {
                act1.inscribir(alumno1);
            } catch (CupoExcedidoException e) {
                System.out.println("error al inscribir: " + e.getMessage());
            }
            try {
                act1.inscribir(alumno2);
            } catch (CupoExcedidoException e) {
                System.out.println("error al inscribir: " + e.getMessage());
            }
            try {
                act2.inscribir(alumno2);
            } catch (CupoExcedidoException e) {
                System.out.println("error al inscribir: " + e.getMessage());
            }
            try {
                act2.inscribir(alumno3);
            } catch (CupoExcedidoException e) {
                System.out.println("error al inscribir: " + e.getMessage());
            }
            try {
                evento1.persistirEventos();
                EventoUniversitario copiaDesdeArchivo = evento1.recuperarEvento(evento1.getId());
                evento1.mostrarDatos();
                copiaDesdeArchivo.mostrarDatos();
            } catch (FileNotFoundException e) {
                System.out.println("error no se encontro el archivo del evento: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println("no se pudo reconstruir el objeto almacenado: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("error en las entradas/salidas: " + e.getMessage());
            }
        }finally{
            System.out.println("cantidad de eventos "+EventoUniversitario.getCantidadEventos());
            System.out.println("PROGRAMA FINALIZADO");
        }

    }
}
