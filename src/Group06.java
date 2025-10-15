import java.util.Scanner;

public class Group06 {
    public static void main(String[] args) {
        final Scanner input = new Scanner(System.in);
        welcome();
        waitToClearScreen(input);
        makeWait();
        mainMenu(input);
    }

    public static void welcome(){
        final String welcome = """
                ██╗    ██╗███████╗██╗      ██████╗ ██████╗ ███╗   ███╗███████╗    ████████╗ ██████╗                                             \s
                ██║    ██║██╔════╝██║     ██╔════╝██╔═══██╗████╗ ████║██╔════╝    ╚══██╔══╝██╔═══██╗                                            \s
                ██║ █╗ ██║█████╗  ██║     ██║     ██║   ██║██╔████╔██║█████╗         ██║   ██║   ██║                                            \s
                ██║███╗██║██╔══╝  ██║     ██║     ██║   ██║██║╚██╔╝██║██╔══╝         ██║   ██║   ██║                                            \s
                ╚███╔███╔╝███████╗███████╗╚██████╗╚██████╔╝██║ ╚═╝ ██║███████╗       ██║   ╚██████╔╝                                            \s
                 ╚══╝╚══╝ ╚══════╝╚══════╝ ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝       ╚═╝    ╚═════╝                                             \s
                
                 ██████╗███╗   ███╗██████╗ ███████╗██████╗ ██╗  ██╗██████╗     ██████╗ ██████╗  ██████╗      ██╗███████╗ ██████╗████████╗     ██╗
                ██╔════╝████╗ ████║██╔══██╗██╔════╝╚════██╗██║  ██║╚════██╗    ██╔══██╗██╔══██╗██╔═══██╗     ██║██╔════╝██╔════╝╚══██╔══╝    ███║
                ██║     ██╔████╔██║██████╔╝█████╗   █████╔╝███████║ █████╔╝    ██████╔╝██████╔╝██║   ██║     ██║█████╗  ██║        ██║       ╚██║
                ██║     ██║╚██╔╝██║██╔═══╝ ██╔══╝   ╚═══██╗╚════██║ ╚═══██╗    ██╔═══╝ ██╔══██╗██║   ██║██   ██║██╔══╝  ██║        ██║        ██║
                ╚██████╗██║ ╚═╝ ██║██║     ███████╗██████╔╝     ██║██████╔╝    ██║     ██║  ██║╚██████╔╝╚█████╔╝███████╗╚██████╗   ██║        ██║
                 ╚═════╝╚═╝     ╚═╝╚═╝     ╚══════╝╚═════╝      ╚═╝╚═════╝     ╚═╝     ╚═╝  ╚═╝ ╚═════╝  ╚════╝ ╚══════╝ ╚═════╝   ╚═╝        ╚═╝ """;

        /*
         * Ascii art is temporary for now. It can be changed.
         * */

        final String members = """
                   ______                            __  ___               __                 \s
                  / ____/________  __  ______       /  |/  /__  ____ ___  / /_  ___  __________
                 / / __/ ___/ __ \\/ / / / __ \\     / /|_/ / _ \\/ __ `__ \\/ __ \\/ _ \\/ ___/ ___/
                / /_/ / /  / /_/ / /_/ / /_/ /    / /  / /  __/ / / / / / /_/ /  __/ /  (__  )\s
                \\____/_/   \\____/\\__,_/ .___/    /_/  /_/\\___/_/ /_/ /_/_.___/\\___/_/  /____/ \s
                                     /_/                                                      \s""";

        final String burak = """
                    ____                   __      ____                  _    \s
                   / __ )__  ___________ _/ /__   / __ \\____  ___ _   __(_)___\s
                  / __  / / / / ___/ __ `/ //_/  / / / /_  / / _ \\ | / / / __ \\
                 / /_/ / /_/ / /  / /_/ / ,<    / /_/ / / /_/  __/ |/ / / / / /
                /_____/\\__,_/_/   \\__,_/_/|_|   \\____/ /___/\\___/|___/_/_/ /_/\s""";
        final String suhan = """
                   _____       __                     ___             __         ____                \s
                  / ___/__  __/ /_  ____ _____       /   |  _________/ /___ _   / __ \\____  ___  _____
                  \\__ \\/ / / / __ \\/ __ `/ __ \\     / /| | / ___/ __  / __ `/  / / / / __ \\/ _ \\/ ___/
                 ___/ / /_/ / / / / /_/ / / / /    / ___ |/ /  / /_/ / /_/ /  / /_/ / / / /  __/ /   \s
                /____/\\__,_/_/ /_/\\__,_/_/ /_/    /_/  |_/_/   \\__,_/\\__,_/   \\____/_/ /_/\\___/_/    \s""";
        final String ramazan = """
                    ____                                               ____  _      __                   ____        __             __ \s
                   / __ \\____ _____ ___  ____ _____  ____ _____       / __ )(_)____/ /______ _____      / __ \\____  / /___  _______/ /__
                  / /_/ / __ `/ __ `__ \\/ __ `/_  / / __ `/ __ \\     / __  / / ___/ //_/ __ `/ __ \\    / / / /_  / / __/ / / / ___/ //_/
                 / _, _/ /_/ / / / / / / /_/ / / /_/ /_/ / / / /    / /_/ / / /  / ,< / /_/ / / / /   / /_/ / / /_/ /_/ /_/ / /  / ,<  \s
                /_/ |_|\\__,_/_/ /_/ /_/\\__,_/ /___/\\__,_/_/ /_/    /_____/_/_/  /_/|_|\\__,_/_/ /_/    \\____/ /___/\\__/\\__,_/_/  /_/|_| \s""";
        final String elif = """
                    _________ _________                                  ______      __          \s
                   / ____/ (_) __/__  /  ___  __  ______  ___  ____     /_  __/___ _/ /___ ___  __
                  / __/ / / / /_   / /  / _ \\/ / / / __ \\/ _ \\/ __ \\     / / / __ `/ / __ `/ / / /
                 / /___/ / / __/  / /__/  __/ /_/ / / / /  __/ /_/ /    / / / /_/ / / /_/ / /_/ /\s
                /_____/_/_/_/    /____/\\___/\\__, /_/ /_/\\___/ .___/    /_/  \\__,_/_/\\__,_/\\__, / \s
                                           /____/          /_/                           /____/  \s""";
        final String pressEnter = """
                 ___ _                                           ___     _             _            _                _   _                                   \s
                | _ \\ |___ __ _ ___ ___     _ __ _ _ ___ ______ | __|_ _| |_ ___ _ _  | |_ ___   __| |___ __ _ _ _  | |_| |_  ___   ___ __ _ _ ___ ___ _ _   \s
                |  _/ / -_) _` (_-</ -_)_  | '_ \\ '_/ -_|_-<_-< | _|| ' \\  _/ -_) '_| |  _/ _ \\ / _| / -_) _` | '_| |  _| ' \\/ -_) (_-</ _| '_/ -_) -_) ' \\ _\s
                |_| |_\\___\\__,_/__/\\___( ) | .__/_| \\___/__/__/ |___|_||_\\__\\___|_|    \\__\\___/ \\__|_\\___\\__,_|_|    \\__|_||_\\___| /__/\\__|_| \\___\\___|_||_(_)
                                       |/  |_|                                                                                                               \s""";

        System.out.println("\033[1;32m" + welcome + "\033[0m");
        System.out.printf("%n%n%n");

        System.out.println("\033[1;33m" + members + "\033[0m");
        System.out.println("\033[1;31m" + burak + "\033[0m");
        System.out.println("\033[1;31m" + suhan + "\033[0m");
        System.out.println("\033[1;31m" + ramazan + "\033[0m");
        System.out.println("\033[1;31m" + elif + "\033[0m");

        System.out.printf("%n%n%n");
    }

