import java.util.Scanner;

import static java.lang.System.out;

public class Main {
    protected int language = 0;
    protected int diff;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Main M = new Main();
        //Score S = new Score();
        HighscoreManager hm = new HighscoreManager();

        int choice; // initialize choice

        hm.addScore("easy","Bart",240);


        do { // Do-While loop for main menu

            M.outputs(M.language,0); // Display MazeGame.Main Menu
            choice = scan.nextInt(); // Read in value
            out.println(); // Blank Line
            switch (choice) { // Switch Statement
                case 0:
                    break; // break out of switch statement
                case 1:
                    int level = 0;
                    do {
                        M.outputs(M.language, 1);
                        level = scan.nextInt();
                        if (level > 3 || level < 1) M.outputs(M.language, 2);
                    } while(level > 3 || level < 1);
                    M.diff = level;
                    Maze Start = new Maze(level);
                    Start.GenerateMaze(level);
                    Start.Move();
                    break; //break out of switch statement
                case 2:
                    out.print(hm.getHighscoreString());
                    break; //break out of switch statement
                case 3:
                    out.println(" [0] English\n [1] Español\n [2] Deutsche\n [0-2]: " );
                    int backup = 0;
                    M.language = scan.nextInt();
                    if(M.language >= 3) {
                        M.language = backup;
                        M.outputs(M.language, 2);
                    }
                    break; //break out of switch statement
                default:
                    M.outputs(M.language, 2);
                    break; // break out of switch statement
            }
        }while(choice != 0); // While to exit is not chosen
    }

    protected void outputs(int lang, int sect) {

        switch(sect) {
            case 0: //main menu languages
                switch (lang) {
                    case 0:
                        out.println("\n\n"); // Make blank lines
                        out.println("\t\tMazeGame.Main Menu");
                        out.println("[1] Start Maze");
                        out.println("[2] Display High Scores");
                        out.println("[3] Change Language");
                        out.println("[0] Quit"); // Quit
                        out.print("What do you want to do?: "); // Ask for input
                        return;
                    case 1:
                        out.println("\n\n"); // Make blank lines
                        out.println("\t\tMenú principal");
                        out.println("[1] Inicio Laberinto");
                        out.println("[2] Mostrar todas las puntuaciones");
                        out.println("[3] Cambiar idioma");
                        out.println("[0] Dejar"); // Quit
                        out.print("¿Qué quieres hacer?: "); // Ask for input
                        return;
                    case 2:
                        out.println("\n\n"); // Make blank lines
                        out.println("\t\tHauptmenü");
                        out.println("[1] Starten Sie Labyrinth");
                        out.println("[2] Highscores anzeigen");
                        out.println("[3] Sprache ändern");
                        out.println("[0] Verlassen"); // Quit
                        out.print("Was möchten Sie tun?: "); // Ask for input
                        return;
                }

            case 1: // difficulty level languages
                switch (lang) {
                    case 0:
                        out.print("\n[1-3] Select Difficulty Level:");
                        return;
                    case 1:
                        out.print("\n[1-3] Seleccione Nivel de dificultad:");
                        return;
                    case 2:
                        out.print("\n[1-3] Schwierigkeitsstufe auswählen");
                        return;

                }
            case 2: // invalid entry languages
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

            default:
                return;
        }
    }
}
