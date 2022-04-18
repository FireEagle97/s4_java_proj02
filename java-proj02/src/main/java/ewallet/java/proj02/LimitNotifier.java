package ewallet.java.proj02;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dany's Computer
 */
import java.util.ArrayList;
import java.util.List;
public class LimitNotifier implements Subject {
    private List<Observer> observers = new ArrayList<>();
    
    @Override
    public void attach(Observer o){
        observers.add(o);
        
    }
    
    @Override
    public void detach(Observer o){
        observers.remove(o);
    }
    
    
    public void notifyUpdate(Card card){
        for(Observer o: observers) {
            o.update(card);
        }
    }
}
