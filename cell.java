package com.company;
import javax.swing.*;
import java.awt.*;

public class cell extends JButton {

    boolean isMine = false, visited = false;
    int X, Y, timesCalled = 0;
    Color[] colors = {Color.GREEN, Color.CYAN, Color.RED, Color.MAGENTA, Color.ORANGE, Color.YELLOW, Color.BLUE, Color.PINK};

    public void setX(int num){ X = num; }
    public void setY(int num){ Y = num; }
    public void setMine(){ isMine = true; }

    // marks cell as visited for dfs and sets the color corresponding to number of mines around it
    public void setVisited() {
        timesCalled++;
        visited = true;
        if (!getText().equals("*") && !getText().equals("") && Integer.parseInt(getText()) > 0) {
            setBackground(colors[Integer.parseInt(getText()) - 1]);
        }
    }
    // displays the mine
    public void showMine(){
        if (isMine) {
            setText("*");
            setBackground(Color.BLACK);
        }
    }

    public cell(){
        setVisible(true);
    }

}
