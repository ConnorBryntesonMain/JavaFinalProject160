            import javax.swing.*;
            //import java.awt.*;
            import java.awt.*;
            import java.awt.event.ActionEvent;
            import java.awt.event.ActionListener;
            import java.io.FileNotFoundException;
            import java.util.*;

            public class GuiFlower {
                String testVar = ("Here!");

                private JFrame frame;
                private static JLabel questionLabel;
                private static JLabel stockListLabel;
                private static JLabel cashLabel;

                private static JLabel stockOwnedLabel;
                
                public GuiFlower() {
                    // Set the GuiFlower instance in the Stock class
                    Stock.setGuiFlowerInstance(this);
                }



                static JTextField textField = new JTextField(20);
                static JButton enterButton = new JButton("Enter");
                public void initialize(){
                   /**
                    *  This method starts the GUI, which runs the game
                    very essential code
                    */
                    // Create the frame
                    frame = new JFrame("Flower GUI with Buttons");
                    frame.setSize(1200, 1000);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                
                    //Button

                    // text field

                    
                    questionLabel = new JLabel("What is your name?");

                    stockListLabel = new JLabel("Stocks go here");



                    cashLabel = new JLabel("Cash Here");
                    
                    stockOwnedLabel = new JLabel("Stock Owned Here");
                    JLabel testingLabel = new JLabel("Here!");
                    

                    //Questions added here
                    questionLabel.setBounds(30, 30, 600, 30);
                    questionLabel.setFont(new Font("Serif", Font.PLAIN, 20));


                    enterButton.setBounds(150, 60, 100, 30);
                    enterButton.setFont(new Font("Serif", Font.PLAIN, 20));
                    textField.setBounds(30,60,150, 30);
                    cashLabel.setBounds(30,90,300,30);
                    cashLabel.setFont(new Font("Serif", Font.PLAIN, 20));
                    stockListLabel.setBounds(30,120,1200,60);
                    stockListLabel.setFont(new Font("Serif", Font.PLAIN, 20));
                    stockOwnedLabel.setBounds(30,180,600,30);
                    stockOwnedLabel.setFont(new Font("Serif", Font.PLAIN, 20));
                    testingLabel.setBounds(250,60,300,30);

                    // Add buttons to the frame directly
                    textField.setText("");
                    frame.add(textField);
                    frame.add(enterButton);
                    frame.add(questionLabel);
                    frame.add(cashLabel);
                    frame.add(stockListLabel);
                    frame.add(stockOwnedLabel);
                    frame.add(testingLabel);
                    //Scanner input = new Scanner(System.in);

                    // Set the layout
                    frame.setLayout(null);

                    // Set the frame to be visible
                    frame.setVisible(true);
                    // Get user input from the text field

                    // Use the user input in the player's turn method
                    enterButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            bufferString = textField.getText();
                            buttonPress = true;
                        }
                    });


                    /** 
                    try {
                        myPlayer.turn(input);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                        */
                    // Call the buildList method to update the stock list label
                    Stock.buildList(); 
                    updatestockListLabel(Arrays.toString(Stock.stockList.toArray()));






                }

                public static void callturn(player myPlayer) throws FileNotFoundException, InterruptedException {
                    myPlayer.turn();
                }

                public static void updateQuestionLabel(String newText) {
                    SwingUtilities.invokeLater(() -> questionLabel.setText(newText));
                    }

                public static void updatestockListLabel(String newText) {
                    SwingUtilities.invokeLater(() -> stockListLabel.setText(newText));
                    }
                public static void updateCashLabel(String newText) {
                    SwingUtilities.invokeLater(() -> cashLabel.setText(newText));
                    }
                public static void updateStockOwnLabel(String newText) {
                    SwingUtilities.invokeLater(() -> stockOwnedLabel.setText(newText));
                    }
                    public static String bufferString = "";
                public static boolean buttonPress = false;
                public static String bufferGrab() throws InterruptedException {
                    System.out.println("You are in buffer grab");
                   enterButton.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       bufferString = textField.getText();
                       buttonPress = true;
                        }
                   });
                   while(!buttonPress){
                        Thread.sleep(100);
                   }

                    System.out.println(bufferString);
                    buttonPress = false;
                    textField.setText("");
                    return bufferString;
                }



            }
            