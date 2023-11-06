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

public class PointLivingCellsWindow extends JFrame implements ActionListener {

    private int GRID_WIDTH = 50;
    private int GRID_HEUGHT = 37;
    private int CELL_SIZE = 15;
    private final JPanel gridPanel = new JPanel(new GridLayout(GRID_HEUGHT, GRID_WIDTH, 1,1));
    private final JButton startButton = new JButton("START");
    private boolean[][] booleanGrid;
    private final JPanel[][] cells;
    public PointLivingCellsWindow() throws HeadlessException {

        setGridAndCellSize();

        this.setLayout(new GridBagLayout());
        cells = new JPanel[GRID_HEUGHT][GRID_WIDTH];
        booleanGrid = new boolean[GRID_HEUGHT][GRID_WIDTH];
        Timer timer = new Timer();

        fillGridWithCells();
        setElementsAppearance();

        setObjectsIntoFrame();
//        setTimerForChanges();

        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Game Of Life");
        this.setVisible(true);
    }

//    private void setTimerForChanges() {
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new ChangeForLifeTimer(cells, booleanGrid), 3000, 200);
//    }

    private void setObjectsIntoFrame() {
        GridBagConstraints c = new GridBagConstraints();

        this.add(gridPanel, c);

        c.gridy = 1;
        this.add(startButton, c);
    }

    private void setElementsAppearance() {
        startButton.setBackground(new Color(174, 188, 253));
        startButton.setForeground(new Color(128, 0, 0));
        startButton.addActionListener(this);
        startButton.setFont(new Font(null, Font.BOLD, 20));

        gridPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
    }

    private void setGridAndCellSize() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src\\main\\resources\\config.properties")) {
            properties.load(fis);
            GRID_WIDTH = Integer.parseInt(properties.getProperty("grid.width"));
            GRID_HEUGHT = Integer.parseInt(properties.getProperty("grid.height"));
            CELL_SIZE = Integer.parseInt(properties.getProperty("cell.size"));
        } catch (IOException e) {
            System.err.println("Exception: " + e);
        }


    }

    private void fillGridWithCells() {
        final Color cellColor = new Color(0xB2FAF0);
        gridPanel.setBackground(Color.GRAY);

        for (int row = 0; row < GRID_HEUGHT; row++) {
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
                        System.out.println("row: " + finalRow + ", column: " + finalColumn);
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
//            new GameWindow(booleanGrid, CELL_SIZE);
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new ChangeForLifeTimer(cells, booleanGrid), 3000, 500);


        }
    }
}
