package arkanoidv1.pkg1;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.border.LineBorder;

enum STATE {
    MENU, GAME, RESUME, CONTINUE, LEVEL1, LEVEL2, LEVEL3, LEVEL4, LEVEL5, SCORE
};

class pConst {

    public final static int WIDTH = 20;
    public final static int GRID_WIDTH = 50;
    public final static int GRID_HEIGHT = 30;
    public final static int DELAY = 100;

}

abstract class Shape {

    protected int x;
    protected int y;
    protected Color c;

    public Shape(int x, int y, Color c) {
        this.x = x * pConst.WIDTH;
        this.y = y * pConst.WIDTH;
        this.c = c;
    }

    public int getX() {
        return x / pConst.WIDTH;
    }

    public int getY() {
        return y / pConst.WIDTH;
    }

    public abstract void paint(Graphics g);

}

class ball extends Shape {

    public ball(int x, int y, Color c) {
        super(x, y, c);
    }

    public void paint(Graphics g) {
        g.setColor(c);
        g.fillOval(x, y, pConst.WIDTH, pConst.WIDTH);

    }
}

class shot extends Shape {

    private int w;
    private int h;

    public shot(int x, int y, Color c, int w, int h) {
        super(x, y, c);
        this.w = w;
        this.h = h;
    }

    public void paint(Graphics g) {
        g.setColor(c);
        g.fillOval(x, y, w, h);

    }
}

class paddle extends Shape {

    private int w;
    private int h;

    public paddle(int x, int y, Color c) {
        super(x, y, c);
    }

    public void paint(Graphics g) {
        if (x < 0) {
            x = 0;
        }
        if (x > 847) {
            x = 847;
        }
        g.setColor(c);
        g.fillRect(x, y, 150, 20);

    }
}

class Rec extends Shape {

    private int w;
    private int h;

    public Rec(int w, int h, Color c, int x, int y) {
        super(x, y, c);
        this.w = w;
        this.h = h;

    }

    public void paint(Graphics g) {
        g.setColor(c);
        g.fillRect(x, y, w, h);

    }
}

class gameFrame extends JPanel {

    private Rectangle playbutton;
    private Rectangle resumebutton;
    private Rectangle scorebutton;
    private Rectangle quitbutton;
    private Rectangle level1button;
    private Rectangle level2button;
    private Rectangle level3button;
    private Rectangle level4button;
    private Rectangle level5button;
    private ImageIcon image = new ImageIcon("Arkanoid3.png");
    private JLabel label = new JLabel(image);
    private ImageIcon m = new ImageIcon("spa.png");
    private JLabel im = new JLabel(m);
    private ImageIcon image2 = new ImageIcon("space2.jpg");
    private JLabel label2 = new JLabel(image2);
    private ImageIcon image3 = new ImageIcon("spaceark.jpg");
    private JLabel label3 = new JLabel(image3);
    private ImageIcon image4 = new ImageIcon("breakout_bg.png");
    private JLabel label4 = new JLabel(image4);
    private ImageIcon image5 = new ImageIcon("boss.jpg");
    private JLabel label5 = new JLabel(image5);
    private ImageIcon image6 = new ImageIcon("space3.png");
    private JLabel label6 = new JLabel(image6);
    private JTextArea scorearea = new JTextArea("0", 5, 2);
    private ball b;
    private ball b2;
    private paddle r1;
    ArrayList<Rec> Recs = new ArrayList();
    ArrayList<shot> balls = new ArrayList();
   

    public void addball(shot b) {
        if (b != null) {
            balls.add(b);
        }
    }

    public void removeball(shot b) {
        if (b != null) {
            balls.remove(b);
        }
    }

    public void addRec(Rec r) {
        if (r != null) {
            Recs.add(r);
        }
    }

    public void removeRec(Rec r) {
        if (r != null) {
            Recs.remove(r);
        }
    }

    public void clearRec() {
        Recs.clear();

    }

    public gameFrame(ball b, paddle r1) {
        this.b = b;
        this.r1 = r1;
        add(label);
    }

    public gameFrame(ball b, paddle r1, ball b2) {
        this.b = b;
        this.r1 = r1;
        this.b2 = b2;
        add(label);
    }

