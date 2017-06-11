import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

/**
 * Listens to messages from game
 * @author Kokos
 *
 */
public class GameListener implements Runnable{

    BufferedReader in;
    Socket socket;
    BlockingQueue<String> blockingQueue;
    
    public GameListener(BlockingQueue<String> blockingQueue, Socket socket){
        this.socket = socket;
        this.blockingQueue = blockingQueue;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        listen();
    }
    private void listen(){
        String inputLine;
        try {
            while ((inputLine = in.readLine()) != null) {
                   sendToOpponent(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sendToOpponent(String message){
        blockingQueue.add(message);
    }
}
