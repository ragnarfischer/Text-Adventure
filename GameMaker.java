import java.util.ArrayList;
import java.util.Arrays;

/**
 * Zentrale Klasse, die das Spiel leitet. Sie legt den Spielablauf fest, erstellt das Spielfeld und setzt die Steuerbefehle um.
 * 
 * @author Ragnar Fischer & Tyll Heinen
 */
public class GameMaker
{
    private boolean quit = false;
    private UserInput userInput = new UserInput();
    private Player player = new Player();
    private Room currentRoom;

    /**
     * Zentrale Methode, die vom Nutzer aufgerufen wird. Sie beinhaltet den Spielablauf.
     */
    public void play()
    {
        introduction();
        choosePlayer();
        lastHints();

        while (!quit)
        {
            execute();
        }
    }

    /**
     * Verarbeitung des Befehlsstrings
     */
    private void execute ()
    {
        String[] command = userInput.getCommand();

        switch (command[0])
        {
            case "Hilfe":       help();                 break;
            case "Beenden":     quit = true;            break;
            case "Gehe":        changeRoom(command[2]); break;
            case "Umschauen":   lookAround();           break;
            case "Sprich":      speakTo(command[2]);    break;
            case "Lies":        speakTo(command[1]);    break;
            case "Benutze":     use(command[1]);        break;
            case "Öffne":       open(command[1]);       break;
            case "Prüfe":       checkAbilitys();        break;
            case "Kämpfe":      fight(command[2]);      break;
            default:            System.out.println ("Bitte nochmal. Ich habe nicht ganz verstanden, was du machen möchtest.");
        }
    }

    //----------------- Einleitende Methoden -----------------

    /**
     * Der Erzähler stellt sich vor, es wird in die Geschichte eingeleitet, die zur Auswahl stehenden Charaktere werden beschrieben.
     */
    private void introduction()
    {
        System.out.println("Wilkommen in dem Schloss der Trolle, du bist ein gesandter des Königs und hast die Aufgabe das Schloss von den Feinden zu säubern.");
        System.out.println("");
        System.out.println("Du kannst, indem du versteckte Items findest deine Rüstung, deinen Nahkampfwert verbessern.");
        System.out.println("Außerdem kannst du deine Energie nach einem Kampf mit Zaubertränken wieder auffüllen.");
        System.out.println("");
        System.out.println("Du hast nun die Auswahl zwischen 3 Figuren.");
        System.out.println("Entweder du wählst einen ehrenvollen Krieger mit dicker Rüstung und breitem Schwert,");
        System.out.println("oder einen Bogenschützen mit dünner Lederrüstung und messerscharfen Pfeilspitzen,"); 
        System.out.println("oder einen Magier mit einem spitzen Hut, einem Zauberstab und einem modrig riechenden Gewand.");
        System.out.println("");
        System.out.println("Bitte gib nun ein, entweder Ritter, Bogenschütze, oder Magier");
    }

    /**
     * Verarbeitung der Character-Auswahl
     */
    private void choosePlayer()
    {
        switch (userInput.getCommand()[0])
        {
            case "Ritter": player.setPlayer(1); System.out.println ("Hallo Herr " + player.getName() +" !"); break;
            case "Bogenschütze": player.setPlayer(2); System.out.println ("Hallo Herr " + player.getName() +" !"); break;
            case "Magier": player.setPlayer(3); System.out.println ("Hallo Herr " + player.getName() +" !"); break;
            default: System.out.println ("Bitte nochmal. Ich habe dich nicht ganz verstanden."); choosePlayer();
        }
    }

    /**
     * Letzte Tipps an den Spieler, wie ein Verweis auf den Hilfe- und Beenden-Befehl.
     */
    private void lastHints()
    {
        System.out.println ("");
        System.out.println ("Falls du nicht weiter weißt, dann gib einfach mal 'Hilfe' ein.");
        System.out.println ("Wenn du keine Lust mehr haben solltest, dann gib einfach 'Beenden' ein");
        System.out.println ("Nun viel Erfolg und stirb am besten nicht!");

    }

