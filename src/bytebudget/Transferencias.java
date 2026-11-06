package bytebudget;
import bytebudget.AdministracionFondos;
import java.util.Scanner;

public class Transferencias {

    public class AdministradorTransferencias{
        Scanner sc = new Scanner (System.in);
        AdministracionFondos.SistemaPresupuestario sistema;
      
        public AdministradorTransferencias(AdministracionFondos.SistemaPresupuestario sistema) {
            this.sistema = sistema;
        }
        
        void transferirFondo(double deposito, String name){
            for (AdministracionFondos.Fondo fd : sistema.fondos){
                if (fd.getNombre().equals(name)){ 
                    if (deposito > 0 && sistema.flujoCajaLibre > 0 && sistema.flujoCajaLibre >= deposito){
                        fd.setSaldo(fd.getSaldo() + deposito);
                        sistema.flujoCajaLibre -= deposito; 
                    }     
                }
            }
        }

        void retirarFondo(double retiro, String name){
            for (AdministracionFondos.Fondo fd : sistema.fondos){
                if (fd.getNombre().equals(name)){
                    if (retiro > 0 && retiro <= fd.getSaldo()){
                        fd.setSaldo(fd.getSaldo() - retiro);
                        sistema.flujoCajaLibre += retiro; 
                    } 
                }
            }
        }
    }
}