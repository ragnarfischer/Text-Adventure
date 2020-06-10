import java.util.*;

public class GameMaker
{
    private boolean quit = false;
    private UserInput userInput = new UserInput();
    private ControlCommand controlCommand = new ControlCommand();
    private Player player = new Player();
    
    public void PLAY()
    {
        introduction();
        choosePlayer();
        lastHints();
        
        while (!quit)
        {
            userInput.parse();
            execute (controlCommand.getCurrentCommand());
        }
    }
    
    private void execute (String controlCommand)
    {
        switch (controlCommand)
        {
            case "Abbrechen": quit = true;
            case "Hilfe": help();
            default: System.out.println ("Bitte nochmal. Ich habe nicht ganz verstanden, was du machen möchtest.");
        }
    }
    
    private void introduction()
    {
        System.out.println ("Vorstellung des Erzählers, des Szenarios, der Figuren");
    }
    
    private void choosePlayer()
    {
        userInput.parse();
        
        switch (controlCommand.getCurrentCommand())
        {
            case "Spieler 1": player.setPlayer(1);
            case "Spieler 2": player.setPlayer(2);
            case "Spieler 3": player.setPlayer(3);
            default: System.out.println ("Bitte nochmal. Ich habe dich nicht ganz verstanden.");
        }
    }
    
    private void lastHints()
    {
        System.out.println ("Help-Befehl, Exit-Befehl, viel Glück");
    }
    
    private void help()
    {
        System.out.println ("Auflistung aller Befehle und vielleicht noch sonst was");
    }
    
    private void gameSetup()
    {
        //Räume
        Room entryHall = new Room ("Eingangshalle", 
                                   "einem langen, prunkvoll breiten Flur, mir hoher Decke. Die kalten steinernen Wände werden durch Fackellicht erhellt, dessen knistern und lodern einen langen Hall erzeugt. Ansonsten ist es totenstill. Nein, links von dir scheint es von der Decke zu tropfen. Aber ansonsten ist es still. Totenstill.",
                                   new ArrayList<Object> (Arrays.asList (new Object ("Pfütze", "die beschissen laute Pfütze", "Wasser"), 
                                                                   new Object ("A", "B", "C"))));
                                                                   
        //Ausgänge
        entryHall.addExit (new Exit ("Norden", entryHall));
    }
}
