# Pac-Man
![pac-man](https://github.com/lucas-akira/pacman-java/blob/master/imgs/game.png)

University of São Paulo - São Carlos

2nd Semester of 2017

Project made for the course "SCC0604 - Object-Oriented Programming", ministered by Professor Luiz Eduardo Virgilio Silva.

This project was originally made by:

* Tiago Toledo Junior (https://github.com/TNanukem)
* Thiago Músico (https://github.com/ThiagoMusico)
* Lucas Akira Morishita (https://github.com/lucas-akira)
* Guilherme Blatt (https://github.com/guilherme-blatt)

## Brief description and objective

Version of the arcade game Pac-Man made in Java, using the IDE NetBeans 8.2. The goal of this project is to apply object-oriented concepts (like classes, inheritance, encapsulation, etc.) learnt during class in a practical way. 

This project was based on a framework made by professor Luiz Eduardo and Jose Fernando Junior. The file "PacMan_Alunos - Arquivos Originais.zip" has this framework from which we developed the project.

The 'Complete project requirements' section (coming soon) contains the English translation of the original PDF file (Projeto-Pacman.pdf) describing the project to be made (in Portuguese) with all the requirements imposed by the professor.

## (some) Project features
* Three stages
* Each ghost has a different behavior:
  * Blinky ![Blinky](https://github.com/lucas-akira/pacman-java/blob/master/imgs/blinky.png) : Chases the player with movement based on Pac-Man's current direction, with a random factor;
  * Pinky ![Pinky](https://github.com/lucas-akira/pacman-java/blob/master/imgs/pinky.png) : Tries to trap Pac-Man by moving parallel to him, with a random factor;
  * Inky ![Inky](https://github.com/lucas-akira/pacman-java/blob/master/imgs/inky.png) : Moves randomly while Blinky is far from it, and behaves similarly as Blinky when the red ghost is close to it;
  * Clyde ![Clyde](https://github.com/lucas-akira/pacman-java/blob/master/imgs/clyde.png) : Behaves similarly as Blinky while the player is far from it, and moves randomly when Pac-Man approaches.
  
* Power pellet ![power](https://github.com/lucas-akira/pacman-java/blob/master/imgs/powerpallet.png) : Gives 50 points and the ability to temporarily eat the ghosts. For each consecutive ghost eaten the amount of earned points doubles, starting at 200.
* Two fruits (extra points):
  * Cherry ![Cherry](https://github.com/lucas-akira/pacman-java/blob/master/imgs/cherry.png) : Gives 100 points. Appears each 50 seconds;
  * Strawberry ![Strawberry](https://github.com/lucas-akira/pacman-java/blob/master/imgs/strawberry.png) : Gives 300 points. Appears each 75 seconds.