    public void paint(Graphics g) {
        STATE state = Arkanoidv11.getGameState();
        if (state == STATE.MENU) {
            Recs.clear();
            remove(im);
            remove(label2);
            remove(label3);
            remove(label4);
            remove(label5);
            remove(scorearea);
            setLayout(new FlowLayout());
            setBackground(Color.gray);
            add(label);
            super.paint(g);
            playbutton = new Rectangle(450, 250, 100, 50);
            scorebutton = new Rectangle(450, 325, 100, 50);
            quitbutton = new Rectangle(450, 400, 100, 50);
            Graphics2D g2d = (Graphics2D) g;
//            Font f1 = new Font("arial", Font.BOLD, 50); //create font of game title "Arkanoid"
//            g.setFont(f1);
//            g.setColor(Color.white);
//            g.drawString("Arkanoid", 185, 80);
            Font f2 = new Font("arial", Font.BOLD, 20); //create font of the play,quit buttons
            g.setFont(f2);
            g.setColor(Color.white);
            g.drawString("Play", playbutton.x + 30, playbutton.y + 32);
            g.drawString("Score", scorebutton.x + 22, scorebutton.y + 32);
            g.drawString("Quit", quitbutton.x + 30, quitbutton.y + 32);
            g2d.draw(playbutton);
            g2d.draw(scorebutton);
            g2d.draw(quitbutton);
        } else if (state == STATE.GAME) {
            setLayout(new FlowLayout());
            super.paint(g);
            level1button = new Rectangle(150, 300, 100, 50);
            level2button = new Rectangle(300, 300, 100, 50);
            level3button = new Rectangle(450, 300, 100, 50);
            level4button = new Rectangle(600, 300, 100, 50);
            level5button = new Rectangle(750, 300, 100, 50);
            Graphics2D g2d = (Graphics2D) g;
//            Font f1 = new Font("arial", Font.BOLD, 50); //create font of game title "Arkanoid"
//            g.setFont(f1);
//            g.setColor(Color.white);
//            g.drawString("Arkanoid", 185, 80);
            Font f2 = new Font("arial", Font.BOLD, 20); //create font of the play,quit buttons
            g.setFont(f2);
            g.setColor(Color.white);
            g.drawString("Level 1", level1button.x + 17, level1button.y + 32);
            g.drawString("Level 2", level2button.x + 17, level2button.y + 32);
            g.drawString("Level 3", level3button.x + 17, level3button.y + 32);
            g.drawString("Level 4", level4button.x + 17, level4button.y + 32);
            g.drawString("Level 5", level5button.x + 17, level5button.y + 32);
            g2d.draw(level1button);
            g2d.draw(level2button);
            g2d.draw(level3button);
            g2d.draw(level4button);
            g2d.draw(level5button);
        } else if (state == STATE.LEVEL1) {
            setLayout(new FlowLayout());
            remove(label);
            add(im);
            super.paint(g);
            b.paint(g);
            r1.paint(g);
            for (int i = 0; i < Recs.size(); i++) {
                ((Recs.get(i))).paint(g);
            }
        } else if (state == STATE.LEVEL2) {
            remove(label);
            add(label2);
            setLayout(new FlowLayout());
            super.paint(g);
            b.paint(g);
            r1.paint(g);
            for (int i = 0; i < Recs.size(); i++) {
                ((Recs.get(i))).paint(g);
            }
        } else if (state == STATE.LEVEL3) {
            remove(label);
            add(label3);
            setLayout(new FlowLayout());
            super.paint(g);
            b.paint(g);
            r1.paint(g);
            for (int i = 0; i < Recs.size(); i++) {
                ((Recs.get(i))).paint(g);
            }
        } else if (state == STATE.LEVEL4) {
            remove(label);
            add(label4);
            setLayout(new FlowLayout());
            super.paint(g);
            b.paint(g);
            r1.paint(g);
            for (int i = 0; i < Recs.size(); i++) {
                ((Recs.get(i))).paint(g);
            }
        } else if (state == STATE.LEVEL5) {
            remove(label);
            add(label5);
            setLayout(new FlowLayout());
            super.paint(g);
            b.paint(g);
            try {
                b2.paint(g);
            } catch (NullPointerException e) {

            }
            r1.paint(g);
        } else if (state == STATE.RESUME) {
            remove(scorearea);
            add(label6);
            setLayout(new FlowLayout());
            super.paint(g);
            resumebutton = new Rectangle(450, 250, 100, 50);
            scorebutton = new Rectangle(450, 325, 100, 50);
            quitbutton = new Rectangle(450, 400, 100, 50);
            Graphics2D g2d = (Graphics2D) g;
            Font f2 = new Font("arial", Font.BOLD, 20);
            g.setFont(f2);
            g.setColor(Color.white);
            g.drawString("Resume", resumebutton.x + 13, resumebutton.y + 32);
            g.drawString("Score", scorebutton.x + 22, scorebutton.y + 32);
            g.drawString("Quit", quitbutton.x + 30, quitbutton.y + 32);
            g2d.draw(resumebutton);
            g2d.draw(scorebutton);
            g2d.draw(quitbutton);
        } else if (state == STATE.CONTINUE) {
            setLayout(new FlowLayout());
            super.paint(g);
            b.paint(g);
            try {
                b2.paint(g);
            } catch (Exception e) {

            }
            r1.paint(g);
            for (int i = 0; i < Recs.size(); i++) {
                ((Recs.get(i))).paint(g);
            }
        } else if (state == STATE.SCORE) {
            remove(im);
            remove(label2);
            remove(label3);
            remove(label4);
            remove(label5);
            remove(label6);
            remove(label);
            scorearea.setBorder(new LineBorder(Color.black));
            setLayout(null);
            scorearea.setBounds(250, 125, 500, 400);
            scorearea.setEnabled(false);
            add(scorearea);
            super.paint(g);
            Font f1 = new Font("Jokerman", Font.PLAIN, 50);
            g.setFont(f1);
            g.setColor(Color.white);
            g.drawString("Highest Scores", 315, 80);
        }
    }

    public void setpaddle(paddle r) {
        this.r1 = r;
    }

    public void setball(ball b) {
        this.b = b;
    }

    public void setball2(ball b) {
        this.b2 = b;
    }

    public JTextArea getTextArea() {
        return scorearea;
    }

    public void setTextArea(JTextArea ta) {
        scorearea = ta;
    }
}

public class Arkanoidv11 extends JFrame {

    private ScoreManager sm = new ScoreManager();
    private Score player;
    private ArrayList<Score> playerscores;
    private ArrayList<Rec> squares = new ArrayList();
    private Random rand = new Random();
    private int counter = 20;
    private static STATE gamestate = STATE.MENU;
    boolean menu = true;
    private JTextArea scoresarea = new JTextArea();
    private ArrayList<Rec> row1 = new ArrayList();
    private ArrayList<Rec> row2 = new ArrayList();
    private ArrayList<Rec> row3 = new ArrayList();
    private ArrayList<Rec> row4 = new ArrayList();
    private ArrayList<Rec> col1 = new ArrayList();
    private ArrayList<Rec> col2 = new ArrayList();
    private Rec row5 = new Rec(70, 30, Color.black, pConst.GRID_WIDTH / 2 - 1, 7);
    private JPanel bottom = new JPanel();
    private JLabel sco = new JLabel("Score=");
    private int score;
    private JLabel Score = new JLabel("0");
    private JLabel Live = new JLabel("Lives=");
    private JLabel Life = new JLabel("0");
    private JLabel enemyhealth = new JLabel("Enemy Health=");
    private JLabel ehealth = new JLabel("0");
    private gameFrame game;
    private ball b;
    private ball b2;
    private int ball2disy = 1;
    private int b2y, b2x;
    private int lives = 3;
    private int enemy = 40;
    private paddle rec;
    private int b1x = pConst.GRID_WIDTH / 2;
    private int b1y = pConst.GRID_HEIGHT - 3;
    private int recx = pConst.GRID_WIDTH / 2 - 3;
    private int recy = pConst.GRID_HEIGHT - 2;
    private javax.swing.Timer t;
    private javax.swing.Timer t1;
    private javax.swing.Timer t2;
    private javax.swing.Timer t3;
    private javax.swing.Timer t4;
    private javax.swing.Timer t5;
    private javax.swing.Timer t6;
    private javax.swing.Timer t7;
    private double balldisx = 0;
    private int balldisy = 0;

