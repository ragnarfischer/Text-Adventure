/**
 * Ausgang, der eine Richtung und ein Ziel-Raum beinhaltet und einem Raum zugewiesen wird.
 * 
 * @author Ragnar Fischer
 */
public class Exit
{
    private String direction;
    private Room destination;
    
    /**
     * Initialisierung der Variablen mit den übergebenen Werten.
     * 
     * @param direction Richtung, in die der Ausgang liegt.
     * @param destination Raum, zu dem der Ausgang führt.
     */
    public Exit (String direction, Room destination)
    {
        this.direction = direction;
        this.destination = destination;
    }
    
    /**
     * @return Richtung, in die der Ausgang liegt.
     */
    public String getDirection()
    {
        return direction;
    }
    
    /**
     * @return Raum, zu dem der Ausgang führt.
     */
    public Room getDestination()
    {
        return destination;
    }
}
