In this program cheaters can not steal cards while somebody else is playing. So cheaters can steal only when it is
their turn to go. E.g: honest guy takes card, cheater can do nothing. The honest guy goes to sleep,
then cheater gets access to operations, only then cheater can steal card from honest guy.

Synchronizing is done via static synchronized methods in a special class of operations. So only 1 thread
can get access to this class and perform its methods. Threads are sleeping in their own classes, so nobody
should wait for them to wake up.

Player can choose the numbers of honest guys and cheaters. They should be > 0 and < 1001. My proc was dying at 1000, so it's ok I think.

JAR is in folder JAR.

Unit tests cover 43% of lines. They cover everything in classes of players. In main class (Game) all methods are private.