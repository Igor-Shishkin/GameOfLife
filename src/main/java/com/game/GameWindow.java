package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Timer;

public class GameWindow extends JFrame implements ActionListener {

    private int GRID_WIDTH = 50;
    private int GRID_HEIGHT = 37;
    private int CELL_SIZE = 15;
    private int UPDATE_FREQUENCY = 450;
    private JPanel gridPanel;
    private final JButton startButton = new JButton("START");
    private final JButton clearButton = new JButton("CLEAR");
    private final boolean[][] booleanGrid;
    private final JPanel[][] cells;
    private Timer timer;
    public GameWindow() throws HeadlessException {

        setGameProperties();

        this.setLayout(new GridBagLayout());
        cells = new JPanel[GRID_HEIGHT][GRID_WIDTH];
        booleanGrid = new boolean[GRID_HEIGHT][GRID_WIDTH];

        fillGridWithCells();
        setElementsProperties();

        setObjectsIntoFrame();

        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Game Of Life");
        this.setVisible(true);
    }

    private void setObjectsIntoFrame() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        this.add(gridPanel, c);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(clearButton);
        buttonPanel.add(startButton);

        c.gridy = 1;
        this.add(buttonPanel, c);
    }

    private void setElementsProperties() {
        startButton.setBackground(new Color(174, 188, 253));
        startButton.setForeground(new Color(128, 0, 0));
        startButton.addActionListener(this);
        startButton.setFont(new Font(null, Font.BOLD, 20));

        clearButton.setBackground(new Color(174, 188, 253));
        clearButton.setForeground(new Color(15, 199, 1));
        clearButton.addActionListener(this);
        clearButton.setFont(new Font(null, Font.BOLD, 20));

        gridPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
    }

    private void setGameProperties() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src\\main\\resources\\config.properties")) {
            properties.load(fis);
            GRID_WIDTH = Integer.parseInt(properties.getProperty("grid.width"));
            GRID_HEIGHT = Integer.parseInt(properties.getProperty("grid.height"));
            CELL_SIZE = Integer.parseInt(properties.getProperty("cell.size"));
            UPDATE_FREQUENCY = Integer.parseInt(properties.getProperty("update.frequency"));
        } catch (IOException e) {
            System.err.println("Exception: " + e);
        }
    }

    private void fillGridWithCells() {
        gridPanel = new JPanel(new GridLayout(GRID_HEIGHT, GRID_WIDTH, 1,1));

        final Color cellColor = new Color(0xB2FAF0);
        gridPanel.setBackground(Color.GRAY);

        for (int row = 0; row < GRID_HEIGHT; row++) {
            for (int column = 0; column < GRID_WIDTH; column++) {
                final JPanel cell = new JPanel();
                cell.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                cell.setBackground(cellColor);
                final int finalRow = row;
                final int finalColumn = column;
                cell.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        cell.setBackground(Color.GREEN);
                        booleanGrid[finalRow][finalColumn] = !booleanGrid[finalRow][finalColumn];
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                });
                cells[row][column] = cell;
                gridPanel.add(cell);
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new UpdateTimer(cells, booleanGrid), 1000, UPDATE_FREQUENCY);
        }
        if (e.getSource() == clearButton) {
            if (timer != null) {
                timer.cancel();
            }
            final Color cellColor = new Color(0xB2FAF0);
            for (int row = 0; row < GRID_HEIGHT; row++) {
                for (int column = 0; column < GRID_WIDTH; column++) {
                    cells[row][column].setBackground(cellColor);
                    booleanGrid[row][column] = false;
                }
            }
        }
    }
}
