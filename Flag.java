import java.awt.*;
import javax.swing.*;

public class Flag extends JApplet{
 private static final long serialVersionUID = 1L;

 
 public Flag() {
  FlagPanel flagPanel = new FlagPanel();
  add(flagPanel);
  Thread thread = new Thread(flagPanel);       // Create Thread & Start
  thread.start();
 }
 
 public static void main(String[] args) {       // Set Screen
  JFrame frame = new JFrame();               
  frame.add(new Flag());
  frame.setTitle("U.S Flag");                     
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setSize(500, 500);
  frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
  frame.setLocationRelativeTo(null);
  frame.setVisible(true);
 }

 class FlagPanel extends JPanel implements Runnable {          //Task Class
  private int currentFlag = 0;
  private ImageIcon imageIcon;
  private int x;
  private int y;
  private static final long serialVersionUID = 1L;
  private boolean newFlag = true;
  private Image image;
  
  @Override
  protected void paintComponent(Graphics g) {          //Implement Image
   super.paintComponent(g);
   if (newFlag) {
    y = getHeight();
    newFlag = false;
     imageIcon = new ImageIcon(this.getClass().getResource("flag" + ".gif"));
    image = imageIcon.getImage();
   }
   x = (getWidth() - imageIcon.getIconWidth()) / 2;
   g.drawImage(image, x,  y, imageIcon.getIconWidth(), imageIcon.getIconHeight(), this);
   if (y + imageIcon.getIconHeight() <= 0) {
    newFlag = true;
    currentFlag++;
    if (currentFlag > 6) {
     currentFlag = 0;
    }    
   }
  }

  @Override
  public void run() {         //Run
   try {
    while (true) {
     Thread.sleep(10);
     y--;          
     repaint();
    }
   } catch (InterruptedException e) {
    e.printStackTrace();
   }
  }
 }
}

 