public class Object
{
    private String name;
    private String description;
    private String content;
    
    public Object (String name, String description, String content)
    {
        this.name = name;
        this.description = description;
        this.content = content;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public String interact()
    {
        //Zurückgeben des Contents. Am besten über Array, bei dem erstes Feld Art der Variable impliziert und zweites den Inhalt trägt // Oder separates Content-Objekt mit versch. Konsturktoren, die das dann irgendwie regeln
        return content;
    }
}
