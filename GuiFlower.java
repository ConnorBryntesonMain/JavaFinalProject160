        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.io.FileNotFoundException;
        import java.util.*;

        public class GuiFlower {
            private JFrame frame;
            private static JLabel questionLabel;
            private static JLabel stockListLabel;
            private static JLabel cashLabel;
            private static JLabel stockOwnedLabel;

            /*Set the GuiFlower instance in the Stock class
            */
            public GuiFlower() {
                Stock.setGuiFlowerInstance(this);
            }

            static JTextField textField = new JTextField(20);
            static JButton enterButton = new JButton("Enter");
            /* This starts and runs the GUI
            * It creates the frame, the labels, text fields, and "Enter" button
            * It also formats the GUI to be somewhat user friendly
            */
            public void initialize() {

                frame = new JFrame("Flower GUI with Buttons");
                frame.setSize(1200, 1000);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                questionLabel = new JLabel("What is your name?");
                stockListLabel = new JLabel("Stocks go here");
                cashLabel = new JLabel("Cash Here");
                stockOwnedLabel = new JLabel("Stock Owned Here");
                


                questionLabel.setBounds(30, 30, 600, 30);
                questionLabel.setFont(new Font("Serif", Font.PLAIN, 20));
                enterButton.setBounds(150, 60, 100, 30);
                enterButton.setFont(new Font("Serif", Font.PLAIN, 20));
                textField.setBounds(30, 60, 150, 30);
                cashLabel.setBounds(30, 90, 300, 30);
                cashLabel.setFont(new Font("Serif", Font.PLAIN, 20));
                stockListLabel.setBounds(30, 120, 1200, 60);
                stockListLabel.setFont(new Font("Serif", Font.PLAIN, 20));
                stockOwnedLabel.setBounds(30, 180, 600, 30);
                stockOwnedLabel.setFont(new Font("Serif", Font.PLAIN, 20));
                textField.setText("");
                
                frame.add(textField);
                frame.add(enterButton);
                frame.add(questionLabel);
                frame.add(cashLabel);
                frame.add(stockListLabel);
                frame.add(stockOwnedLabel);
                
                
                frame.setLayout(null);
                frame.setVisible(true);
                

                /* This method gets the textField information after the "Enter" button is pressed
                * Overrides actionListener with actionPerformed
                */
                enterButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        bufferString = textField.getText();
                        buttonPress = true;
                    }
                });

                
                Stock.buildList();
                updatestockListLabel(Arrays.toString(Stock.stockList.toArray()));

            }
            /* This method calls/starts the turn
             * @param myPlayer player
             */
            public static void callturn(player myPlayer) throws FileNotFoundException, InterruptedException
            {
                myPlayer.turn();
            }
            /* This method sets the questionLabel to the new question prompt
             * @Param newText String
             */
            public static void updateQuestionLabel(String newText) 
            {
                SwingUtilities.invokeLater(() -> questionLabel.setText(newText));
            }
             /* This method sets the stockListLabel to the availble stocks 
             * @Param newText String
             */

            public static void updatestockListLabel(String newText) 
            {
                SwingUtilities.invokeLater(() -> stockListLabel.setText(newText));
            }
             /* This method sets the cashLabel to the amount of cash the player owns
             * @Param newText String
             */

            public static void updateCashLabel(String newText) 
            {
                SwingUtilities.invokeLater(() -> cashLabel.setText(newText));
            }

            /* This method sets the stockOwnLabel to the new owned stocks 
             * @Param newText String
             */
            public static void updateStockOwnLabel(String newText) 
            {
                SwingUtilities.invokeLater(() -> stockOwnedLabel.setText(newText));
            }

           
            public static String bufferString = "";
            public static boolean buttonPress = false;
            /* This method makes the whole game run accordingly.
             * bufferGrab acts as a buffer to make sure each action is performed in the correct order.
             */
            public static String bufferGrab() throws InterruptedException 
            {
                System.out.println("You are in buffer grab");
                enterButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        bufferString = textField.getText();
                        buttonPress = true;
                    }
                });
                while (!buttonPress) 
                {
                    Thread.sleep(100);
                }

                System.out.println(bufferString);
                buttonPress = false;
                textField.setText("");
                return bufferString;
            }

        }
