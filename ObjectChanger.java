/**
 * Objekt, das einen Wert beinhaltet.
 * 
 * @author Ragnar Fischer
 */
public class ObjectChanger extends Object
{
    private int[] content;
    private boolean visibility = true;
    
    /**
     * Initialisierung der Variablen mit den übergebenen Werten.
     * @param name Name des Objektes.
     * @param description Beschreibung des Objektes.
     * @param content Wert, den das Objekt beinhaltet.
     */
    public ObjectChanger (String name, String description, int[] content)
    {
        this.name = name;
        this.description = description;
        this.content = content;
        type = 2;
    }
    
    /**
     * Indikatorwert, der das Objekt unsichtbar schaltet, nachdem es benutzt wurde.
     * @
     */
    public void setVisibility(boolean value)
    {
        visibility = value;
    }
    
    public boolean getVisibility()
    {
        return visibility;
    }
    
    /**
     * @return Wert, den das Objekt beeinhaltet.
     */
    public int[] getContent()
    {
        return content;
    }
}
