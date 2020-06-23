import java.util.Scanner;
/**
 * Beschreiben Sie hier die Klasse UserInput.
 * 
 * @author Tyll Heinen 
 * 
 */
public class UserInput
{
    private Scanner reader;         // Lieferant für eingegebene Befehle

    /**
     * Konstruktor für Objekte der Klasse UserInput
     */
    public UserInput()
    {
        reader = new Scanner(System.in);
    }

    /**
     * @return  den nächsten Befehl des Benutzers
     */
    public String[] getCommand() 
    {
        String inputline;   // für die gesamte Eingabezeile
        String word1 = null;
        String word2 = null;
        String word3 = null;

        System.out.print("> ");     // Eingabeaufforderung

        inputline = reader.nextLine();

        // Finde bis zu zwei Wörter in der Zeile
        Scanner cutter = new Scanner(inputline);
        if(cutter.hasNext()) {
            word1 = cutter.next();     // erstes Wort lesen: Buchstaben bis zum nächsten Leerzeichen/Return
            if (cutter.hasNext()) {
                word2 = cutter.next();    // zweites Wort lesen: Buchstaben bis zum nächsten Leerzeichen/Return
                if (cutter.hasNext()) {
                    word3 = cutter.next();    // zweites Wort lesen: Buchstaben bis zum nächsten Leerzeichen/Return
                    // Hinweis: Wir ignorieren den Rest der Eingabezeile.
                }
            }
        }

        // Jetzt prüfen, ob der Befehl bekannt ist. Wenn ja, erzeugen
        // wir das passende Befehl-Objekt. Wenn nicht, erzeugen wir
        // einen unbekannten Befehl mit 'null'.

        String[] commands = {word1, word2, word3};
        return commands;
    }

    /**
     * Gib eine Liste der bekannten Befehlswörter aus.
     */
    public void showCommand()
    {
        //ControlCommand.showAllCommands();
    }
}
