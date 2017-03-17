import java.util.Random;
import java.util.Scanner;

import static java.lang.Character.toUpperCase;
import static java.lang.System.out;

public class Maze extends Main {
    protected Node root;
    protected int level;
    protected int rooms;
    Scanner scan = new Scanner(System.in);

    public Maze(int level) {
        root = null;
        this.level = level;
    }

    public void GenerateMaze(int difficulty) {
        Random randomGenerator = new Random();
        int iterations = 10 * (difficulty * difficulty);
        this.rooms = iterations;
        int random;
        for (int i = 0; i <= iterations; ++i){
            random = randomGenerator.nextInt(100);
            GenerateMaze(random, false);	// Generate Maze
        }
        random = randomGenerator.nextInt(100);
        out.println("\nPSST. The maze exit is in room " + random);	// Helpful hint
        GenerateMaze(random, true);	// Generate Maze
        ConnectBacks(root);

    }

    protected void GenerateMaze(int random, boolean exit) {
        Node to_add = new Node(random, exit);	// New Root
        if(root == null) root = to_add;
        else {
            Connect(root, to_add);    // Call the Connect Function
            //ConnectBacks(root);    // Call the Back Pointer Connect Function
        }
    }

    protected void Connect(Node root, Node to_add) {	// BST Connector
        if (to_add.getRoom() < root.getRoom() && root.getLeft() == null) { // if left and less than root value
            root.setLeft(to_add); // set temp to the root's left pointer
        }
        else if (to_add.getRoom() >= root.getRoom() && root.getRight() == null) { // if right and more than root value
            root.setRight(to_add); // set temp to the root's right pointer
        }
        else if (to_add.getRoom() < root.getRoom()) Connect(root.getLeft(), to_add); // recursive call to the left
        else Connect(root.getRight(), to_add); // recursive call to the right
    }

    protected boolean ConnectBacks(Node r)	// BST Connector for Back Pointers
    {
        if(r == null) return false;		// If not root
        ConnectBacks(r.getLeft());	//Recursive left call
        ConnectBacks(r.getRight());	//Recursive right call
        if(r.getLeft() != null) r.getLeft().setBack(r);		// set left back
        else if(r.getRight() != null) r.getRight().setBack(r);	// set right back
        return true; // Return 0
    }

    protected void Display(Node root)	// Display Function
    {
        if(root == null)	return; // Return if nothing to do
        out.print("\nroom: " + root.getRoom());		// Display Room ID
        out.print(",\texit: " + root.getExit());	// Display Exit Status
        Display(root.getLeft());		// Recursive Call Left
        Display(root.getRight());	// Recursive Call Right
    }

    protected int Move()	// Move Wrapper Function
    {
        Move(root);	// Move Function with root
        return 0;	// Return 0
    }

    // Maze Movement Function
    protected int Move(Node root)	// Move Menu
    {
        String diff = "Banana";
        if(rooms == 10) diff = "Easy";
        else if (rooms == 40) diff = "Medium";
        else if (rooms == 90) diff = "hard";
        Display(root);
        int count = 0;

        //game play area
        while(!root.getExit()) {	// MAIN GAME LOOP
            out.print("\n\n");
            // MAIN MOVEMENT MENU
            output(language, 0,0);    // initial ment

            // USER CHOICE MENU
            char choice = scan.next().charAt(0);
            choice = toUpperCase(choice);
out.println("choice = " + choice);
            if(choice == 'Q') {
                output(language, 1, 0);
                char YN = scan.next().charAt(0);
                YN = toUpperCase(YN);
                if (YN == 'Y') return 0;
                else break;
            }
            else if(choice == 'L' && root.getLeft() != null) {    // If left is chosen and is valid direction
                    ++count;
                    root = root.getLeft();    // Go Left
                }
            else if(choice == 'R' && root.getRight() != null) {    // If right is chosen and is valid direction
                    ++count;
                    root = root.getRight();    // Go Right
                }
            else if(choice == 'B' && root.getBack() != null) {
                    ++count;
                    root = root.getBack();    // Go Back
                }
            else output(language, 2, 0);    // Bad Input

        }

        output(language, 3, count);

        String name = new String();
        output(language, 4, 0);
        name = scan.nextLine();

        HighscoreManager hm = new HighscoreManager();
        hm.addScore(diff, name, count);
        return 0;
    }


