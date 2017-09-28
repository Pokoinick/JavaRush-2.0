package com.javarush.task.task35.task3513;

import java.util.*;

/**
 * Created by Станислав on 04.06.2017.
 */
public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile gameTiles[][];
    int score = 0;
    int maxTile = 2;
    Stack<Tile[][]> previousStates = new Stack<>();
    Stack<Integer> previousScores = new Stack<>();
    private boolean isSaveNeeded = true;


    public Model() {
       resetGameTiles();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public void autoMove() {
        PriorityQueue<MoveEfficiency> priorityQueue = new PriorityQueue<>(4, Collections.reverseOrder());
        priorityQueue.add(new MoveEfficiency(getEmptyTiles().size(), score, new Move() {
            @Override
            public void move() {
                left();
            }
        }));
        priorityQueue.add(new MoveEfficiency(getEmptyTiles().size(), score, new Move() {
            @Override
            public void move() {
                right();
            }
        }));
        priorityQueue.add(new MoveEfficiency(getEmptyTiles().size(), score, new Move() {
            @Override
            public void move() {
                up();
            }
        }));
        priorityQueue.add(new MoveEfficiency(getEmptyTiles().size(), score, new Move() {
            @Override
            public void move() {
                down();
            }
        }));
        priorityQueue.peek().getMove().move();
    }

    public MoveEfficiency getMoveEfficiency(Move move) {
        MoveEfficiency moveEfficiency;
        move.move();

        if (hasBoardChanged())
            moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        else
            moveEfficiency = new MoveEfficiency(-1, 0, move);

        rollback();
        return moveEfficiency;
    }

    public boolean hasBoardChanged() {
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                if (gameTiles[i][j].value != previousStates.peek()[i][j].value)
                    return true;
            }
        }
        return false;
    }

    public void randomMove() {
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n) {
            case 0 : up();
                    break;
            case 1 : down();
                    break;
            case 2 : right();
                    break;
            case 3 : left();
                    break;
        }
    }

    private void saveState(Tile[][] tiles) {
        Tile[][] savedTiles = new Tile[4][4];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                savedTiles[i][j] = new Tile(tiles[i][j].value);
            }
        }
        previousStates.push(savedTiles);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback() {
        if(previousStates != null && previousStates.size() > 0)
            gameTiles = previousStates.pop();
        if(previousScores != null && previousScores.size() > 0)
            score = previousScores.pop();
    }


    public boolean canMove() {
        if (getEmptyTiles().size()>0)
            return true;

        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length - 1; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j + 1].value && gameTiles[i][j].value != 0) {
                    return true;
                }
                if (gameTiles[j][i].value == gameTiles[j + 1][i].value && gameTiles[j][i].value != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void left() {
        int countChanges = 0;
        if (isSaveNeeded){
            saveState(gameTiles);
        }
        for (int i = 0; i < gameTiles.length; i++) {
            if (compressTiles(gameTiles[i]))
                countChanges++;
            if (mergeTiles(gameTiles[i]))
                countChanges++;
        }
        if (countChanges > 0) {
            addTile();
            isSaveNeeded = true;
        }
    }

    private void rotateMatrixPlus90() {
        Tile[][] tempMatrix = new Tile[4][4];
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles.length; j++) {
                tempMatrix[i][j] = gameTiles[j][tempMatrix.length-1-i];
            }
        }
        gameTiles = tempMatrix;
    }

    private void rotateMatrixMinus90() {
        Tile[][] tempMatrix = new Tile[4][4];
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles.length; j++) {
                tempMatrix[i][j] = gameTiles[tempMatrix.length-1-j][i];
            }
        }
        gameTiles = tempMatrix;
    }


    public void right() {
        saveState(gameTiles);
        rotateMatrixPlus90();
        rotateMatrixPlus90();
        left();
        rotateMatrixMinus90();
        rotateMatrixMinus90();
    }

    public void down() {
        saveState(gameTiles);
        rotateMatrixMinus90();
        left();
        rotateMatrixPlus90();
    }

    public void up() {
        saveState(gameTiles);
        rotateMatrixPlus90();
        left();
        rotateMatrixMinus90();
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean isChanged = false;
        for (int i = 0; i < tiles.length-1; i++) {
            if (tiles[i].isEmpty() && !(tiles[i+1].isEmpty())) {
                Tile tempTile = tiles[i+1];
                tiles[i+1] = tiles[i];
                tiles[i] = tempTile;
                compressTiles(tiles);
                isChanged = true;
            }
        }
        return isChanged;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean isChanged = false;
        for (int i = 0; i < tiles.length-1; i++) {
            if (tiles[i].value == tiles[i+1].value && tiles[i].value != 0) {
                tiles[i].value *= 2;
                tiles[i+1].value = 0;
                score += tiles[i].value;
                if (maxTile < tiles[i].value)
                    maxTile = tiles[i].value;
                compressTiles(tiles);
                isChanged = true;
            }
        }
        return isChanged;
    }

    private void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();

        if (emptyTiles != null && emptyTiles.size() != 0)
            emptyTiles.get((int) (Math.random() * emptyTiles.size())).value = (Math.random() < 0.9 ? 2 : 4);
    }

    public void resetGameTiles() {
        gameTiles = new Tile[4][4];
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> emptyTiles = new ArrayList<>();
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                if (gameTiles[i][j].isEmpty())
                    emptyTiles.add(gameTiles[i][j]);
            }
        }
        return emptyTiles;
    }
}