    //----------------- Steuerungsmethoden -----------------

    /**
     * Auflistung aller Befehle mit einer Beschreibung.
     */
    private void help()
    {
        System.out.println("");
        System.out.println ("Diese Anweisungen verstehe ich am besten: ");
        System.out.println ("'Hilfe'                       - Ich zeige dir welche Anweisungen ich am besten verstehe.");
        System.out.println ("'Beenden'                     - Wir beenden die Mission.");
        System.out.println ("'Gehe nach (Himmelsrichtung)' - Ich verlasse den Raum in die angegebene Richtung.");
        System.out.println ("'Umschauen'                   - Ich sage dir, was ich sehe.");
        System.out.println ("'Sprich mit (Name)'           - Ich spreche die angegebe Person an und höre, ob sie etwas interessantes zu sagen hat.");
        System.out.println ("'Lies (Gegenstand)'           - Ich lese dir vor, was auf dem angegebenen Gegenstand steht.");
        System.out.println ("'Benutze (Gegenstand)'        - Ich benutze den angegeben Gegenstand.");
        System.out.println ("'Öffne (Gegenstand)'          - Ich öffne den angegebenen Gegenstand, sage dir was sich darin befindet und nehme es heraus.");
        System.out.println ("'Prüfe meine Fähigkeiten'     - Ich überprüfe wie gut meine Waffe und meine Rüstung momentan ist.");
        System.out.println ("'Kämpfe mit (Name)'           - Ich beginne einen Kampf mit dem angegebenen Gegner.");
    }

    /**
     * Wechsel des Raumes
     * 
     * @param direction Richtung, in die der momentane Raum verlassen wird.
     */
    private void changeRoom(String direction)
    {
        if (currentRoom.getExits() != null)
        {
            for (Exit exit : currentRoom.getExits())
            {
                if (exit.getDirection().equals(direction))
                {
                    currentRoom = exit.getDestination();
                    return;
                }
            }
        }
        System.out.println ("Bitte nochmal. Ich habe nicht ganz verstanden, wohin du gehen möchtest.");
    }

    /**
     * Wiedergabe der Raumbeschreibung, Namen aller im Raum befindlichen Gegenstände und Gegner
     */
    private void lookAround()
    {
        System.out.println (currentRoom.getDescription());

        System.out.print ("Im Raum befinden sich ");
        if (currentRoom.getObjects() != null)
        {
            for (Object object : currentRoom.getObjects())
            {
                System.out.print(object.getName() + ", ");
            }
        }
        if (currentRoom.getEnemies() != null)
        {
            for (Enemy enemy : currentRoom.getEnemies())
            {
                System.out.print(enemy.getName() + ", ");
            }
        }
        System.out.println (player.getName() + ".");
    }

    /**
     * Wiedergabe des Inhaltes eines Objektes, das einen String beinhaltet
     * 
     * @param name Name des angesprochenen Objektes
     */
    private void speakTo (String name)
    {
        if (currentRoom.getObjectSpeakers() != null)
        {
            for (ObjectSpeaker objectSpeaker : currentRoom.getObjectSpeakers())
            {
                if (objectSpeaker.getName().equals(name))
                {
                    System.out.println (objectSpeaker.getContent());
                    return;
                }
            }
        }
        System.out.println ("Bitte nochmal. Ich habe nicht ganz verstanden, was du machen möchtest.");
    }

