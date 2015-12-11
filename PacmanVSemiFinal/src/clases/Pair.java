
package clases;

public class Pair {
    private int one;
    private int two;
    
    public Pair(int a, int b){
        one = a;
        two = b;
    }
    
    public int getFirst(){
        return one;
    } 
    public int getSecond(){
        return two;
    }
    public void setFirst(int a){
        one = a;
    }
    public void setSecond(int b){
        two = b;
    }
}
