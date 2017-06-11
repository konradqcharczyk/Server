
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

/**
 * Class that contains two threads first listens to game, second to opponent thread
 * @author Kokos
 *
 */
public class GameConnection{
    
    static int seq = 1;
    int PK;
    
    public GameConnection(BlockingQueue<String> blockingQueue, BlockingQueue<String> blockingQueueOpp, Socket socket){
        PK = seq++;
        (new Thread(new GameListener(blockingQueueOpp, socket))).start();
        (new Thread(new OpponentListener(blockingQueue, socket, PK))).start();
    }
}
