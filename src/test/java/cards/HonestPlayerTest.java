package cards;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class HonestPlayerTest {

    HonestPlayer player = new HonestPlayer();

    @Test
    void addBalance() {
        player.addBalance(10);
        assertEquals(player.getBalance(), 10);
    }

    @Test
    void getBalance() {
        player.addBalance(10);
        player.addBalance(10);
        assertEquals(player.getBalance(), 20);
    }

    @Test
    void run() throws InterruptedException {
        Thread thr = new Thread(player);
        thr.start();
        Thread.sleep(1000);
        thr.interrupt();
        assertNotEquals(player.getBalance(), 0);
    }
}