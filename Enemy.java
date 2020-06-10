public class Enemy
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private int x;

    /**
     * Konstruktor für Objekte der Klasse Enemy
     */
    public Enemy()
    {
        // Instanzvariable initialisieren
        x = 0;
    }

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
     */
    public int beispielMethode(int y)
    {
        // tragen Sie hier den Code ein
        return x + y;
    }
    
    public void welcomeText()
    {
        System.out.println("Willkommen Fremdling.");
    }
    
    public void textFight()
    {
        System.out.println("Ich habe direkt an deinem widerlichen Gestank erkannt. Zieh dein Schwert damit ich dich vernichten kann.");
    }
    
    public void textTalk()
    {
        System.out.println("Ich bin" + x + "und kann dir helfen. Möchtest du Informationen haben?");
    }
}
