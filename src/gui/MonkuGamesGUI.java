import javax.swing.*;
import java.awt.*;

public class MonkuGamesGUI {
    public static void main(String[] args) {
        // Membuat frame utama
        JFrame frame = new JFrame("Monku Games");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1450, 850);

        // Panel utama dengan layout null untuk posisi bebas
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(135, 206, 235)); // Warna latar belakang langit

        String imagePath = "asset/Blue Green Yellow Video.png"; 
        ImageIcon originalIcon = new ImageIcon(imagePath);
        if (originalIcon.getIconWidth() == -1) {
            System.out.println("Gambar tidak ditemukan: " + imagePath);
        } else {
            Image originalImage = originalIcon.getImage();
            int width = 1450;
            int height = 830;
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            JLabel background = new JLabel(resizedIcon);
            background.setBounds(0, 0, width, height);
            panel.add(background);

            // Lebar dan tinggi tombol
            int buttonWidth = 200;
            int buttonHeight = 50;
            int spacing = 20; // Jarak antara tombol

            // Menghitung posisi x untuk menempatkan tombol di tengah frame
            int totalButtonWidth = buttonWidth * 2 + spacing;
            int xStart = (frame.getWidth() - totalButtonWidth) / 2;
            int yPosition = height - buttonHeight - 200 ; // Menempatkan tombol sedikit di atas bagian bawah frame

            // Tombol New Games
            JButton newGameButton = new JButton("NEW GAMES");
            newGameButton.setBounds(xStart, yPosition, buttonWidth, buttonHeight);
            newGameButton.setFont(new Font("Arial", Font.BOLD, 18)); // Ganti dengan font yang sesuai
            newGameButton.setBackground(Color.WHITE);
            newGameButton.setForeground(Color.BLACK);
            background.add(newGameButton);

            // Tombol Load Games
            JButton loadGameButton = new JButton("LOAD GAMES");
            loadGameButton.setBounds(xStart + buttonWidth + spacing, yPosition, buttonWidth, buttonHeight);
            loadGameButton.setFont(new Font("Arial", Font.BOLD, 18)); // Ganti dengan font yang sesuai
            loadGameButton.setBackground(Color.WHITE);
            loadGameButton.setForeground(Color.BLACK);
            background.add(loadGameButton);

            // Menambahkan panel ke frame
            frame.setContentPane(panel);
            frame.setVisible(true);
        }
    }
}
