package edu.kit.informatik;

public class Relation {
    Node b;
    Relationship r;

    public enum Relationship {
        contains, containedin, partof, haspart, successorof, predecessorof
    }

    public Relation(Node b, Relationship r) {
        this.b = b;
        this.r = r;
    }

    public boolean equals(Object obj) {

        if (obj instanceof Relation) {
            Relation relation = (Relation) obj;

            if (this.r == relation.r && this.b == relation.b) {
                return true;
            }

        }
        return false;
    }

    public Relationship inversion() {
        switch (this.r) {
        case contains:
            return Relationship.containedin;
            
        case containedin:
            return Relationship.contains;
            
        case partof:
            return Relationship.haspart;
            
        case haspart:
            return Relationship.partof;
            
        case successorof:
            return Relationship.predecessorof;
            
        case predecessorof:
            return Relationship.successorof;
            
        }

        return null;
    }
    
   
}
