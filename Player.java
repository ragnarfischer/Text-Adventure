
/**
 * Beschreiben Sie hier die Klasse Player.
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

    public int getAttackDamage()
    {
        return attackDamage;
    }

    public int getArmor()
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

}
