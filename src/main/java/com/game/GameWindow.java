package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameWindow extends JFrame {

    private static final int width = 20;
    private static final int height = 20;
    private final static JPanel[][] cells = new JPanel[width][height];
    public GameWindow() throws HeadlessException {
        this.setLayout(new GridLayout(width,height,3,3));

        fillGridWithCells();

        cells[0][3].setBackground(Color.GREEN);

        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("CHECK YOUR KNOWLEDGE");
        this.setVisible(true);
    }

    private void fillGridWithCells() {
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                final JPanel cell = new JPanel();
                cell.setPreferredSize(new Dimension(20,20));
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
