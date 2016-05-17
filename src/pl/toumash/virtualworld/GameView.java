package pl.toumash.virtualworld;


import pl.toumash.virtualworld.creature.CreaturesFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;


class GameView extends JPanel implements KeyListener {
    int cursorX, cursorY;
    private GameWorld gameWorld;
    private JFrame jFrame;
    private JPopupMenu jPopupMenu = new JPopupMenu("Spawn Creature");

    public GameView() {
        gameWorld = new GameWorld(20, 20);
        gameWorld.randominit();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new GameView().display());
    }

    public double getScaleX() {
        return (getWidth() - getEventsWidth()) / gameWorld.getWidth();
    }

    public double getScaleY() {
        return getHeight() / gameWorld.getHeight();
    }

    void showDialog(String message, String caption, int messageType) {
        JOptionPane.showMessageDialog(jFrame, message, caption, messageType);
    }

    private void setUpMenu(JFrame frame) {
        JMenuBar bar = new JMenuBar();
        bar.setBounds(0, 0, frame.getWidth(), 25);

        JMenu gameMenu = new JMenu("Game");
        bar.add(gameMenu);
        JMenuItem save = new JMenuItem("Save game");
        save.addActionListener(e -> saveGame());
        JMenuItem load = new JMenuItem("Load game");
        load.addActionListener(e -> loadGame());
        gameMenu.add(save);
        gameMenu.add(load);

        JMenu options = new JMenu("Options");
        bar.add(options);

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> {
            showDialog("Dont leave me!", ":C", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        });
        gameMenu.add(exit);
        frame.setJMenuBar(bar);
    }

    private void saveGame() {
        try {
            File saveFile = new File("game.save");
            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(saveFile));
            out.writeObject(gameWorld);
            out.close();
            showDialog("gamestate saved", "OK", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            showDialog("Error writing save file", "ERROR", JOptionPane.ERROR_MESSAGE);
            showDialog(e.getMessage(), "ERROR", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void loadGame() {
        try {
            File saveFile = new File("game.save");
            if (!saveFile.exists()) {
                showDialog("I cant find your saveFile", "Error loading gamestate", JOptionPane.WARNING_MESSAGE);
            }
            ObjectInputStream out = new ObjectInputStream(new FileInputStream(saveFile));
            GameWorld g = (GameWorld) out.readObject();
            out.close();
            showDialog("Gamestate loaded <OK!>", "OK", JOptionPane.INFORMATION_MESSAGE);
            this.gameWorld = g;
            repaint();
        } catch (IOException e) {
            showDialog("Error writing save file", "ERROR", JOptionPane.PLAIN_MESSAGE);
            showDialog(e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException e) {
            showDialog("De-Serializing Error", "ERROR", JOptionPane.ERROR_MESSAGE);
            showDialog(e.getMessage(), "ERROR", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void setUpPopUpMenu() {
        for (CreaturesFactory.Creatures creature : CreaturesFactory.Creatures.values()) {
            JMenuItem item = new JMenuItem(creature.name());
            item.addActionListener(e -> {
                int x = (int) (cursorX / getScaleX());
                int y = (int) (cursorY / getScaleY());

                if (!gameWorld.isOccupied(x, y)) {
                    gameWorld.spawn(CreaturesFactory.create(gameWorld, creature, x, y));
                    System.out.println("spawning " + creature.name() + "(" + x + "," + y + ")");
                    GameView.this.repaint();
                } else {
                    showDialog("Choose a free area", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            });
            jPopupMenu.add(item);
        }

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    cursorX = e.getX();
                    cursorY = e.getY();
                    jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    public int getEventsWidth() {
        return (int) (getWidth() * 0.3);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.fillRect(0, 0, getWidth(), getHeight());

        gameWorld.draw(g, getScaleX(), getScaleY());

        g.setColor(Color.LIGHT_GRAY);
        // paint events
        int x = getWidth() - getEventsWidth();
        int y = 0;
        for (String s : gameWorld.getEvents()) {
            g.drawString(s, x, y += 10);
        }
    }

    public void display() {
        jFrame = new JFrame("Game of Life <Toumash Dłuski 160741>");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);

        jFrame.setResizable(true);
        jFrame.setSize(800, 600);
        jFrame.setPreferredSize(new Dimension(800, 600));
        jFrame.setMinimumSize(new Dimension(400, 400));

        jFrame.add(this);
        jFrame.setVisible(true);

        setUpMenu(this.jFrame);
        setUpPopUpMenu();
        jFrame.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Direction dir = null;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                dir = Direction.up;
                break;
            case KeyEvent.VK_S:
                dir = Direction.down;
                break;
            case KeyEvent.VK_A:
                dir = Direction.left;
                break;
            case KeyEvent.VK_D:
                dir = Direction.right;
                break;
        }

        gameWorld.playerMove(dir);
        gameWorld.update();
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
