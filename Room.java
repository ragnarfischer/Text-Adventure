import java.util.ArrayList;

public class Room
{
    private String name;
    private String description;
    private ArrayList<Object> objects;
    private ArrayList<Exit> exits;
    
    public Room (String name, String desciption, ArrayList<Object> objects)
    {
        this.name = name;
        this.description = description;
        this.objects = objects;
    }
    
    public void addExit(Exit exit)
    {
        exits.add(exit);
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public ArrayList<Object> getObjects()
    {
        return objects;
    }
    
    public ArrayList<Exit> getExits()
    {
        return exits;
    }
}
