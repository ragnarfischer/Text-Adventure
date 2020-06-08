import java.util.*;

public class GameMaker
{
    private boolean quit = false;
    private UserInput userInput = new UserInput();
    private ControlCommand controlCommand = new ControlCommand();
    
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
        // Dies das execute und so
    }
    
    private void introduction()
    {
        System.out.println ("Hallo, hallo, hallo. Blablabla");
        
        //Vorstellung des Erzählers, des Szenarios, der Figuren
    }
    
    private void choosePlayer()
    {
        //Auswahl zwischen vorgestellten Spielern durch Eingabe des Namens
    }
    
    private void lastHints()
    {
        System.out.println ("Weist du was? Ich bin SCHWANGEER!");
        
        //Help-Befehl, Exit-Befehl, viel Glück
    }
    
    private void gameSetup()
    {
        //Räume
        Room entryHall = new Room ("Eingangshalle", 
                                   "einem langen, prunkvoll breiten Flur, mir hoher Decke. Die kalten steinernen Wände werden durch Fackellicht erhellt, dessen knistern und lodern einen langen Hall erzeugt. Ansonsten ist es totenstill. Nein, links von dir scheint es von der Decke zu tropfen. Aber ansonsten ist es still. Totenstill.",
                                   new ArrayList<Object> (List.of (new Object ("Pfütze", "die beschissen laute Pfütze", "Wasser"), 
                                                                   new Object ("A", "B", "C"))));
        
        //Ausgänge
        entryHall.addExit (new Exit ("Norden", entryHall));
    }
}
