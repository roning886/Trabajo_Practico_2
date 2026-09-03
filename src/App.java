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

        evento1.crearActividad(1,"Proteccion de sistemas",30,"charla","si",false);
        evento1.crearActividad(2,"Seguridad en internet",20,"taller","no",true);

        Actividad act1 =evento1.getActividad(1);
        Actividad act2 =evento1.getActividad(2);

        act1.inscribir(alumno1);
        act1.inscribir(alumno2);
        act2.inscribir(alumno2);
        act2.inscribir(alumno3);

        evento1.mostrarDatos();

        System.out.println("cantidad de eventos "+EventoUniversitario.getCantidadEventos());

    }
}
