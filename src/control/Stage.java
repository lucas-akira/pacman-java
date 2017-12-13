/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import elements.Blinky;
import elements.Clyde;
import elements.Element;
import elements.Fruit;
import elements.Inky;
import elements.Lolo;
import elements.PacDot;
import elements.Pinky;
import elements.PowerPallet;
import elements.Wall;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Consts;

/**
 *
 * @author akira
 */
public class Stage {

    // Atributos da classe
    private Lolo lolo;
    private Blinky blinky;
    private Pinky pinky;
    private Inky inky;
    private Clyde clyde;
    private ArrayList<Element> elemArray;
    private final GameController controller;
    private int level;
    private int score;
    private String[] map;
    Timer timerCherry;
    Timer timerStrawberry;
    Fruit cherry;
    Fruit strawberry;

    public Stage() {
        // Inicializa os atributos
        controller = new GameController();
        map = new String[3];
        timerCherry = new Timer();
        timerStrawberry = new Timer();
        cherry = new Fruit("cherry.png", Consts.CHERRY_SCORE, timerCherry);
        strawberry = new Fruit("strawberry.png", Consts.STRAWBERRY_SCORE, timerStrawberry);
        elemArray = new ArrayList<>();
        lolo = new Lolo("pacman_r.png");
        blinky = new Blinky("blinky.png");
        pinky = new Pinky("pinky.png");
        inky = new Inky("inky.png");
        clyde = new Clyde("clyde.png");
        level = 0;
        score = 0;

        // Declaração dos três mapas de jogo
        // Cada variável representa 1/4 do mapa total, e é refletida para formar o mapa de modo simétrico
        //map[0] = "1111111111100001000010110101111011000001100101110111010100011000010001101111011110100000001000111011";
        map[0] = "1111111111130000000110111011011000000000101110101110000010011111100101222211010122221000222222101100";
        map[1] = "1111111111130001000010110101111011000001100101110111010111011000010001101111011110000000001010110111";
        map[2] = "1111111111100001000010110101111011000001100101110111010111011000010001101111011110000000001010110111";
        //map[3] = "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
    }
    public void runStage() {
        // Limpa o array de elementos 
        elemArray.clear();
        
        // Adiciona os elementos em elemArray
        addElements();
        // Configura os timers
        configureTimers();
    }
    public void drawStage(int x, int y, int width, int height, BufferStrategy bufferStrategy) {
        Graphics g = bufferStrategy.getDrawGraphics();
        
        /*Contexto grafico*/
        Graphics g2 = g.create(x, y, width, height);
        
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
        String temp = "Score: " + lolo.getScore() + " Level: " + level + " Poder: " + controller.poderAtivado + " Vidas: " + lolo.getLife();
        g2.drawString(temp, 10, Consts.NUM_CELLS*Consts.CELL_SIZE+15);
        g.dispose();
        g2.dispose();
        if (!bufferStrategy.contentsLost()) {
            bufferStrategy.show();
        }
    }

    public GameController getController() {
        return controller;
    }
    
    private Wall selectWall(int level) {
        switch (level) {
            case 0:
                return new Wall("wall.png");
            case 1:
                return new Wall("wall2.png");
            default:
                return new Wall("wall3.png");
        }
    }

