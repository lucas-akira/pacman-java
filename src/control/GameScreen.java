package control;

import elements.Blinky;
import elements.Clyde;
//import elements.Skull;
import elements.Lolo;
import elements.Element;
import elements.Fruit;
import elements.Inky;
import elements.PacDot;
import elements.Pinky;
import elements.PowerPallet;
import elements.Wall;
import java.awt.Color;
import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Position;

/**
 * Projeto de POO 2017
 * 
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */
public class GameScreen extends javax.swing.JFrame implements KeyListener {
    
    private Lolo lolo;
    private ArrayList<Element> elemArray;
    private final GameController controller = new GameController();
    private int level = 0;
    private int score = 0;
    private String[] map = new String[3];
    Timer timerCherry = new Timer();
    Timer timerStrawberry = new Timer();
    Fruit cherry = new Fruit("cherry.png", Consts.CHERRY_SCORE, timerCherry);
    Fruit strawberry = new Fruit("strawberry.png", Consts.STRAWBERRY_SCORE, timerStrawberry);

    public GameScreen() {
        Drawing.setGameScreen(this);
        initComponents();
        
        this.addKeyListener(this);   // Adiciona o funcionamento do teclado
        
        // Declaração dos três mapas de jogo
        // Cada variável representa 1/4 do mapa total, e é refletida para formar o mapa de modo simétrico
        //map[0] = "1111111111100001000010110101111011000001100101110111010100011000010001101111011110100000001000111011";
        map[0] = "1111111111130000000110111011011000000000101110101110000010011111101101222210100122221000222222101100";
        map[1] = "1111111111100001000010110101111011000001100101110111010101011000010001101111011110100000001010111011";
        map[2] = "1111111111100001000010110101111011000001100101110111010101011000010001101111011110100000001010111011";
        //map[3] = "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        
        //Cria a janela do tamanho do tabuleiro + insets (bordas) da janela
        this.setSize(Consts.NUM_CELLS * Consts.CELL_SIZE + getInsets().left + getInsets().right,
                     (Consts.NUM_CELLS+1) * Consts.CELL_SIZE + getInsets().top + getInsets().bottom);

        elemArray = new ArrayList<Element>();
        // Cria o Pacman na posição inicial
        lolo = new Lolo("pacman_r.png");
        lolo.setPosition(13, 10);
        this.addElement(lolo);
        
        // Função que desenha o restante dos elementos na tela
        correctBuggyMap(map[level]);
    }
    
