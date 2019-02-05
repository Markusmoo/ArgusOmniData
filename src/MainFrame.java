import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

    public static final String IP_PORT = "10.30.2.157:47840";
    public static final String AUTH_TOKEN = "ENTER_TOKEN_HERE";

    ArgusWindow argusWindow;
    Timer updateTimer = new Timer(1000, this);

    public static void main(String[] args){
        new MainFrame().setVisible(true);
    }

    public MainFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 100);
        setResizable(true);
        this.setTitle("AST Room Temp");

        argusWindow = new ArgusWindow();
        this.setContentPane(argusWindow);
        updateTemp();
        updateTimer.start();
    }

    public void updateTemp(){
        OmniData omniData = new OmniData().getData();
        if(omniData.dataList.size() > 0 && omniData.dataList.get(0).dataSet.size() > 0) {
            String temp = omniData.dataList.get(0).dataSet.get(0).data;
            System.out.println("Temp: " + temp);
            argusWindow.updateTemp(temp);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateTemp();
    }
}
