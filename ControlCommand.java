/**
 * Beschreiben Sie hier die Klasse ControlCommand.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class ControlCommand
{
    private static final String working_Commands[] = {
            "go", "quit", "help", "choose"
        };
    
    private String[] mcommandWord;
    private GameMaker gameMaker;
    public ControlCommand(String[] commandWords)
    {
        mcommandWord = commandWords;
    }

    public String[] getCommand()
    {
        return mcommandWord;
    }
    
    public void execute ()
    {
        gameMaker = new GameMaker();
        switch (mcommandWord[0])
        {
            case "quit": gameMaker.setQuit(); break;
            case "help": gameMaker.help(); break;
            default: System.out.println ("Bitte nochmal. Ich habe nicht ganz verstanden, was du machen möchtest.");
        }
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
