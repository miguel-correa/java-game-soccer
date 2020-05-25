import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

class Menu extends JFrame {
    Image img;
    Desenha des = new Desenha();
    Timer t;

    class Desenha extends JPanel {
       Rectangle jogarBtn;
       String jogarTxt = "JOGAR";
       boolean jogarAtivo = false;

       Rectangle sairBtn;
       String sairTxt = "SAIR";
       boolean sairAtivo = false;

       Font font;


        Desenha() {
            try {
                setPreferredSize(new Dimension(800, 600));
                // lê imagem preta de fundo
                img = ImageIO.read(new File("/home/miguel/Unesp/TPI/game/bg.jpg"));
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "A imagem não pode ser carregada!\n" + e, "Erro", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g.drawImage(img, 0, 0, getSize().width, getSize().height, this);
            g.setFont(font);
            
            int w, h, x, y;
            w = 300;
            h = 100;
            jogarBtn = new Rectangle(getSize().width/2-w/2, getSize().height*1/4-h/2, w, h);
            sairBtn = new Rectangle(getSize().width/2-w/2, getSize().height*3/4-h/2, w, h);
            font = new Font("Roboto", Font.PLAIN, 50);

            g.setColor(Color.GRAY);
            if(selected == 0)
                g.setColor(Color.WHITE);
            g2d.fill(jogarBtn);

            g.setColor(Color.GRAY);
            if(selected == 1)
                g.setColor(Color.WHITE);
            g2d.fill(sairBtn);

            g.setColor(Color.WHITE);
            g2d.draw(jogarBtn);
            g2d.draw(sairBtn);

            int strWidth, strHeight;

            strWidth = g.getFontMetrics(font).stringWidth(jogarTxt);
            strHeight = g.getFontMetrics(font).getHeight();

            g.setColor(Color.green);
            g.drawString(jogarTxt, (int) (jogarBtn.getX() + jogarBtn.getWidth() / 2 - strWidth / 2),
                        (int) (jogarBtn.getY() + jogarBtn.getHeight() / 2 + strHeight / 4));

            strWidth = g.getFontMetrics(font).stringWidth(sairTxt); 
                   
            g.drawString(sairTxt, (int) (sairBtn.getX() + sairBtn.getWidth() / 2 - strWidth / 2),
                        (int) (sairBtn.getY() + sairBtn.getHeight() / 2 + strHeight / 4 + 5));

            Toolkit.getDefaultToolkit().sync();
          }
        

       
    }

    int selected = 0;
    
    void selectUp() {
        if(selected != 0)
            selected--;
    }

    void selectDown() {
        if(selected != 1)
            selected++;
    }

    Menu() {
        super("FUTBILLY");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                   selectDown();
                   des.repaint();
                } else if (e.getKeyCode() == 38) {
                    // selectUp();
                    des.repaint();
                }
            }
        });

        t = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                selectDown();
                selectUp();
            }
        });
        t.start();

        add(des);
        pack();
        setVisible(true);
    }   

    static public void main(String[] args) {
        Menu f = new Menu();
    }
}