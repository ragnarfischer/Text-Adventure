/**
 * Objekt, das einen Namen, eine Beschreibung und einen Typ beinhaltet. Der Inhalt wird in den Tochterklassen zugewiesen.
 * 
 * @author Ragnar Fischer
 */
public class Object
{
    public String name;
    public String description;
    public int type;

    /**
     * @return Name des Objektes.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return Beschreibung des Objektes.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Typ des Objetes. 
     * 1, wenn das Objekt einen String beinhaltet, 
     * 2, wenn das Objekt einen Wert beinhaltet, 
     * 3, wenn das Objekt weiter Objekte beinhaltet.
     */
    public int getType()
    {
        return type;
    }
}