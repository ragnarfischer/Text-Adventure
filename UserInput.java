import java.util.Scanner;

public class UserInput
{
    private ControlCommand command;  // hält die gültigen Befehlswörter
    private Scanner reader;         // Lieferant für eingegebene Befehle

    /**
     * Konstruktor für Objekte der Klasse UserInput
     */
    public UserInput()
    {
        reader = new Scanner(System.in);
        command = new ControlCommand();
    }

    /**
     * @return  den nächsten Befehl des Benutzers
     */
    public void giveCommand() 
    {
        String inputline;   // für die gesamte Eingabezeile
        String word1 = null;
        String word2 = null;

        System.out.print("> ");     // Eingabeaufforderung

        inputline = reader.nextLine();
        
        // Finde bis zu zwei Wörter in der Zeile
        Scanner cutter = new Scanner(inputline);
        if(cutter.hasNext()) {
            word1 = cutter.next();     // erstes Wort lesen: Buchstaben bis zum nächsten Leerzeichen/Return
            if (cutter.hasNext()) {
                word2 = cutter.next();    // zweites Wort lesen: Buchstaben bis zum nächsten Leerzeichen/Return
                // Hinweis: Wir ignorieren den Rest der Eingabezeile.
            }
        }
        
        // Jetzt prüfen, ob der Befehl bekannt ist. Wenn ja, erzeugen
        // wir das passende Befehl-Objekt. Wenn nicht, erzeugen wir
        // einen unbekannten Befehl mit 'null'.
        
        String[] commands = {word1, word2};
        command.setCommandWord(commands);
    }

    /**
     * Gib eine Liste der bekannten Befehlswörter aus.
     */
    public void showCommand()
    {
        command.showAllCommands();
    }
}
