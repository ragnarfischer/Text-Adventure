import java.util.ArrayList;

/**
 * Objekt, das weitere Objekte beinhaltet.
 * 
 * @author Ragnar Fischer
 */
public class ObjectContainer extends Object
{
    private ArrayList<ObjectSpeaker> contentSpeaker;
    private ArrayList<ObjectChanger> contentChanger;
    private ArrayList<ObjectContainer> contentContainer;
    private boolean opened = false;

    /**
     * Initialisierung der Variablen mit den übergebenen Werten.
     * @param name Name des Objektes.
     * @param description Beschreibung des Objektes.
     * @param contentSpeaker Array aller Objekte im Raum, die einen Text beinhalten.
     * @param contentChanger Array aller Objekte im Raum, die einen Wert beinhalten.
     * @param contentContainer Array aller Objekte im Raum, die weitere Objekte beinhalten.
     */
    public ObjectContainer (String name, String description, ArrayList<ObjectSpeaker> contentSpeaker, ArrayList<ObjectChanger> contentChanger, ArrayList<ObjectContainer> contentContainer)
    {
        this.name = name;
        this.description = description;
        this.contentSpeaker = contentSpeaker;
        this.contentChanger = contentChanger;
        this.contentContainer = contentContainer;
        type = 3;
    }

    /**
     * @return Alle Objekte jeglicher Art, die das Objekt beinhaltet.
     */
    public ArrayList<Object> getContent()
    {
        ArrayList<Object> content = new ArrayList<>();
        
        if (contentSpeaker.size() > 0) {
            for (ObjectSpeaker objectSpeaker : contentSpeaker) {
                content.add(objectSpeaker);
            }
        }
        if (contentChanger.size() > 0) {
            for (ObjectChanger objectChanger : contentChanger) {
                content.add(objectChanger);
            }
        }
        if (contentContainer.size() > 0) {
            for (ObjectContainer objectContainer : contentContainer) {
                content.add(objectContainer);
            }
        }
        
        return content;
    }

    /**
     * @return Alle Objekte die das Objekt beinhaltet, die einen Text beinhalten.
     */
    public ArrayList<ObjectSpeaker> getContentSpeaker()
    { 
        return contentSpeaker;
    }

    /**
     * @return Alle Objekte die das Objekt beinhaltet, die einen Wert beinhalten.
     */
    public ArrayList<ObjectChanger> getContentChanger()
    {
        return contentChanger;
    }

    /**
     * @return Alle Objekte die das Objekt beinhaltet, die weitere Objekte beinhalten. WOW! :D
     */
    public ArrayList<ObjectContainer> getContentContainer()
    {
        return contentContainer;
    }
    
    /**
     * @param value Boolean, ob der Container bereits geöffnet wurde.
     * 
     * @author Tyll Heinen & Ragnar Fischer
     */
    public void setOpened(boolean value)
    {
        opened = value;
    }
    
    /**
     * @return Informatiom, ob der Container bereits geöffnet wurde.
     * 
     * @author Tyll Heinen & Ragnar Fischer
     */
    public boolean getOpened()
    {
        return opened;
    }
}