    private void addElements() {
        // Coloca o Pacman e os fantasmas na posição inicial
        lolo.setPosition(13, 10);
        blinky.setPosition(10, 8);
        pinky.setPosition(10, 9);
        inky.setPosition(10, 10);
        clyde.setPosition(10, 11);

        // Adiciona-os no array de elementos
        elemArray.add(lolo);
        elemArray.add(blinky);
        elemArray.add(pinky);
        elemArray.add(inky);
        elemArray.add(clyde);
        

        Wall wall;
        // Adiciona as paredes extras sem simetria
        for (int i = 7; i < 13; i++) {
            // Seleciona qual parede usar de acordo com o level
            wall = selectWall(level);
            wall.setPosition(11, i);
            elemArray.add(wall);
        }
        for (int i = 9; i < 11; i++) {
            wall = selectWall(level);
            wall.setPosition(9, i);
            elemArray.add(wall);
        }
        for (int i = 0; i < 2; i++) {
            wall = selectWall(level);
            wall.setPosition(12, 8 + 3 * i);
            elemArray.add(wall);
        }
        
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
                if (aux) {                    
                    // Adiciona um pedaço de parede onde map tem '1'
                    switch (map[level].charAt(i*Consts.NUM_CELLS/2 + j)) {
                        case '1':
                            // Desenha o quarto superior esquerdo do mapa
                            wall = selectWall(level);
                            wall.setPosition(i, j);
                            elemArray.add(wall);
                            // Desenha o quarto inferior esquerdo do mapa
                            wall = selectWall(level);
                            wall.setPosition(Consts.NUM_CELLS - 1 - i, j);
                            elemArray.add(wall);
                            // Desenha o quarto inferior direito do mapa
                            wall = selectWall(level);
                            wall.setPosition(Consts.NUM_CELLS - 1 - i,Consts.NUM_CELLS - 1 - j);
                            elemArray.add(wall);
                            // Desenha o quarto superior direito do mapa
                            wall = selectWall(level);
                            wall.setPosition(i,Consts.NUM_CELLS - 1 - j);
                            elemArray.add(wall);
                            break;
                        case '0':
                            PacDot pacDot = new PacDot("dot.png");
                            pacDot.setPosition(i, j);
                            elemArray.add(pacDot);
                            lolo.totalDots++;
                            pacDot = new PacDot("dot.png");
                            pacDot.setPosition(Consts.NUM_CELLS - 1 - i, j);
                            elemArray.add(pacDot);
                            lolo.totalDots++;
                            pacDot = new PacDot("dot.png");
                            pacDot.setPosition(Consts.NUM_CELLS - 1 - i,Consts.NUM_CELLS - 1 - j);
                            elemArray.add(pacDot);
                            lolo.totalDots++;
                            pacDot = new PacDot("dot.png");
                            pacDot.setPosition(i,Consts.NUM_CELLS - 1 - j);
                            elemArray.add(pacDot);
                            lolo.totalDots++;
                            break;
                        case '3':
                            PowerPallet powerPallet = new PowerPallet("powerpallet.png");
                            powerPallet.setPosition(i, j);
                            elemArray.add(powerPallet);
                            lolo.totalDots++;
                            powerPallet = new PowerPallet("powerpallet.png");
                            powerPallet.setPosition(Consts.NUM_CELLS - 1 - i, j);
                            elemArray.add(powerPallet);
                            lolo.totalDots++;
                            powerPallet = new PowerPallet("powerpallet.png");
                            powerPallet.setPosition(Consts.NUM_CELLS - 1 - i,Consts.NUM_CELLS - 1 - j);
                            elemArray.add(powerPallet);
                            lolo.totalDots++;
                            powerPallet = new PowerPallet("powerpallet.png");
                            powerPallet.setPosition(i,Consts.NUM_CELLS - 1 - j);
                            elemArray.add(powerPallet);
                            lolo.totalDots++;
                            break;
                        default:
                            break;
                    }
                }
            }
        }       
    }
    
    private void configureTimers() {
        timerCherry.cancel();
        timerCherry = new Timer();
        timerCherry.schedule(new InicioCherry(), Consts.CHERRY_TEMPO_INICIO);  
        timerStrawberry.cancel();
        timerStrawberry = new Timer();
        timerStrawberry.schedule(new InicioStrawberry(), Consts.STRAWBERRY_TEMPO_INICIO);
    }
    
    public void loadStage(Save load) {
        level = load.getLevel();
        lolo = load.getLolo();
        lolo.setMovDirection(Lolo.STOP);
        elemArray.clear();
        elemArray.addAll(load.getElemArray());
        elemArray.set(0, lolo);
        controller.poderAtivado = load.getIsPoderAtivo();
        if(controller.poderAtivado){
            controller.timerPoder.cancel();
            controller.timerPoder = new Timer();
            controller.timerPoder.schedule(controller.new FimPoder(elemArray), 7000);
        }
        timerCherry.cancel();
        timerCherry = new Timer();
        timerCherry.schedule(new InicioCherry(), Consts.CHERRY_TEMPO_INICIO);
        timerStrawberry.cancel();
        timerStrawberry = new Timer();
        timerStrawberry.schedule(new InicioStrawberry(), Consts.STRAWBERRY_TEMPO_INICIO);       
    }
    
    public Blinky getBlinky() {
        return blinky;
    }

    public void setBlinky(Blinky blinky) {
        this.blinky = blinky;
    }

    public Pinky getPinky() {
        return pinky;
    }

    public void setPinky(Pinky pinky) {
        this.pinky = pinky;
    }

    public Inky getInky() {
        return inky;
    }

    public void setInky(Inky inky) {
        this.inky = inky;
    }

    public Clyde getClyde() {
        return clyde;
    }

    public void setClyde(Clyde clyde) {
        this.clyde = clyde;
    }

    public Lolo getLolo() {
        return lolo;
    }

    public void setLolo(Lolo lolo) {
        this.lolo = lolo;
    }
     
    public ArrayList<Element> getElemArray() {
        return elemArray;
    }

    public void setElemArray(ArrayList<Element> elemArray) {
        this.elemArray = elemArray;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void addLevel(int addition) {
        this.level += addition;
    }
    
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String[] getMap() {
        return map;
    }

    public void setMap(String[] map) {
        this.map = map;
    }

    public Timer getTimerCherry() {
        return timerCherry;
    }

    public void setTimerCherry(Timer timerCherry) {
        this.timerCherry = timerCherry;
    }

    public Timer getTimerStrawberry() {
        return timerStrawberry;
    }

    public void setTimerStrawberry(Timer timerStrawberry) {
        this.timerStrawberry = timerStrawberry;
    }

    public Fruit getCherry() {
        return cherry;
    }

    public void setCherry(Fruit cherry) {
        this.cherry = cherry;
    }

    public Fruit getStrawberry() {
        return strawberry;
    }

    public void setStrawberry(Fruit strawberry) {
        this.strawberry = strawberry;
    }
    
   /////////////////////////////////////////////// Classes e métodos para os timers/////////////////////////////////////////////////////////////////////
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
        @Override
        public void run(){
            elemArray.remove(cherry);
            timerCherry.schedule(new InicioCherry(), Consts.CHERRY_TEMPO_INICIO);
        }
    }
    
    public class InicioStrawberry extends TimerTask{
        @Override
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
        @Override
        public void run(){
            elemArray.remove(strawberry);
            timerStrawberry.schedule(new InicioStrawberry(), Consts.STRAWBERRY_TEMPO_INICIO);
        }
    }
    
    private boolean isValidFruitPosition(Fruit f){
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
    public void startTimers() {
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
    }

}
