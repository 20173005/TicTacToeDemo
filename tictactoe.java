import java.util.*;

public class tictactoe
{
    public static void main(String[] args)
    {

        // Variable Declaration and Initialisation
        char[][] gameBoard;
        char playerMarker = 'x', aiMarker = 'o';
        boolean hasPlayerWon = false, hasAIWon = false, isItADraw = false, validInput = true, done = false;
        Scanner userInput = new Scanner(System.in);
        int emptySpaceCounter = 0, markerCoordsRow = 0, markerCoordsCol = 0;

        Random rand = new Random();

        //Create 3x3 Game board and fill it with '-'
        gameBoard = new char[3][3];
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = '-';
            }
        }

        // Loop
        while(!hasPlayerWon && !hasAIWon && !isItADraw)
        {
            // Player's turn
            System.out.println("Your Turn!");
            do
            {
                done = false;
                do
                {
                    try{ 
                        System.out.print("Choose where to place your marker: ");
                        StringTokenizer markerCoords = new StringTokenizer(userInput.nextLine());
                        markerCoordsRow = Integer.parseInt(markerCoords.nextToken());
                        markerCoordsCol = Integer.parseInt(markerCoords.nextToken());
                        done = true;
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.print("Incorrect format!\n");
                    }
                    catch(NoSuchElementException e)
                    {
                        System.out.print("Incorrect format!\n");
                    }

                } while(!done);
                
                if(markerCoordsRow >= 3 || markerCoordsRow < 0 || markerCoordsCol >= 3 || markerCoordsCol < 0)
                {
                    System.out.println("Invalid input!");
                    validInput = false;
                }
                else if(gameBoard[markerCoordsRow][markerCoordsCol] != '-')
                {
                    System.out.println("That space is already taken!");
                    validInput = false;
                }
                else if(gameBoard[markerCoordsRow][markerCoordsCol] == '-' )
                {
                    validInput = true;
                    gameBoard[markerCoordsRow][markerCoordsCol] = playerMarker;
                }

            } while(!validInput);
            printBoard(gameBoard);

            // Checks to see if there is three in a row for player marker
            for(int i = 0; i < 3; i++)
            {
                if(gameBoard[i][0] == 'x' && gameBoard[i][1] == 'x' && gameBoard[i][2] == 'x')
                {
                    hasPlayerWon = true;
                }
            }
            for(int j = 0; j < 3; j++)
            {
                if(gameBoard[0][j] == 'x' && gameBoard[1][j] == 'x' && gameBoard[2][j] == 'x')
                {
                    hasPlayerWon = true;
                }
            }
            if(gameBoard[0][0] == 'x' && gameBoard[1][1] == 'x' && gameBoard[2][2] == 'x')
            {
                hasPlayerWon = true;
            }

            // AI's Turn
            if(emptySpaceCounter != 1 && !hasPlayerWon)
            {
                System.out.println("AI's Turn!");
                do
                {
                    int aiMarkerCoordsRow = rand.nextInt(3), aiMarkerCoordsCol = rand.nextInt(3);

                    if(gameBoard[aiMarkerCoordsRow][aiMarkerCoordsCol] != '-')
                    {
                        validInput = false;
                    }
                    else
                    {
                        gameBoard[aiMarkerCoordsRow][aiMarkerCoordsCol] = aiMarker;
                        validInput = true;
                    }

                } while(!validInput);

                printBoard(gameBoard);
            }

            // Checks to see if there is three in a row for ai marker
            for(int i = 0; i < 3; i++)
            {
                if(gameBoard[i][0] == 'o' && gameBoard[i][1] == 'o' && gameBoard[i][2] == 'o')
                {
                    hasAIWon = true;
                }
            }
            for(int j = 0; j < 3; j++)
            {
                if(gameBoard[0][j] == 'o' && gameBoard[1][j] == 'o' && gameBoard[2][j] == 'o')
                {
                    hasAIWon = true;
                }
            }
            if(gameBoard[0][0] == 'o' && gameBoard[1][1] == 'o' && gameBoard[2][2] == 'o')
            {
                hasAIWon = true;
            }

            // Counts empty spaces and determines if its a draw
            emptySpaceCounter = 0;
            for(int i = 0; i < 3; i++)
            {
                for(int j = 0; j < 3; j++)
                {
                    if(gameBoard[i][j] == '-')
                    {
                        emptySpaceCounter++;
                    }
                }
            }
            if(emptySpaceCounter == 0)
            {
                isItADraw = true;
            }

        }

        //Print winning text
        if(hasPlayerWon == true)
        {
            System.out.println("Player has won!");
        }
        else if(hasAIWon == true)
        {
            System.out.println("AI has won!");
        }
        else if(isItADraw == true)
        {
            System.out.println("It is a draw!");
        }

        userInput.close();
    }

    // Print board method
    public static void printBoard(char[][] pGameBoard)
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                System.out.print(pGameBoard[i][j]);
            }
            System.out.print("\n");
        }
    }

}