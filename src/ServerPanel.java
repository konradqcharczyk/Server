import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Main panel of GUI
 * @author Kokos
 *
 */
public class ServerPanel extends JPanel{

    private static final long serialVersionUID = 1L;
    JLabel player1, player2, connection1, connection2;
    JButton exit, refresh;
    Server server;
    
    public ServerPanel(Server server){
        setLayout(new GridLayout(3, 2));
        this.server = server;
        addPlayer1();
        addPlayer2();
        addButton();
        addButtonRefresh();
    }
    private void addPlayer1(){
        player1 = new JLabel("Player 1", SwingConstants.CENTER);
        connection1 = new JLabel("Disconnected", SwingConstants.CENTER);
        add(player1);
        add(connection1);
    }
    private void addPlayer2(){
        player2 = new JLabel("Player 2", SwingConstants.CENTER);
        connection2 = new JLabel("Disconnected", SwingConstants.CENTER);
        add(player2);
        add(connection2);
    }
    private void addButton(){
        exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if(server.isP1Connected || server.isP2Connected)
                    server.closeSocets();
                System.exit(0);
            }
        });
        add(exit);
    }
    private void addButtonRefresh(){
        refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateWindow();
            }
        });
        add(refresh);
    }
    public void updateWindow(){
        if(server.isP1Connected)
            connection1.setText("Connected");
        else
            connection1.setText("Disconnected");
        
        if(server.isP2Connected)
            connection2.setText("Connected");
        else
            connection2.setText("Disconnected");
        repaint();
    }

}
