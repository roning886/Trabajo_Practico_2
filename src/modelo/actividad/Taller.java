package modelo.actividad;

import java.io.Serializable;

public class Taller extends Actividad implements Serializable {
    private boolean requiereNotebook;

    public Taller (int id,String titulo, int cupoMaximo, boolean requiereNotebook){
        super(id, titulo, cupoMaximo);
        this.requiereNotebook=requiereNotebook;
    }
    public boolean getRequiereNotebook(){return requiereNotebook;}
    public void setRequiereNotebook(boolean requiereNotebook){this.requiereNotebook=requiereNotebook;}

    @Override
    public double calcularCostoMateriales(){
        if(requiereNotebook==true){
            return 5000;
        }else{
            return 2000;
        }

    }
    public String getTipo(){
        return "TALLER";
    }

}
