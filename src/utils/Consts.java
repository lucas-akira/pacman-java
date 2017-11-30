package utils;

import java.io.File;

/**
 * Projeto de POO 2017
 * 
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */

// Classe com algumas constantes definidas
public class Consts {
    
    // Define o tamanho do mapa a partir do número de células e do tamanho das mesmas
    public static final int CELL_SIZE = 30;
    public static final int NUM_CELLS = 20;
    
    // Define o tamanho do passo de cada elemento
    public static final int WALK_STEP_DEC_PLACES = 1;
    public static final double WALK_STEP = 0.1;
    
    public static final String PATH = File.separator+"imgs"+File.separator;
    
    public static final int DELAY = 20;
    public static final int TIMER_FOGO = 40;
}