package edu.kit.informatik;

public class Product extends Node {
    private int indnumber;
    
    public int getIndnumber() {
        return indnumber;
    }
    public void setIndNumber(int indnumber) {
        this.indnumber = indnumber;
    }
    
    public Product(String name, int indnumber) {
        super(name);
        
        this.indnumber = indnumber;
    }
    
    @Override
    public String toString() {
        String a = "";
        a += name + ":" +indnumber;
        return a;
    }
}
