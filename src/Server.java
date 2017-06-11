
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Server that connects two process of game on port 4543 and send messages from one to another
 * @author Kokos
 *
 */
public class Server implements Runnable{
    
    int port = 4543;
    ServerSocket serverSocket;
    Socket player1Socket, player2Socket;
    GameConnection game1, game2;
    BlockingQueue<String> blockingQueue1, blockingQueue2;
    volatile String message;
    boolean isP1Connected = false;
    boolean isP2Connected = false;
    
    public void init(){
    blockingQueue1 = new ArrayBlockingQueue<String>(10);
    blockingQueue2 = new ArrayBlockingQueue<String>(10);
        try {
            serverSocket = new ServerSocket(port);
            
            player1Socket = serverSocket.accept();
            isP1Connected = true;
            game1 = new GameConnection(blockingQueue1, blockingQueue2, player1Socket);
            
            player2Socket = serverSocket.accept();
            isP2Connected = true;
            game2 = new GameConnection(blockingQueue2, blockingQueue1, player2Socket);
            
            serverSocket.setSoTimeout(200000);
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeSocets(){
        try {
            player1Socket.close();
            player2Socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        init();
    }
}
