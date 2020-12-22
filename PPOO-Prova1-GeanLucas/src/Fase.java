
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
    private List<Inimigo1> inimigo1;
    private int numeroInimigos = 5;
    private boolean emJogo;

    
     //Construtor
    public Fase() throws IOException{
        
        setFocusable(true);
        setDoubleBuffered(true);
        
        
        nave = new Nave();
        nave.load();
        addKeyListener(new TecladoAdapter(this));
        
        timer = new Timer(5,this);
        timer.start();
        inicializaInimigos();
        emJogo = true;

    }
    
    public void inicializaInimigos(){
        int coordenadas[]=new int[numeroInimigos];
        inimigo1 =new ArrayList<Inimigo1>();
        
        for(int i = 0;i<coordenadas.length; i++){
            int x = (int) ((Math.random() * (800 - 0)) + 0);;
            int y = (int) ((Math.random() * (0 - (-300))) + 0);;
            inimigo1.add(new Inimigo1(x,y));
            
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

            // System.out.println("Tamanho do list de inimigos: " + inimigo1.size());
            if (inimigo1.size() < numeroInimigos) {
                int x = (int) ((Math.random() * (800 - 0)) + 0);;
                int y = (int) ((Math.random() * (0 - (-300))) + 0);;
                inimigo1.add(new Inimigo1(x, y));
            }
            for (int o = 0; o < inimigo1.size(); o++) {
                Inimigo1 ini = inimigo1.get(o);
                ini.load();
                //  System.out.println("Coordenada x dos inimigos:  " +ini.getX());
                graficos.drawImage(ini.getImagem(), ini.getX(), ini.getY(), this);
            }

        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            ImageIcon fimJogo = new ImageIcon(getClass().getResource("/folder/game over.jpg"));
            graficos.drawImage(fimJogo.getImage(), 0, 0,this.getWidth(),this.getHeight(), null);

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
        
        for(int j=0;j<inimigo1.size();j++){
            Inimigo1 in =inimigo1.get(j);
           // System.out.println("Inimigo é visível;" +in.isIsVisivel());
            if(in.isIsVisivel()){
                in.update(j);
                
            }else{
                inimigo1.remove(in);
            }
            
        }
       checarColisao();
       repaint();
    }

    //Método que desenha os tiros
    public void drawBullet(Graphics2D graficos, TiposTiro t) {       
      
            graficos.drawImage(t.getImagem(), t.getX(), t.getY(), this);

    }
    
    public void checarColisao(){
        Rectangle formaNave = nave.getBounds();
        Rectangle formaInimigo;
        Rectangle formaTiro;
        
        for (int i = 0; i<inimigo1.size();i++){
            Inimigo1 tempInimigo1 = inimigo1.get(i);
            formaInimigo = tempInimigo1.getBounds();
            if(formaNave.intersects(formaInimigo)){
                nave.setVisivel(false);
                tempInimigo1.setIsVisivel(false);
                emJogo=false;
            }
        }
        
        List<TiposTiro> tiros2 = nave.getTiros();
        for(int h = 0; h<tiros2.size();h++){
            TiposTiro tempTiro = tiros2.get(h);
            formaTiro = tempTiro.getBounds();
            for(int ti = 0; ti<inimigo1.size();ti++){
                Inimigo1 tempInimigo1 = inimigo1.get(ti);
                formaInimigo = tempInimigo1.getBounds();
                if(formaTiro.intersects(formaInimigo)){
                    tempInimigo1.setIsVisivel(false);
                    tempTiro.setIsVisivel(false);
                }
                
            }
            
        }
    }

    //Adaptador do teclado
    private class TecladoAdapter extends KeyAdapter{
        private Fase fase;
        public TecladoAdapter(Fase fase){
            this.fase = fase;
            
        }
        
        @Override
        public void keyPressed(KeyEvent e){
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
