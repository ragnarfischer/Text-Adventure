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
     * 
     * @author Ragnar Fischer
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
     * Aufruf der Aktionsmethoden basierend auf der Anwesenheit von Gegnern oder dem eingegebenen Befehlsstring.
     * 
     * @author Ragnar Fischer & Tyll Heinen
     */
    private void execute ()
    {
        if(currentRoom.getEnemies() != null)
        {
            if(currentRoom.getEnemies().size() > 0)
            {
                for (Enemy enemy : currentRoom.getEnemies())
                {
                    fight(enemy.getName());
                    
                    if (quit == true)
                    {
                        return;
                    }
                }
            }
        }
        
        String[] command = userInput.getCommand();

        switch (command[0])
        {
            case "Hilfe":       help();                 break;
            case "Beenden":     quit = true;            break;
            case "Gehe":        changeRoom(command[2]); break;
            case "Umschauen":   lookAround();           break;
            case "Sprich":      speakTo(command[2]);    break;
            case "Lies":        speakTo(command[1]);    break;
            case "Schaue":      card();                 break;
            case "Benutze":     use(command[1]);        break;
            case "Öffne":       open(command[1]);       break;
            case "Inspiziere":  inspect(command[1]);    break;
            case "Prüfe":       checkAbilitys();        break;
            default:            System.out.println ("Bitte nochmal. Ich habe nicht ganz verstanden, was du machen möchtest.");
        }
    }

    //----------------- Einleitende Methoden -----------------

    /**
     * Der Erzähler stellt sich vor, es wird in die Geschichte eingeleitet, die zur Auswahl stehenden Charaktere werden beschrieben.
     * 
     * @author Tyll Heinen
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
     * 
     * @author Ragnar Fischer
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
     * 
     * @author Tyll Heinen
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
     * 
     * @author Ragnar Fischer
     */
    private void help()
    {
        System.out.println("");
        System.out.println ("Diese Anweisungen verstehe ich am besten: ");
        System.out.println ("'Hilfe'                           - Ich zeige dir welche Anweisungen ich am besten verstehe.");
        System.out.println ("'Beenden'                         - Wir beenden die Mission.");
        System.out.println ("'Gehe nach (Himmelsrichtung)'     - Ich verlasse den Raum in die angegebene Richtung.");
        System.out.println ("'Umschauen'                       - Ich sage dir, was ich sehe.");
        System.out.println ("'Sprich mit (Name)'               - Ich spreche die angegebe Person an und höre, ob sie etwas interessantes zu sagen hat.");
        System.out.println ("'Lies (Gegenstand)'               - Ich lese dir vor, was auf dem angegebenen Gegenstand steht.");
        System.out.println ("'Benutze (Gegenstand)'            - Ich benutze den angegeben Gegenstand.");
        System.out.println ("'Öffne (Gegenstand)'              - Ich öffne den angegebenen Gegenstand, sage dir was sich darin befindet und nehme es heraus.");
        System.out.println ("'Inspiziere (Gegenstand) genauer' - Ich erkläre dir, genau, was ich sehe.");
        System.out.println ("'Prüfe meine Fähigkeiten'         - Ich überprüfe wie gut meine Waffe und meine Rüstung momentan ist.");
        System.out.println ("'Schaue auf die Karte'            - Ich zeige dir eine Übersicht über das Gebäude.");
    }

    /**
     * Wechsel des Raumes
     * @param direction Richtung, in die der momentane Raum verlassen wird.
     * 
     * @author Ragnar Fischer
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
                    System.out.println ("Du bist nun in " + currentRoom.getName());
                    return;
                }
            }
        }
        System.out.println ("Bitte nochmal. Ich habe nicht ganz verstanden, wohin du gehen möchtest.");
    }

    /**
     * Wiedergabe der Raumbeschreibung, Namen aller im Raum befindlichen Gegenstände und Gegner
     * 
     * @author Ragnar Fischer
     */
    private void lookAround()
    {
        System.out.println (currentRoom.getDescription());

        System.out.print ("Im Raum befinden sich ");
        if(currentRoom.getObjects().size() > 0)
        {
            for (Object object : currentRoom.getObjects())
            {
                System.out.print(object.getName() + ", ");
            }
        }
        if(currentRoom.getEnemies() != null)
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
     * @param name Name des angesprochenen Objektes
     * 
     * @author Ragnar Fischer
     */
    private void speakTo (String name)
    {
        if( currentRoom.getObjectSpeakers().size() > 0)
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
     * Anwendung des Inhaltes eines Objektes, das einen Wert beinhaltet und anschließendes löschen des Objektes aus Raum und Kiste
     * @param name Name des angesprochenen Objektes
     * 
     * @author Ragnar Fischer & Tyll Heinen
     */
    private void use(String name)
    {
        if(currentRoom.getObjectChangers().size() > 0)
        {
            for (ObjectChanger objectChanger : currentRoom.getObjectChangers())
            {
                if (objectChanger.getName().equals(name))
                {
                    switch (objectChanger.getContent()[0])
                    {
                        case 1: player.setAttackDamage(player.getAttackDamage() + objectChanger.getContent()[1]);    break;
                        case 2: player.setArmor(player.getArmor() + objectChanger.getContent()[1]);                  break;
                        default: System.out.println("Entschuldigung, irgendwie ist mir ein Fehler passiert.");
                    }
                    System.out.println ("Du hast " + objectChanger.getName() + " benutzt.");
                    currentRoom.removeObjectChanger(objectChanger);
                    objectChanger.setContent();
                    return;
                }
            }
        }
        System.out.println ("Bitte nochmal. Ich habe nicht ganz verstanden, was du benutzen möchtest.");
    }
    
    /**
     * Wiedergabe der Beschreibung eines Objektes.
     * 
     * @author Ragnar Fischer
     */
    private void inspect (String name)
    {
        if (currentRoom.getObjects().size() > 0)
        {
            for (Object object : currentRoom.getObjects())
            {
                if (object.getName().equals(name))
                {
                    System.out.println (object.getDescription());
                    return;
                }
            }
        }
        System.out.println ("Bitte nochmal. Ich habe nicht ganz verstanden, was du inspizieren möchtest.");
    }

    /**
     * Aufzählen und Auspacken der Inhalte eines Objektes, das weitere Objekte beinhaltet
     * @param name Name des angesprochenen Objektes
     * 
     * @author Ragnar Fischer
     */
    private void open(String name)
    {
        if (currentRoom.getObjectContainers().size() > 0)
        {
            for (ObjectContainer objectContainer : currentRoom.getObjectContainers())
            {
                if (objectContainer.getName().equals(name))
                {
                    System.out.print ("Hier drin befinden sich: ");
                    if( objectContainer.getContentSpeaker() != null)
                    {

                        for (ObjectSpeaker objectSpeaker : objectContainer.getContentSpeaker())
                        {
                            System.out.print(objectSpeaker.getName() + ", ");
                            if(currentRoom.getAlreadyOpened() == false)
                            {
                                currentRoom.addObjectSpeaker(objectSpeaker);
                            }
                        }
                    }
                    if( objectContainer.getContentChanger().size() > 0)
                    {
                        for (ObjectChanger objectChanger : objectContainer.getContentChanger())
                        {
                            if(objectChanger.getContent()[0] != -1)
                            {
                                System.out.print(objectChanger.getName() + ", ");
                                if(currentRoom.getAlreadyOpened() == false)
                                {
                                    currentRoom.addObjectChanger(objectChanger);
                                }
                            }
                        }
                    }
                    if( objectContainer.getContentContainer() != null)
                    {
                        for (ObjectContainer objectContainerContent : objectContainer.getContentContainer())
                        {
                            System.out.print(objectContainerContent.getName() + ", ");
                            if(currentRoom.getAlreadyOpened() == false)
                            {
                                currentRoom.addObjectContainer(objectContainer);
                            }
                        }
                    }
                    System.out.println ("Luft.");                    
                    currentRoom.setAlreadyOpened();
                    return;
                }
            }
        }
        System.out.println ("Bitte nochmal. Ich habe nicht ganz verstanden, was du öffnen möchtest.");
    }

    /**
     * Wiedergabe der momentanen Eigenschaften des Charakters
     * 
     * @author Ragnar Fischer
     */
    private void checkAbilitys()
    {
        System.out.println ("Hier sind deine Fähigkeiten: ");
        System.out.println ("Stärke: " + player.getAttackDamage());
        System.out.println ("Rüstung: " + player.getArmor());
    }

    /**
     * Vergleich der Eigenschaften des Charakters und des genannten Gegners. Je nach Ergebis: löschen des Gegners, keine Veränderung 
     * oder beenden des Spiels.
     * @param name Name des angesprochenen Gegners
     * 
     * @author Ragnar Fischer
     */
    private void fight (String name)
    {
        int score = 0;

        if (currentRoom.getEnemies() != null )
        {
            for (Enemy enemy : currentRoom.getEnemies())
            {
                if (enemy.getName().equals(name))
                {
                    System.out.println ("Achtung ein Troll greift dich an! Sei vorsichtig!");
                    System.out.println ("");
                    System.out.println ("Troll: " + enemy.getText());

                    score = score + (player.getArmor() - enemy.getAttackDamage());
                    score = score + (player.getAttackDamage() - enemy.getArmor());

                    System.out.println(".....");
                    long currentTime = System.currentTimeMillis();
                    while (System.currentTimeMillis() < currentTime + 3000) { }

                    if (score > 0) {
                        System.out.println ("Gewonnen gegen " + enemy.getName());

                        if (enemy.getName() == "Trollkönig")
                        {
                            System.out.println ("Super! Wir haben es geschafft! Alle Trolle sind tot!");
                            quit = true;
                        }
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

        System.out.println ("Bitte nochmal. Ich habe nicht ganz verstanden, gegen wen du kämpfen möchtest.");
    }

    /**
     * Wiedergeben der Übersichtskarte über das Spielfeld
     * 
     * @author Tyll Heinen
     */
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

    //----------------- Spielaufbau -----------------

    /**
     * Aufbau des Spiels mit Räumen (mit Inhalt) und Ausgängen. Festlegen des Startraums.
     * 
     * @author Ragnar Fischer & Tyll Heinen
     */
    public GameMaker()
    {
        //Räume
        Room room1 = new Room (
                "der Eingangshalle", 
                "Ein Raum mit hohen Decken und großen Fenstern. Man hört ein leises plätschern und spürt einen kalten Windzug.",
                new ArrayList<ObjectSpeaker> (Arrays.asList( new ObjectSpeaker ("Atha-ulf", "Ein alter Mann mit einem leeren Blick.", "Endlich! Wir brauchen dringed hilfe, sprich mit meinen Freund Theoderich im Westen. Der weiß was zu tun ist!"))),

                new ArrayList<ObjectChanger> (), //leeres Objekt das übergeben werden muss, um nachher drauf zugreifen zu können

                new ArrayList<ObjectContainer> (Arrays.asList( new ObjectContainer ("Schrank", "Ein großer alter Schnrak an der Wand", 
                            null,
                            new ArrayList<ObjectChanger> (Arrays.asList( new ObjectChanger ("Kettenhemd", "Ein wahrscheinlich lebensrettendes kettenhemd mit ein paar Blutspritzern...", new int[] {2, 10}))),
                            null //ObjektContainer 
                        ))),
                new ArrayList<Enemy> () // Enemies
            );

        Room room2 = new Room (
                "dem Garten", 
                "Ein vermodertes, hochgewachsenes Gras bedeckt den runden Boden und ist von verwucherten Tannen umgeben. Eine Bank steht in einer Ecke und auf jener Sitzt ein alter Herr.",
                new ArrayList<ObjectSpeaker> (Arrays.asList( new ObjectSpeaker ("Theoderich", "Ein alter Herr mit zerfledderten Klamotten und einem debilen Blick", "Hallo, wir brauchen unbedingt deine Hilfe bei der Befreiung von Geiseln. Die Trolle halten sie im Kerker gefangen, um Getreide von uns zu bekommen. Um in den Kerker zu kommen, musst du durch die Eingangshalle nach Osten, dann in die Küche, dann nach Norden in die Waffenkammer und dann nach Osten in den Kerker. Sei auf der Hut, Trolle sind sehr kampfwütig. Schau dich am besten nochmal um, vielleicht findest du ja eine bessere Waffe, oder bessere Rüstung"))),
                new ArrayList<ObjectChanger> (),
                new ArrayList<ObjectContainer> (),
                new ArrayList<Enemy> ());

        Room room3 = new Room ("der Schlafkammer", 
                "Ein kleiner Raum mit einem Bett voller Wanzen, einem dazugehörigen Nachtschrank und einem Schrank an der Wand, der früher mal ein Kleiderschrank gewesen sein könnte.",
                new ArrayList<ObjectSpeaker> (),
                new ArrayList<ObjectChanger> (),//leeres Objekt das übergeben werden muss, um nachher drauf zugreifen zu können
                new ArrayList<ObjectContainer> (Arrays.asList( new ObjectContainer ("Schrank", "Ein großer alter Schnrak an der Wand", 
                            new ArrayList<ObjectSpeaker> (Arrays.asList( new ObjectSpeaker ("Zettel", "Ein kleiner zerknitterter Zettel", "Hast du nichts übersehen? In der Eingangshalle vielleicht?"))),
                            new ArrayList<ObjectChanger> (Arrays.asList( new ObjectChanger ("Dolch", "Ein ellenlanges Messer mit scharfen Seiten und einem schönen Griff", new int[] {1, 10}))),
                            null //ObjektContainer 
                        ))),

                new ArrayList<Enemy> ());

        Room room4 = new Room ("der Küche", 
                "Ein Gerucht von verwesung schlägt dir entgegen. Auf der Arbeitsfläche sieht man Schimmel, der sich breit macht. Unter der Arbeitsfläche befinden sich Schubladen und auf der anderen Seite des Raumes eine kleine Truhe.",
                new ArrayList<ObjectSpeaker> (),
                new ArrayList<ObjectChanger> (),
                new ArrayList<ObjectContainer> (Arrays.asList( new ObjectContainer ("Truhe", "Ein kleine Truhe an der Wand", 
                            new ArrayList<ObjectSpeaker> (Arrays.asList( new ObjectSpeaker ("Brieffetzen", "Ein kleines Stück von einem Brief.", ".... Wir werden kommen! Verlassen Sie sich drauf! .... Sie werden bald untergehen! ...."),
                                                                         new ObjectSpeaker ("Brieffetzen", "Ein kleines Stück von einem Brief.", ".... Mit überaus unfreundlichen Grüßen, Trollkönig"))),
                            new ArrayList<ObjectChanger> (Arrays.asList( new ObjectChanger ("Wurfmesser", "Ein paar kleine Messer, die leicht in der Hand liegen und sicher schön zu werfen sind.", new int[] {1, 10}))),
                            null //ObjektContainer 
                        ))),
                new ArrayList<Enemy> ());

        Room room5 = new Room ("der Waffenkammer", 
                "An der Wand stehen Ständer, in denen früher mal die Waffen gestanden haben. Und ein kleines Rinnsaal aus Blut läuft an dir vorbei.",
                new ArrayList<ObjectSpeaker> (),
                new ArrayList<ObjectChanger> (),
                new ArrayList<ObjectContainer> (Arrays.asList( new ObjectContainer ("Tasche", "Eine große Tasche, die der Troll wahrscheinlich stehen lassen hat.", 
                            null,
                            new ArrayList<ObjectChanger> (Arrays.asList( new ObjectChanger ("Schwertgriff", "Ein Grill, der sich perfekt an deine Hand anpasst und sich sehr leicht auf deine Waffe draufstecken lässt.", new int[] {1, 10}))),
                            null //ObjektContainer 
                        ))),
                new ArrayList<Enemy> (Arrays.asList( new Enemy ("Stilicho", 120, 40, "Ein hochgewachsener Troll mit einem krummen Rücken und spitzen Ohren.", "Wieder Menschenabschaum! Dir zeig ich was ein Troll mit Mistvieh macht!"))));

        Room room6 = new Room ("dem Flur", 
                "Ein langgezogener Raum mit kaputten Fenstern und einem zerrissenen Banner.",
                new ArrayList<ObjectSpeaker> (Arrays.asList( new ObjectSpeaker ("Alarich", "Ein Mann, der zusammengekauert in der Ecke sitzt. Auch er sieht ungepflegt aus.", "Bitte wir brauchen deine Hilfe! Hier im Nebenraum ist der Troll-König. Ich hoffe du bist gut genug ausgestattet!"))),
                new ArrayList<ObjectChanger> (),
                new ArrayList<ObjectContainer> (),
                new ArrayList<Enemy> ());

        Room room7 = new Room ("dem Verließ", 
                "Ein kleiner, tiefer Raum mit Ketten an der Wand, an denen sicher Leute gefangen waren.",
                new ArrayList<ObjectSpeaker> (Arrays.asList( new ObjectSpeaker ("Eurich", "Ein nun entfesselter Mann auf knien.", "Vielen dank für die Rettung! Wir stehen für ewig in eurer Schuld."))),
                new ArrayList<ObjectChanger> (),
                new ArrayList<ObjectContainer> (),
                new ArrayList<Enemy> ());

        Room room8 = new Room ("der Besenkammer","Ein kleines Kabuff, mit einem Besen und einem Eimer mit komischem Inhalt.",
                new ArrayList<ObjectSpeaker> (Arrays.asList( new ObjectSpeaker ("Pidumel", "Eine änglstliche alte Frau.", "HILFE! HIIIILFEEEE!"))),
                new ArrayList<ObjectChanger> (),
                new ArrayList<ObjectContainer> (Arrays.asList( new ObjectContainer ("Schrank", "Ein kleiner Schrank an der Wand.", 
                            null,
                            new ArrayList<ObjectChanger> (Arrays.asList( new ObjectChanger ("Eisenhandschuhe", "Eisenhandschuhe zum Schutz ", new int[] {2, 10}))),
                            null //ObjektContainer 
                        ))),
                new ArrayList<Enemy> ());

        Room room9 = new Room ("dem Thronsaal","Ein großer Saal, der früher prächtig ausgesehen haben muss, nun aber sehr runtergekommen ist. Es steht ein großer Thron am Ende einer Steintreppe, der ganz aus Gold ist.",
                new ArrayList<ObjectSpeaker> (),
                new ArrayList<ObjectChanger> (),
                new ArrayList<ObjectContainer> (),
                new ArrayList<Enemy> (Arrays.asList( new Enemy ("Trollkönig", 90, 100, "Ein fetter großer Troll mit einer Käule.", "Du dringst hier in meine Festung ein und störrst nun auch mich?! Dafür wirst du in unserem Kerker leiden!"))));

        //Ausgänge
        room1.addExit (new Exit ("Norden", room3));
        room1.addExit (new Exit ("Osten", room4));
        room1.addExit (new Exit ("Westen", room2));

        room2.addExit (new Exit ("Osten", room1));

        room3.addExit (new Exit ("Osten", room6));
        room3.addExit (new Exit ("Süden", room1));

        room4.addExit (new Exit ("Norden", room5));
        room4.addExit (new Exit ("Westen", room1));

        room5.addExit (new Exit ("Norden", room6));
        room5.addExit (new Exit ("Osten", room7));
        room5.addExit (new Exit ("Süden", room4));

        room6.addExit (new Exit ("Norden", room9));
        room6.addExit (new Exit ("Süden", room5));
        room6.addExit (new Exit ("Westen", room3));

        room7.addExit (new Exit ("Osten", room8));
        room7.addExit (new Exit ("Westen", room5));

        room8.addExit (new Exit ("Westen", room7));

        room9.addExit (new Exit ("Süden", room6));

        currentRoom = room1;
    }
}