    protected void output(int lang, int sect, int count) {

        switch(sect) {
            case 0: // Starting Room
                switch (lang) {
                    case 0: // Starting Room
                        if(root.getBack() == null) out.println("You are in the starting room");
                        if(root.getLeft() == null) out.println("The Left door is locked");
                        else out.println("There is a door to your left");

                        if(root.getRight() == null) out.println("The Right door is locked");
                        else out.println("There is a door to your right");
                        out.println("\nWhat way do you want to proceed?");
                        out.println("\nRoom: " + root.getRoom());

                        if(root.getLeft() != null)  out.print("\n\t[L] Left to room " + root.getLeft().getRoom());	// Go Left
                        if(root.getRight() != null) out.print("\n\t[R] Right to room " + root.getRight().getRoom());	// Go Right
                        if(root.getBack() != null)  out.print("\n\t[B] Back to room " + root.getBack().getRoom());	// Go Back
                        out.print("\n\t[Q] Quit");
                        out.print("\n\tDirection: ");	// Input info
                        return;
                    case 1:
                        if(root.getBack() == null) out.println("Estás en la habitación de partida ");
                        if(root.getLeft() == null) out.println("La puerta izquierda está bloqueada ");
                        else out.println("Hay una puerta a su izquierda ");

                        if(root.getRight() == null) out.println("La puerta derecha está bloqueada ");
                        else out.println("Hay una puerta a su derecha ");
                        out.println("\n¿De qué manera quieres proceder?");
                        out.println("\nhabitación: " + root.getRoom());

                        if(root.getLeft() != null)  out.print("\n\t[L] Left to room " + root.getLeft().getRoom());	// Go Left
                        if(root.getRight() != null) out.print("\n\t[R] Right to room " + root.getRight().getRoom());	// Go Right
                        if(root.getBack() != null)  out.print("\n\t[B] Back to room " + root.getBack().getRoom());	// Go Back
                        out.print("\n\t[Q] Dejar");
                        out.print("\n\tdirección: ");	// Input info
                        return;
                    case 2:
                        if(root.getBack() == null) out.println("Sie sind im Startzimmer");
                        if(root.getLeft() == null) out.println("Die linke tür ist verriegelt");
                        else out.println("Es gibt eine Tür zu deiner Linken");

                        if(root.getRight() == null) out.println("Die rechte Tür ist verschlossen");
                        else out.println("Es gibt eine Tür zu deinem Recht");
                        out.println("\nWie willst du vorgehen?");
                        out.println("\nZimmer: " + root.getRoom());

                        if(root.getLeft() != null)  out.print("\n\t[L] Links zum zimmer " + root.getLeft().getRoom());	// Go Left
                        if(root.getRight() != null) out.print("\n\t[R] Recht auf Zimmer " + root.getRight().getRoom());	// Go Right
                        if(root.getBack() != null)  out.print("\n\t[B] Zurück zum zimmer " + root.getBack().getRoom());	// Go Back
                        out.print("\n\t[Q] Verlassen");
                        out.print("\n\tRichtung: ");	// Input info
                        return;
                }
            case 1: // Locked Door
                switch (lang) {
                    case 0:
                        out.print("Are you sure you want to quit? [Y/N]: ");
                        return;
                    case 1:
                        out.print("¿Seguro que quieres salir?[Y/N]: ");
                        return;
                    case 2:
                        out.print("Sind Sie sicher, dass Sie aufhören wollen?[Y/N]: ");
                        return;

                }
            case 2: // Door Description
                switch (lang) {
                    case 0:
                        out.println("\033[31mInvalid Entry\033[0m");
                        return;
                    case 1:
                        out.println("\033[31mEntrada invalida\033[0m");
                        return;
                    case 2:
                        out.println("\033[31mUngültiger Eintrag033[0m");
                        return;
                }
            case 3: // Way to proceed
                switch (lang) {
                    case 0:
                        out.print("\033[1;32m\n\n\t\tYOU WON \n\t\tScore: ");	// Victory
                        out.println(count + "\033[0m");
                        return;
                    case 1:
                        out.print("\033[1;32m\n\n\t\t¡Ganaste!\n\t\tcalificar:");	// Victory
                        out.println(count + "\033[0m");
                        return;
                    case 2:
                        out.print("\033[1;32m\n\n\t\tYOU WON \n\t\tScore: ");	// Victory
                        out.println(count + "\033[0m");
                        return;
                }
            case 4:
                switch(lang) {
                    case 0:
                        out.print("\n\tPlease Enter your name:");
                        return;
                    case 1:
                        out.print("\n\tpor favor, escriba su nombre: ");
                        return;
                    case 2:
                        out.print("\n\tBitte geben Sie Ihren Namen ein: ");
                        return;
                }
        }
    }





}
