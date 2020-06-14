
/**
 * Beschreiben Sie hier die Klasse Player.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Player
{
    private int attackDamage;
    private int armor;
    private int energy = 100;
    private String name;
    /**
     * Funktion wird von GameMaker aufgerufen und übergibt eine Zahl, mit der dann die Werte des Player festgelegt werden
     */
    public void setPlayer(int player)
    {
        switch (player)
        {
            case 1: name = "NameSpieler1"; armor = 100; attackDamage = 50; break; //knight
            case 2: name = "NameSpieler2"; armor = 50; attackDamage = 70; ; break; //elb
            case 3: name = "NameSpieler3"; armor = 20; attackDamage = 100; break; //witcher
        }
    }
    
    public void afterFight()
    {
        energy = energy - 50;
    }
    
    public int giveAttackDamage()
    {
        return attackDamage;
    }
    
    public int giveEnergy()
    {
        return energy;
    }
    
    public int giveArmor()
    {
        return armor;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setAttackDamage(int value)
    {
        attackDamage = value;
    }
    
    public void setArmor(int value)
    {
        armor = value;
    }
    
    public void setEnergy(int value)
    {
        energy = value;
    }
}
