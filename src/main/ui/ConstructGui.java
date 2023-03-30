package ui;

import javax.swing.*;
import java.awt.*;

public class ConstructGui extends JFrame {

    private final int width = 1000;
    private final int height = 700;

    public ConstructGui(JFrame frame, JLabel background, JPanel panel) {

        //code used from: www.forums.oracle.com

        frame.setSize(this.width, this.height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        background.setBounds(0, 0, this.width, this.height);
        background.setVisible(true);

        panel.setLayout(null);
        panel.setVisible(true);
        panel.setOpaque(false);
        frame.add(panel);

        panel.add(background);
    }

    public void constructLabel(JLabel background, int x, int y, int w, int h,
                               String labelName, String fontName, boolean visibility, int style, int fontSize) {
        JLabel greetingMenuTitle = new JLabel(labelName);
        greetingMenuTitle.setBounds(x, y, w, h);
        greetingMenuTitle.setFont(new Font(fontName, style, fontSize));
        greetingMenuTitle.setVisible(visibility);
        background.add(greetingMenuTitle);
    }

    public void constructTextField(JTextField textField, JLabel background, int x, int y, int w, int h, String fontName,
                                   int style, int fontSize, boolean visibility) {
        textField.setFont(new Font(fontName, style, fontSize));
        textField.setBounds(x, y, w, h);
        textField.setVisible(visibility);
        background.add(textField);
    }

    public void constructTextArea(JTextArea textArea, JLabel background, int x, int y, int w, int h, String fontName,
                                  int style, int fontSize) {
        textArea.setFont(new Font(fontName, style, fontSize));
        textArea.setBounds(x, y, w, h);
        textArea.setVisible(true);
        background.add(textArea);
    }

}
