package pl.toumash.worldgame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


class GameView extends JPanel implements MouseListener {
    private World world;
    private JFrame jFrame;

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

        JMenu exit = new JMenu("exit");
        exit.addActionListener(e -> System.exit(0));
        bar.add(options);
        frame.setJMenuBar(bar);
    }

    void render(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.red);
        g.drawRect(0,0,20,20);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //  e.getX(), e.getY(;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void start() {
        this.world = new World(20, 20);
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

        addMouseListener(this);
        setUpMenu(this.jFrame);
    }
}