    public static void waitToClearScreen(Scanner input){
        final String pressEnter = """
                 ___ _                                           ___     _             _            _                _   _                                   \s
                | _ \\ |___ __ _ ___ ___     _ __ _ _ ___ ______ | __|_ _| |_ ___ _ _  | |_ ___   __| |___ __ _ _ _  | |_| |_  ___   ___ __ _ _ ___ ___ _ _   \s
                |  _/ / -_) _` (_-</ -_)_  | '_ \\ '_/ -_|_-<_-< | _|| ' \\  _/ -_) '_| |  _/ _ \\ / _| / -_) _` | '_| |  _| ' \\/ -_) (_-</ _| '_/ -_) -_) ' \\ _\s
                |_| |_\\___\\__,_/__/\\___( ) | .__/_| \\___/__/__/ |___|_||_\\__\\___|_|    \\__\\___/ \\__|_\\___\\__,_|_|    \\__|_||_\\___| /__/\\__|_| \\___\\___|_||_(_)
                                       |/  |_|                                                                                                               \s""";

        System.out.println("\033[1;39m" + pressEnter + "\033[0m");
        input.nextLine();
        clearScreen();
    }

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void makeWait(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interruption cut: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private static void mainMenu(Scanner input) {
        boolean isNotTerminated = true;

        do {
            System.out.println("**********************************");
            System.out.println("Select an option:");
            System.out.println("[A] Primary School");
            System.out.println("[B] Secondary School");
            System.out.println("[C] High School");
            System.out.println("[D] University");
            System.out.println("[E] Terminate");
            System.out.println("**********************************");
            System.out.print("Your choice: ");

            String choice;

            try {
                if (!input.hasNextLine()) {
                    System.out.println("\nInput closed.");
                    input = new Scanner(System.in);
                    continue;
                }

                choice = input.nextLine().trim().toUpperCase();
            } catch (java.util.NoSuchElementException e) {
                System.out.println("No input available!");
                input = new Scanner(System.in);
                continue;
            } catch (IllegalStateException e) {
                System.out.println("Scanner is closed.");
                input = new Scanner(System.in);
                continue;
            }

            switch (choice){
                case "A":
                    clearScreen();
                    //primarySchoolMenu();
                    break;
                case "B":
                    clearScreen();
                    //secondrarySchoolMenu();
                    break;
                case "C":
                    clearScreen();
                    //highSchoolMenu();
                    break;
                case "D":
                    clearScreen();
                    //universityMenu();
                    break;
                case "E":
                    clearScreen();
                    //goodbye();
                    isNotTerminated = false;
                    break;
                default:
                    System.out.println("Please enter a valid value [A-E]: ");
                    break;
            }
        }while(isNotTerminated);
    }
}