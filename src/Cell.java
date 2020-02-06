import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Cell extends JLabel  {

    private static ArrayList<Color> cellColors = new ArrayList<>();

    Cell() {
        Color color = Color.decode("#0F0");
        setPreferredSize(new Dimension(100,100));
        //setOpaque(true);
        setBackground(color);
    }
}