    public Arkanoidv11() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Arkanoid Returns");
        b = new ball(b1x, b1y, Color.RED);
        rec = new paddle(recx, recy, Color.black);
        Score.setBounds(20, 20, 120, 20);
        bottom.add(sco);
        bottom.add(Score);
        game = new gameFrame(b, rec);
        game.setPreferredSize(new Dimension(pConst.WIDTH * pConst.GRID_WIDTH, pConst.WIDTH * pConst.GRID_HEIGHT));
        b2x = rand.nextInt(pConst.GRID_WIDTH);
        b2y = rand.nextInt(pConst.GRID_HEIGHT);
        b2 = new ball(b2x, b2y, Color.black);
        game.setball(b2);
        this.add(game);
        this.add(bottom, BorderLayout.SOUTH);
        this.pack();
        game.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (gamestate == STATE.MENU) {
                    if (x >= 450 && x <= 550) {
                        if (y <= 300 && y >= 250) {
                            gamestate = STATE.GAME;
                            game.repaint();
                        }
                    }
                    if (x >= 450 && x <= 550) {
                        if (y <= 450 && y >= 400) {
                            System.exit(0);
                        }
                    }
                    if (x >= 450 && x <= 550) {
                        if (y <= 375 && y >= 325) {
                            scoresarea.setText(sm.getHighestScore());
                            game.setTextArea(scoresarea);
                            gamestate = STATE.SCORE;
                            game.repaint();
                        }
                    }
                } else if (gamestate == STATE.GAME) {
                    if (x >= 150 && x <= 250) {
                        if (y <= 350 && y >= 300) {
                            menu = false;
                            gamestate = STATE.LEVEL1;
                            for (int i = 4; i < pConst.GRID_WIDTH - 4; i += 4) {
                                row1.add(new Rec(70, 30, Color.red, i, 1));
                                row2.add(new Rec(70, 30, Color.WHITE, i, 3));
                                row3.add(new Rec(70, 30, Color.BLUE, i, 5));
                                row4.add(new Rec(70, 30, Color.green, i, 7));
                            }
                            for (int i = 0; i < row1.size(); i++) {
                                game.addRec(row1.get(i));
                            }
                            for (int i = 0; i < row4.size(); i++) {
                                game.addRec(row4.get(i));
                            }
                            for (int i = 0; i < row2.size(); i++) {
                                game.addRec(row2.get(i));
                            }
                            for (int i = 0; i < row3.size(); i++) {
                                game.addRec(row3.get(i));
                            }
                            game.repaint();
                            t = new javax.swing.Timer(120, new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    b1x += balldisx;
                                    b1y += balldisy;
                                    b = new ball(b1x, b1y, Color.red);
                                    game.setball(b);
                                    game.repaint();

                                    iscollidewithpaddleorwall();
                                    iscollidewithbricks();

                                    if (b1y == 0) {
                                        balldisy++;
                                    }
                                    if (b1y >= pConst.GRID_HEIGHT) {
                                        JOptionPane.showMessageDialog(null, "Game Over");
                                        menu = true;
                                        String strname = JOptionPane.showInputDialog("Enter your name");
                                        if (strname != null) {
                                            player = new Score(strname, "Level1", score);
                                            sm.addScore(player);
                                            scoresarea.setText(sm.getHighestScore());
                                            game.setTextArea(scoresarea);
                                        }
                                        reset();
                                        Score.setText("0");
                                          row1.clear();
                                            row2.clear();
                                            row3.clear();
                                            row4.clear();
                                        t.stop();
                                        gamestate = STATE.MENU;
                                        game.repaint();
                                    }
                                    if (row1.isEmpty() && row2.isEmpty() && row3.isEmpty() && row4.isEmpty()&&t.isRunning()) {
                                        
                                        JOptionPane.showMessageDialog(null, "Level passed");
                                        menu = true;
                                        String strname = JOptionPane.showInputDialog("Enter your name");
                                        if (strname != null) {
                                            player = new Score(strname, "Level1", score);
                                            sm.addScore(player);
                                            scoresarea.setText(sm.getHighestScore());
                                            game.setTextArea(scoresarea);
                                        }
                                        reset();
                                        Score.setText("0");
                                        t.stop();
                                        gamestate = STATE.MENU;
                                        game.repaint();
                                    }
                                }
                            });
                            t.start();
                        }
                        game.repaint();
                    } else if (x >= 300 && x < 400) {
                        if (y <= 350 && y >= 300) {
                            menu = false;
                            gamestate = STATE.LEVEL2;
                            for (int i = 0; i < counter; i++) {
                                int x1 = rand.nextInt(48);
                                int y1 = rand.nextInt(18);
                                int r = rand.nextInt(255);
                                int g = rand.nextInt(255);
                                int b = rand.nextInt(255);
                                squares.add(new Rec(70, 30, new Color(r, g, b), x1, y1));
                            }
                            for (int i = 0; i < counter; i++) {
                                game.addRec(squares.get(i));

                            }
                            t1 = new javax.swing.Timer(10000, new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    
                                    for (int i = 0; i < counter; i++) {
                                        game.removeRec(squares.get(i));
                                    }
                                    
                                    squares.clear();
                                    for (int i = 0; i < counter; i++) {
                                        int x = rand.nextInt(48);
                                        int y = rand.nextInt(18);
                                        int r = rand.nextInt(255);
                                        int g = rand.nextInt(255);
                                        int b = rand.nextInt(255);
                                        squares.add(new Rec(70, 30, new Color(r, g, b), x, y));
                                    }
                                    for (int i = 0; i < counter; i++) {
                                        game.addRec(squares.get(i));
                                    }
                                   if (squares.isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "Level passed!!");
                                        menu = true;
                                        String strname = JOptionPane.showInputDialog("Enter your name");
                                        if (strname != null) {
                                            player = new Score(strname, "Level2", score);
                                            sm.addScore(player);
                                            scoresarea.setText(sm.getHighestScore());
                                            game.setTextArea(scoresarea);
                                        }
                                        
                                       
                                        Score.setText("0");
                                        t2.stop();
                                        t1.stop();
                                        gamestate = STATE.MENU;
                                        game.repaint();
                                    }
                                    game.repaint();
                                }
                            });
                            t1.start();
                            t2 = new javax.swing.Timer(90, new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    b1x += balldisx;
                                    b1y += balldisy;
                                    b = new ball(b1x, b1y, Color.red);
                                    game.setball(b);
                                    game.repaint();
                                    iscollidewithpaddleorwall();
                                    iscollidewithbricks2();
                                    if (balldisy > 1) {
                                        balldisy = 1;
                                    } else if (balldisy < -1) {
                                        balldisy = -1;
                                    } else if (balldisy == 0 && b1y != pConst.GRID_HEIGHT - 3) {
                                        balldisy = 1;
                                    }
                                    if (b1y == 0) {
                                        balldisy++;
                                    }
                                    if (b1y >= pConst.GRID_HEIGHT) {
                                        JOptionPane.showMessageDialog(null, "Game Over");
                                        menu = true;
                                        String strname = JOptionPane.showInputDialog("Enter your name");
                                        if (strname != null) {
                                            player = new Score(strname, "Level2", score);
                                            sm.addScore(player);
                                            scoresarea.setText(sm.getHighestScore());
                                            game.setTextArea(scoresarea);
                                        }
                                        reset();
                                        
                                        Score.setText("0");
                                        t2.stop();
                                        t1.stop();
                                        gamestate = STATE.MENU;
                                        game.repaint();
                                    }
                                    if (squares.isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "Level passed!!");
                                        menu = true;
                                        String strname = JOptionPane.showInputDialog("Enter your name");
                                        if (strname != null) {
                                            player = new Score(strname, "Level2", score);
                                            sm.addScore(player);
                                            scoresarea.setText(sm.getHighestScore());
                                            game.setTextArea(scoresarea);
                                        }
                                        
                                        Score.setText("0");
                                        t2.stop();
                                        
                                        gamestate = STATE.MENU;
                                        game.repaint();
                                    }

                                }

                            });
                            t2.start();
                        }
                        game.repaint();
                    } else if (x >= 450 && x < 550) {
                        if (y <= 350 && y >= 300) {
                            menu = false;
                            gamestate = STATE.LEVEL3;
                            for (int i = 4; i < pConst.GRID_WIDTH - 2; i += 4) {
                                row1.add(new Rec(70, 30, Color.red, i, 1));
                                row2.add(new Rec(70, 30, Color.WHITE, i, 3));
                                row3.add(new Rec(70, 30, Color.blue, i, 5));

                            }
                            for (int i = 0; i < row1.size(); i++) {
                                game.addRec(row1.get(i));
                            }
                            for (int i = 0; i < row2.size(); i++) {
                                game.addRec(row2.get(i));
                            }
                            for (int i = 0; i < row3.size(); i++) {
                                game.addRec(row3.get(i));

                            }
                            t3 = new javax.swing.Timer(80, new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    b1x += balldisx;
                                    b1y += balldisy;
                                    b = new ball(b1x, b1y, Color.red);
                                    game.setball(b);
                                    game.repaint();

                                    iscollidewithpaddleorwall3();
                                    iscollidewithbricks3();

                                    if (b1y == 0) {
                                        balldisy = 1;
                                    }
                                    if (b1x == 0 && balldisy == 0 || b1x == pConst.GRID_WIDTH && balldisy == 0) {
                                        balldisy = 1;
                                    }
                                    if (balldisy == 0 && b1y < 7) {
                                        balldisy = 1;
                                    }
                                    if (b1y >= pConst.GRID_HEIGHT) {
                                        JOptionPane.showMessageDialog(null, "Game Over");
                                        menu = true;
                                        String strname = JOptionPane.showInputDialog("Enter your name");
                                        if (strname != null) {
                                            player = new Score(strname, "Level3", score);
                                            sm.addScore(player);
                                            scoresarea.setText(sm.getHighestScore());
                                            game.setTextArea(scoresarea);
                                        }
                                        reset();
                                        Score.setText("0");
                                        row1.clear();
                                            row2.clear();
                                            row3.clear();
                                        t3.stop();
                                        t4.stop();
                                        
                                        gamestate = STATE.MENU;
                                        game.repaint();
                                    }
                                    for (int i = 0; i < row1.size(); i++) {
                                        if (row1.get(i).getY() == recy) {
                                            JOptionPane.showMessageDialog(null, "Game Over");
                                            menu = true;
                                            String strname = JOptionPane.showInputDialog("Enter your name");
                                            if (strname != null) {
                                                player = new Score(strname, "Level3", score);
                                                sm.addScore(player);
                                                scoresarea.setText(sm.getHighestScore());
                                                game.setTextArea(scoresarea);
                                            }
                                            reset();
                                            Score.setText("0");
                                            t3.stop();
                                            t4.stop();
                                            row1.clear();
                                            row2.clear();
                                            row3.clear();
                                            gamestate = STATE.MENU;
                                            game.repaint();
                                        }
                                    }
                                    for (int i = 0; i < row2.size(); i++) {
                                        if (row2.get(i).getY() == recy) {
                                            JOptionPane.showMessageDialog(null, "Game Over");
                                            menu = true;
                                            String strname = JOptionPane.showInputDialog("Enter your name");
                                            if (strname != null) {
                                                player = new Score(strname, "Level3", score);
                                                sm.addScore(player);
                                                scoresarea.setText(sm.getHighestScore());
                                                game.setTextArea(scoresarea);
                                            }
                                            reset();
                                            Score.setText("0");
                                            t3.stop();
                                            t4.stop();
                                            row1.clear();
                                            row2.clear();
                                            row3.clear();
                                            gamestate = STATE.MENU;
                                            game.repaint();
                                        }
                                    }
                                    for (int i = 0; i < row3.size(); i++) {
                                        if (row3.get(i).getY() == recy) {
                                            JOptionPane.showMessageDialog(null, "Game Over");
                                            menu = true;
                                            String strname = JOptionPane.showInputDialog("Enter your name");
                                            if (strname != null) {
                                                player = new Score(strname, "Level3", score);
                                                sm.addScore(player);
                                                scoresarea.setText(sm.getHighestScore());
                                                game.setTextArea(scoresarea);
                                            }
                                            reset();
                                            Score.setText("0");
                                            t3.stop();
                                            t4.stop();
                                            row1.clear();
                                            row2.clear();
                                            row3.clear();
                                            gamestate = STATE.MENU;
                                            game.repaint();
                                        }
                                    }
                                    if (row1.isEmpty() && row2.isEmpty() && row3.isEmpty()&&t3.isRunning()) {
                                        JOptionPane.showMessageDialog(null, "Level passed");
                                        menu = true;
                                        String strname = JOptionPane.showInputDialog("Enter your name");
                                        if (strname != null) {
                                            player = new Score(strname, "Level3", score);
                                            sm.addScore(player);
                                            scoresarea.setText(sm.getHighestScore());
                                            game.setTextArea(scoresarea);
                                        }
                                        reset();
                                        Score.setText("0");
                                        t3.stop();
                                        t4.stop();
                                        /*row1.clear();
                                        row2.clear();
                                        row3.clear();*/
                                        gamestate = STATE.MENU;
                                        game.repaint();
                                    }

                                }
                            });
                            t3.start();
                            t4 = new javax.swing.Timer(10000, new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    for (int i = 0; i < row1.size(); i++) {
                                        row1.get(i).y += 30;
                                    }
                                    for (int i = 0; i < row2.size(); i++) {
                                        row2.get(i).y += 30;
                                    }
                                    for (int i = 0; i < row3.size(); i++) {
                                        row3.get(i).y += 30;
                                    }
                                    game.repaint();
                                }
                            });
                            t4.start();
                        }
                        game.repaint();
                    } else if (x >= 600 && x < 700) {
                        if (y <= 350 && y >= 300) {
                            menu = false;
                            gamestate = STATE.LEVEL4;
                            for (int i = 3; i < pConst.GRID_HEIGHT - 10; i += 3) {
                                col1.add(new Rec(70, 30, Color.red, 1, i));
                                col2.add(new Rec(70, 30, Color.blue, pConst.GRID_WIDTH - 4, i));

                            }
                            for (int i = 5; i < pConst.GRID_WIDTH - 5; i += 4) {
                                row1.add(new Rec(70, 30, Color.red, i, 1));

                            }
                            for (int i = 10; i < pConst.GRID_WIDTH - 10; i += 4) {
                                row2.add(new Rec(70, 30, Color.blue, i, 3));
                            }
                            for (int i = 12; i < pConst.GRID_WIDTH - 12; i += 4) {
                                row3.add(new Rec(70, 30, Color.white, i, 5));
                            }
                            for (int i = 0; i < row1.size(); i++) {
                                game.addRec(row1.get(i));

                            }
                            for (int i = 0; i < row2.size(); i++) {
                                game.addRec(row2.get(i));

                            }
                            for (int i = 0; i < row3.size(); i++) {
                                game.addRec(row3.get(i));

                            }
                            for (int i = 0; i < col1.size(); i++) {
                                game.addRec(col1.get(i));

                            }
                            for (int i = 0; i < col2.size(); i++) {
                                game.addRec(col2.get(i));
                            }
                            row5 = new Rec(70, 30, Color.black, pConst.GRID_WIDTH / 2 - 1, 7);
                            game.addRec(row5);
                            t5 = new javax.swing.Timer(60, new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    b1x += balldisx;
                                    b1y += balldisy;
                                    b = new ball(b1x, b1y, Color.red);
                                    game.setball(b);
                                    game.repaint();

                                    iscollidewithpaddleorwall4();
                                    iscollidewithbricks4();

                                    if (b1y == 0) {
                                        balldisy++;
                                    }
                                    if (balldisy == 0 && b1y < 7) {
                                        balldisy = 1;
                                    }
                                    if (b1y >= pConst.GRID_HEIGHT) {
                                        JOptionPane.showMessageDialog(null, "Game Over");
                                        menu = true;
                                        String strname = JOptionPane.showInputDialog("Enter your name");
                                        if (strname != null) {
                                            player = new Score(strname, "Level4", score);
                                            sm.addScore(player);
                                            scoresarea.setText(sm.getHighestScore());
                                            game.setTextArea(scoresarea);
                                        }
                                        reset();
                                        /*row1.clear();
                                        row2.clear();
                                        row3.clear();*/
                                        
                                        Score.setText("0");
                                        t5.stop();
                                        gamestate = STATE.MENU;
                                        game.repaint();
                                    }
                                    if (row1.isEmpty() && row2.isEmpty() && row3.isEmpty() && row5 == null) {
                                        JOptionPane.showMessageDialog(null, "Level passed");
                                        menu = true;
                                        String strname = JOptionPane.showInputDialog("Enter your name");
                                        if (strname != null) {
                                            player = new Score(strname, "Level4", score);
                                            sm.addScore(player);
                                            scoresarea.setText(sm.getHighestScore());
                                            game.setTextArea(scoresarea);
                                        }
                                        reset();
                                        
                                        Score.setText("0");
                                        t5.stop();
                                        gamestate = STATE.MENU;
                                        game.repaint();
                                    }

                                }
                            });
                            t5.start();
                        }
                        game.repaint();
                    } else if (x >= 750 && x <= 850) {
                        if (y <= 350 && y >= 300) {
                            menu = false;
                            gamestate = STATE.LEVEL5;
                            game.setball(b2);
                            //game = new gameFrame(b, rec, b2);
                            bottom.add(enemyhealth);
                            bottom.add(ehealth);
                            bottom.add(sco);
                            bottom.add(Score);
                            bottom.add(Live);
                            bottom.add(Life);
                            Life.setText(lives + "");
                            ehealth.setText("" + enemy);
                            t7 = new javax.swing.Timer(120, new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    b2y += ball2disy;
                                    b2 = new ball(b2x, b2y, Color.black);
                                    game.setball2(b2);
                                    game.repaint();
                                    if (b2y >= pConst.GRID_HEIGHT) {
                                        b2x = rand.nextInt(pConst.GRID_WIDTH);
                                        b2y = rand.nextInt(pConst.GRID_HEIGHT);
                                        b2 = new ball(b2x, b2y, Color.black);
                                        ball2disy = 1;
                                    }
                                    if (b2y == recy && b2x >= recx && b2x <= recx + (150 / pConst.WIDTH)) {
                                        lives--;
                                        Life.setText(lives + "");
                                    }
                                }
                            });
                            t7.start();
                            t6 = new javax.swing.Timer(80, new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    b1x += balldisx;
                                    b1y += balldisy;
                                    b = new ball(b1x, b1y, Color.red);
                                    game.setball(b);
                                    game.repaint();
                                    iscollidewithpaddleorwall();
                                    if (b1x <= (815 / pConst.WIDTH) && b1x >= (630 / pConst.WIDTH) && b1y >= (40 / pConst.WIDTH) && b1y <= (300 / pConst.WIDTH)) {
                                        score += 20;
                                        Score.setText(score + "");
                                        enemy--;
                                        balldisy = -balldisy;
                                        ehealth.setText(enemy + "");
                                    }

                                    if (b1y == 0) {
                                        balldisy++;
                                    }
                                    if (balldisy == 0 && b1y < 7) {
                                        balldisy = 1;
                                    }
                                    if (b1y >= pConst.GRID_HEIGHT || lives == 0) {
                                        JOptionPane.showMessageDialog(null, "Game Over");
                                        menu = true;
                                        String strname = JOptionPane.showInputDialog("Enter your name");
                                        if (strname != null) {
                                            player = new Score(strname, "Level5", score);
                                            sm.addScore(player);
                                            scoresarea.setText(sm.getHighestScore());
                                            game.setTextArea(scoresarea);
                                        }
                                       reset();
                                        Score.setText("0");
                                        t6.stop();
                                        t7.stop();
                                        gamestate = STATE.MENU;
                                        game.repaint();
                                    }
                                    if (enemy == 0) {
                                        JOptionPane.showMessageDialog(null, "Congratulations!!");
                                        menu = true;
                                        String strname = JOptionPane.showInputDialog("Enter your name");
                                        if (strname != null) {
                                            player = new Score(strname, "Level5", score);
                                            sm.addScore(player);
                                            scoresarea.setText(sm.getHighestScore());
                                            game.setTextArea(scoresarea);
                                        }
                                        reset();
                                        Score.setText("0");
                                        t6.stop();
                                        t7.stop();
                                        gamestate = STATE.MENU;
                                        game.repaint();
                                    }

                                }
                            });
                            t6.start();
                        }
                        game.repaint();
                    }
                } else if (gamestate == STATE.RESUME) {
                    if (t != null) {
                        t.stop();
                    }
                    if (t1 != null) {
                        t1.stop();
                    }
                    if (t2 != null) {
                        t2.stop();
                    }
                    if (t3 != null) {
                        t3.stop();
                    }
                    if (t4 != null) {
                        t4.stop();
                    }
                    if (t5 != null) {
                        t5.stop();
                    }
                    if (t6 != null) {
                        t6.stop();
                    }
                    if (t7 != null) {
                        t7.stop();
                    }
                    if (x >= 450 && x <= 550) {
                        if (y <= 450 && y >= 400) {
                            String strname = JOptionPane.showInputDialog("Enter your name");
                            if (strname != null) {
                                player = new Score(strname, "Uncompleted level", 0);
                                sm.addScore(player);
                                scoresarea.setText(sm.getHighestScore());
                                game.setTextArea(scoresarea);
                            }
                            System.exit(0);
                        }
                    }
                    if (x >= 450 && x <= 550) {
                        if (y <= 375 && y >= 325) {
                            scoresarea.setText(sm.getHighestScore());
                            game.setTextArea(scoresarea);
                            gamestate = STATE.SCORE;
                            game.repaint();
                        }
                    }
                    if (x >= 450 && x <= 550) {
                        if (y <= 300 && y >= 250) {
                            if (t != null) {
                                t.start();
                            }
                            if (t1 != null) {
                                t1.start();
                            }
                            if (t2 != null) {
                                t2.start();
                            }
                            if (t3 != null) {
                                t3.start();
                            }
                            if (t4 != null) {
                                t4.start();
                            }
                            if (t5 != null) {
                                t5.start();
                            }
                            if (t6 != null) {
                                t6.start();
                            }
                            if (t7 != null) {
                                t7.start();
                            }
                            gamestate = STATE.CONTINUE;
                            game.repaint();
                        }
                    }
                }
            }

        }
        );
        game.setFocusable(
                true);
        game.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    recx -= 1;
                    rec = new paddle(recx, recy, Color.black);
                    game.setpaddle(rec);
                    game.repaint();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    recx += 1;
                    rec = new paddle(recx, recy, Color.black);
                    game.setpaddle(rec);
                    game.repaint();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    if (menu) {
                        gamestate = STATE.MENU;
                        game.repaint();
                    } else {
                        if (gamestate == STATE.LEVEL1) {
                            t.stop();
                        }
                        if (gamestate == STATE.LEVEL2) {
                            t1.stop();
                            t2.stop();
                        }
                        if (gamestate == STATE.LEVEL3) {
                            t3.stop();
                            t4.stop();
                        }
                        if (gamestate == STATE.LEVEL4) {
                            t5.stop();
                        }
                        if (gamestate == STATE.LEVEL5) {
                            t6.stop();
                            t7.stop();
                        }
                        if (gamestate == STATE.CONTINUE) {
                            if (t != null) {
                                t.stop();
                            }
                            if (t1 != null) {
                                t1.stop();
                            }
                            if (t2 != null) {
                                t2.stop();
                            }
                            if (t3 != null) {
                                t3.stop();
                            }
                            if (t4 != null) {
                                t4.stop();
                            }
                            if (t5 != null) {
                                t5.stop();
                            }
                            if (t6 != null) {
                                t6.stop();
                            }
                            if (t7 != null) {
                                t7.stop();
                            }
                        }
                        gamestate = STATE.RESUME;
                        game.repaint();
                    }
                }
            }
        });
    }

    public void iscollidewithpaddleorwall() {
        if ((b1y == pConst.GRID_HEIGHT - 3) && ((b1x >= recx) && (b1x <= recx + (150 / pConst.WIDTH)))) {
            if (b1x >= recx + (90 / pConst.WIDTH)) {
                balldisx = 1;
            } else if (b1x <= recx + (50 / pConst.WIDTH)) {
                balldisx = -1;
            } else {
                balldisx = 0;
            }
            balldisy -= 1;
        }

        if (b1x == pConst.GRID_WIDTH - 1) {
            balldisx--;
        }
        if (b1x == 0) {
            balldisx++;
        }
        if (b1y == 0) {
            balldisy++;
        }
    }

    public void iscollidewithbricks() {
       iscollidewithbricks3();
        for (int i = 0; i < row4.size(); i++) {
            if (((b1x + 1 >= row4.get(i).getX() && b1x + 1 <= row4.get(i).getX() + 3.5) || (b1x + 1 <= row4.get(i).getX() + 4.5 && b1x >= row4.get(i).getX())) && ((row4.get(i).getY() == b1y - 1) || (row4.get(i).getY() == b1y + 1))) {
                score += 10;
                Score.setText(score + "");
                game.removeRec(row4.get(i));
                if (row4.get(i).getY() == b1y - 1) {
                    balldisy = 1;
                } else if (row4.get(i).getY() == b1y + 1) {
                    balldisy -= 1;
                } else if (row4.get(i).getX() == b1x + 1 && b1y == row4.get(i).getY()) {
                    balldisy = 1;
                } else if (b1x == row4.get(i).getX() + 3.5) {
                    balldisy = 1;
                }
                row4.remove(row4.get(i));

            }
        }
    }

    public void iscollidewithbricks2() {
        for (int i = 0; i < squares.size(); i++) {
            if (((b1x + 1 >= squares.get(i).getX() && b1x + 1 <= squares.get(i).getX() + 3.5) || (b1x + 1 <= squares.get(i).getX() + 4.5 && b1x >= squares.get(i).getX())) && ((squares.get(i).getY() == b1y - 1) || (squares.get(i).getY() == b1y + 1))) {
                score += 10;
                Score.setText(score + "");
                game.removeRec(squares.get(i));
                if (squares.get(i).getY() == b1y - 1) {
                    balldisy = 1;
                } else if (squares.get(i).getY() == b1y + 1) {
                    balldisy = - 1;

                } else if (squares.get(i).getX() == b1x + 1 && b1y == squares.get(i).getY()) {
                    balldisy = 1;
                } else if (b1x == squares.get(i).getX() + 3.5 && b1y == squares.get(i).getY()) {
                    balldisy = 1;
                }
                else if(b1x == squares.get(i).getX() + 3.5 &&(b1y >= squares.get(i).getY()&&b1y+1 <= squares.get(i).getY()+1.5)){
                    balldisx=-balldisx;
                }
                squares.remove(squares.get(i));
                counter--;
            }
        }

    }

    public void iscollidewithpaddleorwall3() {
        if ((b1y == pConst.GRID_HEIGHT - 3) && ((b1x >= recx) && (b1x <= recx + (150 / pConst.WIDTH)))) {
            if (b1x >= recx + (90 / pConst.WIDTH)) {
                balldisx = 1;
            } else if (b1x <= recx + (50 / pConst.WIDTH)) {
                balldisx = -1;
            } else {
                balldisx = 0;
            }
            balldisy -= 1;
        }

        if (b1x == pConst.GRID_WIDTH - 1) {
            balldisx--;
        }
        if (b1x == 0) {
            balldisx++;
        }
        if (b1y == 0) {
            balldisy++;
        }
    }

    public void iscollidewithbricks3() {
        for (int i = 0; i < row1.size(); i++) {
            if ((((b1x + 1 >= row1.get(i).getX() && b1x + 1 <= row1.get(i).getX() + 3.5) || (b1x + 1 <= row1.get(i).getX() + 4.5 && b1x >= row1.get(i).getX())) && ((row1.get(i).getY() == b1y - 1) || (row1.get(i).getY() == b1y + 1)))) {
                score += 10;
                Score.setText(score + "");
                game.removeRec(row1.get(i));
                if (row1.get(i).getY() == b1y - 1) {
                    balldisy = 1;
                } else if (row1.get(i).getY() == b1y + 1) {
                    balldisy = - 1;
                } else if (row1.get(i).getX() == b1x + 1) {
                    balldisy = 1;
                } else if (b1x == row1.get(i).getX() + 3.5 && b1y == row1.get(i).getY() + 1) {
                    balldisy = 1;
                }
                row1.remove(row1.get(i));

            }
        }
        for (int i = 0; i < row2.size(); i++) {
            if (((b1x + 1 >= row2.get(i).getX() && b1x + 1 <= row2.get(i).getX() + 3.5) || (b1x + 1 <= row2.get(i).getX() + 4.5 && b1x >= row2.get(i).getX())) && ((row2.get(i).getY() == b1y - 1) || (row2.get(i).getY() == b1y + 1))) {
                score += 10;
                Score.setText(score + "");
                game.removeRec(row2.get(i));
                if (row2.get(i).getY() == b1y - 1) {
                    balldisy = 1;
                } else if (row2.get(i).getY() == b1y + 1) {
                    balldisy -= 1;
                } else if (row2.get(i).getX() == b1x + 1 && b1y == row2.get(i).getY()) {
                    balldisy = 1;
                } else if (b1x == row2.get(i).getX() + 1.5 && b1y == row2.get(i).getY() + 1) {
                    balldisy = 1;
                }

                row2.remove(row2.get(i));
            }
        }

        for (int i = 0; i < row3.size(); i++) {
            if (((b1x + 1 >= row3.get(i).getX() && b1x + 1 <= row3.get(i).getX() + 3.5) || (b1x + 1 <= row3.get(i).getX() + 4.5 && b1x >= row3.get(i).getX())) && ((row3.get(i).getY() == b1y - 1) || (row3.get(i).getY() == b1y + 1))) {
                score += 10;
                Score.setText(score + "");
                game.removeRec(row3.get(i));
                if (row3.get(i).getY() == b1y - 1) {
                    balldisy = 1;
                } else if (row3.get(i).getY() == b1y + 1) {
                    balldisy -= 1;
                } else if (row3.get(i).getX() == b1x + 1 && b1y == row3.get(i).getY()) {
                    balldisy = 1;
                } else if (b1x == row3.get(i).getX() + 3.5 && b1y == row3.get(i).getY() + 1) {
                    balldisy = 1;
                }

                row3.remove(row3.get(i));

            }
        }

    }

    public void iscollidewithpaddleorwall4() {
        if ((b1y == pConst.GRID_HEIGHT - 3) && ((b1x >= recx) && (b1x <= recx + (150 / pConst.WIDTH)))) {
            if (b1x >= recx + (90 / pConst.WIDTH)) {
                balldisx = 1;
            } else if (b1x <= recx + (50 / pConst.WIDTH)) {
                balldisx = -1;
            } else {
                balldisx = 0;
            }
            balldisy = - 1;
        }

        if (b1x == pConst.GRID_WIDTH - 1) {
            balldisx--;
        }
        if (b1x == 0) {
            balldisx++;
        }
        if (b1y == 0) {
            balldisy++;
        }
    }

    public void iscollidewithbricks4() {
        for (int i = 0; i < col1.size(); i++) {
            if ((((b1x + 1 >= col1.get(i).getX() && b1x + 1 <= col1.get(i).getX() + 3.5) || (b1x + 1 <= col1.get(i).getX() + 4.5 && b1x >= col1.get(i).getX())) && ((col1.get(i).getY() == b1y - 1) || (col1.get(i).getY() == b1y + 1)))) {
                score += 5;
                Score.setText(score + "");
                if (col1.get(i).getY() == b1y - 1) {
                    balldisy = 1;
                } else if (col1.get(i).getY() == b1y + 1) {
                    balldisy = - 1;
                } else if (col1.get(i).getX() == b1x - 3.5) {
                    balldisx = 1;
                } else if (b1x == col1.get(i).getX() + 3.5 && b1y == col1.get(i).getY() + 1) {
                    balldisy = 1;
                }

            }
        }
        for (int i = 0; i < col2.size(); i++) {
            if (((b1x + 1 >= col2.get(i).getX() && b1x + 1 <= col2.get(i).getX() + 3.5) || (b1x + 1 <= col2.get(i).getX() + 4.5 && b1x >= col2.get(i).getX())) && ((col2.get(i).getY() == b1y - 1) || (col2.get(i).getY() == b1y + 1))) {
                score += 5;
                Score.setText(score + "");

                if (col2.get(i).getY() == b1y - 1) {
                    balldisy = 1;
                } else if (col2.get(i).getY() == b1y + 1) {
                    balldisy = - 1;
                } else if (col1.get(i).getX() == b1x + 1) {
                    balldisx = -1;
                } else if (col2.get(i).getX() == b1x + 1 && b1y == col2.get(i).getY()) {
                    balldisy = 1;
                }

            }
        }
       iscollidewithbricks3();
        if (row5 != null) {
            if (((b1x + 1 >= row5.getX() && b1x + 1 <= row5.getX() + 3.5) || (b1x + 1 <= row5.getX() + 4.5 && b1x >= row5.getX())) && ((row5.getY() == b1y - 1) || (row5.getY() == b1y + 1))) {
                score += 20;
                Score.setText(score + "");
                game.removeRec(row5);
                if (row5.getY() == b1y - 1) {
                    balldisy = 1;
                } else if (row5.getY() == b1y + 1) {
                    balldisy -= 1;
                } else if (row5.getX() == b1x + 1 && b1y == row5.getY()) {
                    balldisy = 1;
                } else if (b1x == row5.getX() + 3.5 && b1y == row5.getY() + 1) {
                    balldisy = 1;
                }

                row5 = null;

            }
        }
    }
    public void reset(){
     balldisx = 0;
     balldisy = 0;
       b1x = pConst.GRID_WIDTH / 2;
    b1y = pConst.GRID_HEIGHT - 3;
     recx = pConst.GRID_WIDTH / 2 - 3;
     recy = pConst.GRID_HEIGHT - 2;
     counter =20;
     lives=3;
     enemy=40;
     score=0;
    }

    public static STATE getGameState() {
        return gamestate;
    }

    public static void main(String[] args) {
        Arkanoidv11 ar = new Arkanoidv11();
        ar.setVisible(true);
    }

}

