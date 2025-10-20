package entity;

import main.GamePanel;

public class NPC_Bubba extends Entity{
    public NPC_Bubba(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 0;

        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("/npc/bubba");
        up2 = setup("/npc/bubba1");
        down1 = setup("/npc/bubba");
        down2 = setup("/npc/bubba1");
        left1 = setup("/npc/bubba");
        left2 = setup("/npc/bubba1");
        right1 = setup("/npc/bubba");
        right2 = setup("/npc/bubba1");
    }

    public void setDialogue() {
        dialogues[0] = "Bubba: Grrrrrr";
        dialogues[1] = "Bubba: Ruff, Ruff!";
    }

    public void speak() {
        super.speak();
    }
}

