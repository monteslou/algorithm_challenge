package com.company.Maze;

import org.junit.Test;

import static org.junit.Assert.*;

public class MazeTest {
    char F = ' ', W = '1', S = 'S', E = '0', V = '0';
    //F = FREE WAY, W =  WALL, S = START, V=FOUND WAY
    @Test
    public void MazeChallenge() throws CloneNotSupportedException {
        int START_I = 0, START_J = 0;
        int END_I = 0, END_J = 2;
        char[][] input = {
                {S, W, E},
                {F, W, F},
                {F, W, F},
                {F, F, F}
        };
        char[][] expected = {
                {V, W, V},
                {V, W, V},
                {V, W, V},
                {V, V, V}
        };
        Maze maze = new Maze();
        assertArrayEquals("Maze Challenge is not solved", maze.Maze(input, START_I, START_J, END_I, END_J), expected);
    }

    @Test
    public void Maze1() throws CloneNotSupportedException {
        int START_I = 0, START_J = 0;
        int END_I = 0, END_J = 3;
        char[][] input = {
                {S, W, F, E},
                {F, W, F, W},
                {F, W, F, W},
                {F, F, F, W}
        };
        char[][] expected = {
                {V, W, V, V},
                {V, W, V, W},
                {V, W, V, W},
                {V, V, V, W}
        };
        Maze maze = new Maze();
        assertArrayEquals("Maze 1 is not solved", maze.Maze(input, START_I, START_J, END_I, END_J), expected);
    }

    @Test
    public void Maze2() throws CloneNotSupportedException {
        int START_I = 0, START_J = 1;
        int END_I = 3, END_J = 5;
        char[][] input = {
                {W, S, W, W, W, W},
                {W, F, F, F, F, W},
                {W, F, W, W, W, W},
                {W, F, W, F, F, E},
                {W, F, F, F, W, W},
                {W, W, W, W, W, W}
        };
        char[][] expected = {
                {W, V, W, W, W, W},
                {W, V, F, F, F, W},
                {W, V, W, W, W, W},
                {W, V, W, V, V, V},
                {W, V, V, V, W, W},
                {W, W, W, W, W, W}
        };
        Maze maze = new Maze();
        assertArrayEquals("Maze 1 is not solved", maze.Maze(input, START_I, START_J, END_I, END_J), expected);
    }

    @Test
    public void MazeWithSize0() throws CloneNotSupportedException {
        int START_I = 0, START_J = 0;
        int END_I = 0, END_J = 0;
        char[][] input = new char[0][0];
        char[][] expected = new char[0][0];
        Maze maze = new Maze();
        assertArrayEquals("Maze with Size 0 is failed", maze.Maze(input, START_I, START_J, END_I, END_J), expected);
    }

    @Test
    public void MazeEmpty() throws CloneNotSupportedException {
        int START_I = 0, START_J = 0;
        int END_I = 0, END_J = 0;
        char[][] input = new char[5][4];
        char[][] expected = new char[5][4];
        Maze maze = new Maze();
        assertArrayEquals("Empty Maze is failed", maze.Maze(input, START_I, START_J, END_I, END_J), expected);
    }

    @Test
    public void MazeNull() throws CloneNotSupportedException {
        int START_I = 0, START_J = 0;
        int END_I = 0, END_J = 0;
        char[][] input = null;
        char[][] expected = null;
        Maze maze = new Maze();
        assertArrayEquals("Null Maze is failed", maze.Maze(input, START_I, START_J, END_I, END_J), expected);
    }

}