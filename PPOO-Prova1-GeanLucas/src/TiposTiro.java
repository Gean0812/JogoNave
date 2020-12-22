
import java.awt.Image;
import java.awt.Rectangle;


/**
 *
 * @author Souza
 */
public interface TiposTiro {
    
     //PADRAO STRATEGY UTILIZADO

   public void update(int x);
   public boolean isIsVisivel();
   public void load();
   public int getX();
   public int getY();
   public Image getImagem();
   public Rectangle getBounds();
   public void setIsVisivel(boolean isVisivel);
}
