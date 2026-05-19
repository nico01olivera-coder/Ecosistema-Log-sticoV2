package logistica.gestion;

import logistica.modelo.Vehiculo;
import logistica.interfaces.IConectable;
import java.util.ArrayList;
import java.util.List;

/************************************************************************************
 * Clase gestora que administra el monitoreo y control de las unidades de transporte.
 * Representa una relación de agregación con la entidad abstracta Vehiculo, ya que
 * agrupa objetos instanciados externamente y su ciclo de vida es independiente.
 ***********************************************************************************/
public class CentroControl {

    /*********************************************************************************************
     * Colección para almacenar las unidades vehiculares.
     * Se declara utilizando la interfaz List para desacoplar la implementación (buenas prácticas)
     * y se marca como final para asegurar que la referencia a la lista no sea reasignada.
     ********************************************************************************************/
    private final List<Vehiculo> flota;

    /******************************************************************************
     * Constructor por defecto que inicializa la estructura de datos para la flota.
     *****************************************************************************/
    public CentroControl() {
        this.flota = new ArrayList<>();
    }

    /***************************************************************************************
     * Registra un nuevo vehículo en la flota del sistema.
     * Incluye validación de nulidad e integridad para evitar errores en tiempo de ejecución
     * y duplicidad de registros.
     *
     * @param v Instancia de cualquier clase concreta que herede de Vehiculo.
     **************************************************************************************/
    public void registrarUnidad(Vehiculo v) {
        if (v == null) {
            System.err.println("Error: No se puede registrar un vehículo nulo.");
            return;
        }

        if (flota.contains(v)) {
            System.out.println("Aviso: El vehículo con ID " + v.getId() + " ya se encuentra en la flota.");
            return;
        }

        flota.add(v);
        System.out.println("Vehículo ID " + v.getId() + " registrado exitosamente.");
    }

    /*********************************************************************************************
     * Itera sobre la colección de vehículos registrados para ejecutar sus comportamientos.
     * Este método demuestra el uso central del polimorfismo: se llama a patronMovimiento()
     * sin necesidad de evaluar si es un Dron o un Camión.
     * Adicionalmente, verifica dinámicamente si la unidad implementa capacidades de conectividad.
     ********************************************************************************************/
    public void monitorearFlota() {
        if (flota.isEmpty()) {
            System.out.println("Operación abortada: La flota se encuentra vacía.");
            return;
        }

        System.out.println("\n--- Iniciando Monitoreo del Ecosistema Logístico ---");

        for (Vehiculo v : flota) {
            System.out.println("Analizando unidad ID: " + v.getId());

            // Llamada polimórfica al método abstracto sobreescrito en la clase concreta
            v.patronMovimiento();

            // Verificación del contrato de conectividad mediante Pattern Matching (Java 16+)
            // Si el vehículo implementa IConectable, se realiza el casteo automático a 'conectable'
            if (v instanceof IConectable conectable) {
                conectable.sincronizarGPS();
            }

            System.out.println("--------------------------------------------------");
        }
    }
}
