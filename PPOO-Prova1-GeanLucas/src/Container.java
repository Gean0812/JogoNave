
import java.io.IOException;
import javax.swing.JFrame;


/**
 *
 * @author Souza
 */
public class Container extends JFrame {
    
    public Container() throws IOException{
        add(new Fase());
        setTitle("Meu Joguito");
        setSize(1024,728);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(false);
        setVisible(true);
    }
    
    public static void main(String []args) throws IOException{
        new Container();
    }
    
}
