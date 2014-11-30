package src.john01dav.votifiertester.customswing;

import javax.swing.*;
import java.awt.*;

public class JPlaceholderTextField extends JTextField{
    private String placeholder = "";

    public void setPlaceholder(String placeholder){
        this.placeholder = placeholder;
    }

    public String getPlaceholder(){
        return placeholder;
    }

    public void paint(Graphics g){
        super.paint(g);
        if(getText().length() < 1) {
            g.setColor(Color.LIGHT_GRAY);
            g.setFont(getFont().deriveFont(Font.ITALIC));
            g.drawString(getPlaceholder(), 5, 17);
        }
    }

}
