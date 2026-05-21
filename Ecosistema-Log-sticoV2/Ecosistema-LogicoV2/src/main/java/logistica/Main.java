package logistica;

import gestion.CentroControl;
import modelo.CamionAutonomo;
import modelo.DronTransporte;

/**********************************************************************************
 * Clase principal que actúa como punto de ejecución del sistema.
 * Su función es orquestar la creación de objetos y demostrar la correcta
 * implementación de la herencia, agregación y polimorfismo en tiempo de ejecución.
 *********************************************************************************/
public class Main {

    public static void main(String[] args) {
    CentroControl centro = new CentroControl();
    
    DronTransporte dron1 = new DronTransporte("101a", 150.0);
    DronTransporte dron2 = new DronTransporte("303c", 200.0);
    CamionAutonomo camion1 = new CamionAutonomo("201aa", 35.5);
    CamionAutonomo camion2 = new CamionAutonomo("202b", 18.0);

    // EJERCICIO 1 - CRUD
    System.out.println("=== CRUD ===");
    centro.registrarUnidad(dron1);
    centro.registrarUnidad(dron2);
    centro.registrarUnidad(camion1);
    centro.registrarUnidad(camion2);
    centro.registrarUnidad(dron1); // duplicado para probar validacion
    
    centro.listarVehiculos();
    centro.buscarVehiculo("101a");
    centro.modificarVehiculo("202b", "999z");
    centro.eliminarVehiculo("303c");
    centro.listarVehiculos();

    // EJERCICIO 2 - Lambda
    System.out.println("\n=== LAMBDA ===");
    centro.mostrarInfoFlota();

    // EJERCICIO 3 - Streams
    System.out.println("\n=== STREAMS ===");
    centro.listarConectables();
    centro.listarIDs();
    System.out.println("Total vehiculos: " + centro.getCantidadVehiculos());
    centro.buscarPorTipo("CamionAutonomo");

    // EJERCICIO 4 - Ordenamiento
    System.out.println("\n=== ORDENAMIENTO ===");
    centro.ordenarPorIDs();
    centro.ordenarPorTipo();

    // EJERCICIO 5 - Monitoreo completo
    System.out.println("\n=== MONITOREO ===");
    centro.monitorearFlota();

    // EJERCICIO 6 - Reporte
    centro.generarReporte();
    }
}
