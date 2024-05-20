package gui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

//buat nyimpen method yang mungkin dipake berulang-ulang
public class Template {
    public static JButton addButtons(JPanel panelBG, BufferedImage img, String path, int buttonWidth, int buttonHeight, int imageWidth, int imageHeight, int x, int y, String name) {
        try{
            img = ImageIO.read(new File(path));
        } catch(IOException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
        
        BufferedImage iconImage;
        iconImage = resize(img, imageWidth, imageHeight);
        Image scaledButtonImage = iconImage.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
        
        JButton button = new JButton(new ImageIcon(scaledButtonImage));
        button.setBounds(x, y, buttonWidth, buttonHeight);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        return button;
    }

    public static JButton createButtonWithGIF(JPanel panelBG, String path, int buttonWidth, int buttonHeight, int x, int y) {
        ImageIcon icon = new ImageIcon(path);
        JButton button = new JButton(icon);
        button.setBounds(x, y, buttonWidth, buttonHeight);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        return button;
    }
    
    public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
    
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
    
        return dimg;
    }
}
