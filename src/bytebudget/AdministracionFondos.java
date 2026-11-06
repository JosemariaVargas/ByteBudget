
package bytebudget;
import java.util.ArrayList;
import java.util.Scanner;

public class AdministracionFondos {
    Scanner sc = new Scanner (System.in);
    public class Fondo{
        
        String nombre;
        double porcentaje;
        int prioridad;
        double salariomin;
        double saldo;
         

        public Fondo(String nombre, double porcentaje, int prioridad, double salariomin, double salario) {
            
            this.nombre = nombre;
            this.porcentaje = porcentaje;
            this.prioridad = prioridad;
            this.salariomin = salariomin;
            this.saldo=salario;
        }

        public String getNombre() {
            return nombre;
        }

        public double getPorcentaje() {
            return porcentaje;
        }

        public int getPrioridad() {
            return prioridad;
        }

        public double getSalariomin() {
            return salariomin;
        }

        public double getSaldo() {
            return saldo;
        }
        


        void setNombre(String nombre) {
            this.nombre = nombre;
        }

        void setPorcentaje(double porcentaje) {
            this.porcentaje = porcentaje;
        }

        void setPrioridad(int prioridad) {
            this.prioridad = prioridad;
        }

        void setSalariomin(double salariomin) {
            this.salariomin = salariomin;
        }

        void setSaldo(double saldo) {
            this.saldo = saldo;
        }
        
    
    }
    public class SistemaPresupuestario{
        ArrayList<Fondo> fondos= new ArrayList<>();
        double flujoCajaLibre=0.00;
        double porcentajeRestante=100.00;
        ArrayList<Integer> prioridades=new ArrayList<>();
        

        public double getFlujoCajaLibre() {
            return flujoCajaLibre;
        }

        public double getPorcentajeRestante() {
            return porcentajeRestante;
        }

        public ArrayList<Fondo> getFondos() {
            return fondos;
        }
        

        public void setPorcentajeRestante(double porcentajeRestante) {
            this.porcentajeRestante = porcentajeRestante;
            
        }

        public void setFlujoCajaLibre(double flujoCajaLibre) {
            this.flujoCajaLibre = flujoCajaLibre;
        }
        
        
    
    }
    public class AdministradorFondos{
      
        SistemaPresupuestario sistema;
        
        public AdministradorFondos(SistemaPresupuestario sistema) {
            this.sistema = sistema;
        }
        
        
           public void crearfondo(){
            String name = sc.nextLine();
            double porc = sc.nextDouble();
            int prio = sc.nextInt();
            double fmin = sc.nextDouble();
            sc.nextLine(); 
            
            
            if (prio < 1) {
                System.out.println("Error: La prioridad debe ser mayor o igual a 1.");
                ;
            }
            
            if (sistema.prioridades.contains(prio)) {
                System.out.println("Error: La prioridad " + prio + " ya está asignada a otro fondo.");
                ;
            }
            if (sistema.fondos.contains(name)){
                System.out.println("Error: El nombre ya fue asignado a otro fondo");}
           
            if (porc > 0 && porc <= sistema.getPorcentajeRestante()) {
                Fondo fund = new Fondo(name, porc, prio, fmin, 0.00);
                sistema.fondos.add(fund);
                sistema.porcentajeRestante = sistema.getPorcentajeRestante() - porc;
                sistema.prioridades.add(prio); // Registramos la prioridad única
                System.out.println("¡Fondo creado con éxito! Porcentaje restante disponible: " + sistema.porcentajeRestante + "%");
            } else {
                System.out.println("Error: No hay suficiente porcentaje disponible. Te queda: " + sistema.getPorcentajeRestante() + "%");
            }
        } 
        
        public void modificarFondo(String name){
            for (AdministracionFondos.Fondo fd : sistema.fondos){
                if (fd.getNombre().equals(name)){
                    double porc = sc.nextDouble();
                    
                   
                    double capacidadMaxima = sistema.getPorcentajeRestante() + fd.getPorcentaje();
                    if (porc > 0 && porc <= capacidadMaxima){
                        sistema.porcentajeRestante = capacidadMaxima - porc;
                        fd.setPorcentaje(porc);
                    } else {
                        System.out.println("Error: Porcentaje inválido o insuficiente espacio.");
                    }
                    
                    int prio = sc.nextInt();
                   
                    if (prio == fd.getPrioridad()) {
                        
                    } else if (prio >= 1 && !sistema.prioridades.contains(prio)) {
                      
                        sistema.prioridades.remove(Integer.valueOf(fd.getPrioridad())); 
                        fd.setPrioridad(prio);                                          
                        sistema.prioridades.add(prio);                                  
                    } else {
                        System.out.println("Error: La prioridad es inválida o ya está repetida. No se modificó este campo.");
                    }
                    
                    double salmin = sc.nextDouble();
                    sc.nextLine();
                    if (salmin > 0){
                        fd.setSalariomin(salmin);
                    }
                    System.out.println("¡Proceso de modificación finalizado!");
                }
            }
        }
        
        public void eliminarFondo(String name){
            for (AdministracionFondos.Fondo fd :sistema.fondos){
            if (fd.getNombre().equals(name)){
            sistema.fondos.remove(fd);
            sistema.flujoCajaLibre+=fd.getSaldo();
            sistema.porcentajeRestante+=fd.getPorcentaje();
            sistema.prioridades.remove(Integer.valueOf(fd.getPrioridad()));}
            }
    
        }
    
    }}
