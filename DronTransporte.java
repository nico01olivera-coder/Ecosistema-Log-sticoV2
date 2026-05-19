package logistica.modelo;
/*****************************************************************************
 * Clase concreta que representa una unidad de transporte aéreo no tripulado.
 * Extiende la identidad base de Vehiculo y cumple con el contrato IConectable
 * para el reporte de coordenadas en tiempo real.
 ****************************************************************************/
import logistica.interfaces.IConectable;


public class DronTransporte extends Vehiculo implements IConectable {

    /** Altitud máxima permitida por regulaciones aéreas, definida al instanciar (inmutable). */
    private final double altitudMaxima;

    /** Altitud de operación en el momento actual. */
    private double altitudActual;

    /** Indicador de estado para determinar si la unidad se encuentra operando en el aire. */
    private boolean enVuelo;

    /***********************************************************
     * Constructor para inicializar la unidad aérea.
     * * @param id Identificador único del vehículo.
     * @param altitudMaxima Límite de altura de vuelo en metros.
     **********************************************************/
    public DronTransporte(int id, double altitudMaxima) {
        super(id);
        this.altitudMaxima = altitudMaxima;
        this.altitudActual = 0.0;
        this.enVuelo = false;
    }

    /********************************************************************
     * Método auxiliar para gestionar la transición al estado de vuelo.
     * Centraliza la lógica de despegue para evitar duplicación de código
     * y asegurar que la altitud inicial sea segura.
     *******************************************************************/
    private void iniciarDespegue() {
        this.enVuelo = true;
        // Se asume una altitud de crucero estándar del 60% de su capacidad máxima
        this.altitudActual = this.altitudMaxima * 0.6;
        System.out.println("Dron [ID: " + getId() + "] - Despegue completado. Altitud estable a "
                + this.altitudActual + "m.");
    }

    /*************************************************************************
     * Define el comportamiento específico de movilidad en el espacio aéreo.
     * Si el dron está en tierra, procede a despegar antes de iniciar la ruta.
     ************************************************************************/
    @Override
    public void patronMovimiento() {
        if (!enVuelo) {
            iniciarDespegue();
        }

        System.out.println("Dron [ID: " + getId() + "] - Navegando por ruta aérea predefinida a "
                + this.altitudActual + " metros de altura.");
    }

    /********************************************************************
     * Cumple con el contrato de la interfaz IConectable.
     * Emite la telemetría actual de la unidad para el Centro de Control.
     *******************************************************************/
    @Override
    public void sincronizarGPS() {
        // En un entorno real, aquí se integrarían librerías de geolocalización.
        // Se simula la transmisión de datos estructurados.
        String estado = enVuelo ? "EN MOVIMIENTO" : "EN TIERRA";
        System.out.println("-> [TELEMETRÍA GPS] Dron ID: " + getId()
                + " | Estado: " + estado
                + " | Altitud Z: " + this.altitudActual + "m"
                + " | Señal: Óptima.");
    }
}
