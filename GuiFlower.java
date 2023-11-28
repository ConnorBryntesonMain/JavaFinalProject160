import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiFlower {

    public GuiFlower() {
        // Create the frame
        JFrame frame = new JFrame("Flower GUI with Buttons");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create three buttons
        JButton button1 = new JButton("Buy");
        JButton button2 = new JButton("Sell");
        JButton button3 = new JButton("TEMP!");


        button1.setBounds(50, 30, 100, 30);
        button2.setBounds(50, 70, 100, 30);
        button3.setBounds(50, 110, 100, 30);

        // Add buttons to the frame directly
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);

        // Add action listeners to the buttons (even though they do nothing in this example)
        button1.addActionListener(new ButtonClickListener());
        button2.addActionListener(new ButtonClickListener());
        button3.addActionListener(new ButtonClickListener());

        // Set layout manager to null (absolute positioning)
        frame.setLayout(null);

        // Set the frame to be visible
        frame.setVisible(true);
    }

    // ActionListener implementation for the buttons
    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // The code here will be executed when a button is clicked
            System.out.println("Button clicked, but doing nothing!");
        }
    }

    
   
}
