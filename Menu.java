import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.event.MouseEvent;

import java.io.*;
import javax.imageio.*;

class Menu extends JFrame {
    Image img[] = new Image[2];
    Desenha des = new Desenha();
    Timer t;

    final int JOGAR = 0;
    final int OPT = 1;
    final int SAIR = 2;
    final int MENU_IMG = 0;
    final int OPT_IMG = 1;
    final int CANCELAR = 0;
    final int SALVAR = 1;

    // Controla o botão ativo
    int selected = JOGAR;
    int selectedOpt = CANCELAR;
    int currScreen = MENU_IMG;
    class Desenha extends JPanel {
       Rectangle jogarBtn;
       String jogarTxt = "JOGAR";

       Rectangle sairBtn;
       String sairTxt = "SAIR";

       Rectangle optBtn;
       String optTxt = "OPÇÕES";

       Rectangle salvarBtn;
       String salvarTxt = "SALVAR";

       Rectangle cancelarBtn;
       String cancelarTxt = "CANCELAR";

       Font font;


        Desenha() {
            try {
                setPreferredSize(new Dimension(1000, 600));
                // lê imagens de fundo
                img[0] = ImageIO.read(new File("campo-blur.png"));
                img[1] = ImageIO.read(new File("rede-blur.png"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "A imagem não pode ser carregada!\n" + e, "Erro", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            // AntiAliasing para o texto
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
                                RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            if(currScreen == MENU_IMG)
                g.drawImage(img[MENU_IMG], 0, 0, getSize().width, getSize().height, this);
            else if(currScreen == OPT_IMG)
                g.drawImage(img[OPT_IMG], 0, 0, getSize().width, getSize().height, this);
            g.setFont(font);
            
            // Tamanho dos botões, pode ser alterado para uma
            // proporção do tamanho da tela ao invés de um valor fixo
            int w = 300, h = 100;
            // Cria os botões e define a fonte dos textos
            jogarBtn = new Rectangle(getSize().width/2-w/2, getSize().height*2/8-h/2, w, h);
            optBtn = new Rectangle(getSize().width/2-w/2, getSize().height*4/8-h/2, w, h);
            sairBtn = new Rectangle(getSize().width/2-w/2, getSize().height*6/8-h/2, w, h);
            salvarBtn = new Rectangle(getSize().width*3/4-w/2, getSize().height*6/8-h/2, w, h);
            cancelarBtn = new Rectangle(getSize().width/4-w/2, getSize().height*6/8-h/2, w, h);
            font = new Font("Roboto", Font.PLAIN, 50);

            // Cores utilizadas na interface grafica
            Color btnUnselected = new Color(211,38,38);
            Color btnSelected = new Color(121,215,15);
            Color txtColor = new Color(237,244,242);
            Color outlineColor = new Color(221,221,221);
            //245,163,26 laranja -> não utilizada
            
            int strWidth, strHeight;
            // Desenha os componentes do menu
            if(currScreen == MENU_IMG) {
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

                // Outline dos botões
                g.setColor(outlineColor);
                g2d.draw(jogarBtn);
                g2d.draw(sairBtn);
                g2d.draw(optBtn);

                
                // Escreve os textos do menu
                strWidth = g.getFontMetrics(font).stringWidth(jogarTxt);
                strHeight = g.getFontMetrics(font).getHeight();
                g.setColor(txtColor);
                g.drawString(jogarTxt, (int) (jogarBtn.getX() + jogarBtn.getWidth() / 2 - strWidth / 2),
                            (int) (jogarBtn.getY() + jogarBtn.getHeight() / 2 + strHeight / 4));

                strWidth = g.getFontMetrics(font).stringWidth(sairTxt);         
                g.drawString(sairTxt, (int) (sairBtn.getX() + sairBtn.getWidth() / 2 - strWidth / 2),
                            (int) (sairBtn.getY() + sairBtn.getHeight() / 2 + strHeight / 4 + 5));

                strWidth = g.getFontMetrics(font).stringWidth(optTxt);
                g.drawString(optTxt, (int) (optBtn.getX() + optBtn.getWidth() / 2 - strWidth / 2),
                            (int) (optBtn.getY() + optBtn.getHeight() / 2 + strHeight / 4 + 5));
            }
             
            // Desenha os componentes da tela de opções
            if(currScreen == OPT_IMG) {
                g.setColor(btnUnselected);
                if(selectedOpt == CANCELAR)
                    g.setColor(btnSelected);
                g2d.fill(cancelarBtn);

                g.setColor(btnUnselected);
                if(selectedOpt == SALVAR)
                    g.setColor(btnSelected);
                g2d.fill(salvarBtn);

                g.setColor(outlineColor);
                g2d.draw(salvarBtn);
                g2d.draw(cancelarBtn);

                strWidth = g.getFontMetrics(font).stringWidth(cancelarTxt);
                strHeight = g.getFontMetrics(font).getHeight();
                g.setColor(txtColor);
                g.drawString(cancelarTxt, (int) (cancelarBtn.getX() + cancelarBtn.getWidth() / 2 - strWidth / 2),
                            (int) (cancelarBtn.getY() + cancelarBtn.getHeight() / 2 + strHeight / 4));
                
                strWidth = g.getFontMetrics(font).stringWidth(salvarTxt);
                g.drawString(salvarTxt, (int) (salvarBtn.getX() + salvarBtn.getWidth() / 2 - strWidth / 2),
                            (int) (salvarBtn.getY() + salvarBtn.getHeight() / 2 + strHeight / 4));
            }

            g.setColor(txtColor);
            strHeight = g.getFontMetrics(font).getHeight();
            strWidth = g.getFontMetrics(font).stringWidth("FUTEBOL EXTREME DOIDERA");
            g.drawString("FUTEBOL EXTREME DOIDERA", (int) (getWidth() / 2 - strWidth / 2),
                (int) (50 + strHeight / 4));            

            Toolkit.getDefaultToolkit().sync();
          }
    }

    // Muda botão selecionado
    void selectUp() {
        if(selected > JOGAR)
            selected--;
    }
    // Igual o acima, mas na direçao contrária
    void selectDown() {
        if(selected < SAIR)
            selected++;
    }

    Menu() {
        super("FUTBILLY");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Lida com ações do teclado
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(currScreen == MENU_IMG) {
                    switch(e.getKeyCode()) {
                        case KeyEvent.VK_DOWN:
                            selectDown();
                            des.repaint();
                            break;
                        case KeyEvent.VK_UP:
                            selectUp();
                            des.repaint();
                            break;
                        case KeyEvent.VK_ENTER:
                            switch(selected) {
                                case JOGAR:
        
                                    break;
                                case OPT:
                                    currScreen = OPT_IMG;
                                    des.repaint();
                                    break;
                                case SAIR:
                                    System.exit(0);
                            }
                    }
                } else if(currScreen == OPT_IMG) {
                    switch(e.getKeyCode()) {
                        case KeyEvent.VK_RIGHT:
                            if(selectedOpt != SALVAR) {
                                selectedOpt = SALVAR;
                                des.repaint();
                                break;
                            }
                        case KeyEvent.VK_LEFT:
                            if(selectedOpt != CANCELAR) {
                                selectedOpt = CANCELAR;
                                des.repaint();
                                break;
                            } else {
                                selectedOpt = SALVAR;
                                des.repaint();
                                break;
                            }
                    }
                }
                // if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                //    selectDown();
                //    des.repaint();
                // } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                //     selectUp();
                //     des.repaint();
                // } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                //     // switch da proxima tela
                //     switch(selected) {
                //         case JOGAR:

                //             break;
                //         case OPT:
                //             currScreen = OPT_IMG;
                //             des.repaint();
                //             break;
                //         case SAIR:
                //             System.exit(0);
                //     }
                // } else if ()
            }
        });

