package main;

import entity.NPC_Bubba;
import entity.NPC_OldMan;
import entity.NPC_Rufus;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

    }

    public void setNPC() {
        gp.npc[97] = new NPC_Bubba(gp);

        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 13;
        gp.npc[0].worldY = gp.tileSize * 11;

        gp.npc[1] = new NPC_Rufus(gp);
        gp.npc[1].worldX = gp.tileSize * 15;
        gp.npc[1].worldY = gp.tileSize * 10;
    }
}
