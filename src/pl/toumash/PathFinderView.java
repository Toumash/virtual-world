package pl.toumash;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;


class PathFinderView extends Canvas implements Runnable, MouseListener {
    private final JFrame jFrame;
    private int height = 520;
    private int width = 520;


    public PathFinderView(JFrame frame, int width, int height) {
        this.jFrame = frame;

        this.width = width;
        this.height = height;
        init();
    }

    void showDialog(String message, String caption, int messageType) {
        JOptionPane.showMessageDialog(jFrame, message, caption, messageType);
    }


    @Override
    public void run() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
            bs = getBufferStrategy();
        }
        // nowa koncepcja, rysujemy dopiero po kliknieciu, będzie łatwiej oraz bez wątków
        while (true) {
            Graphics2D g2d = (Graphics2D) bs.getDrawGraphics();

            render(g2d);
            //g2d.setColor(Color.ORANGE);
            //g2d.drawString(String.valueOf(lastMS), 10, 20);

            bs.show();
            //try { Thread.sleep(125);} catch (Exception ignored) { }
        }
    }
    private void renderNode(Graphics2D g) {
       /* g.setColor(Color.BLACK);
        g.fillRect((int) (node.getX() * nodeWidth), (int) (node.getY() * nodeHeight), (int) nodeWidth, (int) nodeHeight);

   g.setColor(Color.DARK_GRAY);

        g.fillRect((int) (node.getX() * nodeWidth + 1), (int) (node.getY() * nodeHeight + 1), (int) (nodeWidth - 1), (int) (nodeHeight - 1));*/
    }

    public void init() {
        requestFocus();
        this.addMouseListener(this);
        setUpMenu(jFrame);
    }

    private void setUpMenu(JFrame frame) {
        JMenuBar bar = new JMenuBar();
        bar.setBounds(0, 0, frame.getWidth(), 25);
        JMenu file = new JMenu("File");
        bar.add(file);


        JMenu options = new JMenu("Options");
        bar.add(options);


        JMenu exit = new JMenu("exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        bar.add(options);
        frame.setJMenuBar(bar);
    }

    void render(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.width, this.height);

//        for (int y = 0; y < map.getHeight(); y++) {
//            for (int x = 0; x < map.getWidth(); x++) {
//                renderNode(g, map.getNode(x, y));
//            }
//        }
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
    }
}
