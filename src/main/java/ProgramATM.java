import pl.kruczala.michal.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProgramATM {
    public static void main(String[] args) {


        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";
        final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
        final String BLUE_UNDERLINED = "\033[4;34m";

        try {
            Scanner scanner = new Scanner(System.in);
            if (tryAuthenticate(scanner)) {
                // if (User.isAdmin(scanner,s)){
                //    System.out.println("siema adminie");
                // }

                CashMachine cashMachine = new CashMachine();
                System.out.println(ANSI_GREEN + "Hello inside ATM bank");

                do {
                    System.out.print("how much money would you get?: ");
                    int moneyQuantity = scanner.nextInt();
                    Quantum quantum =new Quantum(moneyQuantity);

                    ArrayList<PayOutResult> payOutResult1 = cashMachine.payOut(quantum);
                    for (PayOutResult e : payOutResult1) {
                        System.out.println(e.toString());
                    }

                    System.out.println("Amount  of money on your account " + cashMachine.getAccountBalance());

                }
                while (canRepeatView(scanner));
            }
        } catch (ApplicationException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(ANSI_YELLOW + ANSI_BLACK_BACKGROUND + "----Thank you for using our services----" + ANSI_RESET);
        }
    }

    private static boolean tryAuthenticate(Scanner scanner) {
        final String RED_UNDERLINED = "\033[4;31m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";

        System.out.println("Hello in ATM online bank");
        System.out.print("Insert login: ");
        String loginGiven = scanner.next();
        System.out.print("Insert password: ");
        String passwordGiven = scanner.next();
        Authenticator authenticator = new Authenticator();
        User user = authenticator.tryAunthenticate(loginGiven, passwordGiven);
        if (user == null) {
            System.out.println(ANSI_RED + RED_UNDERLINED + "------Wrong credentials------" + ANSI_RESET);
            return false;
        }
        if (user.isAdmin) {                                          //
            Authenticator authenticator1 = new Authenticator();     //
            System.out.println(authenticator1.getUsersInSystem());  //
        }
        return true;
    }

    private static boolean canRepeatView(Scanner scanner) {
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";

        System.out.println("Would you pau out more? or exit ATM bank?");
        System.out.print("Insert 'c' to continue ,or 'e' to exit bank:");
        switch (scanner.next()) {
            case "c":
                return true;
            case "e":
                return false;
            default:
                System.out.println(ANSI_RED + "Wrong type of data" + ANSI_RESET);
                return canRepeatView(scanner);
        }
    }


}




