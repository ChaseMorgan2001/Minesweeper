package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class board extends JPanel {

    public final int defW = 12, defH = 12, defM = 40;
    private int width = defW, height = defH, numMines = defM, numVisited = 0, clicks = 0;
    public ArrayList<ArrayList<cell>> x;
    private boolean mineClicked = false;

    public void setWidth(int w) {
        width = w;
    }
    public void setHeight(int h) {
        height = h;
    }
    public void setNumMines(int n) {
        numMines = n;
    }
    public void setNumVisited(int n) { numVisited = n; }
    public void setClicks(int n) { clicks = n; }
    public int getNumMines() { return numMines;}
    public void resetBoard() {
        mineClicked = false;
        removeAll();
        x.clear();
    }
    public void fillBoard(){
        setLayout(new GridLayout(height, width));
        int cellNum = 0; // to count number of cells to make it easy to add the right number of mines
        // create 2d array list of cells
        for (int i = 0; i < width; i++){
            ArrayList<cell> c = new ArrayList<>();

            for (int j = 0; j < height; j++){
                c.add(new cell());
            }
            x.add(c);
        }

        // transpose matrix of cells
        ArrayList<ArrayList<cell>> temp = x;
        for(int i = 0; i < width; i++){
            ArrayList<cell> r = new ArrayList<>();
            for(int j = 0; j < height; j++){
                r.add(x.get(j).get(i));
                // set the first n cells to mines
                if (cellNum < numMines){
                    r.get(j).setMine();
                }
                cellNum++;
            }
            temp.add(r);
        }
        x = temp;

        // shuffle the matrix for random distribution of mines
        for (int i = 0; i < width; i++){
            Collections.shuffle(x.get(i));
            for (int j = 0; j < height; j++){
                add(x.get(i).get(j));
                x.get(i).get(j).addActionListener(AL);
                x.get(i).get(j).setX(i);
                x.get(i).get(j).setY(j);
            }
        }
        setVisible(true);
        repaint();
        revalidate();
    }

    public void findMines(cell n){
        int num = 0;

        // calculates number of mines in immediate vicinity
        if (n.Y + 1 < height) {
            if (x.get(n.X).get(n.Y + 1).isMine) { num++; }
        }
        if (n.X + 1 < width && n.Y + 1 < height) {
            if (x.get(n.X + 1).get(n.Y + 1).isMine) { num++; }
        }
        if (n.X + 1 < width) {
            if (x.get(n.X + 1).get(n.Y).isMine) { num++; }
        }
        if (n.X + 1 < width && n.Y - 1 > -1) {
            if (x.get(n.X + 1).get(n.Y - 1).isMine) { num++; }
        }
        if (n.Y - 1 > -1) {
            if (x.get(n.X).get(n.Y - 1).isMine) { num++; }
        }
        if (n.X - 1 > -1 && n.Y - 1 > -1) {
            if (x.get(n.X - 1).get(n.Y - 1).isMine) { num++; }
        }
        if (n.X - 1 > -1){
            if (x.get(n.X - 1).get(n.Y).isMine) { num++; }
        }
        if (n.X - 1 > -1 && n.Y + 1 < height) {
            if (x.get(n.X - 1).get(n.Y + 1).isMine) { num++; }
        }

        // if no mines around sets color to light gray and does a dfs
        if (num == 0) {
            if (!n.isMine) {
                n.setBackground(Color.LIGHT_GRAY);
                if (!n.visited) {
                    n.setVisited();
                    numVisited++;
                    if (n.Y + 1 < height) {
                        findMines(x.get(n.X).get(n.Y + 1));
                    }
                    if (n.X + 1 < width && n.Y + 1 < height) {
                        findMines(x.get(n.X + 1).get(n.Y + 1));
                    }
                    if (n.X + 1 < width) {
                        findMines(x.get(n.X + 1).get(n.Y));
                    }
                    if (n.X + 1 < width && n.Y - 1 > -1) {
                        findMines(x.get(n.X + 1).get(n.Y - 1));
                    }
                    if (n.Y - 1 > -1) {
                        findMines(x.get(n.X).get(n.Y - 1));
                    }
                    if (n.X - 1 > -1 && n.Y - 1 > -1) {
                        findMines(x.get(n.X - 1).get(n.Y - 1));
                    }
                    if (n.X - 1 > -1) {
                        findMines(x.get(n.X - 1).get(n.Y));
                    }
                    if (n.X - 1 > -1 && n.Y + 1 < height) {
                        findMines(x.get(n.X - 1).get(n.Y + 1));
                    }
                }
            }
        }
        // if there are mines around, sets text of the cell to number of mines around
        else {
            n.setText(String.valueOf(num));
            n.setVisited();
            if (n.timesCalled == 1) { numVisited++; }
        }
        // if no mine has been clicked check for win
        if (!mineClicked) {
            checkWin();
        }
    }
    public void checkWin() {

        // creates the jdialog for win message
        JDialog win = new JDialog();
        win.setSize(250, 200);
        win.setLayout(new BorderLayout());
        JLabel youWin = new JLabel("YOU WIN!");
        youWin.setHorizontalAlignment(SwingConstants.CENTER);
        youWin.setFont(new Font("Arial", Font.PLAIN, 40));
        win.add(youWin, BorderLayout.CENTER);
        JButton OK = new JButton("OK");
        OK.addActionListener(actionEvent -> win.setVisible(false));
        win.add(OK, BorderLayout.SOUTH);
        // set jdialog to visible and stops game if all non-mine cells have been visited
        if (numVisited == ((height * width) - numMines)) {
            win.setVisible(true);
            Minesweeper.setRunning(false);
        }
    }

    // if a mine is clicked, stop game, reveal all cells, and display lose message box
    public void explode(){
        Minesweeper.setRunning(false);
        mineClicked = true;
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                findMines(x.get(i).get(j));
                x.get(i).get(j).showMine();
            }
        }
        JDialog lose = new JDialog();
        lose.setSize(250, 200);
        lose.setLayout(new BorderLayout());
        JLabel youLose = new JLabel("YOU LOSE");
        youLose.setHorizontalAlignment(SwingConstants.CENTER);
        youLose.setFont(new Font("Arial", Font.PLAIN, 40));
        lose.add(youLose, BorderLayout.CENTER);
        JButton oK = new JButton("OK");
        oK.addActionListener(e -> lose.setVisible(false));
        lose.add(oK, BorderLayout.SOUTH);
        lose.setVisible(true);
    }

    // action listener for when a cell is clicked. ensures first cell clicked is not a mine by moving the mine if it was
    // originally assigned as a mine
    public ActionListener AL = actionEvent -> {
        clicks++;
        int numChanged = 0;
        cell currCell = (cell) actionEvent.getSource();
        if (clicks == 1){
            if (currCell.isMine){
                for (int i = 0; i < width; i++){
                    for (int j = 0; j < height; j++){
                        if (!x.get(i).get(j).isMine && numChanged == 0 && x.get(i).get(j) != currCell){
                            x.get(i).get(j).setMine();
                            numChanged++;
                        }
                    }
                }
                currCell.isMine = false;
            }
        }
        // if mine is clicked, call explode
        if (currCell.isMine){
            explode();
        }
        // else call the dfs
        else {
            findMines(currCell);
        }
    };
    public board(){
        x = new ArrayList<>();
        setSize(500, 500);

    }

}
