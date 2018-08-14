package com.company.Maze;

import java.util.LinkedList;
import java.util.Stack;

public class Maze {
    char F = ' ', W = '1', S = 'S', E = '0', V = '0';
    //F = FREE WAY, W =  WALL, S = START, V=FOUND WAY

    private int START_I, START_J;
    private int END_I, END_J;
    private char[][] maze;


    public char[][] Maze(char maze[][], int START_I, int START_J, int END_I, int END_J) throws CloneNotSupportedException {
        this.maze = maze;
        this.START_I = START_I;
        this.START_J = START_J;
        this.END_I = END_I;
        this.END_J = END_J;

        if (maze == null){
            return this.maze;
        }

        if(sizeI() == 0 || sizeJ() == 0){
            return this.maze;
        }

        System.out.println("Giving Maze: ");
        this.printInit();
        this.solveStack();
        this.solveQueue();
        this.solveRec();

        return this.maze;
    }

    private int sizeI() {
        return maze.length;
    }

    private int sizeJ() {
        return maze[0].length;
    }

    private void printInit() {
        for (int i = 0; i < sizeI(); i++) {
            for (int j = 0; j < sizeJ(); j++) {
                if (!(maze[i][j] == '1')) {
                    System.out.print(0);
                } else {
                    System.out.print(1);
                }
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    private void printSolved() {
        for (int i = 0; i < sizeI(); i++) {
            for (int j = 0; j < sizeJ(); j++) {
                if (maze[i][j] == '1' || maze[i][j] == ' ') {
                    System.out.print(0);
                } else {
                    System.out.print(1);
                }
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    private char mark(int i, int j, char value) {
        assert(isInMaze(i, j));
        char tmp = maze[i][j];
        maze[i][j] = value;
        return tmp;
    }

    //true if cell is within maze
    private boolean isInMaze(MazePosition pos) {
        return isInMaze(pos.i(), pos.j());
    }

    //true if cell is within maze
    private boolean isInMaze(int i, int j) {
        return i >= 0 && i < sizeI() && j >= 0 && j < sizeJ();
    }

    private void mark(MazePosition pos, char value) {
        mark(pos.i(), pos.j(), value);
    }


    private boolean isMarked(int i, int j) {
        assert (isInMaze(i, j));
        return (maze[i][j] == V);
    }

    public boolean isMarked(MazePosition pos) {
        return isMarked(pos.i(), pos.j());
    }


    private boolean isClear(int i, int j) {
        assert (isInMaze(i, j));
        return (maze[i][j] != W && maze[i][j] != V);
    }

    private boolean isClear(MazePosition pos) {
        return isClear(pos.i(), pos.j());
    }

    private boolean isFinal(int i, int j) {
        return (i == END_I && j == END_J);
    }

    private boolean isFinal(MazePosition pos) {
        return isFinal(pos.i(), pos.j());
    }


    protected char[][] clone() throws CloneNotSupportedException {

        char[][] mazeCopy = new char[sizeI()][sizeJ()];
        for (int i=0; i< sizeI(); i++)
            System.arraycopy(maze[i], 0, mazeCopy[i], 0, sizeJ());
        return mazeCopy;
    }

    private void restore(char[][] savedMaze) {
        for (int i=0; i< sizeI(); i++)
            System.arraycopy(savedMaze[i], 0, maze[i], 0, sizeJ());
    }

    //THE GOAL IS TO FIND A PATH FROM START TO END

    //**************************************************
    //this solution uses a stack to keep track of possible
    //states/positions to explore; it marks the maze to remember the
    //positions that it's already explored.
    private void solveStack() throws CloneNotSupportedException {

        //save the maze
        char[][] savedMaze = clone();

        //declare the locations stack
        Stack<MazePosition> candidates = new Stack<>();

        //insert the start
        candidates.push(new MazePosition(START_I, START_J));

        MazePosition crt, next;
        while (!candidates.empty()) {
            //get current position
            crt = candidates.pop();
            if (isFinal(crt)) break;
            //mark the current position
            mark(crt, V);
            //put its neighbors in the queue
            next = crt.north();
            if (isInMaze(next) && isClear(next)) candidates.push(next);
            next = crt.east();
            if (isInMaze(next) && isClear(next)) candidates.push(next);
            next = crt.west();
            if (isInMaze(next) && isClear(next)) candidates.push(next);
            next = crt.south();
            if (isInMaze(next) && isClear(next)) candidates.push(next);
        }
        //restore the maze
        restore(savedMaze);
    }


    //**************************************************
    //this solution uses a QUEUE to keep track of possible
    //states/positions to explore; it marks the maze to remember the
    //positions that it's already explored.
    private void solveQueue() throws CloneNotSupportedException {
        //save the maze
        char[][] savedMaze = clone();

        //declare the locations stack
        LinkedList<MazePosition> candidates = new LinkedList<>();

        //insert the start
        candidates.add(new MazePosition(START_I, START_J));

        MazePosition currentPosition, next;
        while (!candidates.isEmpty()) {

            //get current position
            currentPosition = candidates.removeFirst();

            if (isFinal(currentPosition)) break;

            //mark the current position
            mark(currentPosition, V);

            //put its neighbors in the queue
            next = currentPosition.north();
            if (isInMaze(next) && isClear(next)) candidates.add(next);
            next = currentPosition.east();
            if (isInMaze(next) && isClear(next)) candidates.add(next);
            next = currentPosition.west();
            if (isInMaze(next) && isClear(next)) candidates.add(next);
            next = currentPosition.south();
            if (isInMaze(next) && isClear(next)) candidates.add(next);
        }

        //restore the maze
        restore(savedMaze);
    }


    //************************************************** solve using
    //recursion. Note: this solution unmarks the path upon reaching
    //dead ends, so in the end only the path is left marked. It is
    //possible to write a solution that does not un-mark its traces,
    //but instead it clones and restores the maze.
    private void solveRec() {
        if (solve(new MazePosition(START_I, START_J)))
            System.out.println("Solved Maze: ");
        else System.out.println("No solution");
        printSolved();

    }


    //find a path to exit the maze from this position. Works
    //recursively, by advancing to a neighbor and continuing from
    //there. If a path is found, return true. Otherwise return false.
    private boolean solve(MazePosition pos) {

        //base case
        if (!isInMaze(pos)) return false;
        if (isFinal(pos)) return true;
        if (!isClear(pos)) return false;

        //current position must be clear
        assert (isClear(pos));

        //recurse

        //first mark this  location
        mark(pos, V);

        //try to go south
        if (solve(pos.south())) {
            //we found a solution going south: if we want to leave the
            //maze clean, then unmark current cell and return; if we
            //want to mark the path in the maze, then don't unmark
            //mark(pos, F);
            return true;
        }

        //else west
        if (solve(pos.west())) {
            //we found a solution going west: if we want to leave the
            //maze clean, then unmark current cell and return; if we
            //want to mark the path in the maze, then don't unmark
            //return
            //mark(pos, F);
            return true;
        }

        //else north
        if (solve(pos.north())) {
            //we found a solution going north: if we want to leave the
            //maze clean, then unmark current cell and return; if we
            //want to mark the path in the maze, then don't unmark
            //return
            // mark(pos, F);
            return true;
        }

        //else east
        if (solve(pos.east())) {
            //we found a solution going east: if we want to leave the
            //maze clean, then unmark current cell and return; if we
            //want to mark the path in the maze, then don't unmark
            //return
            //mark(pos, F);
            return true;
        }

        //unmark all dead ends; since it was marked, the position must
        //have been clear
        mark(pos, F);
        //if none of the above returned, then there is no solution
        return false;
    }
}


