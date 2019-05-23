import java.awt.*;
import java.awt.event.*;   // Per MouseEvent
import java.util.*;  // Per ArrayList
import javax.swing.*;

public class View
{
    private Adaptuesi adapteri;
    private JFrame TicTacToeFrame;
    private JButton[][] grid;
    private JButton reset;
    private JTextArea playerTurn;

    public View() // Konstruktori e krijon JFrame
    {
        this.TicTacToeFrame = new JFrame("Loja: Tic-Tac-Toe");
        this.grid = new JButton[3][3];
        this.reset = new JButton(" Ristarto Tabelen! ");
        this.playerTurn = new JTextArea();

        // Pjesa: Layout dhe Butonat!
        initialize();
        reset.setBackground(Color.GRAY);
        reset.setFocusPainted(false);  //This does that thing when you click no highlight afterwards Doni

        for (int i=0; i < 3; i++)
        {
            for (int j=0; j < 3; j++)
            {grid[i][j].setBackground(Color.DARK_GRAY);}
        }
    }

    public void setActionListener(Controller c)     //Kjo metode jau shton ActionListener secilit button te krijuar ne gridin e mehershem
      { this.adapteri = new Adaptuesi(c,this);
        for(int row = 0; row<3 ;row++) {
            for(int column = 0; column<3 ;column++) 
              {grid[row][column].addActionListener(adapteri);}
        }
        reset.addActionListener(adapteri);
      }

    public void initialize () //Metoda e cila thirret per ti caktuar Layout dhe Butonat te cilet i shtojme me von ne JFrame
    {
        TicTacToeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TicTacToeFrame.setSize(450, 300);
        TicTacToeFrame.setResizable(false);  //kjo metod nuk e lejon shfrytezuesin ta ndryshoj madhesin e JFrame

        JPanel gamePanel = new JPanel(new FlowLayout());
        JPanel game = new JPanel(new GridLayout(3,3));
        gamePanel.add(game, BorderLayout.CENTER);
        JPanel options = new JPanel(new FlowLayout());
        options.add(reset);
        JPanel messages = new JPanel(new FlowLayout());
        messages.setBackground(Color.red);

        TicTacToeFrame.add(gamePanel, BorderLayout.NORTH);
        TicTacToeFrame.add(options, BorderLayout.CENTER);
        TicTacToeFrame.add(messages, BorderLayout.SOUTH);
        playerTurn.setText("Lojtari 1 lëvizë, shenja: 'X'");
        messages.add(playerTurn);   // Kemi shtuar fushen private te Text-Area ne South

        for(int row = 0; row<3 ;row++)
        {
            for(int column = 0; column<3 ;column++)
            {
                grid[row][column] = new JButton();
                grid[row][column].setPreferredSize(new Dimension(60,60));
                grid[row][column].setText("");
                game.add(grid[row][column]);
            }
        }
        TicTacToeFrame.setVisible(true); // e bejme visible JFrame
    }

    public boolean isReset(ActionEvent event)  //Kjo metod shikon se a eshte prekur butoni 'Reset'
    {
        if(event.getSource() == reset)  {return true;}
        else {return false;}
    }

    public ArrayList<Integer> getPosition(ActionEvent event) // Kjo metode rikthen kordinatat (X,Y) Te butonit te klikuar
    {
        ArrayList<Integer> position = new ArrayList<Integer>();   //Krijojme nje array list te veqant per gjdo klikim individual i cili eshte i shoqeruar per nje buton

        for(int row = 0; row<3 ;row++)
        {
            for(int column = 0; column < 3 ;column++)
            {
                if(event.getSource() == grid[row][column])   {position.add(row);
                                                              position.add(column);}
            }
        }
        return position;
    }

    public void update(int row, int column, char symbol, String message)   //Kjo metod thirret nga Modeli dhe me ndihmen e saj ne bejme Update butonat ne grid array    {
     {  grid[row][column].setText(Character.toString(symbol));
        grid[row][column].setEnabled(false); // pasi qe ne manipulojme nje buton ne ate buton e bejme false qe te mos mundet te klikohet prap
        playerTurn.setText(message); // kjo e tregon rendin se cili lojetare duhet te luaj tani me rend
    }

    public void isWinner(int row, int column, char symbol, String message) // Kjo metode thirret nga modeli ne momentin kur njeri lojetare fiton lojen
    {
        grid[row][column].setText(Character.toString(symbol));
        grid[row][column].setEnabled(false);

        for(int i = 0; i<3 ;i++)
        {
            for(int j = 0; j<3 ;j++)
            {
                grid[i][j].setEnabled(false);   // Me nje for i bejme te gjithe butonat te 'paprekshem' per aryse se loja mund te perfundoj nen 4 levizje te 2 lojetareve
            }
        }
        playerTurn.setText(message); //Kush eshte fituesi tregon mesazhin
    }

    public void resetGame() //Kjo metode thirret nga Modeli ne momentin kur loja kerkohet te behet 'reset' 
    {
        for(int row = 0;row<3;row++)
        {
            for(int column = 0;column<3;column++)
            {
                grid[row][column].setText("");  //caktojme tekstet boshe per gjdo buton
                grid[row][column].setEnabled(true);  //mundesojme klikimin e butonave
            }
        }
        playerTurn.setText("Lojtari 1 lëvizë, shenja: 'X'"); // Kjo thirret gjdo here si veprim statik
    }
}
