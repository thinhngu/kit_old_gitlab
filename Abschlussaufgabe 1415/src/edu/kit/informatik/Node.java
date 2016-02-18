package edu.kit.informatik;

import java.util.*;



public class Node {
    protected String name;
    private LinkedList<Relation> relationship = new LinkedList<Relation>();

    public LinkedList<Relation> getRelationship() {
        return relationship;
    }

    public Node(String name) {
        this.name = name;
    }

    public void addRelation(Node b, Relation.Relationship r) {
        Relation e = new Relation(b, r);
        if (relationship.contains(e) != true) {
            relationship.add(e);
            b.addRelation(this, e.inversion());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String RelationtoString() {
        String a = "";
        for (int i = 0; i < relationship.size(); i++) {
            a += name + "-" + "[" + relationship.get(i).r + "]" + "-" + ">"
                    + relationship.get(i).b.name + "\n";
        }

        return a;
    }
    
    public String toString() {
        return name;
    }
    
    public boolean equals(Object obj) {
        if(obj instanceof Node) {
            Node node = (Node)obj;
            if(this.name.equals(node.name)) {
                return true;
            }
        }
        
        return false;
        
    }

}
