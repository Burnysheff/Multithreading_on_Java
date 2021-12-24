package cards;

import java.util.ArrayList;

/**
 * A class (Thread) for honest players
 * */

public class HonestPlayer extends Thread {
    // A collections of honest players (for cheaters to know who to robb)
    public static ArrayList<HonestPlayer> players = new ArrayList<HonestPlayer>();
    // A balance (number of points)
    private int balance = 0;

    // Getter and setter for balance (called only from synchronized methods)
    public int getBalance() {
        return balance;
    }

    public void addBalance(int points) {
        balance += points;
    }

    // The thread itself
    public void run() {
        while (!isInterrupted()) {
            // Go to operations class, the come back to sleep
            Operations.getCard(this);
            int sleepTime = (int) (Math.random() * 101);
            sleepTime += 100;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
