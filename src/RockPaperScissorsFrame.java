import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    JPanel mainPnl, titlePnl, scorePnl, displayPnl, controlPnl;
    JButton rockBtn, paperBtn, scissorsBtn, quitBtn;
    JLabel titleLbl, playerWinLbl, computerWinLbl, tieLbl;
    ImageIcon rockIcon, paperIcon, scissorsIcon;
    JTextArea displayTA;
    JScrollPane scroller;
    JTextField playerTF, computerTF, tieTF;
    Random gen = new Random();
    int playerWin = 0;
    int computerWin = 0;
    int tie = 0;


    /**
     * This method creates the main panel and sets everything in it
     */
    public RockPaperScissorsFrame(){
        setTitle("Rock Paper Scissors Game");
        setSize(900,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        add(mainPnl);
        createTitlePanel();
        createScorePanel();
        createDisplayPanel();
        createControlPanel();

        setVisible(true);
    }

    /**
     * This method creates the title panel
     */
    private void createTitlePanel(){
        titlePnl = new JPanel();
        titleLbl = new JLabel("Rock Paper Scissors Game");
        titleLbl.setFont(new Font("Serif", Font.PLAIN, 36));
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);
        titleLbl.setVerticalTextPosition(JLabel.CENTER);

        titlePnl.add(titleLbl);
        mainPnl.add(titlePnl, BorderLayout.NORTH);
    }

    /**
     * This method creates the score panel
     */
    private void createScorePanel(){
        scorePnl = new JPanel();
        scorePnl.setLayout(new GridLayout(3,2));

        playerWinLbl = new JLabel("Player wins:");
        playerWinLbl.setFont(new Font("Serif", Font.PLAIN, 20));
        playerTF = new JTextField("0");
        playerTF.setEditable(false);

        computerWinLbl = new JLabel("Computer wins:");
        computerWinLbl.setFont(new Font("Serif", Font.PLAIN, 20));
        computerTF = new JTextField("0");
        computerTF.setEditable(false);

        tieLbl = new JLabel("Ties:");
        tieLbl.setFont(new Font("Serif", Font.PLAIN, 20));
        tieTF = new JTextField("0");
        tieTF.setEditable(false);

        scorePnl.add(playerWinLbl);
        scorePnl.add(playerTF);
        scorePnl.add(computerWinLbl);
        scorePnl.add(computerTF);
        scorePnl.add(tieLbl);
        scorePnl.add(tieTF);
        mainPnl.add(scorePnl, BorderLayout.WEST);
    }

    /**
     * This method creates and display panel
     */
    private void createDisplayPanel(){
        displayPnl = new JPanel();
        displayTA = new JTextArea(35,45);
        displayTA.setEditable(false);
        scroller = new JScrollPane(displayTA);

        displayPnl.add(scroller);
        mainPnl.add(displayPnl, BorderLayout.EAST);
    }

    /**
     * This method creates the control panel and sets the functions for the buttons
     */
    private void createControlPanel(){
        controlPnl = new JPanel();
        controlPnl.setLayout(new GridLayout(1,4));

        rockIcon = new ImageIcon("src/rock.png");
        rockIcon = iconResize(rockIcon);
        rockBtn = new JButton(rockIcon);
        rockBtn.addActionListener((ActionEvent ae) -> {
            String playerChoice = "r";
            playGame(playerChoice);
        });

        paperIcon = new ImageIcon("src/paper.png");
        paperIcon = iconResize(paperIcon);
        paperBtn = new JButton(paperIcon);
        paperBtn.addActionListener((ActionEvent ae) -> {
            String playerChoice = "p";
            playGame(playerChoice);
        });

        scissorsIcon = new ImageIcon("src/scissors.png");
        scissorsIcon = iconResize(scissorsIcon);
        scissorsBtn = new JButton(scissorsIcon);
        scissorsBtn.addActionListener((ActionEvent ae) -> {
            String playerChoice = "s";
            playGame(playerChoice);
        });

        quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font("monospaced", Font.PLAIN, 20));
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        controlPnl.add(rockBtn);
        controlPnl.add(paperBtn);
        controlPnl.add(scissorsBtn);
        controlPnl.add(quitBtn);
        mainPnl.add(controlPnl, BorderLayout.SOUTH);
    }

    /**
     * This function plays the logic of the rock paper scissors game
     * @param playerChoice-a string of the move the user had selected
     */
    private void playGame(String playerChoice){
        int computerPlay = gen.nextInt(3);
        //for the computer, 0 = rock, 1 = paper, and 2 = scissors
        switch (playerChoice) {
            case "r":
                if (computerPlay == 0) {
                    displayTA.append("You both chose rock. It's a tie.\n");
                    tie++;
                    tieTF.setText("" + tie);
                } else if (computerPlay == 1) {
                    displayTA.append("You chose rock and the computer chose paper. You lose.\n");
                    computerWin++;
                    computerTF.setText("" + computerWin);
                } else {
                    displayTA.append("You chose rock and the computer chose scissors. You win!\n");
                    playerWin++;
                    playerTF.setText("" + playerWin);
                }
                break;
            case "p":
                if (computerPlay == 0) {
                    displayTA.append("You chose paper and the computer chose rock. You win!\n");
                    playerWin++;
                    playerTF.setText("" + playerWin);
                } else if (computerPlay == 1) {
                    displayTA.append("You both chose paper. It's a tie.\n");
                    tie++;
                    tieTF.setText("" + tie);
                } else {
                    displayTA.append("You chose paper and the computer chose scissors. You lose.\n");
                    computerWin++;
                    computerTF.setText("" + computerWin);
                }
                break;
            case "s":
                if (computerPlay == 0) {
                    displayTA.append("You chose scissors and the computer chose rock. You lose\n");
                    computerWin++;
                    computerTF.setText("" + computerWin);
                } else if (computerPlay == 1) {
                    displayTA.append("You chose scissors and the computer chose paper. You Win!\n");
                    playerWin++;
                    playerTF.setText("" + playerWin);
                } else {
                    displayTA.append("You both chose scissors. It's a tie.\n");
                    tie++;
                    tieTF.setText("" + tie);
                }
                break;
            default:
                displayTA.append("There seems to have been an error. Please try again.\n");
                break;
        }
    }

    /**
     * Rescales an image icon to be smaller
     * @param icon-the image icon that is being resized
     * @return an image icon
     */
    private static ImageIcon iconResize(ImageIcon icon){
        Image img = icon.getImage();
        int newWidth = img.getWidth(null)/2;
        int newHeight = img.getHeight(null)/2;
        Image resizedImg = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
}

