package cards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheaterTest {

    Cheater cheater = new Cheater();
    HonestPlayer player = new HonestPlayer();

    @Test
    void run() throws InterruptedException {
        HonestPlayer.players.add(player);
        Thread thr = new Thread(cheater);
        thr.start();
        Thread.sleep(1000);
        thr.interrupt();
        assertNotEquals(cheater.getBalance(), 0);
    }
}