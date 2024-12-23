package application;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TestingGui extends JFrame {
	public int time = MainEngTest.time;
    public ArrayList<Process> processesQueue;


    public TestingGui(ArrayList<Process> hld) {
    	this.processesQueue = hld;
        setTitle("SJF Diagram");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        JPanel l = new JPanel() {
            public void paintComponent(Graphics g) {
                int x = 50;
                int y = 50;
                int w = 50;
                int h = 50;
                for (int i = 0; i <= time; i++) {
                    g.drawLine(x + i * w, y + h + 10, x + i * w, y + h + 20);
                    g.drawString("" + i, x + i * w - 5, y + h + 35);
                }
                for (Process p : processesQueue) {
                	int executedAtTime = p.getExecutedAtTime();
                    g.drawString(" P" + p.getPid(), x + executedAtTime * w + p.getExecutionTime() * w / 2 - 5, y + h / 2 + 5);
                    executedAtTime += p.getExecutionTime();
                    g.drawRect(x + p.getExecutedAtTime()*w, y, p.getExecutionTime() * w, h+10);
                    
                }

            }
        };


        add(l);
        setVisible(true);
    }

}