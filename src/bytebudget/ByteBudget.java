package bytebudget;
import bytebudget.ConsultasHistorial;
import bytebudget.Transferencias;
import bytebudget.IngresosDistribucionPresupuestaria;
import bytebudget.ConfiguracionCapital;
import bytebudget.AdministracionFondos;
import java.util.Scanner;

public class ByteBudget {

    public static void main(String[] args) {
       
        AdministracionFondos admin1=new AdministracionFondos();
       
        AdministracionFondos.SistemaPresupuestario sistemaCompartido = admin1.new SistemaPresupuestario();
        ConfiguracionCapital configuracion = new ConfiguracionCapital(sistemaCompartido);
        ConfiguracionCapital.Capital dep = configuracion.new Capital(0.0);
        AdministracionFondos.AdministradorFondos admin = admin1.new AdministradorFondos(sistemaCompartido);
        ConfiguracionCapital configuracion1 = new ConfiguracionCapital(sistemaCompartido);
        ConfiguracionCapital.Capital dep1 = configuracion.new Capital(0.0);
        
        Transferencias tr = new Transferencias();
        Transferencias.AdministradorTransferencias sistema = tr.new AdministradorTransferencias(sistemaCompartido);
        
        ConsultasHistorial ch = new ConsultasHistorial();
        ConsultasHistorial.Consultas co = ch.new Consultas(sistemaCompartido);
        
        IngresosDistribucionPresupuestaria distribuidor = new IngresosDistribucionPresupuestaria(sistemaCompartido);       
        
        int contador=0;
        while(contador!=5){
            Scanner sc= new Scanner (System.in);
            System.out.println("====Bienvenido al sistema ByteBudge====");
            System.out.println("¿Que desea hacer?(Escoga un numero)");
            System.out.println("1. Ingresar Presupuesto");
            System.out.println("2. Administrar Fondos");
            System.out.println("3. Transferir Dinero");
            System.out.println("4. Consultas");
            System.out.println("5. Distribucion automatica");
            System.out.println("6. Salir");
            int opcion=sc.nextInt();
            double valor =0;
            sc.nextLine();
            switch(opcion){
                case 1: System.out.println("Ingrese el valor de su presupuesto:");
                        valor= sc.nextDouble();
                        sc.nextLine();
                        dep.agregarCapital(valor);
                        dep.transferirFlujodeCaja();
                        break;
                case 2: System.out.println("Escoge que haras a continuacion:");
                        System.out.println("1. Crear fondo nuevo");
                        System.out.println("2. Modificar un fondo");
                        System.out.println("3. Eliminar un fondo");
                        int opcion2= sc.nextInt();
                        sc.nextLine();
                        switch(opcion2){
                            case 1:
                                System.out.println("====Crear un nuevo fondo====");
                                System.out.println("Escribe el nombre del fondo\nPorcentaje destinado al fondo"
                                        + "\nNivel de prioridad (1 para arriba) \nFondo minimo admitido");
                                admin.crearfondo();
                                break;
                            case 2:
                                System.out.println("====Editar un fondo====");
                                System.out.println("Escribe el nombre del fondo que quieres editar\nPorcentaje destinado al fondo"
                                        + "\nNivel de prioridad (1 para arriba) \nFondo minimo admitido");
                                        String nombre = sc.nextLine();
                                admin.modificarFondo(nombre);
                                break;
                            case 3:
                                System.out.println("====Eliminar un fondo====");
                                System.out.println("Escribe el nombre del fondo que quieres eliminar");
                                        String nombre1 = sc.nextLine();
                                admin.eliminarFondo(nombre1);
                                break; 
                        };break;
                case 3: System.out.println("Escoge la transferencia a continuacion:");
                        System.out.println("1. Transferir a un fondo");
                        System.out.println("2. Transferir a el flujo");
                        int opcion3= sc.nextInt();
                        sc.nextLine();
                        switch (opcion3){
                            case 1: 
                                System.out.println("Escriba primero el nombre del fondo de destino y luego el valor a depositar en el fondo");
                                String name1=sc.nextLine();
                                double valor1=sc.nextDouble();
                                sistema.transferirFondo(valor1, name1);break;
                            case 2:
                                System.out.println("Escriba primero el nombre del fondo de retiro y luego el valor a retirar");
                                String name2=sc.nextLine();
                                double valor2=sc.nextDouble();
                                sistema.retirarFondo(valor2, name2);};break;
                case 4: System.out.println("====Consultas====");
                        System.out.println("1. Consultar Lista de Fondos");
                        System.out.println("2. Consultar Saldos de Fondos");
                        System.out.println("3. Consultar Flujo de Caja Restante");
                        int opcion4=sc.nextInt();
                        switch(opcion4){
                            case 1: co.listarFondos(); break;
                            case 2: co.consultarSaldos(); break;
                            case 3: co.consultarFlujoLibre(); break;
                        };break;
                case 5: System.out.println("Repartiendo los fondos...");
                        dep.agregarCapital(valor);
                        sistemaCompartido.setFlujoCajaLibre(sistemaCompartido.getFlujoCajaLibre() + valor);
                        distribuidor.distribuirIngreso();
                        break;
                case 6:
                    contador=5; break;
                default: 
                    System.out.println("Opcion no Valida"); break;
            }
        }
    }
}