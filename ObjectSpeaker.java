/**
 * Objekt, das Text beinhaltet.
 * 
 * @author Ragnar Fischer
 */
public class ObjectSpeaker extends Object
{
    String content;
    
    /**
     * Initialisierung der Variablen mit den übergebenen Werten.
     * @param name Name des Objektes.
     * @param description Beschreibung des Objektes.
     * @param content Text, den das Objekt beinhaltet.
     */
    public ObjectSpeaker (String name, String description, String content)
    {
        this.name = name;
        this.description = description;
        this.content = content;
        type = 1;
    }
    
    /**
     * @return Text, den das Objekt beeinhaltet.
     */
    public String getContent()
    {
        return content;
    }
}
