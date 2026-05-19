package logistica;

import logistica.gestion.CentroControl;
import logistica.modelo.CamionAutonomo;
import logistica.modelo.DronTransporte;

/**********************************************************************************
 * Clase principal que actúa como punto de ejecución del sistema.
 * Su función es orquestar la creación de objetos y demostrar la correcta
 * implementación de la herencia, agregación y polimorfismo en tiempo de ejecución.
 *********************************************************************************/
public class Main {

    public static void main(String[] args) {
        // 1. Instanciación de la clase gestora
        CentroControl centro = new CentroControl();

        // 2. Creación de las unidades de transporte con parámetros iniciales
        // Se definen IDs únicos, altitudes máximas y capacidades de carga según corresponda.
        DronTransporte dron1 = new DronTransporte(101, 150.0);
        CamionAutonomo camion1 = new CamionAutonomo(201, 35.5);
        CamionAutonomo camion2 = new CamionAutonomo(202, 18.0);

        System.out.println("--- Fase de Registro de Flota ---");
        // 3. Integración de las unidades al centro de control (Agregación)
        centro.registrarUnidad(dron1);
        centro.registrarUnidad(camion1);
        centro.registrarUnidad(camion2);

        // Intento de registrar un vehículo duplicado para probar validaciones
        centro.registrarUnidad(dron1);

        // 4. Ejecución del ciclo de monitoreo
        // Aquí se evidencia el polimorfismo y el uso de Pattern Matching (instanceof)
        centro.monitorearFlota();
    }
}
