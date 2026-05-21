/****************************************************************************************
 * Contrato que define las capacidades de conectividad para las entidades del ecosistema.
 * Separa la lógica de comunicación de la lógica de movilidad, permitiendo que solo
 * aquellos vehículos o dispositivos que lo requieran implementen esta funcionalidad.
 ***************************************************************************************/
package interfaces;

/******************************************************************************
 * Establece la comunicación con la red central o sistema satelital para
 * actualizar y transmitir la posición actual de la unidad.
 * Nota de diseño: En las interfaces de Java, los métodos son implícitamente
 * public y abstract, por lo que no es necesario redundar en esos modificadores.
 ******************************************************************************/

public interface IConectable {
    void sincronizarGPS();
}

