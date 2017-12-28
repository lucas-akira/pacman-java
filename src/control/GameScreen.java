package control;

import elements.Lolo;
import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Projeto de POO 2017
 * 
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */
public class GameScreen extends javax.swing.JFrame implements KeyListener {

    private Stage stage;
    private boolean cheatsEnabled = false;
    public GameScreen() {
        Drawing.setGameScreen(this);
        initComponents();
        
        this.addKeyListener(this);   // Adiciona o funcionamento do teclado

        //Cria a janela do tamanho do tabuleiro + insets (bordas) da janela
        this.setSize(Consts.NUM_CELLS * Consts.CELL_SIZE + getInsets().left + getInsets().right,
                     (Consts.NUM_CELLS+1) * Consts.CELL_SIZE + getInsets().top + getInsets().bottom);

        // Instancia a fase
        stage = new Stage();
        // Função que desenha o restante dos elementos na tela
        stage.runStage();
    }

    @Override
    public void paint(Graphics gOld) {
       
        stage.startTimers();     
        stage.drawStage(getInsets().right, getInsets().top, getWidth() - getInsets().left, getHeight() - getInsets().bottom, getBufferStrategy());
        
        // Sistema de progressão de level baseado no número de pacdots na tela
        if(stage.getLolo().totalDots == 0){
            stage.getController().poderAtivado = false;
            stage.addLevel(1);
            if(stage.getLevel() > 2)
                stage.setLevel(0);
            stage.runStage();
        } 
        if (stage.getController().gameOver == true) {
            stage.setLevel(3);
            stage.getController().gameOver = false;
            stage.runStage();
        }
        
        //this.setTitle("-> Cell: " + stage.getLolo().getStringPosition() + "Score: " + stage.getLolo().getScore() + "Level: " + stage.getLevel());
        this.setTitle("Pacman Grupo 6");
    }
    
    public void go() {
        TimerTask task = new TimerTask() {
            
            public void run() {
                repaint();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, Consts.DELAY);
    }
    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            stage.getLolo().setMovDirection(Lolo.MOVE_UP);
            stage.getLolo().setCurrentMove(Lolo.MOVE_UP);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            stage.getLolo().setMovDirection(Lolo.MOVE_DOWN);
            stage.getLolo().setCurrentMove(Lolo.MOVE_DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            stage.getLolo().setMovDirection(Lolo.MOVE_LEFT);
            stage.getLolo().setCurrentMove(Lolo.MOVE_LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            stage.getLolo().setMovDirection(Lolo.MOVE_RIGHT);
            stage.getLolo().setCurrentMove(Lolo.MOVE_RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            stage.getLolo().setMovDirection(Lolo.STOP);
        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SLASH) {
            if (stage.getLevel() == 3) {
                cheatsEnabled = false;                
            } else {
                // Ativa ou Desativa os cheats
                cheatsEnabled = !cheatsEnabled;
            }

            
        } else if (e.getKeyCode() == KeyEvent.VK_SLASH) {
            //Cheat para testes, para pular de level
            if (cheatsEnabled)
                stage.getLolo().totalDots = 0;
            
        } else if (e.getKeyCode() == KeyEvent.VK_T) {
            boolean ff1, ff2;
            ff1 = stage.getElemArray().remove(stage.getCherry());
            ff2 = stage.getElemArray().remove(stage.getStrawberry());
            Save save = new Save(stage.getElemArray(), stage.getLevel(), stage.getLolo(), stage.getController().poderAtivado);
            save.SaveFile("Save.dat");
            if(ff1) stage.getElemArray().add(stage.getCherry());
            if(ff2) stage.getElemArray().add(stage.getStrawberry());
        } else if (e.getKeyCode() == KeyEvent.VK_Y) {
            Save load = new Save(stage.getElemArray(), stage.getLevel(), stage.getLolo(), stage.getController().poderAtivado);
            load.LoadFile("Save.dat");
            stage.loadStage(load);
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            
            // Se esta na tela de Game Over:
            // Reinicia o jogo na primeira fase
            if (stage.getLevel() == 3) {
                stage.getLolo().addLife(4);
                stage.getLolo().setScore(0);
                stage.getLolo().totalDots = 0;
            }       
        } else if (e.getKeyCode() == KeyEvent.VK_P) {
            if (cheatsEnabled)
                stage.getLolo().addScore(1000);
        }
        
        //repaint(); /*invoca o paint imediatamente, sem aguardar o refresh*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SCC0604 - Pacman");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(20, 20));
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
}

