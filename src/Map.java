import javax.swing.*;
import java.awt.*;

public class Map extends JPanel {
    public static final int GAME_MODE_HVH = 0;
    public static final int GAME_MODE_HVA = 1;

    Map() {
        setBackground(Color.BLACK);
    }

    void startNewGame(int gameMode, int fieldSizeX, int fieldSizeY, int winLength) {
        System.out.printf("game mode: %d\nfieldSize: %d\nwinLength: %d",
                gameMode, fieldSizeX, winLength);

        DONTWORK(fieldSizeX, fieldSizeY);
        // Я уже прошёл курс java2, там у нас был javafx. Можете посмотреть мою работу: https://github.com/Qioki/gb-java2/pull/8
        // Переписывать все сейчас на Javafx нет времени.
        // Разбираться со Swing нет желания...
    }

    private void DONTWORK(int fieldSizeX, int fieldSizeY){

        setLayout(new GridLayout(fieldSizeY, fieldSizeX));

        for (int i = 0; i < fieldSizeY; i++)
        {
            for (int j = 0; j < fieldSizeX; j++)
            {
                add(new JButton("swing - &%&#$!!!!!"));
                //add(new Cell());
            }
        }
    }
}