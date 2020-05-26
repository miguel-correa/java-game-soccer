import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

class Bola extends JFrame {
    Desenho des = new Desenho();
    final int FUNDO = 0;
    final int BOLA = 1;
    final int LARGURA = 800;
    final int ALTURA = 600;
    Image fundo, bola;
    int xPosBall;
    int yPosBall;
    int lastX;
    int lastY;
    int xSpeedBall;
    int ySpeedBall;
    Timer timer;

    class Desenho extends JPanel {

        Desenho() {
          try {
            setPreferredSize(new Dimension(800, 600));
            fundo = ImageIO.read(new File("data/bg.jpg"));
            bola = ImageIO.read(new File("data/soccer-ball.png")).getScaledInstance(70, 70, Image.SCALE_DEFAULT);;
          } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "A imagem não pode ser carregada!\n" + e, "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
          }
          startBall();
        }

        void startBall() {
            
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(fundo, 0, 0, getSize().width, getSize().height, this);
            
            g.drawImage(bola, xPosBall, yPosBall, this);
            Toolkit.getDefaultToolkit().sync();
          }
    }

    void updateBallPos() {
        int nextX = xPosBall + xSpeedBall;
        int nextY = yPosBall + ySpeedBall;
        if(des.getWidth() - bola.getWidth(this) <= nextX /*|| nextX <= 0*/) {
            xPosBall = des.getWidth() - bola.getWidth(this) - 1;
            xSpeedBall *= -1;
        } 
        if(nextX <= 0) {
            xPosBall = 1;
            xSpeedBall *= -1;
        }
        if(des.getHeight() - bola.getHeight(this) <= nextY) {
            yPosBall = des.getHeight() - bola.getHeight(this) - 1;
            ySpeedBall *= -1;
        }
        if(nextY <= 0) {
            yPosBall = 1;
            ySpeedBall *= -1;
            bola.getHeight(this);
        }
        xPosBall += xSpeedBall;
        yPosBall += ySpeedBall;
    }

    Bola() {
        super("Trab");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(des);
        pack();
        setVisible(true);
        
        xPosBall = getSize().width/2 - bola.getWidth(this)/2;
        yPosBall = getSize().height/2 - bola.getHeight(this)/2;
        xSpeedBall = 15;
        ySpeedBall = -7;
        timer = new Timer(25, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                updateBallPos();
                // System.out.println(xPosBall);
                des.repaint();
              }
        });
        timer.start();
    }

    static public void main(String[] args) {
        Bola b = new Bola();
    }
}