package cards;

/**
 * A class (Thread) for cheaters (extends honest and override the run method)
 * */

public class Cheater extends HonestPlayer {

    @Override
    public void run() {
        while (!isInterrupted()) {
            // Go to operations and come back to sleep
            boolean stealOrGet = Operations.stealOrGetCard(this);
            int sleepTime = 0;
            if (stealOrGet) {
                sleepTime = (int) (Math.random() * 101);
                sleepTime += 100;
            } else {
                sleepTime = (int) (Math.random() * 121);
                sleepTime += 180;
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
