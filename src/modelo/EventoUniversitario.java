package modelo;

import modelo.actividad.Actividad;
import modelo.actividad.Charla;
import modelo.actividad.Taller;

import java.io.*;
import java.util.ArrayList;

public class EventoUniversitario implements Serializable{
    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos = 0;
    private Sala sala;
    private ArrayList<Actividad> actividades;

    public EventoUniversitario(String id, String titulo, double costoBase, boolean gratuito){
        this.id = id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        cantidadEventos++;
        this.actividades = new ArrayList<>();
    }

    //SALA
    public void asignarSala(Sala sala){
        this.sala = sala;
    }

    //CONSTRUCTOR COPIA
    public EventoUniversitario(EventoUniversitario otro){
        this(otro.id +"-Copia ",otro.titulo, otro.costoBase, otro.gratuito);
    }

    public void mostrarDatos (){
        System.out.println("=================================================" + "\nid " + this.id + "\ntitulo " + this.titulo + "\ncosto base " + this.costoBase + "\nes gratuito? " + (this.gratuito ? "si":"no"));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+"\nSALA ASIGNADA");
        System.out.println("nombre de la sala: "+ this.sala.getNombre() + "\nID de la sala: "+ this.sala.getId());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("DATOS DE LAS ACTIVIDADES");
        System.out.println( "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for (Actividad i : this.actividades)
                {
                    System.out.println("     ID: " + i.getId() + "\n     Titulo: "+ i.getTitulo()+ "\n     Cupo maximo: "+ i.getCupoMaximo());
                    i.mostrarInscripciones();
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }
        System.out.println( "COSTO ESTIMADO "+ calcularCostoEstimado() );
    }

    public Actividad getActividad(int id){
        for(Actividad a: this.actividades){
            if ( a.getId() == id ){
                return a;
            }
        }
        return null;
    }

    //calcular costo no funciona porque en el ejercicio todavia no esta definido como se tiene que programar
    public double calcularCostoEstimado() {
        double costoEstimado = 0;
        if (gratuito){
            return 0;
        }else{
            for(Actividad i:this.actividades){
                double precioActividad= i.calcularCostoMateriales();
                costoEstimado= costoEstimado+ precioActividad;
            }
        }
        return (costoBase+costoEstimado)*1.21;
    }
    public void crearActividad(int id, String titulo, int cupo, String tipoActividad,String disertante, boolean requiereNotebook){
         if (tipoActividad.equalsIgnoreCase("charla")) {
             Actividad charla =new Charla(id,titulo,cupo,disertante);
             this.actividades.add(charla);
         }else if(tipoActividad.equalsIgnoreCase("taller")){
             Actividad taller=new Taller(id,titulo,cupo,requiereNotebook);
             this.actividades.add(taller);
         }
    }

    public boolean persistirEventos()throws IOException {
        String nombreArchivo = "evento_"+this.id+".dat";
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))){
            oos.writeObject(this);
            return true;
        }
    }
    public EventoUniversitario recuperarEvento(String id) throws IOException, ClassNotFoundException{
        String nombreArchivo = "evento_"+id +".dat";
        try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(nombreArchivo))){
            return(EventoUniversitario) ois.readObject();
        }
    }

    //SETTER Y GETTER
    public String getId() {
        return id;
    }

    public double getCostoBase() {
        return costoBase;
    }

    public void setCostoBase(double costoBase) {
        this.costoBase = costoBase;
    }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isGratuito() {
        return gratuito;
    }

    public void setGratuito(boolean gratuito) {
        this.gratuito = gratuito;
    }
    public static int getCantidadEventos(){
        System.out.println("=================================================");
        return cantidadEventos;
    }
    public static void setCantidadEventos(int cantidadEventos) {
        EventoUniversitario.cantidadEventos = cantidadEventos;
    }
}