    /**
     * Anwendung des Inhaltes eines Objektes, das einen Wert beinhaltet
     * 
     * @param name Name des angesprochenen Objektes
     */
    private void use(String name)
    {
        if ("Karte".equals(name))
        {
            card();
            return;
        }
        if (currentRoom.getObjectChangers() != null)
        {
            for (ObjectChanger objectChanger : currentRoom.getObjectChangers())
            {
                if (objectChanger.getName().equals(name))
                {
                    switch (objectChanger.getContent()[0])
                    {
                        case 1: player.setAttackDamage(player.getAttackDamage() + objectChanger.getContent()[1]); break;
                        case 2: player.setArmor(player.getArmor() + objectChanger.getContent()[1]);               break;
                        case 3: player.setEnergy(player.getEnergy() + objectChanger.getContent()[1]);             break;
                        default: System.out.println("Entschuldigung, irgendwie ist mir Fehler passiert.");
                    }
                    return;
                }
            }
        }
        System.out.println ("Bitte nochmal. Ich habe nicht ganz verstanden, was du benutzen möchtest.");
    }

    /**
     * Aufzählen und Auspacken der Inhalte eines Objektes, das weitere Objekte beinhaltet
     * 
     * @param name Name des angesprochenen Objektes
     */
    private void open(String name)
    {
        if (currentRoom.getObjectContainers() != null)
        {
            for (ObjectContainer objectContainer : currentRoom.getObjectContainers())
            {
                if (objectContainer.getName().equals(name))
                {
                    System.out.print ("Hier drin befinden sich: ");
                    if (objectContainer.getContentSpeaker() != null)
                    {
                        for (ObjectSpeaker objectSpeaker : objectContainer.getContentSpeaker())
                        {
                            System.out.print(objectSpeaker.getName() + ", ");
                            currentRoom.addObjectSpeaker(objectSpeaker);
                        }
                    }
                    if (objectContainer.getContentChanger() != null)
                    {
                        for (ObjectChanger objectChanger : objectContainer.getContentChanger())
                        {
                            System.out.print(objectChanger.getName() + ", ");
                            currentRoom.addObjectChanger(objectChanger);
                        }
                    }
                    if (objectContainer.getContentContainer() != null)
                    {
                        for (ObjectContainer objectContainerContent : objectContainer.getContentContainer())
                        {
                            System.out.print(objectContainerContent.getName() + ", ");
                            currentRoom.addObjectContainer(objectContainer);
                        }
                    }
                    System.out.println ("Luft.");
                    return;
                }
            }
        }
        System.out.println ("Bitte nochmal. Ich habe nicht ganz verstanden, was du öffnen möchtest.");
    }

    /**
     * Wiedergabe der momentanen Eigenschaften des Charakters
     */
    private void checkAbilitys()
    {
        System.out.println ("Hier sind deine Fähigkeiten: ");
        System.out.println ("Stärke: " + player.getAttackDamage());
        System.out.println ("Rüstung: " + player.getArmor());
        System.out.println ("Energie: " + player.getEnergy());
    }

    /**
     * Vergleich der Eigenschaften des Charakters und des genannten Gegners. Je nach Ergebis: löschen des Gegners, keine Veränderung oder beenden des Spiels.
     * 
     * @param name Name des angesprochenen Gegners
     */
    private void fight (String name)
    {
        int score = 0;

        if (currentRoom.getEnemies() != null)
        {
            for (Enemy enemy : currentRoom.getEnemies())
            {
                if (enemy.getName().equals(name))
                {
                    System.out.println (enemy.getText());

                    if(player.getEnergy() == 0)
                    {
                        System.out.println("Durch das ganze kämpfen bist du erschöpft bitte such dir einen Energieauffrischer. Falls du einen Gegner triffst, dann verlierst du aus schwäche den Kampf.");
                        System.out.println ("Verloren gegen " + enemy.getName());
                        quit = true;
                    }
                    score = score + (player.getArmor() - enemy.getAttackDamage());
                    score = score + (player.getAttackDamage() - enemy.getArmor());

                    long currentTime = System.currentTimeMillis();
                    while (System.currentTimeMillis() < currentTime + 3000) { }

                    if (score > 0) {
                        System.out.println ("Gewonnen gegen " + enemy.getName());
                        currentRoom.removeEnemy(enemy);
                    }
                    else if (score == 0) {
                        System.out.println ("Unentschieden gegen " + enemy.getName());
                    }
                    else {
                        System.out.println ("Verloren gegen " + enemy.getName());
                        quit = true;
                    }
                    player.afterFight();
                    return;
                }
            }
        }

        System.out.println ("Bitte nochmal. Ich habe nicht ganz verstanden, gegen wen du kämpfen möchtest.");
    }

