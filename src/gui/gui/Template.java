package gui;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Template {
    public static void addHoverEffect(JButton button, ImageIcon originalIcon) {
        // Add transparency effect
        float originalAlpha = 1.0f; // Original opacity
        float hoverAlpha = 0.7f; // Opacity on hover

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                // Apply hover effect (change opacity)
                BufferedImage hoverImage = applyOpacity((BufferedImage) originalIcon.getImage(), hoverAlpha);
                button.setIcon(new ImageIcon(hoverImage));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                // Revert to original opacity
                button.setIcon(originalIcon);
            }
        });
    }

    private static BufferedImage applyOpacity(BufferedImage originalImage, float opacity) {
        BufferedImage newImage = new BufferedImage(
            originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = newImage.createGraphics();
        g2d.setComposite(AlphaComposite.SrcOver.derive(opacity));
        g2d.drawImage(originalImage, 0, 0, null);
        g2d.dispose();
        return newImage;
    }

    public static JButton addButtons(JPanel panelBG, BufferedImage img, String path, int buttonWidth, int buttonHeight, int imageWidth, int imageHeight, int x, int y) {
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
        
        BufferedImage iconImage = resize(img, imageWidth, imageHeight);
        Image scaledButtonImage = iconImage.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
        
        JButton button = new JButton(new ImageIcon(scaledButtonImage));
        button.setBounds(x, y, buttonWidth, buttonHeight);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        return button;
    }

    public static ButtonWithIcon createButtonWithGIF(JPanel panelBG, String path, int buttonWidth, int buttonHeight, int x, int y) {
        ImageIcon icon = new ImageIcon(path);
        JButton button = new JButton(icon);
        button.setBounds(x, y, buttonWidth, buttonHeight);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        return new ButtonWithIcon(button, icon);
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
