package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_25, arial_40, arial_40B;
    // BufferedImage keyImage;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_25 = new Font("Arial", Font.PLAIN, 25);
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_40B = new Font("Arial", Font.BOLD, 40);
        //OBJ_Key key = new OBJ_Key(gp);
        //keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        if(gp.gameState == gp.playState) {
            // do playstate stuff
        }
        if(gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        if(gp.debugEnabled && gp.gameState == gp.playState) {
            drawDebugMenu(this.gp);
        }
    }

    public void drawDebugMenu(GamePanel gp) {
        this.gp = gp;

        String fpsText = "FPS: " + gp.currentFPS;

        g2.setFont(arial_25);
        g2.drawString(fpsText, 50, 50);
    }

    public void drawPauseScreen() {
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}