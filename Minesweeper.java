package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.TimerTask;


public class Minesweeper extends JFrame {

    private static boolean running; // boolean flag to mark if game is running
    public static void setRunning(boolean x) {running = x;}
    public board gameBoard = new board();
    public JLabel time = new JLabel("0:00");
    java.util.Timer timer = new java.util.Timer();
    class RunTimer extends TimerTask {
        public void run(){
            // if game is running, increment time and set label with correct format
            if (running){
                t++;
                if (t < 60) {
                    if (t < 10){
                        time.setText("0:0" + t);
                    }
                    else {
                        time.setText("0:" + t);
                    }
                }
                else{
                    if (t % 60 < 10){
                        time.setText((t / 60) + ":0" + (t % 60));
                    }
                    else {
                        time.setText((t / 60) + ":" + (t % 60));
                    }
                }
                timer.schedule(new RunTimer(), 1000);
            }
            // if game is not running reset timer
            else {
                t = 0;
                cancel();
            }
        }
    }
    public void setupMenu(JPanel m){

        // create buttons and label for number of mines
        JButton start, settings, quit, help;
        start = new JButton("Start");
        settings = new JButton("Settings");
        quit = new JButton("Quit");
        help = new JButton("Help");
        JLabel mines = new JLabel();
        m.add(time);
        m.add(start);
        m.add(settings);
        m.add(quit);
        m.add(help);
        m.add(mines);

        // create the jdialog for the help button
        JDialog h = new JDialog();
        JPanel helpPanel = new JPanel();
        h.setSize(300, 250);
        h.add(helpPanel);
        help.addActionListener(actionEvent -> h.setVisible(true));
        JLabel htp = new JLabel("How to Play:");
        helpPanel.add(htp);
        JTextArea instructions = new JTextArea("Press the Settings button to select\n" +
                "a level of difficulty, or make a custom game.\n" +
                "To create a custom game, enter dimensions\n" +
                "and number of mines before selecting custom. \n" +
                "Once ready, press the Start button. Clicking\n" +
                "on a tile will tell you how many mines are\n" +
                "around it. Select all tiles that are not mines\n" +
                "to win. If you click on a mine, you will lose.");
        instructions.setEditable(false);
        instructions.setSize(250, 200);
        instructions.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        helpPanel.add(instructions);
        JButton gotit = new JButton("Got it!");
        helpPanel.add(gotit);
        gotit.addActionListener(actionEvent -> h.setVisible(false));

        // create jdialog for the settings button
        JDialog opts = new JDialog();
        JPanel settingsPanel = new JPanel();
        opts.setSize(300,250);
        opts.add(settingsPanel);
        settings.addActionListener(actionEvent -> opts.setVisible(true));
        settingsPanel.setLayout(new BorderLayout());
        JLabel size = new JLabel("Set Size");
        settingsPanel.add(size, BorderLayout.NORTH);
        JLabel errors = new JLabel();

        // add radio buttons and action listeners for each option
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(4,2));
        ButtonGroup bg = new ButtonGroup();
        JRadioButton beginner, intermediate, expert, custom;
        beginner = new JRadioButton("Beginner:");
        beginner.addActionListener(actionEvent -> {
            gameBoard.setHeight(4);
            gameBoard.setWidth(4);
            gameBoard.setNumMines(4);
        });
        intermediate = new JRadioButton("Intermediate:");
        intermediate.addActionListener(actionEvent -> {
            gameBoard.setHeight(8);
            gameBoard.setWidth(8);
            gameBoard.setNumMines(15);
        });
        expert = new JRadioButton("Expert:", true);
        expert.addActionListener(actionEvent -> {
            gameBoard.setHeight(12);
            gameBoard.setWidth(12);
            gameBoard.setNumMines(40);
        });
        custom = new JRadioButton("Custom:");
        bg.add(beginner);
        bg.add(intermediate);
        bg.add(expert);
        bg.add(custom);

