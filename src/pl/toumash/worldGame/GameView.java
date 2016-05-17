package pl.toumash.worldgame;


import pl.toumash.worldgame.creature.CreaturesFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


class GameView extends JPanel implements KeyListener {
    int cursorX, cursorY;
    private GameWorld gameGameWorld;
    private JFrame jFrame;
    private JPopupMenu jPopupMenu = new JPopupMenu("Spawn Creature");
    private int counter = 0;

    public GameView() {
        gameGameWorld = new GameWorld(20, 20);
        gameGameWorld.randominit();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new GameView().display());
    }

    public double getScaleX() {
        return getWidth() / gameGameWorld.getWidth();
    }

    public double getScaleY() {
        return getHeight() / gameGameWorld.getHeight();
    }

    void showDialog(String message, String caption, int messageType) {
        JOptionPane.showMessageDialog(jFrame, message, caption, messageType);
    }

    private void setUpMenu(JFrame frame) {
        JMenuBar bar = new JMenuBar();
        bar.setBounds(0, 0, frame.getWidth(), 25);
        JMenu file = new JMenu("File");
        bar.add(file);

        JMenu options = new JMenu("Options");
        bar.add(options);

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> System.exit(0));
        file.add(exit);
        frame.setJMenuBar(bar);
    }

    private void setUpPopUpMenu() {
        for (CreaturesFactory.Creatures creature : CreaturesFactory.Creatures.values()) {
            JMenuItem item = new JMenuItem(creature.name());
            item.addActionListener(e -> {
                int x = (int) (cursorX / getScaleX());
                int y = (int) (cursorY / getScaleY());

                if (!gameGameWorld.isOccupied(x, y)) {
                    gameGameWorld.spawn(CreaturesFactory.create(gameGameWorld, creature, x, y));
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(0, 0, getWidth(), getHeight());

        gameGameWorld.draw(g, getScaleX(), getScaleY());
        g.drawString(String.valueOf(counter++), 5, 10);
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

        gameGameWorld.playerMove(dir);
        gameGameWorld.update();
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
