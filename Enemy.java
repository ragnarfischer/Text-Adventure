public class Enemy
{
    private int attackDamage;
    private int armor;
    private String description;
    private String textFight;
    String name;

    /**
     * Konstruktor für Objekte der Klasse Enemy
     */
    public Enemy(String name, int attackDamage, int armor, String description, String textFight)
    {
        this.name = name;
        this.attackDamage = attackDamage;
        this.armor = armor;
        this.description = description;
        this.textFight = textFight;
    }
    
    public String getName()
    {
        return name;
    }

    public int getAttackDamage()
    {
        return attackDamage;
    }
    
    public int getArmor()
    {
        return armor;
    }
    
    public String getDesciption()
    {
        return description;
    }
    
    public String getText()
    {
        return textFight;
    }
}
