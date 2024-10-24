package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed, sprintPressed, objecInteraction, projectileK, debugK;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if(gp.gameState == gp.titleState){
            if(code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.gameState = gp.playState;
                    
                }
                if(gp.ui.commandNum == 1){

                }
                if(gp.ui.commandNum == 2){
                    System.exit(0);
                }
            }
        }
        if(gp.gameState == gp.playState){
            if(code == KeyEvent.VK_W){
                upPressed = true;
            }
            if(code == KeyEvent.VK_S){
                downPressed = true;
            }
            if(code == KeyEvent.VK_A){
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D){
                rightPressed = true;
            }
            if(code == KeyEvent.VK_K){
                sprintPressed = true;
            }
            if(code == KeyEvent.VK_J){
                objecInteraction = true;
            }
            if(code == KeyEvent.VK_P){
                projectileK = true;
            }
            if(code == KeyEvent.VK_Q){
                
                gp.gameState = gp.pauseState;
            }
            if(code == KeyEvent.VK_T){
                if(debugK == false){
                    debugK = true;
                }
                else if(debugK == true){
                    debugK = false;
                }
    
            }

        }

        //Pause State
        if(gp.gameState == gp.pauseState){
            if(code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 4){
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                System.out.println("Current commandNum: " + gp.ui.commandNum);
                if(gp.ui.commandNum == 0){
                   
                    
                }
                if(gp.ui.commandNum == 1){
                    gp.gameState = gp.spellMenuState;
                }
                if(gp.ui.commandNum == 2){
                    
                }
                if(gp.ui.commandNum == 3){
                   
                }
                if(gp.ui.commandNum == 4){
                     gp.gameState = gp.playState;
                }
            }
        }
        //Dialouge State
       if(gp.gameState == gp.dialogeState){
            if(code == KeyEvent.VK_J){
                gp.gameState = gp.playState;
            }
       }

       if(gp.gameState == gp.spellMenuState){

       if(code == KeyEvent.VK_D){
        gp.ui.subCommandNum++;
            if(gp.ui.subCommandNum > 1){
                gp.ui.subCommandNum = 0;
            }
        }
        if(code == KeyEvent.VK_A){
            gp.ui.subCommandNum--;
                if(gp.ui.subCommandNum < 0){
                    gp.ui.subCommandNum = 1;
                }
        }
        
            if(gp.ui.subCommandNum == 0){
                if(code == KeyEvent.VK_SPACE){
                    gp.player.currSpell = "Healing";
                    gp.gameState = gp.playState;
                    System.out.println("Healing Spell Selected");
                    System.out.println("Player current spell:" + gp.player.currSpell);

                }
                
            }
            if(gp.ui.subCommandNum == 1){
                if(code == KeyEvent.VK_SPACE){
                    gp.player.currSpell = "Fire Ball";
                    gp.gameState = gp.playState;
                    System.out.println("Fire Spell Selected");
                    System.out.println("Player current spell:" + gp.player.currSpell);

                }
            }
            
        

       }

        
    }



    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_K){
            sprintPressed = false;
        }
        if(code == KeyEvent.VK_J){
            objecInteraction = false;
        }
        if(code == KeyEvent.VK_P){
            projectileK = false;
        }

    }




}
