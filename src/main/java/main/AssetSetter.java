package main;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 25 * gp.tileSize;
        gp.obj[0].worldY = 11 * gp.tileSize;

        gp.obj[1] = new OBJ_Key(gp);
        gp.obj[1].worldX = 6 * gp.tileSize;
        gp.obj[1].worldY = 21 * gp.tileSize;

        gp.obj[2] = new OBJ_Chest(gp);
        gp.obj[2].worldX = 43 * gp.tileSize;
        gp.obj[2].worldY = 16 * gp.tileSize;

        gp.obj[3] = new OBJ_Door(gp);
        gp.obj[3].worldX = 29 * gp.tileSize;
        gp.obj[3].worldY = 24 * gp.tileSize;

        gp.obj[4] = new OBJ_Chest(gp);
        gp.obj[4].worldX = 13 * gp.tileSize;
        gp.obj[4].worldY = 11 * gp.tileSize;

        gp.obj[5] = new OBJ_Door(gp);
        gp.obj[5].worldX = 13 * gp.tileSize;
        gp.obj[5].worldY = 14 * gp.tileSize;

        gp.obj[6] = new OBJ_Boots(gp);
        gp.obj[6].worldX = 38 * gp.tileSize;
        gp.obj[6].worldY = 31 * gp.tileSize;
    }
}
