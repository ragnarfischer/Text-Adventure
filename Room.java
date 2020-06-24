import java.util.ArrayList;
import java.util.Iterator;

/**
 * Raum, der einen Namen, eine Beschreibung, Objekte aller Art, Feinde und Ausgänge enthält.
 * 
 * @author Ragnar Fischer
 */
public class Room
{
    private String name;
    private String description;
    private ArrayList<ObjectSpeaker> objectSpeakers;
    private ArrayList<ObjectChanger> objectChangers;
    private ArrayList<ObjectContainer> objectContainers;
    private ArrayList<Enemy> enemies;
    private ArrayList<Exit> exits = new ArrayList<>();

    /**
     * Initialisierung der Variablen mit den übergebenen Werten.
     * 
     * @param name Name des Raums (ein Wort!).
     * @param desciption Beschreibung des Raums.
     * @param objectSpeakers Array aller Objekte im Raum, die einen Text beinhalten.
     * @param objectChangers Array aller Objekte im Raum, die einen Wert beinhalten.
     * @param objectContainers Array aller Objekte im Raum, die weitere Objekte beinhalten.
     * @param enemies Array aller Gegner im Raum
     */
    public Room (String name, String desciption,ArrayList<ObjectSpeaker> objectSpeakers, ArrayList<ObjectChanger> objectChangers, ArrayList<ObjectContainer> objectContainers, ArrayList<Enemy> enemies)
    {
        this.name = name;
        this.description = desciption;
        this.objectSpeakers = objectSpeakers;
        this.objectChangers = objectChangers;
        this.objectContainers = objectContainers;
        this.enemies = enemies;
    }

    /**
     * Hinzufügen eines Ausgangs.
     * 
     * @param exit Ausgang, der hinzugefügt werden soll.
     */
    public void addExit(Exit exit)
    {
        exits.add(exit);
    }

    /**
     * @return Name des Raums.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return Beschreibung des Raums.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @return Alle Objekte jeglicher Art, die sich im Raum befinden.
     */
    public ArrayList<Object> getObjects()
    {
        ArrayList<Object> objects = new ArrayList<>();
        
        if (objectSpeakers != null) {
            for (ObjectSpeaker objectSpeaker : objectSpeakers) {
                objects.add(objectSpeaker);
            }
        }
        if (objectChangers != null) {
            for (ObjectChanger objectChanger : objectChangers) {
                objects.add(objectChanger);
            }
        }
        if (objectContainers != null) {
            for (ObjectContainer objectContainer : objectContainers) {
                objects.add(objectContainer);
            }
        }
        
        return objects;
    }

    /**
     * @return Alle Objekte des Raums, die einen Text beinhalten.
     */
    public ArrayList<ObjectSpeaker> getObjectSpeakers()
    {
        return objectSpeakers;
    }

    /**
     * Hinzufügen eines weiteren Objektes, das einen String beinhaltet.
     * 
     * @param objectSpeaker Objekt, das einen String beinhaltet.
     */
    public void addObjectSpeaker(ObjectSpeaker objectSpeaker)
    {
        objectSpeakers.add(objectSpeaker);
    }

    /**
     * @return Alle Objekte des Raums, die einen Wert beinhalten.
     */
    public ArrayList<ObjectChanger> getObjectChangers()
    {
        return objectChangers;
    }

    /**
     * Hinzufügen eines weiteren Objektes, das einen Wert beinhaltet.
     * 
     * @param objectChanger Objekt, das einen Wert beinhaltet.
     */
    public void addObjectChanger(ObjectChanger objectChanger)
    {
        objectChangers.add(objectChanger);
    }

    /**
     * @return Alle Objekte des Raums, die weitere Objekte beinhalten.
     */
    public ArrayList<ObjectContainer> getObjectContainers()
    {
        return objectContainers;
    }

    /**
     * Hinzufügen eines weiteren Objektes, das weitere Objekte beinhaltet.
     * 
     * @param objectContainer Objekt, das Objekte beinhaltet.
     */
    public void addObjectContainer(ObjectContainer objectContainer)
    {
        objectContainers.add(objectContainer);
    }

    /**
     * @return Alle lebenden Feinde im Raum.
     */
    public ArrayList<Enemy> getEnemies()
    {
        if (enemies == null) {
            return null;
        }
        else {
            ArrayList<Enemy> enemyReturn = new ArrayList<>();
            Iterator<Enemy> enemyIterator = enemies.iterator();

            while (enemyIterator.hasNext()) {
                enemyReturn.add (enemyIterator.next());
            }

            return enemyReturn;
        }
    }

    /**
     * Löschen eines Feindes.
     * 
     * @param enemy Feind, der gelöscht werden soll.
     */
    public void removeEnemy(Enemy enemy)
    {
        enemies.remove(enemy);
    }
    
    /**
     * Objekt wird aus dem Raum nach der Benutzung entfernt.
     * 
     * @author Tyll Heinen
     */
    public void removeObjectChanger(ObjectChanger objectChanger)
    {
        objectChangers.remove(objectChanger);
    }
    
    /**
     * @return Ausgänge des Raums.
     */
    public ArrayList<Exit> getExits()
    {
        return exits;
    }
}
