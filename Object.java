/**
 * Objekte können sich in Räumen befinden. Sie tragen einen Namen, eine Beschreibung und einen Inhalt.
 * 
 * @author Ragnar Fischer
 */
public class Object
{
    private String name;
    private String description;
    
    private String contentString;
    private int[] contentValue;
    private Object[] contentObjects;

    /**
     * Konstruktor für Objekte, die einen String beeinhalten.
     * @param name Name des Objektes
     * @param description Beschreibung des Objektes
     * @param contentString Inhalt, der bei Interaktion wiedergegeben werden soll
     */
    public Object (String name, String description, String contentString)
    {
        this.name = name;
        this.description = description;
        this.contentString = contentString;
    }
    
    /**
     * Konstruktor für Objekte, die einen Wert beeinhalten.
     * @param name Name des Objektes
     * @param description Beschreibung des Objektes
     * @param contentValue Inhalt, der bei Interaktion verarbeitet werden soll
     */
    public Object (String name, String description, int[] contentValue)
    {
        this.name = name;
        this.description = description;
        this.contentValue = contentValue;
    }
    
    /**
     * Konstruktor für Objekte, die einen Wert beeinhalten.
     * @param name Name des Objektes
     * @param description Beschreibung des Objektes
     * @param contentObject Objekte, die sich in dem Objekt befinden
     */
    public Object (String name, String description, Object[] contentObjects)
    {
        this.name = name;
        this.description = description;
        this.contentObjects = contentObjects;
    }

    /**
     * @return Der Name des Objektes
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return Eine Beschreibung des Objektes
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * @return Der Typ des Objektes (1 für String-Objekt, 2 für Wert-Objekt, 3 für Objekte-Objekt, -1 wenn Fehler)
     */
    public int getType()
    {
        if (contentString != null)
        {
            return 1;
        }
        else if (contentValue != null)
        {
            return 2;
        }
        else if (contentObjects != null)
        {
            return 3;
        }
        else
        {
            return -1;
        }
    }
    
    /**
     * @return Der Inhalt des Objektes (Text)
     */
    public String getContentString()
    {
        return contentString;
    }
    
    /**
     * @return Der Inhalt des Objektes (Wert)
     */
    public int[] getContentValue()
    {
        return contentValue;
    }
    
    /**
     * @return Der Inhalt des Objektes (Objekte)
     */
    public Object[] getContentObjetcs()
    {
        return contentObjects;
    }
}