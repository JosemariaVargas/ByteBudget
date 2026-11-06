
package bytebudget;

import bytebudget.AdministracionFondos;


public class ConfiguracionCapital {
    
    AdministracionFondos.SistemaPresupuestario sistema;
    public ConfiguracionCapital(AdministracionFondos.SistemaPresupuestario sistema) {
        this.sistema = sistema;
    }    
    
    public class Capital{
        double capitalInicial;

        public Capital(double capi) {
            this.capitalInicial=capi;
        }
        
        void agregarCapital(double capital){
            this.capitalInicial=capital;    }
    
        void transferirFlujodeCaja(){
            sistema.flujoCajaLibre+= capitalInicial;
          }    }
    
    
}
