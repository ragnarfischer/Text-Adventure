import java.util.Scanner;

/**
 * Beschreiben Sie hier die Klasse UserInput.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
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
        command = new ControlCommand(null, null);
    }

    /**
     * @return  den nächsten Befehl des Benutzers
     */
    public ControlCommand giveCommand() 
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
        if(ControlCommand.isCommand(word1)) {
            return new ControlCommand(word1, word2);
        }
        else {
            return new ControlCommand(null, word2);
        }
    }

    /**
     * Gib eine Liste der bekannten Befehlswörter aus.
     */
    public void showCommand()
    {
        command.showAllCommands();
    }
}
