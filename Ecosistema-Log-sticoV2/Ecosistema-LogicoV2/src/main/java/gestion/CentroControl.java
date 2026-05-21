package gestion;

import modelo.Vehiculo;
import interfaces.IConectable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import modelo.CamionAutonomo;
import modelo.DronTransporte;
import java.util.stream.Collectors;

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
    private int cantVehiculos = 0;

    /******************************************************************************
     * Constructor por defecto que inicializa la estructura de datos para la flota.
     *****************************************************************************/
    public CentroControl() {
        this.flota = new ArrayList<>();
    }

    
    /***************************************************************************************
     * Crea un nuevo vehículo en la flota del sistema.
     * Incluye validación de nulidad e integridad para evitar errores en tiempo de ejecución
     * y duplicidad de registros.
     *
     * @param v Instancia de cualquier clase concreta que herede de Vehiculo.
     **************************************************************************************/
    public void registrarUnidad(Vehiculo v) {
        if (v == null) {
            System.err.println("Error: No se puede registrar un vehiculo nulo.");
            return;
        }
        for (Vehiculo vehiculo : flota) {
            if (vehiculo.getId() == v.getId()) {
                System.out.println("Aviso: Ya existe un vehiculo con ID " + v.getId());
                return;
            }
        }
        flota.add(v);
        System.out.println("Vehiculo ID " + v.getId() + " creado exitosamente.");
        cantVehiculos++;
    }
    
    /**
     * lista todos los vehiculos de la flota 
     * verifica si la flota se encuentra vacia y en caso de que no lo este
     * muestra por pantalla la lista de vehiculos
     */
    public void listarVehiculos() {
        if (flota.isEmpty()) {
            System.out.println("La flota esta vacia.");
            return;
        }
        System.out.println("\n--- Lista de Vehiculos ---");
        for (Vehiculo v : flota) {
            System.out.println("ID: " + v.getId() + " | Tipo: " + v.getClass().getSimpleName());
        }
    }
    
    /**
     * Busca un vehiculo por su id y retorna el vehiculo si lo encuentra
     * @param id
     * @return 
     */
    public Vehiculo buscarVehiculo(String id) {
        for (Vehiculo v : flota) {
            if (v.getId().equals(id)) {
                return v;
            }
        }
        System.out.println("No se encontro vehiculo con ID: " + id);
        return null;
    }
    
    /**
     * Modifica el id del vehiculo, buscandolo en la flota y eliminando el valor viejo
     * para despues agregar el nuevo vehiculo 
     * @param id
     * @param nuevoId 
     */
    public void modificarVehiculo(String id, String nuevoId) {
        Vehiculo encontrado = buscarVehiculo(id);
        if (encontrado != null) {
            flota.remove(encontrado);
            // Como id es final, creamos uno nuevo del mismo tipo
            Vehiculo actualizado;
            if (encontrado instanceof CamionAutonomo) {
                actualizado = new CamionAutonomo(nuevoId, 10);
            }
            else {
                actualizado = new DronTransporte(nuevoId, 100);
            }
            flota.add(actualizado);
            System.out.println("Vehiculo actualizado: ID " + id + " " + nuevoId);
        }
    }
    
    /**
     * Elimina un vehiculo de la flota y cuenta con comprobacion de su existencia
     * @param id 
     */
    public void eliminarVehiculo(String id) {
        Vehiculo encontrado = buscarVehiculo(id);
        if (encontrado != null) {
            flota.remove(encontrado);
            System.out.println("Vehiculo ID " + id + " eliminado correctamente.");
            cantVehiculos--;
        }
    }
    /*********************************************************************************************
     * Itera sobre la colección de vehículos registrados para ejecutar sus comportamientos.
     * Este método demuestra el uso central del polimorfismo: se llama a patronMovimiento()
     * sin necesidad de evaluar si es un Dron o un Camión.
     * Adicionalmente, verifica dinámicamente si la unidad implementa capacidades de conectividad.
     ********************************************************************************************/
    public void monitorearFlota() {
        if (flota.isEmpty()) {
            System.out.println("Operacion abortada: La flota se encuentra vacia.");
            return;
        }

        System.out.println("\n--- Iniciando Monitoreo del Ecosistema Logistico ---");

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
    /**
     * filtra todos los vehiculos que tienen conectado el gps
     */
    public void listarConectables() {
        flota.stream().filter(v -> v instanceof IConectable)
       .forEach(v -> System.out.println("Conectable → ID: " + v.getId() 
        + " | Tipo: " + v.getClass().getSimpleName()));
    }
    /**
     * devulve una lista de los IDsnde la flota
     */
    public void listarIDs(){
        List<String> ids = flota.stream().map(v -> v.getId()).collect(Collectors.toList());
        System.out.println("IDs en la flota: " + ids);
    }
    
    /**
     * devulve la cantidad de vehiculos de la flota, 
     * el valor aumneta automaticamnete al ccrear un vehiculo y dismuniye al eluminarlo
     * @return 
     */
    public int getCantidadVehiculos(){
        return this.cantVehiculos;
    }
    
    /**
     * busca todos lo vehiculos con el mismo tipo que el recicibo al ser llamado
     * @param tipo 
     */
    public void buscarPorTipo(String tipo){
        System.out.println("\n--- Vehiculos de tipo: " + tipo + " ---");
        flota.stream().filter(v -> v.getClass().getSimpleName().equalsIgnoreCase(tipo))
         .forEach(v -> System.out.println("ID: " + v.getId() 
                       + " | Tipo: " + v.getClass().getSimpleName()));
    }
    
    /**
     * imprime una lista de los vehiculos ordenada por los IDs
     */
    public void ordenarPorIDs() {
        System.out.println("\n--- Vehiculos ordenados por ID ---");
        flota.stream()
             .sorted(Comparator.comparing(Vehiculo::getId))
             .collect(Collectors.toList())
             .forEach(v -> System.out.println("ID: " + v.getId() 
                           + " | Tipo: " + v.getClass().getSimpleName()));
    }
    
    /**
     * imprime una lista de los vehiculos ordenada por tipo
     */
    public void ordenarPorTipo(){
        System.out.println("\n--- Vehiculos ordenados por TIPO ---");
        flota.stream()
             .sorted(Comparator.comparing(v -> v.getClass().getSimpleName()))
             .collect(Collectors.toList())
             .forEach(v -> System.out.println("ID: " + v.getId() 
                           + " | Tipo: " + v.getClass().getSimpleName()));       
    }
    
    /**
     * imprime la informacion de los vehiculos de la flota
     */
    public void mostrarInfoFlota() {
        System.out.println("\n--- Informacion de la Flota ---");
        flota.forEach(v -> System.out.println(
            "ID: " + v.getId() + 
            " | Tipo: " + v.getClass().getSimpleName() +
            " | Conectable: " + (v instanceof IConectable ? "Si" : "No")));
    }
    
    /**
     * genera un reporte de la flota, cuantos vehiculos son, cuantos son concetables y 
     *los camiones autonomos y los drones
     */
    public void generarReporte() {
        System.out.println("\n========= REPORTE GENERAL =========");
        System.out.println("Total vehiculos: " + cantVehiculos);

        long conectables = flota.stream()
                                .filter(v -> v instanceof IConectable)
                                .count();
        System.out.println("Vehiculos conectables: " + conectables);

        long camiones = flota.stream()
                             .filter(v -> v instanceof CamionAutonomo)
                             .count();
        System.out.println("Camiones autonomos: " + camiones);

        long drones = flota.stream()
                           .filter(v -> v instanceof DronTransporte)
                           .count();
        System.out.println("Drones: " + drones);
        System.out.println("====================================");
    }    
    
    
}
