            import javax.swing.*;
            //import java.awt.*;
            import java.awt.event.ActionEvent;
            import java.awt.event.ActionListener;
            import java.util.Scanner;

            public class GuiFlower {
                String testVar = ("Here!");

                private JFrame frame;
                private JLabel questionLabel;
                private JLabel stockListLabel;
                private JLabel cashLabel;
                
                public GuiFlower() {
                    // Set the GuiFlower instance in the Stock class
                    Stock.setGuiFlowerInstance(this);
                }




                public void initialize() {
                   /**
                    *  This method starts the GUI, which runs the game
                    very essential code
                    */
                    // Create the frame
                    frame = new JFrame("Flower GUI with Buttons");
                    frame.setSize(1000, 1000);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                
                    //Button
                    JButton enterButton = new JButton("Enter");
                    // text field
                    JTextField textField = new JTextField(20);
                    
                    questionLabel = new JLabel("Questions go here");

                    stockListLabel = new JLabel("Stocks go here");



                    cashLabel = new JLabel("Cash Here");
                    
                    JLabel stockOwnedLabel = new JLabel("Stock Owned Here");
                    JLabel testingLabel = new JLabel("Here!");
                    

                    //Questions added here
                    questionLabel.setBounds(30, 30, 300, 30);



                    enterButton.setBounds(150, 60, 100, 30);
                    textField.setBounds(30,60,150, 30);
                    cashLabel.setBounds(30,90,300,30);
                    stockListLabel.setBounds(30,120,1200,60);
                    stockOwnedLabel.setBounds(30,180,300,30);
                    testingLabel.setBounds(250,60,300,30);

                    // Add buttons to the frame directly
                    frame.add(textField);
                    frame.add(enterButton);
                    frame.add(questionLabel);
                    frame.add(cashLabel);
                    frame.add(stockListLabel);
                    frame.add(stockOwnedLabel);
                    frame.add(testingLabel);
                    
                    enterButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Get the text from the text field
                            String enteredText = textField.getText();
            
                            // Set the text in the label
                            testingLabel.setText("Result: " + enteredText);
                        }
                    });
            
                    // Set the layout
                    frame.setLayout(null);
            
                    // Set the frame to be visible
                    frame.setVisible(true);

                    //call player
                    player myPlayer = new player("LLCTitle", 100, 0.1, "small", "spring");
                    //Scanner input = new Scanner(System.in);

                    /** 
                    try {
                        myPlayer.turn(input);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                        */
                    // Call the buildList method to update the stock list label
                    Stock.buildList();
                    

                    
                }

                public void updateQuestionLabel(String newText) {
                    SwingUtilities.invokeLater(() -> questionLabel.setText(newText));
                    }

                public void updatestockListLabel(String newText) {
                    SwingUtilities.invokeLater(() -> stockListLabel.setText(newText));
                    }
                public void updateCashLabel(String newText) {
                    SwingUtilities.invokeLater(() -> cashLabel.setText(newText));
                    }
                    
            }
            