import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3]; // 3x3 grid for the board
    private boolean isPlayerX = true; // True for Player X, False for Player O
    private int moveCount = 0; // Count the number of moves made
    private JLabel titleLabel; // Title Label

    public TicTacToeGame() {
        super("Tic-Tac-Toe");

         // Make the JFrame fullscreen
        // Maximizes the frame
        //  setUndecorated(true); // Removes title bar for fullscreen effect
 
         // Change the background color of the content pane
         getContentPane().setBackground(new Color(192, 192,192 )); // Sky blue background
         getContentPane().setLayout(null); // Set null layout for manual positioning

        // Setting up the layout
        setLayout(null);

        // Add title
        titleLabel = new JLabel("Tic Tac Toe", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI Black", Font.BOLD + Font.ITALIC, 30));
        titleLabel.setForeground(new Color(0, 153, 153));
        titleLabel.setBounds(200, 20, 300, 30); // Positioning the title at the top
        add(titleLabel);

        JSeparator sp = new JSeparator();
        sp.setBounds(10, 55, 670, 30); // x, y, width, height
        sp.setForeground(Color.BLACK); // Set color if needed
        add(sp);
        
        JLabel Palayer1 = new JLabel("Player 1");
        Palayer1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
        Palayer1.setBounds(110, 70, 300, 30); // Positioning the title at the top
        add(Palayer1);

        JLabel Symbole1 = new JLabel("x");
        Symbole1.setFont(new Font("Arial", Font.BOLD, 48));
        Symbole1.setForeground(Color.RED);
        Symbole1.setBounds(130, 100, 300, 30); // Positioning the title at the top
        add(Symbole1);

        JLabel Symbole2 = new JLabel("o");
        Symbole2.setFont(new Font("Arial", Font.BOLD, 48));
        Symbole2.setForeground(Color.blue);
        Symbole2.setBounds(520, 100, 300, 30); // Positioning the title at the top
        add(Symbole2);

        JLabel Palayer2 = new JLabel("Player 2");
        Palayer2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
        Palayer2.setBounds(500, 70, 300, 30); // Positioning the title at the top
        add(Palayer2);

        JLabel creater = new JLabel("@powered by Vinay");
        creater.setFont(new Font("Arial", Font.BOLD, 14));
        creater.setForeground(new Color(220,220 ,220));
        creater.setBounds(520, 520, 300, 30); // Positioning the title at the top
        add(creater);

        // Create the board with manual positioning
        initializeBoard();

        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    private void initializeBoard() {
        // Position and size for the buttons
        int buttonSize = 100;
        int startX = 190, startY = 170;

        // Initialize the grid with buttons
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("SansSerif", Font.BOLD, 40));
                buttons[row][col].setBackground(new Color(224, 224, 224));
                buttons[row][col].setForeground(new Color(0, 0,51));
                buttons[row][col].setFocusPainted(false);

                // Manually set position for each button
                int x = startX + col * buttonSize;
                int y = startY + row * buttonSize;

                buttons[row][col].setBounds(x, y, buttonSize, buttonSize);
                buttons[row][col].addActionListener(this);

                add(buttons[row][col]); // Add to the frame
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        // If the button is already clicked, ignore
        if (!clickedButton.getText().equals("") && !clickedButton.getText().matches("\\(.*\\)")) {
            return;
        }

        // Set the button text to "X" or "O" based on the current player
        clickedButton.setText(isPlayerX ? "X" : "O");
        moveCount++;

        // Check if the current player wins
        if (checkWinner()) {
            JOptionPane.showMessageDialog(this, "Player " + (isPlayerX ? "X" : "O") + " wins!");
            resetGame();
            return;
        }

        // Check for a draw
        if (moveCount == 9) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetGame();
            return;
        }

        // Switch player
        isPlayerX = !isPlayerX;
    }

    private boolean checkWinner() {
        // Check rows and columns for a win
        for (int i = 0; i < 3; i++) {
            if (checkLine(buttons[i][0], buttons[i][1], buttons[i][2])) return true;
            if (checkLine(buttons[0][i], buttons[1][i], buttons[2][i])) return true;
        }

        // Check diagonals for a win
        if (checkLine(buttons[0][0], buttons[1][1], buttons[2][2])) return true;
        if (checkLine(buttons[0][2], buttons[1][1], buttons[2][0])) return true;

        return false;
    }

    private boolean checkLine(JButton b1, JButton b2, JButton b3) {
        String text1 = b1.getText();
        String text2 = b2.getText();
        String text3 = b3.getText();

        return !text1.equals("") && text1.equals(text2) && text2.equals(text3);
    }

    private void resetGame() {
        // Clear the board and reset the state
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        isPlayerX = true;
        moveCount = 0;
    }

    public static void main(String[] args) {
        new TicTacToeGame();
    }
}
