package cards;

/**
 * A class with operations. The 2 methods can be called from the other classes. These
 * methods are static synchronized. So basically only one thread can get access to that class at any point
 * of time. So, the algorithm is like that: honest guy takes card and go away for sleep, only then another guy
 * can do smth here. They do sleeping in there own classes, here they just do the operations.
 * */

public class Operations {

    /**
     * A method for generating card from deck
     * @return a card (number of points there)
     * */
    private static int getNumber() {
        return (int) (Math.random() * 10) + 1;
    }

    /**
     * A method for just taking card from deck
     * @param player - player who takes a card
     * */
    public static synchronized void getCard(HonestPlayer player) {
        player.addBalance(getNumber());
    }

    /**
     * A method for cheaters, where they decide take the card or steal it.
     * @param player - a cheater who decides
     * */
    public static synchronized boolean stealOrGetCard(Cheater player) {
        int stealOrNot = (int) (Math.random() * 10);
        // Not to steal
        if (stealOrNot < 6) {
            getCard(player);
            return true;
        } else {
            // Steal
            stealFromPlayer(player);
            return false;
        }
    }

    /**
     * A method for stealing the card.
     * @param player - a cheater who steals
     * */
    private static void stealFromPlayer(Cheater player) {
        int playerToSteal = (int)(Math.random() * HonestPlayer.players.size());
        int pointsToSteal = (int)(Math.random() * 9);
        if (HonestPlayer.players.get(playerToSteal).getBalance() < pointsToSteal) {
            pointsToSteal = HonestPlayer.players.get(playerToSteal).getBalance();
        }
        HonestPlayer.players.get(playerToSteal).addBalance(-1 * pointsToSteal);
        player.addBalance(pointsToSteal);
    }
}
