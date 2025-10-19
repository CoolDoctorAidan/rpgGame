package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_OldMan extends Entity{
    public NPC_OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("/npc/oldman_up_1");
        up2 = setup("/npc/oldman_up_2");
        down1 = setup("/npc/oldman_down_1");
        down2 = setup("/npc/oldman_down_2");
        left1 = setup("/npc/oldman_left_1");
        left2 = setup("/npc/oldman_left_2");
        right1 = setup("/npc/oldman_right_1");
        right2 = setup("/npc/oldman_right_2");
    }

    public void setDialogue() {
        dialogues[0] = "Hey, pal. Mind if you can do me \na favor?";
        dialogues[1] = "Someone stole my DOG!";
        dialogues[2] = "Would you be able to help me \nfind bubba?";
        dialogues[3] = "If you do, I will reward you \ngreatly!";
        dialogues[4] = "He's all I've got left and he's \ngetting pretty old";
        dialogues[5] = "I will do anything for you to get \nme closer to this thief.";
        dialogues[6] = "Good luck out there, I will be \navailable if you need me later!";
    }

    public void setAction() {
        actionLockCounter ++;

        if(actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100)+1; // pick up a # from 1 to 100

            if(i <= 25) {
                direction = "up";
            }
            if(i > 25 && i <= 50) {
                direction = "down";
            }
            if(i > 50 && i <= 75) {
                direction = "left";
            }
            if(i > 75 && i <= 100) {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }
    public void speak() {
        super.speak();
    }
}
