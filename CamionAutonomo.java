/************************************************************************************
 * Clase concreta que representa una unidad de carga terrestre automatizada.
 * Extiende la clase base Vehiculo, heredando su identidad, e implementa
 * su propia lógica de movilidad basada en restricciones de estado interno (batería).
 ***********************************************************************************/
public class CamionAutonomo extends Vehiculo {

    /** Porcentaje de energía disponible (0 a 100). */
    private int nivelBateria;

    /** Capacidad máxima de transporte definida al instanciar, inmutable. */
    private final double capacidadCargaToneladas;


    /***********************************************************************************
     * Constructor que inicializa la identidad del camión y sus características físicas.
     * * @param id Identificador único heredado.
     * @param capacidadCargaToneladas Límite de carga de la unidad.
     **********************************************************************************/
    public CamionAutonomo(int id, double capacidadCargaToneladas) {
        super(id); // Llamada obligatoria al constructor de la clase padre
        this.capacidadCargaToneladas = capacidadCargaToneladas;
        this.nivelBateria = 100; // Toda unidad inicia su ciclo con carga completa
    }

    /*****************************************************************************
     * Método auxiliar para gestionar la descarga de la batería de forma segura.
     * Centraliza la lógica de consumo evitando duplicación de código y asegurando
     * que el porcentaje no descienda de 0.
     * * @param cantidad Consumo a restar de la batería actual.
     ****************************************************************************/
    private void consumirBateria(int cantidad) {
        // Uso de Math.max para garantizar que el valor nunca sea negativo
        this.nivelBateria = Math.max(0, this.nivelBateria - cantidad);
    }

    /*************************************************************************************
     * Implementación concreta del comportamiento de movilidad definido en la clase padre.
     * Incorpora validación de estado: el comportamiento varía según el nivel de batería,
     * demostrando una abstracción más completa de la entidad.
     ************************************************************************************/
    @Override
    public void patronMovimiento() {
        if (this.nivelBateria <= 15) {
            System.out.println("Camión Autónomo [ID: " + getId() + "] - ALERTA: Batería crítica ("
                    + this.nivelBateria + "%). Desviando a estación de carga más cercana.");
            return; // Se interrumpe el movimiento normal
        }

        System.out.println("Camión Autónomo [ID: " + getId() + "] - Iniciando ruta terrestre. "
                + "Capacidad: " + this.capacidadCargaToneladas + "t. "
                + "Batería restante: " + this.nivelBateria + "%.");

        consumirBateria(10); // Simulación de costo energético por desplazamiento
    }

    /******************************************
     * Recupera el estado actual de la batería.
     * @return Porcentaje de batería.
     *****************************************/
    public int getNivelBateria() {
        return this.nivelBateria;
    }
}

package logistica.modelo;
