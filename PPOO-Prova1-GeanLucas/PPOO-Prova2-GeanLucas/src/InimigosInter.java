
import java.awt.Image;
import java.awt.Rectangle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Souza
 */
public interface InimigosInter {
    
    public void load();

    public void update(int x);

    public Rectangle getBounds();

    public int getX();

    public int getY();

    public boolean isIsVisivel();

    public void setIsVisivel(boolean isVisivel);
    
    public Image getImagem();
    
    public boolean isFlagTiro();

    
}
