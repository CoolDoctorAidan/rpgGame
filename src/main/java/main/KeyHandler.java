package main;

import main.UI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener{
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    // public boolean showDebug = false;

    // DEBUG
    boolean debugEnabled = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // DONT USE, not needed
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // TITLE
        if(gp.gameState == gp.titleState) {
            if(gp.ui.titleScreenState == 0) {
                if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 2;
                    }
                }
                if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 2) {
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_E) {
                    if(gp.ui.commandNum == 0) {
                        // new game → go to difficulty menu
                        gp.ui.titleScreenState = 1;
                        gp.ui.commandNum = 0; // reset selection
                    }
                    if(gp.ui.commandNum == 1) {
                        // load game
                    }
                    if(gp.ui.commandNum == 2) {
                        // quit
                        System.exit(0);
                    }
                }
            }

            else if(gp.ui.titleScreenState == 1) {
                if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 3;
                    }
                }
                if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 3) {
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_E) {
                    if(gp.ui.commandNum == 0) { // easy
                        gp.difficulty = 0;
                        gp.player.setDefaultValues();
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if(gp.ui.commandNum == 1) { // normal
                        gp.difficulty = 1;
                        gp.player.setDefaultValues();
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if(gp.ui.commandNum == 2) { // hard
                        gp.difficulty = 2;
                        gp.player.setDefaultValues();
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if(gp.ui.commandNum == 3) {
                        gp.ui.titleScreenState = 0; // back
                        gp.ui.commandNum = 0;
                    }
                }
            }
        }

        // PLAY STATE
        if(gp.gameState == gp.playState) {
            if(code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if(code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if(code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if(code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
            if(code == KeyEvent.VK_F3) {
                if(!gp.debugEnabled) {
                    gp.debugEnabled = true;
                }
                else if(gp.debugEnabled) {
                    gp.debugEnabled = false;
                }
            }
        }
        // DIALOGUE STUFF
        else if(gp.gameState == gp.dialogueState) {
            if(code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
        }

        // PAUSE STUFF
        if(code == KeyEvent.VK_ESCAPE) {
            if(gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
            }
            else if(gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
