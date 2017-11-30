package control;

import elements.Element;
import elements.*;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Projeto de POO 2017
 * 
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */
public class GameController {
    
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
        if (!isValidPosition(e, lLolo)) {
            
            // Impede que o pacman acesse uma posição inválida
            lLolo.backToLastPosition();
            lLolo.setMovDirection(Lolo.STOP);
            return;
        }
        
        Element eTemp;
        
        // Verifica todos os elementos da tela
        for(int i = 1; i < e.size(); i++){
            
            eTemp = e.get(i);
            
            // Caso o Pacman esteja sobre o elemento
            if(lLolo.overlap(eTemp)){
                
                // Verifica se o elemento é transponível, se sim, passa por cima
                if(eTemp.isTransposable())
                    e.remove(eTemp);
                
                // Verifica se o elemento é mortal, se sim, mata o pacman
                // Não implementado ainda
            }
            
        }
        
        lLolo.move();
    }

    // Uma função que recebe um array de elementos e verifica se cada um deles é transponível
    // para determinar se a posição onde ele está é válida para o Pacman
    public boolean isValidPosition(ArrayList<Element> elemArray, Element elem){
        Element elemAux;
        for(int i = 1; i < elemArray.size(); i++){
            elemAux = elemArray.get(i);            
            if(!elemAux.isTransposable())
                if(elemAux.overlap(elem))
                    return false;
        }        
        return true;
    }
}
