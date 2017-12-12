package control;

import elements.Element;
import elements.*;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Projeto de POO 2017
 * 
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */
public class GameController {
    
    Timer timerPoder = new Timer();
    boolean poderAtivado = false;
    
    // Imprime todos os elementos na tela chamando a função autoDraw de cada um deles.
    // Recebe um arrayList com todos os elementos.

    public void drawAllElements(ArrayList<Element> elemArray, Graphics g){
        for(int i=0; i<elemArray.size(); i++){
            elemArray.get(i).autoDraw(g);
        }
    }

    
    public void processAllElements(ArrayList<Element> e){
        if(e.isEmpty())
            return;
        
        Lolo lLolo = (Lolo)e.get(0);

        // Novo método de movimentação
        lLolo.correctBuggyMovement(e, this);
        
        Element eTemp;
        
        // Verifica todos os elementos da tela
        for(int i = 1; i < e.size(); i++){
            
            eTemp = e.get(i);
            
            // Caso o Pacman esteja sobre o elemento
            if(lLolo.overlap(eTemp)){
                
                // Verifica se o elemento é transponível, se sim, passa por cima
                if(eTemp.isTransposable()){
                    e.remove(eTemp);
                    
                    // Se for um pacdot (10 pontos), reduz o contador de controle
                    if(eTemp instanceof PacDot){
                        lLolo.totalDots--;
                    }
                    
                    if(eTemp instanceof PowerPallet){
                        lLolo.totalDots--;
                        timerPoder.schedule(new FimPoder(), 7000);
                        poderAtivado = true;
                        //FAZ PODER
                    }
                    
                    // Adiciona o score baseado no tipo de elemento
                    lLolo.addScore(eTemp.getScore());
                }
                // Verifica se o elemento é mortal, se sim, mata o pacman
                // Não implementado ainda
            }
            if(eTemp instanceof Ghost){                
                ((Ghost) eTemp).ai(e, lLolo, this);
            }
            
        }
        
        lLolo.move();
    }

    // Uma função que recebe um array de elementos e verifica se cada um deles é transponível
    // para determinar se a posição onde ele está é válida para o Pacman
    public boolean isValidPosition(ArrayList<Element> elemArray, Element elem){
        Element elemAux;
        Lolo lolo = (Lolo) elemArray.get(0);
        for(int i = 1; i < elemArray.size(); i++){
            elemAux = elemArray.get(i);
            if(elemAux instanceof Ghost){
                if(elemAux.overlap(elem)){
                    if(elemAux instanceof Lolo){
                        lolo.death = true;
                        System.out.println(lolo.death);
                    }
                }
                continue;
            }
            if(!elemAux.isTransposable())
                if(elemAux.overlap(elem))
                    return false;
        }        
        return true;
    }
    
    public class FimPoder extends TimerTask{
        public void run(){
            poderAtivado = false;
        }
    }
}