class Score implements Serializable {

    private String name;
    private String level;
    private int score;

    public Score(String name, String level, int score) {
        this.name = name;
        this.level = level;
        this.score = score;
    }

    public Score() {
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }
}

class ScoreCompare implements Comparator<Score> {

    @Override
    public int compare(Score s1, Score s2) {
        return s2.getScore() - s1.getScore();
    }
}

class ScoreManager {

    private ArrayList<Score> scores = new ArrayList();
    private FileInputStream filein = null;
    private ObjectInputStream in = null;
    private FileOutputStream fileout = null;
    private ObjectOutputStream out = null;
    private File f = new File("Highscores.txt");

public ArrayList<Score> getScores(){
    if(f.exists())
    loadScoresList();
    return scores;
}
    public void addScore(Score s) {
        if (f.exists()) {
            loadScoresList();
        }
        scores.add(s);
        ScoreCompare sc = new ScoreCompare();
        Collections.sort(scores, sc);
        updateScoresList();
    }

    public void loadScoresList() {
        try {
            if (!(f.exists())) {
                f.createNewFile();
            }
            filein = new FileInputStream(f);
            in = new ObjectInputStream(filein);
            scores = (ArrayList<Score>) in.readObject();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        } finally {
            try {
                in.close();
                filein.close();
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
    }

    public void updateScoresList() {
        try {
            if (!(f.exists())) {
                f.createNewFile();
            }
            fileout = new FileOutputStream(f);
            out = new ObjectOutputStream(fileout);
            out.writeObject(scores);
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                    fileout.close();
                }
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
    }

    public String getHighestScore() {
        scores=getScores();
        String highest = "";
        int max = 20;
        int i = 0;
        int x = scores.size();
        if (x > max) {
            x = max;
        }
        while (i < x) {
            highest += (i + 1) + ".\t" + scores.get(i).getName() + "\t\t"
                    + scores.get(i).getLevel() + "\t" + scores.get(i).getScore() + "\n";
            i++;
        }
        return highest;
    }
    
}
