package control;
        
import elements.Element;
import elements.Lolo;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Save implements Serializable{
    ArrayList<Element> elemArray;
    int level;
    Lolo lolo;
    
    public Save(){}
    
    public Save(ArrayList<Element> elemArray, int level, Lolo lolo){
        this.elemArray = elemArray;
        this.level = level;
        this.lolo = lolo;
    }
    
    public void SaveFile(String path){
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
            out.writeInt(level);
            out.writeObject(lolo);
            out.writeObject(elemArray);            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void LoadFile(String path){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
            level = in.readInt();
            lolo = (Lolo) in.readObject();
            elemArray = (ArrayList<Element>) in.readObject();       
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public int getScore() { return lolo.getScore(); }
    public int getMovDirection() { return lolo.getMovDirection(); }
    public double getPositionX() { return lolo.getPosition().getX(); }
    public double getPositionY() { return lolo.getPosition().getY(); }
    public int getLevel() { return level; }
    public Lolo getLolo() { return lolo; }
    public ArrayList<Element> getElemArray() { return elemArray; }
}