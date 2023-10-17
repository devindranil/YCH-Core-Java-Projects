import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MyFrame extends JFrame {
    MyFrame() {

        // set Image Icon of the JFrame
        ImageIcon image1 = new ImageIcon("Swing Basic\\src\\media\\logo.png"); // create an imageicon
        this.setIconImage(image1.getImage());// change icon of frame

        ImageIcon image2 = new ImageIcon("Swing Basic\\src\\media\\laptop3.png");
        Image scaleImage = image2.getImage().getScaledInstance(5, 5,Image.SCALE_DEFAULT);
        this.setIconImage(image2.getImage());
        

        // change the background colur of the frame
        // (1) -> r - g - b (0 to 255)
        // (2) -> Hex Code
        this.getContentPane().setBackground(new Color(0x722DE7));

        // JLabel = a GUI display area for a string of text, an image, or both
        JLabel label = new JLabel();
        label.setText("Like, share, and Subscribe Youth Career Hub!!!!");
        //label.setIcon(image2);
        label.setHorizontalAlignment(JLabel.CENTER);// set text left,center,right of image
        label.setVerticalAlignment(JLabel.TOP);// set text top,center, botom of imageicon
        label.setForeground(new Color(0x4CE72D));
        label.setFont(new Font("MV Boli",Font.BOLD,18));//set font 

        // This is for entire project - basic structure
        this.setTitle("Welcome to, YOUTH CAREER HUB");// SET TITLE OF FRAME
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// exit out of ap[plication
        this.setResizable(false); // prevent frame from being resized
        this.setSize(500, 500);// set the x dimension and y dimension
        this.setVisible(true);// make frame visible
        this.add(label);// it will add the label
    }
}
