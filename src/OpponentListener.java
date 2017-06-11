
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

/**
 * Listens opponent's thread and if there is a message sends it to game
 * @author Kokos
 *
 */
public class OpponentListener implements Runnable{

    PrintWriter out;
    BlockingQueue<String> blockingQueue;
    Socket socket;
    int PK;
    public OpponentListener(BlockingQueue<String> blockingQueue, Socket socket, int PK){
        this.socket = socket;
        this.blockingQueue = blockingQueue;
        this.PK = PK;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(PK);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while(true)
            listen();
    }
    private void listen(){
        String input = null;
        try {
            input = blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        out.println(input);
    }
}
