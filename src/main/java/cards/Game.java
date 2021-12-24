package cards;

import java.util.Scanner;

/**
 * A main class
 * */

public class Game {

    /**
     * A Method where we check a string to be an integer
     * @param s Given string
     * @return true if integer; false if not
     * */
    private static boolean checkInt(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            if (Integer.parseInt(s) == 0 || Integer.parseInt(s) > 1000) {
                throw new NumberFormatException();
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * A method for entering numbers of honest players and cheaters (is called twice)
     * @param in - Scanner for entering an element
     * @param areHonest - string of 2 conditions ("honest" or "cheater") for putting the right string on the console
     * @return the number of players
     * */
    private static int enterPlayers(Scanner in, String areHonest) {
        System.out.println("\nEnter the number of " + areHonest + " players (at least 1 and no more than 1000)");
        String check = in.next();
        while (!checkInt(check) || Integer.parseInt(check) < 0 ) {
            System.out.println("Please enter a correct integer number (> 0 and < 1001)!");
            check = in.next();
        }
        return Integer.parseInt(check);
    }

    /**
     * A method for starting the threads
     * @param cheaters - an array of cheaters threads
     * @param honest - an array of honest threads
     * @param cheatNumber - number of cheaters
     * @param honNumber - number of honest players
     * */
    private static void startThread(Thread[] honest, Thread[] cheaters, int honNumber, int cheatNumber) {
        for (int i = 0; i < honNumber; ++i) {
            honest[i].start();
        }
        for (int i = 0; i < cheatNumber; ++i) {
            cheaters[i].start();
        }
    }

    /**
     * A method for ending the threads
     * @param cheaters - an array of cheaters threads
     * @param honest - an array of honest threads
     * @param cheatNumber - number of cheaters
     * @param honNumber - number of honest players
     * */
    private static void endThread(Thread[] honest, Thread[] cheaters, int honNumber, int cheatNumber) {
        for (int i = 0; i < honNumber; ++i) {
            honest[i].interrupt();
        }
        for (int i = 0; i < cheatNumber; ++i) {
            cheaters[i].interrupt();
        }
    }

    /**
     * A method for getting a winner, which put the data about winner on the console
     * @param cheaters - an array of cheaters
     * @param honest - an array of honest players
     * @param cheatNumber - number of cheaters
     * @param honNumber - number of honest players
     * */
    private static void getWinner(HonestPlayer[] honest, Cheater[] cheaters, int honNumber, int cheatNumber) {
        int max = -1;
        for (int i = 0; i < honNumber; ++i) {
            if (honest[i].getBalance() > max) {
                max = honest[i].getBalance();
            }
        }
        for (int i = 0; i < cheatNumber; ++i) {
            if (cheaters[i].getBalance() > max) {
                max = cheaters[i].getBalance();
            }
        }
        for (int i = 0; i < honNumber; ++i) {
            if (honest[i].getBalance() == max) {
                System.out.println("\n\nA winner is a honest player with " + honest[i].getBalance() + " points!\n");
            }
        }
        for (int i = 0; i < cheatNumber; ++i) {
            if (cheaters[i].getBalance() == max) {
                System.out.println("\nA winner is a cheater with " + cheaters[i].getBalance() + " points!\n");
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int honestPlayersNumber = enterPlayers(in, "honest");
        int cheatersNumber = enterPlayers(in, "cheater");
        // arrays for threads
        Thread[] honestPlayers = new Thread[honestPlayersNumber];
        Thread[] cheater = new Thread[cheatersNumber];
        // arrays for players
        HonestPlayer[] honest = new HonestPlayer[honestPlayersNumber];
        Cheater[] cheaters = new Cheater[cheatersNumber];
        // initialising
        for (int i = 0; i < honestPlayersNumber; ++i) {
            HonestPlayer player = new HonestPlayer();
            honest[i] = player;
            honestPlayers[i] = new Thread(player);
            HonestPlayer.players.add(player);
        }
        for (int i = 0; i < cheatersNumber; ++i) {
            Cheater player = new Cheater();
            cheater[i] = new Thread(player);
            cheaters[i] = player;
        }
        startThread(honestPlayers, cheater, honestPlayersNumber, cheatersNumber);
        System.out.println("\nAway we go!\n");
        // wait for 10 sec after start
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("\nSomething wrong...");
        }
        System.out.println("\nTime is up!\n");
        endThread(honestPlayers, cheater, honestPlayersNumber, cheatersNumber);
        // wait for another second for threads to be ok
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("\nSomething wrong...");
        }
        getWinner(honest, cheaters, honestPlayersNumber, cheatersNumber);
    }
}
