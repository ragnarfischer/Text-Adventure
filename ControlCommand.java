
/**
 * Beschreiben Sie hier die Klasse ControlCommand.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class ControlCommand
{
    // ein konstantes Array mit den gültigen Befehlswörtern
    private static final String working_Commands[] = {
            "go", "quit", "help", "choose"
        };

    private String commandWord;
    private String secondWord;

    /**
     * Konstruktor für Objekte der Klasse ControlCommand
     */
    public ControlCommand(String commandWord, String secondWord)
    {
        commandWord = commandWord;
        this.secondWord = secondWord;
    }

    /**
     * Liefere das Befehlswort (das erste Wort) dieses Befehls.
     * Wenn der Befehl nicht verstanden wurde, ist das Ergebnis
     * 'null'.
     * @return  das Befehlswort
     */
    public String giveCommandWord()
    {
        return commandWord;
    }

    /**
     * @return  das zweite Wort dieses Befehls 
     *          liefere 'null', wenn es kein zweites Wort gab
     */
    public String giveSecondWord()
    {
        return secondWord;
    }

    /**
     * @return  true, wenn dieser Befehl nicht verstanden wurde
     */
    public boolean isUnknown()
    {
        return (commandWord == null);
    }

    /**
     * @return  true, wenn dieser Befehl ein zweites Wort hat
     */
    public boolean gotSecondWord()
    {
        return (secondWord != null);
    }

    /**
     * Prüfe, ob eine gegebene Zeichenkette ein gültiger
     * Befehl ist.
     * @return true  wenn die gegebene Zeichenkette ein gültiger
     *               Befehl ist, false sonst
     */
    public static boolean isCommand(String input)
    {
        for(int i = 0; i < working_Commands.length; i++) {
            if(working_Commands[i].equals(input))
                return true;
        }
        // Wenn wir hierher gelangen, wurde die Eingabe nicht
        // in den Befehlswörter gefunden.
        return false;
    }

    /**
     * Gib alle gültigen Befehlswörter auf die Konsole aus.
     */
    public void showAllCommands() 
    {
        for(String befehl : working_Commands) {
            System.out.print(befehl + "  ");
        }
        System.out.println();
    }

}