        // Verifica se o clique do mouse está contido em algum botao
        // e executa a ação correspondente
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();
                // TODO: MUDAR PARA SWITCH CASE
                if(currScreen == MENU_IMG) {
                    if(des.jogarBtn.contains(p)) {
                        // iniciar jogo
                    } else if(des.optBtn.contains(p)) {
                        // tela de opções
                        currScreen = OPT_IMG;
                        des.repaint();
                    } else if(des.sairBtn.contains(p))
                        System.exit(0);
                } else if(currScreen == OPT_IMG) {
                    if(des.salvarBtn.contains(p)) {
                        currScreen = MENU_IMG;
                        des.repaint();
                    } else if(des.cancelarBtn.contains(p)) {
                        currScreen = MENU_IMG;
                        des.repaint();
                    }
                }
            }
        });

        // Altera botão ativo conforme a posição do mouse
        addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                Point p = e.getPoint();
                if(currScreen == MENU_IMG) {
                    if(des.jogarBtn.contains(p)) {
                        selected = JOGAR;
                        des.repaint();
                    } else if (des.optBtn.contains(p)) {
                        selected = OPT;
                        des.repaint();
                    } else if (des.sairBtn.contains(p)) {
                        selected = SAIR;
                        des.repaint();
                    }
                } else if(currScreen == OPT_IMG) {
                    if(des.cancelarBtn.contains(p)) {
                        selectedOpt = CANCELAR;
                        des.repaint();
                    } else if (des.salvarBtn.contains(p)) {
                        selectedOpt = SALVAR;
                        des.repaint();
                    }
                }
            }
        });
        des.repaint();
        t = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                // System.out.println(selected);
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