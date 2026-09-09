package modelo.actividad;

import excepciones.CupoExcedidoException;
import modelo.Estudiante;
import modelo.Inscripcion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.io.Serializable;

public abstract class Actividad implements Serializable {
    protected int id;
    protected String titulo;
    protected int cupoMaximo;
    protected static final int CUPO_MINIMO = 0;

    private ArrayList<Inscripcion>inscripciones = new ArrayList<>();

    public Actividad(int id, String titulo, int cupoMaximo){
        this.id = id;
        this.titulo = titulo;
        this.cupoMaximo = cupoMaximo;
    }

    public Inscripcion inscribir(Estudiante estudiante)throws CupoExcedidoException {
        if (inscripciones.size() ==cupoMaximo) {
            throw new CupoExcedidoException("no se puede inscribir al alumno "+ estudiante.getNombre()+", cupo maximo alcanzado");
        }
        Inscripcion nuevaInscripcion = new Inscripcion(LocalDate.now(),"INSCRIPTO",estudiante,this);
        this.inscripciones.add(nuevaInscripcion);
        return nuevaInscripcion;
    }
    public void mostrarInscripciones(){
        System.out.println("*    Inscripciones a actividad: " +this.titulo);
        for (Inscripcion i:this.inscripciones)
        {
            System.out.println("        Nombre del estudiante: "+ i.getEstudiante().getNombre()+ " | legajo del estudiante: "+ i.getEstudiante().getLegajo()+" | fecha de inscripcion: "+i.getFecha());
        }
    }
    public final void mostrarIdentificacion(){
        System.out.println("IDENTIFICACION "+"\n identificacion de la actividad "+ getId() +" titulo de la actividad "+ getTitulo() +" tipo de actividad "+ getTipo());
    }
    public abstract double calcularCostoMateriales();
    public abstract String getTipo();


    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id;}

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }
}