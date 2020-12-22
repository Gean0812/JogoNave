
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


/**
 *
 * @author Souza
 */
public class Nave {
    
    private int x,y;
    private int dx, dy;
    private Image imagem;
    private int altura, largura;
    private List <TiposTiro> tiros;
    private boolean flagF3 = false;
    private boolean visivel;
    
    
    //Construtor
    public Nave(){
        this.x = 1024/2;
        this.y = 540;
        visivel = true;
        
        this.tiros = new ArrayList<TiposTiro>();
    }
    
    //Método de load
    public void load() throws IOException{
        System.out.println(new File("/folder/nave.png").getCanonicalPath());
        ImageIcon referencia = new ImageIcon(getClass().getResource("/folder/nave.png"));
        imagem = referencia.getImage();
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
    }
    
    
    //Update para ajudar nos movimentos da nave
    
    public void update(){


        x += dx;
        y += dy;

    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, altura, largura);
    }
    
    //Método para a nave disparar os tiros
    
    public void atirar(){
        if (flagF3 == false) {
            this.tiros.add(new Tiro(x + 45, y - 30));

        } else {
             this.tiros.add(new TiroEspecial(x + 45, y - 30));
        }

        
    }

    
    //Comandos da nave (direita,esquerda,atirar e mudar tiro)
    
    public void teclaPressionada(KeyEvent tecla){
        int codigo = tecla.getKeyCode();
        
        if (codigo == KeyEvent.VK_LEFT) {
            dx = -3;

        }
        
              
                
        if (codigo == KeyEvent.VK_RIGHT) {
            dx = 3;
        }
        
        if(codigo == KeyEvent.VK_SPACE){
            atirar();
        }
        
        if(codigo == KeyEvent.VK_F3){           
            flagF3 = !flagF3;
            
            
        }
        
        
    }
    

    //Métodos para zerar os movimentos da nave
       public void teclaLiberada(KeyEvent tecla){
        int codigo = tecla.getKeyCode();
        
        if(codigo== KeyEvent.VK_LEFT){
            dx = 0;
        }
        
        if (codigo == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        
       
        
        
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagem() {
        return imagem;
    }

    public List<TiposTiro> getTiros() {
        return tiros;
    }

    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }


    
    
       
       
            
    
    
    
}
