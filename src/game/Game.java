package game;

import model.Cell;
import model.GameBoard;
import model.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    static Scanner scanner = new Scanner(System.in);
    private Player player1;
    private Player player2;
    private GameBoard board;

    public Game(Player player1, Player player2, GameBoard board) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = board;
    }

    public static void printBoard(Cell[] boardList) {
        for (Cell cell : boardList) {
            System.out.println(cell.toString());
        }
    }

    // Method to simulate playing the game
    public static void GameStart(Player player1, Player player2, GameBoard board) {
        ArrayList<Cell> Player1CellOnSide = new ArrayList<>();
        ArrayList<Cell> Player2CellOnSide = new ArrayList<>();

        Cell[] boardList = board.getBoard();

        for (int i = 1; i <= 5; i++) {
            Player1CellOnSide.add(boardList[i]);
        }

        for (int i = 7; i <= 11; i++) {
            Player2CellOnSide.add(boardList[i]);
        }

        player1.setCellsOnSide(Player1CellOnSide);
        player2.setCellsOnSide(Player2CellOnSide);

        while (!(Cell.isGameOver(boardList))) {
            player1.borrowGemsIfNeeded(board);
            printBoard(boardList);
            System.out.println("********************************************");
            System.out.println(player1.getName() + "'s turn");
            System.out.println(player1.getName() + " chooses a cell to pick gems from: ");
            int cellNum = scanner.nextInt();
            scanner.nextLine();

            // Modify to check if the cell is in player1's side
            while (!player1.getCellsOnSide().contains(boardList[cellNum]) || !player1.pickUpGemFrom(boardList[cellNum])) {
                System.out.println("Invalid cell. Please choose a cell from your side!");
                cellNum = scanner.nextInt();
                scanner.nextLine();
            }

            System.out.println(player1.getName() + " chooses Hand Direction");
            int Player1HandDirection = scanner.nextInt();
            scanner.nextLine();

            while (Player1HandDirection != 1 && Player1HandDirection != 0) {
                System.out.println("Invalid Hand Direction. Please choose again!");
                Player1HandDirection = scanner.nextInt();
            }
            player1.spreadGem(boardList[cellNum], Player1HandDirection, board);

            for (Cell cell : boardList) {
                System.out.println(cell.toString());
            }

            player2.borrowGemsIfNeeded(board);
            System.out.println("********************************************");
            System.out.println(player2.getName() + "'s turn");
            System.out.println(player2.getName() + " chooses a cell to pick gems from: ");

            int cellNum2 = scanner.nextInt();
            scanner.nextLine();

            // Modify to check if the cell is in player2's side
            while (!player2.getCellsOnSide().contains(boardList[cellNum2]) || !player2.pickUpGemFrom(boardList[cellNum2])) {
                System.out.println("Invalid cell. Please choose a cell from your side!");
                cellNum2 = scanner.nextInt();
                scanner.nextLine();
            }

            System.out.println("Player 2 chooses Hand Direction");
            int Player2HandDirection = scanner.nextInt();
            scanner.nextLine();

            while (Player2HandDirection != 1 && Player2HandDirection != 0) {
                System.out.println("Invalid Hand Direction. Please choose again!");
                Player2HandDirection = scanner.nextInt();
            }
            player2.spreadGem(boardList[cellNum2], Player2HandDirection, board);

            for (Cell cell : boardList) {
                System.out.println(cell.toString());
            }
            System.out.println(player1.getName() + ":" + player1.getScore());
            System.out.println(player2.getName() + ":" + player2.getScore());


        }

        Player winner = Game.determineWinner(player1, player2);
        System.out.println("Game over! Winner: " + winner);
    }

    //Check who is the winner by comparing score
    public static Player determineWinner(Player player1, Player player2) {
        if (player1.getScore() > player2.getScore()) {
            System.out.println("Player 1 is the winner");
            return player1;
        } else if (player1.getScore() < player2.getScore()) {
            System.out.println("Player 2 is the winner");
            return player2;
        }
        System.out.println("There is no winner");
        return null;
    }


    public static void main(String[] args) {
        GameBoard board = new GameBoard();
        Player Player1 = new Player(1, "Bach", 0, false);
        Player Player2 = new Player(2, "Huy", 0, true);

        System.out.println("Introduction");
        Player1.setInTurn((Math.random()) < 0.5);
        if (Player1.isInTurn()) {
            System.out.println("Player 1 play first");
            GameStart(Player1, Player2, board);

        } else {
            System.out.println("Player 2 play first");
            GameStart(Player2, Player1, board);
        }

    }
}
