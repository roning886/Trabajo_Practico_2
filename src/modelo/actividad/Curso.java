package modelo.actividad;

import modelo.Estudiante;
import modelo.certificacion.Certificable;

import java.io.Serializable;

public class Curso extends Actividad implements Certificable, Serializable {
    private int nivel;
    public Curso(int id,String titulo, int cupoMaximo, int nivel){
        super(id,titulo,cupoMaximo);
        this.nivel=nivel;
    }

    public int getNivel(){return nivel;}
    public void setNivel(int nivel){this.nivel=nivel;}

    @Override
    public double calcularCostoMateriales(){
        return 0;
    }
    public String getTipo(){
        return "Curso";
    }
    public String generarCertificado(Estudiante estudiante){
        return "";
    }
}
