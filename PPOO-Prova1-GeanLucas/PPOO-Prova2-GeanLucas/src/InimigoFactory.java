/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Souza
 */
public class InimigoFactory {

    public static InimigosInter criarInimigo(TiposInimigo tiposInimigo ){
        
        //Variáveis para gerar o inimigo aleatoriamente na tela
     int x = (int) ((Math.random() * (800 - 0)) + 0);
     int y = (int) ((Math.random() * (0 - (-300))) + 0);
     
        if(tiposInimigo.equals(TiposInimigo.INIMIGO_1)){
             return new Inimigo1(x,y);
            
        } else if(tiposInimigo.equals(TiposInimigo.INIMIGO_2)){
           return new Inimigo2(x,y);
        }
        return null;
    }
    
}
