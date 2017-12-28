package control;

/**
 * Universidade De São Paulo - São Carlos
 * SCC0604 - Programação Orientada a Objetos
 * 
 * Professor: Luiz Eduardo Virgilio Silva
 * Monitor PAE: Daniel Tozadore
 * 
 * Projeto de POO 2017
 * 
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 * 
 * Engenharia de Computação
 * Grupo 6 - Membros
 * Guilherme Blatt         Numero USP: 9771470
 * Lucas Akira Morishita   Numero USP: 9771320
 * Thiago Músico           Numero USP: 9771567
 * Tiago Toledo Junior     Numero USP: 9771309
 * 
 */
public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GameScreen screen = new GameScreen();
                screen.setVisible(true);
                screen.createBufferStrategy(2);
                screen.go();
            }
        });
    }
}