    public void correctBuggyMap(String map){
        
        // Limpa o array de elementos 
        elemArray.clear();
        
        // Adiciona novamente o Pacman no cenário
        lolo.setPosition(13, 10);
        elemArray.add(lolo);
        
        // Cria os fantasmas e adiciona eles na tela
        Blinky blinky = new Blinky("blinky.png");
        blinky.setPosition(9,8);
        this.addElement(blinky);

        Pinky pinky = new Pinky("pinky.png");
        pinky.setPosition(9,9);
        this.addElement(pinky);

        Inky inky = new Inky("inky.png");
        inky.setPosition(9,10);
        this.addElement(inky);

        Clyde clyde = new Clyde("clyde.png");
        clyde.setPosition(9,11);
        this.addElement(clyde);
       
        
        // Adiciona os PacDots
        
        // Para cada elemento da tela, verifica se é uma posição válida
        for (int i = 0; i < Consts.NUM_CELLS/2; i++) {
            for (int j = 0; j < Consts.NUM_CELLS/2; j++) {
                boolean aux = true;
                for(int k = 0; k < elemArray.size(); k++){
                    if((elemArray.get(k).getPosition().getX() == i || elemArray.get(k).getPosition().getX() == Consts.NUM_CELLS - 1 - i) && 
                            (elemArray.get(k).getPosition().getY() == j || elemArray.get(k).getPosition().getY() == Consts.NUM_CELLS - 1 - j)){
                        aux = false;
                    }
                 }
                
                // Se sim, adiciona na tela
                if (aux){
                    
                    // Adiciona um pedaço de parede onde map tem '1'
                    if(map.charAt(i*Consts.NUM_CELLS/2 + j) == '1'){
                        Wall w;
                        
                        // Desenha o quarto superior esquerdo do mapa
                        if(level == 0)
                            w = new Wall("wall.png");
                        else if(level == 1)
                            w = new Wall("wall2.png");
                        else w = new Wall("wall3.png");
                        
                        w.setPosition(i, j);
                        elemArray.add(w);
                        
                        // Desenha o quarto inferior esquerdo do mapa
                        if(level == 0)
                            w = new Wall("wall.png");
                        else if(level == 1)
                            w = new Wall("wall2.png");
                        else w = new Wall("wall3.png");
                        
                        w.setPosition(Consts.NUM_CELLS - 1 - i, j);
                        elemArray.add(w);
                        
                        // Desenha o quarto inferior direito do mapa
                        if(level == 0)
                            w = new Wall("wall.png");
                        else if(level == 1)
                            w = new Wall("wall2.png");
                        else w = new Wall("wall3.png");
                        
                        w.setPosition(Consts.NUM_CELLS - 1 - i,Consts.NUM_CELLS - 1 - j);
                        elemArray.add(w);
                        
                        // Desenha o quarto superior direito do mapa
                        if(level == 0)
                            w = new Wall("wall.png");
                        else if(level == 1)
                            w = new Wall("wall2.png");
                        else w = new Wall("wall3.png");
                                                
                        w.setPosition(i,Consts.NUM_CELLS - 1 - j);
                        elemArray.add(w);
                    }   
                    
                    // Se não for parede, adiciona-se o pacdot
                    else if (map.charAt(i*Consts.NUM_CELLS/2 + j) == '0'){
                        PacDot p = new PacDot("dot.png");
                        p.setPosition(i, j);
                        elemArray.add(p);
                        lolo.totalDots++;
                        
                        p = new PacDot("dot.png");
                        p.setPosition(Consts.NUM_CELLS - 1 - i, j);
                        elemArray.add(p);
                        lolo.totalDots++;
                        
                        p = new PacDot("dot.png");
                        p.setPosition(Consts.NUM_CELLS - 1 - i,Consts.NUM_CELLS - 1 - j);
                        elemArray.add(p);
                        lolo.totalDots++;
                        
                        p = new PacDot("dot.png");
                        p.setPosition(i,Consts.NUM_CELLS - 1 - j);
                        elemArray.add(p);
                        lolo.totalDots++;
                    }
                    else if ((map.charAt(i*Consts.NUM_CELLS/2 + j) == '3')){
                        PowerPallet pp = new PowerPallet("powerpallet.png");
                        pp.setPosition(i, j);
                        elemArray.add(pp);
                        lolo.totalDots++;
                        
                        pp = new PowerPallet("powerpallet.png");
                        pp.setPosition(Consts.NUM_CELLS - 1 - i, j);
                        elemArray.add(pp);
                        lolo.totalDots++;
                        
                        pp = new PowerPallet("powerpallet.png");
                        pp.setPosition(Consts.NUM_CELLS - 1 - i,Consts.NUM_CELLS - 1 - j);
                        elemArray.add(pp);
                        lolo.totalDots++;
                        
                        pp = new PowerPallet("powerpallet.png");
                        pp.setPosition(i,Consts.NUM_CELLS - 1 - j);
                        elemArray.add(pp);
                        lolo.totalDots++;
                    }
                     
                }
            }
        }

        timerCherry.cancel();
        timerCherry = new Timer();
        timerCherry.schedule(new InicioCherry(), Consts.CHERRY_TEMPO_INICIO);
        
        timerStrawberry.cancel();
        timerStrawberry = new Timer();
        timerStrawberry.schedule(new InicioStrawberry(), Consts.STRAWBERRY_TEMPO_INICIO);
    }
    
    public final void addElement(Element elem) {
        elemArray.add(elem);
    }
    
    public void removeElement(Element elem) {
        elemArray.remove(elem);
    }
    
