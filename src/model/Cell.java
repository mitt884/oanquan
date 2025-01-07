package model;

import gem.Gem;

import java.util.ArrayList;
import java.util.List;

public abstract class Cell {
    protected List<Gem> gems;
    private int position;
    private boolean isUpperRow;
    private int score;

    //constructor
    public Cell(int position, boolean isUpperRow, int numberOfGems) {
        //TODO: implement Cell constructor
        this.gems = new ArrayList<>();
        this.position = position;
        this.isUpperRow = isUpperRow;
        for (int i = 0; i < numberOfGems; i++) { 
            this.gems.add(null);
        }
    }

    //getter
    public List<Gem> getGems() {
        return gems;
    }

    public int getPosition() {
        return position;
    }

    public boolean isUpperRow() {
        return isUpperRow;
    }

    public int getNUmberOfGems() {
        return gems.size();
    }

    // method them xoa gem khoi cell
    public void addGem(Gem gem) {
        if (gem != null)
            this.gems.add(gem);
    }

    public int getGemsCount(){
        return getGems().size();
    };

    public boolean isEmpty(){
        return getGems().isEmpty();
    };

    public abstract String toString();
    // Method to check if it's a half-circle
    public boolean isHalfCircle() {
        if (this.isUpperRow()) {
            return position == 0;  // Giả sử ô half-circle trên ở vị trí 0
        } else {
            return position == 6;  // Giả sử ô half-circle dưới ở vị trí 6
        }
    }

    // method kiem tra ket thuc game (2 halfcircle rong hoac hang square tren rong hoac hang square duoi rong
    public static boolean isGameOver(Cell[] cells) {
        boolean upperHalfCircleEmpty = false;
        boolean lowerHalfCircleEmpty = false;
        // Check the status of the squares and half-circles
        for (Cell cell : cells) {
            if (cell.isHalfCircle()) {
                if (cell.isUpperRow()) {
                    upperHalfCircleEmpty = cell.isEmpty();
                } else {
                    lowerHalfCircleEmpty = cell.isEmpty();
                }
            }
        }
            return (upperHalfCircleEmpty && lowerHalfCircleEmpty);
    }

    // method lam trong cell khi nguoi choi chon cell de rai gem
    public void emptyCell() {
        gems.clear();
    }

    public int calculateScore() {
        score = 0;
        for (Gem gem : gems) {
            score += gem.getValue();
        }
        return score;
    }
}
