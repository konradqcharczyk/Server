import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Main frame of GUI
 * @author Kokos
 *
 */
public class ServerFrame extends JFrame{

    private static final long serialVersionUID = 1L;
    ServerPanel serverPanel;
    public ServerFrame(Server server){
        setSize(WIDTH, HEIGHT);
        serverPanel = new ServerPanel(server);
        setMinimumSize(new Dimension(650, 400));
        setResizable(false);
        setLocationRelativeTo(null);
        add(serverPanel);
        }
}
