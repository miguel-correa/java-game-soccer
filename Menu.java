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

    final int JOGAR = 0;
    final int OPT = 1;
    final int SAIR = 2;

    class Desenha extends JPanel {
       Rectangle jogarBtn;
       String jogarTxt = "JOGAR";
    //    boolean jogarAtivo = false;

       Rectangle sairBtn;
       String sairTxt = "SAIR";

       Rectangle optBtn;
       String optTxt = "OPÇÕES";
    //    boolean sairAtivo = false;

       Font font;


        Desenha() {
            try {
                setPreferredSize(new Dimension(800, 600));
                // lê imagem preta de fundo
                img = ImageIO.read(new File("/home/miguel/Unesp/TPI/game/campo.jpg"));
                
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
            jogarBtn = new Rectangle(getSize().width/2-w/2, getSize().height*2/8-h/2, w, h);
            optBtn = new Rectangle(getSize().width/2-w/2, getSize().height*4/8-h/2, w, h);
            sairBtn = new Rectangle(getSize().width/2-w/2, getSize().height*6/8-h/2, w, h);
            font = new Font("Roboto", Font.PLAIN, 50);

            Color btnUnselected = new Color(211,38,38);
            Color btnSelected = new Color(121,215,15);
            Color txtColor = new Color(237,244,242);
            Color outlineColor = new Color(221,221,221);
            //245,163,26 laranja
            
            g.setColor(btnUnselected);
                if(selected == JOGAR)
                g.setColor(btnSelected);
            g2d.fill(jogarBtn);

            g.setColor(btnUnselected);
            if(selected == OPT)
                g.setColor(btnSelected);
            g2d.fill(optBtn);

            g.setColor(btnUnselected);
            if(selected == SAIR)
                g.setColor(btnSelected);
            g2d.fill(sairBtn);

            g.setColor(outlineColor);
            g2d.draw(jogarBtn);
            g2d.draw(sairBtn);
            g2d.draw(optBtn);

            int strWidth, strHeight;

            strWidth = g.getFontMetrics(font).stringWidth(jogarTxt);
            strHeight = g.getFontMetrics(font).getHeight();

            g.setColor(txtColor);
            g.drawString(jogarTxt, (int) (jogarBtn.getX() + jogarBtn.getWidth() / 2 - strWidth / 2),
                        (int) (jogarBtn.getY() + jogarBtn.getHeight() / 2 + strHeight / 4));

            strWidth = g.getFontMetrics(font).stringWidth(sairTxt); 
            // strHeight = g.getFontMetrics(font).getHeight();        
            g.drawString(sairTxt, (int) (sairBtn.getX() + sairBtn.getWidth() / 2 - strWidth / 2),
                        (int) (sairBtn.getY() + sairBtn.getHeight() / 2 + strHeight / 4 + 5));

            strWidth = g.getFontMetrics(font).stringWidth(optTxt);
            g.drawString(optTxt, (int) (optBtn.getX() + optBtn.getWidth() / 2 - strWidth / 2),
                        (int) (optBtn.getY() + optBtn.getHeight() / 2 + strHeight / 4 + 5)); 

            g.drawString("FUTEBOL EXTREME DOIDERA", (int) (strWidth / 6),
                (int) (50 + strHeight / 4));            

            Toolkit.getDefaultToolkit().sync();
          }
        

       
    }

    int selected = 0;
    
    void selectUp() {
        if(selected > JOGAR)
            selected--;
    }

    void selectDown() {
        if(selected < SAIR)
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
                    selectUp();
                    des.repaint();
                }
            }
        });

        t = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.out.println(selected);
                // selectDown();
                // selectUp();
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