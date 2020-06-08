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
     * @return Der Typ des Objektes (1 für String-Objekt, 2 für Wert-Objekt)
     */
    public int getType()
    {
        if (contentValue == null)
        {
            return 1;
        }
        else
        {
            return 2;
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
}