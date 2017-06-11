import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * Server to connect two process and send message between them
 * @author Kokos
 *
 */
public class Main {
    public static void main(String[] args) {

        Server server = new Server();
        (new Thread(server)).start();
        EventQueue.invokeLater(new Runnable() { 
            @Override
            public void run() {
                ServerFrame frame = new ServerFrame(server); 
                frame.setTitle("Server");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });


    }
    
}
