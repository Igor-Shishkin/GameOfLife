package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GameWindow extends JFrame {

    private int gridWidth = 50;
    private int gridHeight = 37;
    private int cellWidth = 15;
    private int cellHeight = 15;


    private final JPanel[][] cells;
    public GameWindow() throws HeadlessException {

        setGridAndCellSize();



        this.setLayout(new GridLayout(gridHeight,gridWidth, 2,2));
        cells = new JPanel[gridHeight][gridWidth];

        fillGridWithCells();

        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("CHECK YOUR KNOWLEDGE");
        this.setVisible(true);
    }

    private void setGridAndCellSize() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src\\main\\resources\\config.properties")) {
            properties.load(fis);
            gridWidth = Integer.parseInt(properties.getProperty("grid.width"));
            gridHeight = Integer.parseInt(properties.getProperty("grid.height"));
            cellWidth = Integer.parseInt(properties.getProperty("cell.width"));
            cellHeight = Integer.parseInt(properties.getProperty("cell.height"));
        } catch (IOException e) {
            System.err.println("Exception: " + e);
        }


    }

    private void fillGridWithCells() {
        for (int row = 0; row < gridHeight; row++) {
            for (int column = 0; column < gridWidth; column++) {
                final JPanel cell = new JPanel();
                cell.setPreferredSize(new Dimension(cellWidth, cellHeight));
                cell.setBackground(Color.darkGray);
                final int finalRow = row;
                final int finalColumn = column;
                cell.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        cell.setBackground(Color.BLUE);
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
                this.add(cell);
            }
        }
    }
}
