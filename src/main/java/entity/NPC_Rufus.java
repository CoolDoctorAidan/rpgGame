package entity;

import main.GamePanel;

public class NPC_Rufus extends Entity{
    public NPC_Rufus(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 0;

        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("/npc/rufus");
        up2 = setup("/npc/rufus1");
        down1 = setup("/npc/rufus");
        down2 = setup("/npc/rufus1");
        left1 = setup("/npc/rufus");
        left2 = setup("/npc/rufus1");
        right1 = setup("/npc/rufus");
        right2 = setup("/npc/rufus1");
    }

    public void setDialogue() {
        dialogues[0] = "Rufus: Grrrrrr";
        dialogues[1] = "Rufus: Ruff, Ruff!";
    }

    public void speak() {
        super.speak();
    }
}