    //----------------- Spielaufbau -----------------

    /**
     * Aufbau des Spiels mit Räumen (mit Inhalt) und Ausgängen. Festlegen des Startraums.
     */
    public GameMaker()
    {
        //Räume
        Room room1 = new Room (
            "Eingangshalle", 
            "Ein Raum mit hohen Decken und großen Fenstern. Man hört ein leises plätschern und spürt einen kalten Windzug.",
            new ArrayList<ObjectSpeaker> (Arrays.asList( new ObjectSpeaker ("Atha-ulf", "Ein alter Mann mit einem leeren Blick.", "Endlich! Wir brauchen dringed hilfe, sprich mit meinen Freund Theoderich im Westen. Der weiß was zu tun ist!"))),
            //new ArrayList<ObjectChanger>(),            
            null,
            new ArrayList<ObjectContainer> (Arrays.asList( new ObjectContainer ("Schrank", "Ein großer alter Schnrak an der Wand", 
            new ArrayList<ObjectSpeaker> (Arrays.asList( new ObjectSpeaker ("Atha-ulf", "Ein alter Mann mit einem leeren Blick.", "Endlich! einekm Freund Theoderich im Westen. Der weiß was zu tun ist!"))),
            new ArrayList<ObjectChanger> (Arrays.asList( new ObjectChanger ("Kettenhemd", "Ein wahrscheinlich lebensrettendes kettenhemd mit ein paar Blutspritzern...", new int[] {1,10}))),
                            null))),
                null);

        Room room2 = new Room ("NameRaum2", 
                "Beschreibung Raum 2",
                new ArrayList<ObjectSpeaker> (Arrays.asList( new ObjectSpeaker ("NameObjekt3", "Beschreibung Objekt 3", "Inhalt Objekt 3"))),
                new ArrayList<ObjectChanger> (Arrays.asList( new ObjectChanger ("NameObjekt4", "Beschreibung Objekt 4", new int[] {1,10}))),
                null,
                new ArrayList<Enemy> (Arrays.asList( new Enemy ("Feind1", 20, 20, "Beschreibung Feind 1", "Kampftext Feind 1"))));

        //Ausgänge
        room1.addExit (new Exit ("Norden", room2));
        room2.addExit (new Exit ("Süden", room1));

        currentRoom = room1;
    }

    public void card()
    { 
        System.out.println(""); 
        System.out.println("Hier siehst du die Karte, das O markiert deinen Startpunkt und die markierten Stellen mit dem x kannst du nicht betreten:");  
        System.out.println(""); 
        System.out.println("                                 ------------");  
        System.out.println("                                |            |");
        System.out.println("                                |            |");
        System.out.println("                                |            |");  
        System.out.println("           ---------- ---------- --------------------");
        System.out.println("          |          |                               |");
        System.out.println("          |          |                               |");
        System.out.println("          |           --------------------------------------------------------------");
        System.out.println("          |          | xxxxxxxxxxxxxxxxxxxxxx|            |            |            |");
        System.out.println("          |--------------------------------|x|            |            |            |");
        System.out.println(" ---------|                                |x|            |            |            |");
        System.out.println("|         |                                |x|--------------------------------------");
        System.out.println("|         |  O                             |-|          |");
        System.out.println("|         |                                  |          |");
        System.out.println(" ---------|                                  |          |");
        System.out.println("          |                                  |          |");
        System.out.println("           ---------------------------------------------");
    }
}
