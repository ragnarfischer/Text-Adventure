
/**
 * Die Klasse Player speichert alle für sich relevanten Variablen und lässt sie von GameMaker aufrufen und verändern über 
 * getter und setter.
 * 
 * @author Tyll Heinen 
 * 
 */
public class Player
{
    private int attackDamage;
    private int armor;
    private String name;
    /**
     * Funktion wird von GameMaker aufgerufen und übergibt eine Zahl, mit der dann die Werte des Player festgelegt werden
     */
    public void setPlayer(int player)
    {
        switch (player)
        {
            case 1: name = "Ritter"; armor = 100; attackDamage = 50; break; //knight
            case 2: name = "Bogenschütze"; armor = 75; attackDamage = 75; ; break; //elb
            case 3: name = "Magier"; armor = 50; attackDamage = 100; break; //witcher
        }
    }

    /**
     * @return Schaden des Players
     */
    public int getAttackDamage()
    {
        return attackDamage;
    }

    /**
     * @return Rüstung des Players
     */
    public int getArmor()
    {
        return armor;
    }

    /**
     * @return Name des Players
     */
    public String getName()
    {
        return name;
    }

    /**
     * @verändert Schaden des Players mit einer Rechnung in GameMaker, wenn Objekte benutzt werden
     */
    public void setAttackDamage(int value)
    {
        attackDamage = value;
    }

    /**
     * @verändert Rüstung des Players mit einer Rechnung in GameMaker, wenn Objekte benutzt werden
     */
    public void setArmor(int value)
    {
        armor = value;
    }

}
