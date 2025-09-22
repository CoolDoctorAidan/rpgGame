package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UltilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferDouble;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public BufferedImage image = null;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    int pixelCounter = 0;
    boolean hasBoots = false;
    boolean moving = false;
    int standCounter = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 1;
        solidArea.y = 1;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 46;
        solidArea.height = 46;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        worldX = gp.tileSize * 25;
        worldY = gp.tileSize * 25;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage() {
        up1 = setup("boy_up_1");
        up2 = setup("boy_up_2");
        down1 = setup("boy_down_1");
        down2 = setup("boy_down_2");
        left1 = setup("boy_left_1");
        left2 = setup("boy_left_2");
        right1 = setup("boy_right_1");
        right2 = setup("boy_right_2");
    }

    public BufferedImage setup(String imageName) {
        UltilityTool uTool = new UltilityTool();
        BufferedImage scaledImage = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update() {
        if (!moving) {
            if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
                if(keyH.upPressed) {
                    direction = "up";
                }
                if(keyH.downPressed) {
                    direction = "down";
                }
                if(keyH.leftPressed) {
                    direction = "left";
                }
                if(keyH.rightPressed) {
                    direction = "right";
                }

                moving = true;

                // CHECK TILE COLLISION
                collisionOn = false;
                gp.collisionCheck.CheckTile(this);

                // CHECK OBJECT COLLISION
                int objIndex = gp.collisionCheck.CheckObject(this, true);
                pickUpObject(objIndex);
            }
            else {
                // IDLE ANIMATION
                standCounter++;

                if(standCounter == 20) {
                    spriteNum = 1;
                    standCounter = 0;
                }
            }
        }
        if(moving) {
            // IF COLLISION IS FALSE PLAYER CAN MOVE
            if(!collisionOn) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if(spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0; // reset only when toggling sprite
            }

            pixelCounter += speed;

            if (pixelCounter == 48) {
                moving = false;
                pixelCounter = 0;
            }
        }
    }

    public void pickUpObject(int i) {
        if(i != 999) {
            String objectName = gp.obj[i].name;

            switch(objectName) {
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("you got a key");
                    break;
                case "Door":
                    if(hasKey > 0) {
                        gp.playSE(3);
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("you opened a door");
                    }
                    else {
                        gp.ui.showMessage("you need a key");
                    }
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
                case "Boots":
                    gp.playSE(2);
                    hasBoots = true;
                    speed += 1.5;
                    gp.obj[i] = null;
                    gp.ui.showMessage("you got some boots");
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        switch(direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, null);
        // draw collider
        // g2.setColor(Color.red);
        // g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }
}
