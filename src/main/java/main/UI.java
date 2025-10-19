package main;

import object.OBJ_Key;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font kiwiSoda, pixeled, kiwiSoda_32, pixeled_18;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;

    double playTime;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/KiwiSoda.ttf");
            kiwiSoda = Font.createFont(Font.TRUETYPE_FONT, is);
            kiwiSoda_32 = kiwiSoda.deriveFont(32f);
            is = getClass().getResourceAsStream("/font/Pixeled.ttf");
            pixeled = Font.createFont(Font.TRUETYPE_FONT, is);
            pixeled_18 = pixeled.deriveFont(18f);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(pixeled_18);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        // TITLE STATE
        if(gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        // PLAY STATE
        if(gp.gameState == gp.playState) {
            // do playstate stuff
        }
        // PAUSE STATE
        if(gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        // DEBUG
        if(gp.debugEnabled && gp.gameState == gp.playState) {
            drawDebugMenu(this.gp);
        }
        // DIALOGUE STATE
        if(gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
    }

    public void drawTitleScreen() {
        // bg color
        //g2.setColor(new Color(70,120,80));
        //g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);

        // TITLE NAME
        g2.setFont(kiwiSoda);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,118F));
        String text = "RPG Game";
        int x = getXforCenteredText(text);
        int y = gp.tileSize*3;

        // SHADOW
        g2.setColor(Color.gray);
        g2.drawString(text,x+5,y+5);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // CHARACTER IMAGE
        x = gp.screenWidth/2 - (gp.tileSize*2)/2;
        y += gp.tileSize*2;
        g2.drawImage(gp.npc[0].down1, x, y, gp.tileSize*2, gp.tileSize*2, null);

        //MENU
        g2.setFont(pixeled);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,34F));

        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.drawString("-", x-gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1) {
            g2.drawString("-", x-gp.tileSize, y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2) {
            g2.drawString("-", x-gp.tileSize, y);
        }
    }

    public void drawDebugMenu(GamePanel gp) {
        this.gp = gp;

        String fpsText = "FPS: " + gp.currentFPS;

        g2.setFont(pixeled_18);
        g2.drawString(fpsText, 50, 50);
    }

    public void drawPauseScreen() {
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {
        // WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;
        drawSubWindow(x, y, width, height);

        g2.setFont(kiwiSoda);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,35F));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0,0,0,220);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35,35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}