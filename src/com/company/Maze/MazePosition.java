package com.company.Maze;

class MazePosition {
    int i, j;

    MazePosition(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int i() {
        return i;
    }

    public int j() {
        return j;
    }

    public void print() {
        System.out.println("(" + i + "," + j + ")");
    }

    public MazePosition north() {
        return new MazePosition(i - 1, j);
    }

    public MazePosition south() {
        return new MazePosition(i + 1, j);
    }

    public MazePosition east() {
        return new MazePosition(i, j + 1);
    }

    public MazePosition west() {
        return new MazePosition(i, j - 1);
    }


}
