import java.util.ArrayList;
import java.util.Arrays;

/**
 * Zentrale Klasse, die das Spiel leitet. Sie legt den Spielablauf fest, erstellt das Spielfeld und setzt die Steuerbefehle um.
 * 
 * @author Ragnar Fischer
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
        System.out.println ("Vorstellung des Erzählers, des Szenarios, der Figuren");
    }

    /**
     * Verarbeitung der Character-Auswahl
     */
    private void choosePlayer()
    {
        switch (userInput.getCommand()[0])
        {
            case "Spieler1": player.setPlayer(1); System.out.println ("Hallo " + player.getName()); break;
            case "Spieler2": player.setPlayer(2); System.out.println ("Hallo " + player.getName()); break;
            case "Spieler3": player.setPlayer(3); System.out.println ("Hallo " + player.getName()); break;
            default: System.out.println ("Bitte nochmal. Ich habe dich nicht ganz verstanden."); choosePlayer();
        }
    }

    /**
     * Letzte Tipps an den Spieler, wie ein Verweis auf den Hilfe- und Beenden-Befehl.
     */
    private void lastHints()
    {
        System.out.println ("");
        System.out.println ("Help-Befehl, Exit-Befehl, viel Glück");
    }

    //----------------- Steuerungsmethoden -----------------

    /**
     * Auflistung aller Befehle mit einer Beschreibung.
     */
    private void help()
    {
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
        for (Exit exit : currentRoom.getExits())
        {
            if (exit.getDirection().equals(direction))
            {
                currentRoom = exit.getDestination();
            }
        }
    }

    /**
     * Wiedergabe der Raumbeschreibung, Namen aller im Raum befindlichen Gegenstände und Gegner
     */
    private void lookAround()
    {
        System.out.println (currentRoom.getDescription());

        System.out.print ("Im Raum befinden sich ");
        for (Object object : currentRoom.getObjects())
        {
            System.out.print(object.getName() + ", ");
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
        for (ObjectSpeaker objectSpeaker : currentRoom.getObjectSpeakers())
        {
            if (objectSpeaker.getName().equals(name))
            {
                System.out.println (objectSpeaker.getContent());
            }
        }
    }

    /**
     * Anwendung des Inhaltes eines Objektes, das einen Wert beinhaltet
     * 
     * @param name Name des angesprochenen Objektes
     */
    private void use(String name)
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
                    default: System.out.println("Entschuldigung, es ist ein Fehler aufgetreten.");
                }
            }
        }
    }

    /**
     * Aufzählen und Auspacken der Inhalte eines Objektes, das weitere Objekte beinhaltet
     * 
     * @param name Name des angesprochenen Objektes
     */
    private void open(String name)
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
            }
        }
    }

    /**
     * Wiedergabe der momentanen Eigenschaften des Charakters
     */
    private void checkAbilitys()
    {
        System.out.println("Hier sind deine Fähigkeiten: ");
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

        for (Enemy enemy : currentRoom.getEnemies())
        {
            if (enemy.getName().equals(name))
            {
                System.out.println (enemy.getText());
                
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
                return;
            }
        }
    }

    //----------------- Spielaufbau -----------------

    /**
     * Aufbau des Spiels mit Räumen (mit Inhalt) und Ausgängen. Festlegen des Startraums.
     */
    public GameMaker()
    {
        //Räume
        Room room1 = new Room ("NameRaum1", 
                "Beschreibung Raum 1",
                new ArrayList<ObjectSpeaker> (Arrays.asList( new ObjectSpeaker ("NameObjekt1", "Beschreibung Objekt 1", "Inhalt Objekt 1"), 
                        new ObjectSpeaker ("NameObjekt2", "Beschreibung Objekt 2", "Inhalt Objekt 2"))),
                null,
                new ArrayList<ObjectContainer> (Arrays.asList( new ObjectContainer ("NameObjekt5", "Beschreibung Objekt 5", 
                            new ArrayList<ObjectSpeaker> (Arrays.asList( new ObjectSpeaker("NameA", "Beschreibung A", "Inhalt A"), 
                                    new ObjectSpeaker("NameB", "Beschreibung B", "Inhalt B"))),
                            null,
                            null))),
                null);
        Room room2 = new Room ("NameRaum2", 
                "Beschreibung Raum 2",
                new ArrayList<ObjectSpeaker> (Arrays.asList( new ObjectSpeaker ("NameObjekt3", "Beschreibung Objekt 3", "Inhalt Objekt 3"))),
                new ArrayList<ObjectChanger> (Arrays.asList( new ObjectChanger ("NameObjekt4", "Beschreibung Objekt 4", new int[] {1,10}))),
                null,
                new ArrayList<Enemy> (Arrays.asList( new Enemy ("Feind1", 20, 20, "Beschreibung Feind 1", "Kampftext Fein 1"))));

        //Ausgänge
        room1.addExit (new Exit ("Norden", room2));
        room2.addExit (new Exit ("Süden", room1));

        currentRoom = room1;
    }
}
