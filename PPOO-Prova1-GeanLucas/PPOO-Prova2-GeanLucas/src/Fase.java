
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Souza
 */
public class Fase extends JPanel implements ActionListener {

    private Nave nave;
    private Timer timer;
    private KeyEvent tecla;
    private List<InimigosInter> inimigos;
    private int numeroInimigos = 5;
    private boolean emJogo;

    //Construtor
    public Fase() throws IOException {

        setFocusable(true);
        setDoubleBuffered(true);

        nave = new Nave();
        nave.load();
        addKeyListener(new TecladoAdapter(this));

        timer = new Timer(5, this);
        timer.start();
        inicializaInimigos();
        emJogo = true;

    }

    //Método de Inicilializar os nimigos na Tela
    public void inicializaInimigos() {
        int coordenadas[] = new int[numeroInimigos];
        inimigos = new ArrayList<InimigosInter>();

        TiposInimigo tpi[] = {
            TiposInimigo.INIMIGO_1, TiposInimigo.INIMIGO_2

        };

        for (int i = 0; i < coordenadas.length; i++) {
            int sorteio = (int) ((Math.random() * (tpi.length - 0)) + 0);

            inimigos.add(InimigoFactory.criarInimigo(tpi[sorteio]));

        }
    }

    //Desenhando na Tela os Componentes 
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        if (emJogo == true) {
            super.paintComponent(g);
            this.setBackground(Color.BLACK);
            graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);

            List<TiposTiro> tiros = nave.getTiros();
            if (!tiros.isEmpty()) {
                for (int i = 0; i < tiros.size(); i++) {
                    TiposTiro m = tiros.get(i);
                    m.load();
                    drawBullet(graficos, m);

                }
            }

            //Sorteio de Inimigos
            TiposInimigo tpi[] = {
                TiposInimigo.INIMIGO_1, TiposInimigo.INIMIGO_2

            };


            if (inimigos.size() < numeroInimigos) {

                int sorteio = (int) ((Math.random() * (tpi.length - 0)) + 0);

                inimigos.add(InimigoFactory.criarInimigo(tpi[sorteio]));
            }
            for (int o = 0; o < inimigos.size(); o++) {
                InimigosInter ini = inimigos.get(o);
                ini.load();
                graficos.drawImage(ini.getImagem(), ini.getX(), ini.getY(), this);
            }

        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            ImageIcon fimJogo = new ImageIcon(getClass().getResource("/folder/game over.jpg"));
            graficos.drawImage(fimJogo.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);

        }

    }

    //Eventos do tiro
    @Override
    public void actionPerformed(ActionEvent ae) {
        nave.update();
        List<TiposTiro> tiros = nave.getTiros();

        for (int i = 0; i < tiros.size(); i++) {
            TiposTiro m = tiros.get(i);
            if (m.isIsVisivel()) {
                m.update(nave.getX());
            } else {
                tiros.remove(i);
            }
        }

        for (int j = 0; j < inimigos.size(); j++) {
            InimigosInter in = inimigos.get(j);
            // System.out.println("Inimigo é visível;" +in.isIsVisivel());
            if (in.isIsVisivel()) {
                in.update(j);

            } else {
                inimigos.remove(in);
            }

        }
        checarColisao();
        repaint();
    }

    //Método que desenha os tiros
    public void drawBullet(Graphics2D graficos, TiposTiro t) {

        graficos.drawImage(t.getImagem(), t.getX(), t.getY(), this);

    }

    public void checarColisao() {
        Rectangle formaNave = nave.getBounds();
        Rectangle formaInimigo;
        Rectangle formaTiro;

        //Checando colisao Inimigo & Nave
        for (int i = 0; i < inimigos.size(); i++) {
            InimigosInter tempInimigo1 = inimigos.get(i);
            formaInimigo = tempInimigo1.getBounds();
            if (formaNave.intersects(formaInimigo)) {
                nave.setVisivel(false);
                tempInimigo1.setIsVisivel(false);
                emJogo = false;
            }
        }

        //Checando colisao Tiro & Inimigo
        List<TiposTiro> tiros2 = nave.getTiros();
        for (int h = 0; h < tiros2.size(); h++) {
            TiposTiro tempTiro = tiros2.get(h);
            formaTiro = tempTiro.getBounds();
            for (int ti = 0; ti < inimigos.size(); ti++) {
                InimigosInter tempInimigo1 = inimigos.get(ti);
                formaInimigo = tempInimigo1.getBounds();
                if (formaTiro.intersects(formaInimigo)) {
                    tempInimigo1.setIsVisivel(false);
                    tempTiro.setIsVisivel(false);
                }

            }

        }
    }

    //Adaptador do teclado
    private class TecladoAdapter extends KeyAdapter {

        private Fase fase;

        public TecladoAdapter(Fase fase) {
            this.fase = fase;

        }

        @Override
        public void keyPressed(KeyEvent e) {
            this.fase.tecla = e;
            nave.teclaPressionada(e);
            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            nave.teclaLiberada(e);
            repaint();
        }

    }
}
