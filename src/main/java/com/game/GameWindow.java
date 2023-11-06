//package com.game;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Properties;
//
//public class GameWindow extends JFrame {
//    private int GRID_WIDTH = 50;
//    private int GRID_HEUGHT = 37;
//    private final int CELL_SIZE;
//    private final JPanel gridPanel;
//    private boolean[][] booleanGrid;
//    private final JPanel[][] cells;
//
//    public GameWindow(boolean[][] booleanGrid, int CELL_SIZE) throws HeadlessException {
//        this.CELL_SIZE = CELL_SIZE;
//        this.GRID_WIDTH = booleanGrid[0].length;
//        this.GRID_HEUGHT = booleanGrid.length;
//
//        gridPanel = new JPanel(new GridLayout(GRID_HEUGHT, GRID_WIDTH, 1, 1));
//
//        setGridAndCellSize();
//
//        this.setLayout(new GridBagLayout());
//        cells = new JPanel[GRID_HEUGHT][GRID_WIDTH];
//        booleanGrid = new boolean[GRID_HEUGHT][GRID_WIDTH];
//
//        fillGridWithCells();
//        setElementsAppearance();
//
//        setObjectsIntoFrame();
//
//        this.pack();
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        this.setLocationRelativeTo(null);
//        this.setTitle("Game Of Life");
//        this.setVisible(true);
//    }
//
//    private void setObjectsIntoFrame() {
//        GridBagConstraints c = new GridBagConstraints();
//
//        this.add(gridPanel, c);
//
//        c.gridy = 1;
//        this.add(startButton, c);
//    }
//
//    private void setElementsAppearance() {
//        startButton.setBackground(new Color(174, 188, 253));
//        startButton.setForeground(new Color(128, 0, 0));
//        startButton.addActionListener(this);
//        startButton.setFont(new Font(null, Font.BOLD, 20));
//
//        gridPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
//    }
//
//    private void setGridAndCellSize() {
//        Properties properties = new Properties();
//        try (FileInputStream fis = new FileInputStream("src\\main\\resources\\config.properties")) {
//            properties.load(fis);
//            GRID_WIDTH = Integer.parseInt(properties.getProperty("grid.width"));
//            GRID_HEUGHT = Integer.parseInt(properties.getProperty("grid.height"));
//            CELL_WIDTH = Integer.parseInt(properties.getProperty("cell.width"));
//            CELL_HEIGHT = Integer.parseInt(properties.getProperty("cell.height"));
//        } catch (IOException e) {
//            System.err.println("Exception: " + e);
//        }
//
//
//    }
//
//    private void fillGridWithCells() {
//        final Color cellColor = new Color(0xB2FAF0);
//        gridPanel.setBackground(Color.GRAY);
//
//        for (int row = 0; row < GRID_HEUGHT; row++) {
//            for (int column = 0; column < GRID_WIDTH; column++) {
//                final JPanel cell = new JPanel();
//                cell.setPreferredSize(new Dimension(CELL_WIDTH, CELL_HEIGHT));
//                cell.setBackground(cellColor);
//                cells[row][column] = cell;
//                gridPanel.add(cell);
//            }
//        }
//    }
//
//}
//}
