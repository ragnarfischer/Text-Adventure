import java.util.*;

public class GameMaker
{
    private boolean quit = false;
    private UserInput userInput = new UserInput();
    private ControlCommand controlCommand;
    private Player player = new Player();
    
    public void play()
    {
        introduction();
        choosePlayer();
        lastHints();
        
        while (!quit)
        {
            controlCommand = new ControlCommand(userInput.giveCommand());
            controlCommand.execute();
        }
    }
    
    public void setQuit()
    {
        quit = true;
    }
    
    private void introduction()
    {
        System.out.println ("Vorstellung des Erzählers, des Szenarios, der Figuren");
    }
    
    private void choosePlayer()
    {
        controlCommand = new ControlCommand(userInput.giveCommand());
        
        switch (controlCommand.getCommand()[0])
        {
            case "player": player.setPlayer(1); break;
            case "hunter": player.setPlayer(2); break;
            case "witcher": player.setPlayer(3); break;
            default: System.out.println ("Bitte nochmal. Ich habe dich nicht ganz verstanden.");
        }
    }
    
    private void lastHints()
    {
        System.out.println ("Help-Befehl, Exit-Befehl, viel Glück");
    }
    
    public void help()
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
