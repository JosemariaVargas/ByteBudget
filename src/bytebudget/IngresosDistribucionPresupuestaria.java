package bytebudget;

import bytebudget.AdministracionFondos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class IngresosDistribucionPresupuestaria {
    AdministracionFondos.SistemaPresupuestario sistema;
    ArrayList<AdministracionFondos.Fondo> orden = new ArrayList<>();

   
    public IngresosDistribucionPresupuestaria(AdministracionFondos.SistemaPresupuestario sistema) {
        this.sistema = sistema;
    }
    
    void distribuirIngreso() {
     
        orden.clear();
        orden.addAll(sistema.getFondos());
        
       
        Collections.sort(orden, new Comparator<AdministracionFondos.Fondo>() {
            @Override
            public int compare(AdministracionFondos.Fondo f1, AdministracionFondos.Fondo f2) {
                return Integer.compare(f1.getPrioridad(), f2.getPrioridad());
            }
        });
        
        double flujoOriginal = sistema.getFlujoCajaLibre();
        
        
        for (AdministracionFondos.Fondo fondo : orden) {
          
            double montoAsignado = (fondo.getPorcentaje() / 100.0) * flujoOriginal;
            
           
            if (montoAsignado >= fondo.getSalariomin()) {
                fondo.setSaldo(fondo.getSaldo() + montoAsignado);
               
                sistema.setFlujoCajaLibre(sistema.getFlujoCajaLibre() - montoAsignado);
            }
        }
        System.out.println("¡Distribución automática realizada con éxito respetando prioridades!");
    }
}