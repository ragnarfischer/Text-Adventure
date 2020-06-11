
/**
 * Beschreiben Sie hier die Klasse Player.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Player
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private String beschreibung;
    private int stärke = 0;
    private int rüstung = 0;
    
    /**
     * Konstruktor für Objekte der Klasse Player
     */
    public Player()
    {
        this.beschreibung = beschreibung;
    }

    /**
     * @return  die kurze Beschreibung dieses Raums (die dem Konstruktor
     *          übergeben wurde)
     */
    public String gibKurzbeschreibung()
    {
        return beschreibung;
    }
    
    public void setPlayer(int index)
    {
        System.out.println ("You chose Player " + index);
    }
}
