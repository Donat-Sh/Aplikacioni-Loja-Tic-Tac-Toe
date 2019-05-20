public class Model
{
    private View view;
    private char[][] board;
    private String message;
    private int playerId;
    private int count_of_Moves;

    public Model()   //Ky eshte Konstruktori i Modelit
    {
        this.count_of_Moves = 9;    // caktojme nr.e levijeve si Kombinacion Statik i cili matematikisht shkon duke u zvogeluar ne intervalin [0,9]
        this.playerId = 1;          // ketu ne e caktojne me nje 'ID' sikurse tek rasti i 'Databazes' lojetarin, vlerat e mundshme jane vetem [1,2]
        this.board = new char[3][3];   //kirjojme nje char array per tabelen
    }

    public void registerView(View v)   //inicializojme View
    {this.view = v;}

    public int getPlayerId()  // metoda e cila sipas standartit te specifikacionit kthen vleren e player id
    {return playerId;}

    public void setPlayerId(int playerId) // metoda e cila sipas standartit te specifikacionit ndryshon vleren e player id
    {this.playerId = playerId;}

    public int getMovesCount()   // metoda e cila sipas standartit te specifikacionit kthen vleren e nr. te levizjeve
    {return count_of_Moves;}

    public void setMovesCount(int movesCount) // metoda e cila sipas standartit te specifikacionit ndryshon vleren e nr. te levizjeve
    {this.count_of_Moves = movesCount;}

    public char[][] getBoard() // metoda e cila sipas standartit te specifikacionit kthen board char array
    {return board;}

    public void setBoard(char[][] board) // metoda e cila sipas standartit te specifikacionit ben update board char array
    {this.board = board;}

    public String getMessage()  // metoda e cila sipas standartit te specifikacionit kthen String mesazhi
    {return message;}

    public void setMessage(String message) // metoda e cila sipas standartit te specifikacionit ndryshon String mesazhi
    {this.message = message;}

   
    public void PlayMove(int x, int y) //Kjo metode eshte shume e rendesishme sepse ajo kthen nr. ne te cilet tregohet gjendja se: a eshte fituar loja, a kemi barazim, a ka ndonje levizje tjeter te mundshme, etj.
      {
        if(getMovesCount() > 0)
        {
            if(playerId%2 != 0) {board[x][y] = 'X';}  // nese ID == 1   ==> se eshte fjala per 'X'
            else {board[x][y] = 'O';}                 // nese ID == 2   ==> se eshte fjala per 'O'

            setMovesCount(--count_of_Moves); // zvogelojme nr. e levizjeve deri sa te arrijme ne zero!

            if(ifWinning(x, y))   //Kjo thirre nje metode qe na tregon a kemi : FITIM, HUMBJE dhe BARAZIM!!!
            {
                setMessage("Lojëtari nr. " + playerId + " fiton Lojën!");
                view.isWinner(x, y, board[x][y], getMessage());      // I tregojme View qe kemi fitues ashtu qe ta ndalojme lojen dhe ta shpallim fituesin ne Frame
            }
            else if(getMovesCount()==0)
            {
                setMessage("BARAZIM");
                view.isWinner(x, y, board[x][y], getMessage());  // I tregojme View qe kemi barazim
            }
            else     //Ketu kalojme nese nuk kemi fitues edhe nuk kemi barazim
                {
                if(playerId%2 != 0)
                {
                    setPlayerId(2);
                    setMessage("'O':  Lojëtari " +getPlayerId()); //Ndryshojme renditjen e lojetarit 
                }
                else
                    {
                    setPlayerId(1);
                    setMessage("'X':  Lojëtari " +getPlayerId()); //Ndryshojme renditjen e lojetarit

                }
                view.update(x, y, board[x][y], getMessage());  // Ketu bejme update View me ndryshimet e cekura
            }

        }

    }

    public boolean ifWinning(int x, int y) // Metoda qe na tregon a kemi : FITIM, HUMBJE dhe BARAZIM!!!
    {
        // poshte caktojme variablat te cilat ne na ndihmojne per ta caktuar rastet e mundshme!
        int rowCount = 0;
        int colCount = 0;
        int countLDiag = 0;
        int countRDiag = 0;
        char symbol;
        
        if(getPlayerId() %2 !=0)  {symbol = 'X';}     // kjo si larte
        else {symbol = 'O';}

        for(int i=0; i<board.length;i++)        // vetem 3 raste shqyrtohen gjdo here pra : 0,1,2 se gjatesia eshte 3 e cila ==>  =/= 
        {
            if(board[x][i] == symbol)  // Shikon rreshtin i cili caktohet nga Integer X 
            {rowCount++;}
            
            if(board[i][y] == symbol)  // Shikon shtyllen i cili caktohet nga Integer Y
            {colCount++;}
            
            if(board[i][i] == symbol)        // Ne lojen Tic-Tac-Toe ne kemi vetem 2 Diagonale prandaj ato i shikojme vetem
            {countRDiag++;                   // Kjo eshte diagonalja Top-Left ==> Bottom-Right
            }
            if(board[board.length-1-i][i] == symbol)  // Kjo eshte diagonalja Bottom-Left ==> Top-Right
            {countLDiag++;}
        }
         // Nese kemi arritur ndokund vlera ==3 nese vetem nje eshte e sakte atehere aty ndalojme lojen dhe caktojme fituesin
        if(colCount==board.length || rowCount==board.length || countLDiag == board.length || countRDiag == board.length)
        {return true;}
        else {return false;}
    }

    public void ResetModel()  // Kjo metode thirret nga Adapter nese klikohet butoni reset me qrast bejme ndryshimet e duhura per array dhe variablat e tjera
    {
        count_of_Moves = 9;
        setPlayerId(1);    // Gjithmone lojetari nr.1 eshte i pari
        setMessage("");
        for(int i=0; i < board.length;i++)
        {
            for(int j=0; j < board.length;j++)
            {
                board[i][j] = '\0';    // caktojme vlera null per char array 
            }
        }
        view.resetGame(); // pastaj ne i tregojme View qe ti bej ndryshimet e duhura tek butonat e Frame 
    }
}
