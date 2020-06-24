/**
 * Die Klasse Player speichert alle für sich relevanten Variablen und lässt sie von GameMaker aufrufen über 
 * getter.
 * 
 * @author Tyll Heinen 
 * 
 */
public class Enemy
{
    private int attackDamage;
    private int armor;
    private String description;
    private String textFight;
    String name;

    /**
     * Variablen für Enemy werden übergeben
     */
    public Enemy(String name, int attackDamage, int armor, String description, String textFight)
    {
        this.name = name;
        this.attackDamage = attackDamage;
        this.armor = armor;
        this.description = description;
        this.textFight = textFight;
    }
    
    /**
     * @return Name des Enemys
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return Schaden des Enemys
     */
    public int getAttackDamage()
    {
        return attackDamage;
    }
    
    /**
     * @return Rüstung des Enemys
     */
    public int getArmor()
    {
        return armor;
    }
    
    /**
     * @return Beschreibung des Enemys
     */
    public String getDesciption()
    {
        return description;
    }
    
    /**
     * @return Kampftext des Enemys
     */
    public String getText()
    {
        return textFight;
    }
}