        // display the dimensions and number of mines for beginner level
        JPanel begDim = new JPanel();
        JTextField begr = new JTextField("4");
        JLabel begx = new JLabel("x");
        JTextField begc = new JTextField("4");
        JLabel begMines = new JLabel("Mines:");
        JTextField begMineNum = new JTextField("4");
        begr.setEditable(false);
        begc.setEditable(false);
        begMineNum.setEditable(false);
        begDim.add(begr);
        begDim.add(begx);
        begDim.add(begc);
        begDim.add(begMines);
        begDim.add(begMineNum);
        buttons.add(beginner);
        buttons.add(begDim);
        // display the dimensions and number of mines for intermediate level
        JPanel intDim = new JPanel();
        JTextField intr = new JTextField("8");
        JLabel intx = new JLabel("x");
        JTextField intc = new JTextField("8");
        JLabel intMines = new JLabel("Mines:");
        JTextField intMineNum = new JTextField("15");
        intr.setEditable(false);
        intc.setEditable(false);
        intMineNum.setEditable(false);
        intDim.add(intr);
        intDim.add(intx);
        intDim.add(intc);
        intDim.add(intMines);
        intDim.add(intMineNum);
        buttons.add(intermediate);
        buttons.add(intDim);
        // display the dimensions and number of mines for expert level
        JPanel expDim = new JPanel();
        JTextField expr = new JTextField("12");
        JLabel expx = new JLabel("x");
        JTextField expc = new JTextField("12");
        JLabel expMines = new JLabel("Mines:");
        JTextField expMineNum = new JTextField("40");
        expr.setEditable(false);
        expc.setEditable(false);
        expMineNum.setEditable(false);
        expDim.add(expr);
        expDim.add(expx);
        expDim.add(expc);
        expDim.add(expMines);
        expDim.add(expMineNum);
        buttons.add(expert);
        buttons.add(expDim);
        // add text fields for user to input dimensions and number of mines for custom difficulty
        JPanel cusDim = new JPanel();
        JTextField cusr = new JTextField();
        cusr.setPreferredSize(new Dimension(20,20));
        JLabel cusx = new JLabel("x");
        JTextField cusc = new JTextField();
        cusc.setPreferredSize(new Dimension(20,20));
        JLabel cusMines = new JLabel("Mines:");
        JTextField cusMineNum = new JTextField();
        cusMineNum.setPreferredSize(new Dimension(20,20));
        cusDim.add(cusr);
        cusDim.add(cusx);
        cusDim.add(cusc);
        cusDim.add(cusMines);
        cusDim.add(cusMineNum);
        buttons.add(custom);
        buttons.add(cusDim);
        // add action listener for custom button
        custom.addActionListener(actionEvent -> {
            String wid = cusr.getText();
            String hig = cusc.getText();
            String min = cusMineNum.getText();
            int row = Integer.parseInt(wid);
            int col = Integer.parseInt(hig);
            int num = Integer.parseInt(min);
            // input validation
            if (row == col) {
                if (row < 13 && row > 2) {

                    if (num <= ((row * col)/2) && num > 1) {
                        gameBoard.setNumMines(num);
                        gameBoard.setWidth(row);
                        gameBoard.setHeight(col);
                        errors.setText("");
                    }
                    else {
                        errors.setText("Invalid number of mines.");
                    }
                }
                else if (row > 12) { errors.setText("Max size of 12"); }
                else { errors.setText("Min size of 3"); }
            }
            else{
                errors.setText("Dimensions must be the same.");

            }
            repaint();
        });
        // add objects to the settings panel
        settingsPanel.add(buttons);
        JPanel bottom = new JPanel();
        JButton ok = new JButton("OK");
        bottom.add(ok, BorderLayout.SOUTH);
        bottom.add(errors, BorderLayout.NORTH);
        settingsPanel.add(bottom, BorderLayout.SOUTH);
        ok.addActionListener(actionEvent -> {
            if (errors.getText().equals("")) {
                opts.setVisible(false);
            }
        });

        // start new game, set running to true and reset values to 0
        start.addActionListener(actionEvent -> {
            gameBoard.resetBoard();
            gameBoard.fillBoard();
            gameBoard.setVisible(true);
            gameBoard.setNumVisited(0);
            gameBoard.setClicks(0);
            mines.setText(String.valueOf(gameBoard.getNumMines()));
            setRunning(true);
            time.setText("0:00");
            timer.schedule(new RunTimer(), 1000);

            repaint();
        });

        // quit program
        quit.addActionListener(e -> System.exit(0));

    }
    public int t = 0;


    Minesweeper(){

        // add menu and game board
        JPanel menu = new JPanel();
        setupMenu(menu);
        Container c = getContentPane();
        c.add(menu, BorderLayout.NORTH);
        c.add(gameBoard, BorderLayout.CENTER);

        setVisible(true);
        setSize(550, 550);
    }

    public static void main(String[] args) {
        Minesweeper game = new Minesweeper();
        game.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });
    }
}
