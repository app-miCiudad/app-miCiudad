package miCiudad.modules.miCiudad.dom.reporte;

import org.joda.time.DateTime;

import lombok.Getter;
import lombok.Setter;
import miCiudad.modules.miCiudad.dom.empresa.Empresa;

public class ReporteObras {

    @Getter @Setter
    private String titulo;

     @Getter @Setter
    private String tipo;

    @Getter @Setter
    private String presupuesto;

    @Getter @Setter
    private String nombreEmpresa;
    
    @Getter @Setter
    private String fechaInicio; // Cambiar a String

    @Getter @Setter
    private String fechaFinal;  // Cambiar a String

      @Getter @Setter
    private String estado;    

    public ReporteObras (String titulo, String tipo, String presupuesto,  String fechaInicio, String fechaFinal, String estado) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.presupuesto = presupuesto;
        
        this.fechaInicio = fechaInicio; // Convertir DateTime a String
        this.fechaFinal = fechaFinal;   // Convertir DateTime a String
        this.estado = estado; 
    }

    public ReporteObras() {
    }
public String getNombreEmpresa() {
    return nombreEmpresa;
}
}