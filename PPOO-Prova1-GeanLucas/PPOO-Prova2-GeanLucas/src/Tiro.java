
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;



/**
 *
 * @author Souza
 */
public class Tiro implements TiposTiro {
    
    private Image imagem;
    private int x,y;
    private int largura,altura;
    private boolean isVisivel;
    private boolean flagTiro;
    
    private static final int ALTURA = 20;
     private static final int VELOCIDADE = 2;
     
     public Tiro(int x, int y){
         this.x = x;
         this.y= y;
         isVisivel = true;
         
         
         
     }
     
     public void load(){
        ImageIcon referencia = new ImageIcon(getClass().getResource("/folder/tiro.png"));
        imagem = referencia.getImage();
        
        
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
     }
     
     //MÉTODO USADO PARA IMPLEMENTAÇAO O PADRAO STRATEGY (TIRO VERTICAL COMUM)
    @Override
    public void update(int x) {
        this.y -= VELOCIDADE;
        if (this.y < ALTURA) {
            isVisivel = false;
        }

    }
    
    public Rectangle getBounds(){
        return new Rectangle(x,y,altura,largura);
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isIsVisivel() {
        return isVisivel;
    }

    public void setIsVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }
    
    

    public static int getVELOCIDADE() {
        return VELOCIDADE;
    }
    
    public static void setVELOCIDADE(int VELOCIDADE) {
        VELOCIDADE = VELOCIDADE;
    }

    public Image getImagem() {
        return imagem;
    }

    public boolean isFlagTiro() {
        return flagTiro;
    }




    
    
    
    
    
    
    
    

}