    @Override
    public void paint(Graphics gOld) {
        Graphics g = getBufferStrategy().getDrawGraphics();
        
        /*Criamos um contexto grafico*/
        Graphics g2 = g.create(getInsets().right, getInsets().top, getWidth() - getInsets().left, getHeight() - getInsets().bottom);
        
        if(cherry.isEaten){
            cherry.isEaten = false;
            timerCherry.cancel();
            timerCherry = new Timer();
            timerCherry.schedule(new InicioCherry(), Consts.CHERRY_TEMPO_INICIO);
        }
        if(strawberry.isEaten){
            strawberry.isEaten = false;
            timerStrawberry.cancel();
            timerStrawberry = new Timer();
            timerStrawberry.schedule(new InicioStrawberry(), Consts.STRAWBERRY_TEMPO_INICIO);
        }
        
        /* DESENHA CENARIO
           Trocar essa parte por uma estrutura mais bem organizada
           Utilizando a classe Stage
        */
        for (int i = 0; i <= Consts.NUM_CELLS; i++) {
            for (int j = 0; j < Consts.NUM_CELLS; j++) {
                try {
                    Image newImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "background1.png");
                    g2.drawImage(newImage,
                            j * Consts.CELL_SIZE, i * Consts.CELL_SIZE, Consts.CELL_SIZE, Consts.CELL_SIZE, null);
                    
                } catch (IOException ex) {
                    Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        this.controller.drawAllElements(elemArray, g2);
        this.controller.processAllElements(elemArray);
        g2.setColor(Color.WHITE);
        String temp = "Score: " + lolo.getScore() + " Level: " + level + " Poder: " + controller.poderAtivado;
        g2.drawString(temp, 10, Consts.NUM_CELLS*Consts.CELL_SIZE+15);
        // Sistema de progressão de level baseado no número de pacdots na tela
        if(lolo.totalDots == 0){
            controller.poderAtivado = false;
            level++;
            if(level > 2)
                level = 0;
            correctBuggyMap(map[level]);
        }
        
        this.setTitle("-> Cell: " + lolo.getStringPosition() + "Score: " + lolo.getScore() + "Level: " + level);
        
        g.dispose();
        g2.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
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
            lolo.setMovDirection(Lolo.MOVE_UP);
            lolo.setCurrentMove(Lolo.MOVE_UP);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            lolo.setMovDirection(Lolo.MOVE_DOWN);
            lolo.setCurrentMove(Lolo.MOVE_DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            lolo.setMovDirection(Lolo.MOVE_LEFT);
            lolo.setCurrentMove(Lolo.MOVE_LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            lolo.setMovDirection(Lolo.MOVE_RIGHT);
            lolo.setCurrentMove(Lolo.MOVE_RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            lolo.setMovDirection(Lolo.STOP);
        } else if (e.getKeyCode() == KeyEvent.VK_T) {
            Save save = new Save(elemArray, level, lolo);
            save.SaveFile("Save.dat");
        } else if (e.getKeyCode() == KeyEvent.VK_Y) {
            Save load = new Save(elemArray, level, lolo);
            load.LoadFile("Save.dat");
            level = load.getLevel();
            lolo = load.getLolo();
            lolo.setMovDirection(Lolo.STOP);
            elemArray.clear();
            elemArray.addAll(load.getElemArray());
            elemArray.set(0, lolo);
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            //Cheat para testes, para pular de level
            lolo.totalDots=0;
        }
        
        //repaint(); /*invoca o paint imediatamente, sem aguardar o refresh*/
    }
    
    public class InicioCherry extends TimerTask{
        public void run(){
            cherry = new Fruit("cherry.png", Consts.CHERRY_SCORE, timerCherry);
            do{
                cherry.setPosition((int)(Math.random()*Consts.NUM_CELLS), (int)(Math.random()*Consts.NUM_CELLS));
            }while(!controller.isValidPosition(elemArray, cherry) || !isValidFruitPosition(cherry));
            elemArray.add(cherry);
            timerCherry.schedule(new FimCherry(cherry), Consts.CHERRY_TEMPO_FIM);
        }
    }
    public class FimCherry extends TimerTask{
        Fruit cherry;
        public FimCherry(Fruit cherry){
            super();
            this.cherry = cherry;
        }
        public void run(){
            elemArray.remove(cherry);
            timerCherry.schedule(new InicioCherry(), Consts.CHERRY_TEMPO_INICIO);
        }
    }
    
    public class InicioStrawberry extends TimerTask{
        public void run(){
            strawberry = new Fruit("strawberry.png", Consts.STRAWBERRY_SCORE, timerStrawberry);
            do{
                strawberry.setPosition((int)(Math.random()*Consts.NUM_CELLS), (int)(Math.random()*Consts.NUM_CELLS));
            }while(!controller.isValidPosition(elemArray, strawberry) || !isValidFruitPosition(strawberry));
            elemArray.add(strawberry);
            timerStrawberry.schedule(new FimStrawberry(strawberry), Consts.STRAWBERRY_TEMPO_FIM);
        }
    }
    public class FimStrawberry extends TimerTask{
        Fruit strawberry;
        public FimStrawberry(Fruit strawberry){
            super();
            this.strawberry = strawberry;
        }
        public void run(){
            elemArray.remove(strawberry);
            timerStrawberry.schedule(new InicioStrawberry(), Consts.STRAWBERRY_TEMPO_INICIO);
        }
    }
    
    boolean isValidFruitPosition(Fruit f){
        int x = (int) f.getPosition().getX();
        int y = (int) f.getPosition().getY();
        int i, j;
        if(x < Consts.NUM_CELLS/2)
            i = x;
        else
            i = Consts.NUM_CELLS - x - 1;
        if(y < Consts.NUM_CELLS/2)
            j = y;
        else
            j = Consts.NUM_CELLS - y - 1;
        if(map[level].charAt(j + i*Consts.NUM_CELLS/2) == '0') return true;
        return false;
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
    
    public void setLevel(int level){
        this.level = level;
    }
}

