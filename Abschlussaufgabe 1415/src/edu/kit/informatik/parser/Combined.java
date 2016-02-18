package edu.kit.informatik.parser;

public class Combined extends Operation{
    Operation stratone;
    Operation strattwo;
    
    public Combined(Operation one, Operation two) {
        this.stratone = one;
        this.strattwo = two;
    }
}
