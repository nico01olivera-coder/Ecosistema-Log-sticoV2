/*****************************
 * Clase abstracta que representa la entidad base para cualquier unidad de transporte
 * dentro del proyecto.
 * Proporciona la identidad fundamental y establece el contrato para la movilidad.
 ******************************/
public abstract class Vehiculo {

    /*****************************
     * Identificador único del vehículo.
     * Se declara final para proteger la identidad y evitar modificaciones accidentales
     * durante el ciclo de vida del objeto.
     *****************************/
    private final int id;

    /*****************************
     * Constructor para la inicialización segura de la identidad del vehículo.
     * @param id Identificador numérico único de la unidad.
     *****************************/
    public Vehiculo(int id) {
        this.id = id;
    }

    /****************************
     * Recupera el identificador único de la unidad de transporte.
     * Este método concreto asegura la reutilización de código para todas las subclases.
     * @return El valor entero del id del vehículo.
     ****************************/
    public int getId() {
        return this.id;
    }

    /*****************************
     * Define el comportamiento de desplazamiento específico de la unidad.
     * Al ser un método abstracto, actúa como una directiva obligatoria para que
     * cada subclase concreta defina su propia lógica de movimiento.
     *****************************/
    public abstract void patronMovimiento();
}
