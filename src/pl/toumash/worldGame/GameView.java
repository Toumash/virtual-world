package pl.toumash.worldgame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


class GameView extends JPanel {
    private World world;
    private JFrame jFrame;
    private JPopupMenu jPopupMenu = new JPopupMenu("troll");

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new GameView().display());
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
        JMenuItem item = new JMenuItem("Add Wolf");
        item.addActionListener(e -> System.out.println("xd")/*world.spawn()*/);
        this.jPopupMenu.add(item);
        //this.jPopupMenu.add()
        this.jFrame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger())
                    jPopupMenu.show(e.getComponent(), e.getX(), e.getY());

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
        g.setColor(Color.red);
        g.drawRect(0, 0, 20, 20);
    }

    public void start() {
        world = new World(20, 20);
        world.randominit();
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
    }

}