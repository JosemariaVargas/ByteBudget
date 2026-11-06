package bytebudget;

import bytebudget.AdministracionFondos;

public class ConsultasHistorial {
    AdministracionFondos admin1=new AdministracionFondos();
    AdministracionFondos.SistemaPresupuestario sistemaCompartido = admin1.new SistemaPresupuestario();
    AdministracionFondos.AdministradorFondos admin = admin1.new AdministradorFondos(sistemaCompartido);
    
    public class Consultas{
    
        AdministracionFondos.SistemaPresupuestario sistema;
        AdministracionFondos.Fondo fund;
        
        public Consultas(AdministracionFondos.SistemaPresupuestario sistema) {
            this.sistema = sistema;
        }    
        
        void listarFondos(){
            int i=1;
            for (AdministracionFondos.Fondo fund :sistema.fondos){
                System.out.println(i+"." + fund);
                i+=1;
            }       
        }
    
        void consultarSaldos(){
            for (AdministracionFondos.Fondo fund :sistema.fondos){
                System.out.println(fund.nombre + ": " + fund.saldo);
            }        
        }
    
        void consultarFlujoLibre(){
            System.out.println("El flujo disponible es: " + sistema.flujoCajaLibre);      
        }
    }
}