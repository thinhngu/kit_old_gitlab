package edu.kit.informatik;

import java.util.*;

import edu.kit.informatik.Relation.Relationship;

public class NodeList {
    private LinkedList<Node> listofnodes;

    public NodeList(LinkedList<Node> listofnodes) {
        this.listofnodes = listofnodes;
    }

    public LinkedList<Node> getListOfNodes() {
        return listofnodes;
    }
    
    public Node getNodeById(int id) {
        for (int i = 0; i < listofnodes.size(); i++) {
            if(listofnodes.get(i) instanceof Product){
                Product product = (Product)listofnodes.get(i);
            if (product.getIndnumber() == id) {
                return listofnodes.get(i);
                }
            }
        }
        return null;
    }

    public Node getNode(String name) {
        for (int i = 0; i < listofnodes.size(); i++) {
            if (listofnodes.get(i).name.equals(name)) {
                return listofnodes.get(i);
            }
        }
        return null;
    }

    public void add(Node node) {
        if (listofnodes.contains(node) == false) {
            listofnodes.add(node);
        }

    }

    public LinkedList<Node> getSiblingsOf(Node node) {
        LinkedList<Node> productlist = new LinkedList<Node>();
        LinkedList<Relation> relations = node.getRelationship();

        for (int i = 0; i < relations.size(); i++) {
            if (relations.get(i).r == Relationship.containedin) {
                for (int j = 0; j < relations.get(i).b.getRelationship().size(); j++) {
                    if (relations.get(i).b.getRelationship().get(j).r == Relationship.contains) {
                        productlist.add(relations.get(i).b.getRelationship()
                                .get(j).b);
                    }
                }
            }
        }

        return productlist;
    }

    public LinkedList<Node> getPredecessor(Node node) {
        LinkedList<Node> productlist = new LinkedList<Node>();

        getPredecessorsOf(node, productlist);
        return productlist;

    }

    private void getPredecessorsOf(Node node, LinkedList<Node> productlist) {

        LinkedList<Relation> relations = node.getRelationship();

        for (int i = 0; i < relations.size(); i++) {

            if (relations.get(i).r == Relationship.predecessorof) {
                if (productlist.contains(relations.get(i).b) == false) {
                    productlist.add(relations.get(i).b);
                    getPredecessorsOf(relations.get(i).b, productlist);
                }
            }
        }
    }

    public LinkedList<Node> getSuccesor(Node node) {
        LinkedList<Node> productlisttwo = new LinkedList<Node>();

        getSuccessorOf(node, productlisttwo);

        return productlisttwo;
    }

    private void getSuccessorOf(Node node, LinkedList<Node> productlisttwo) {
        LinkedList<Relation> relations = node.getRelationship();

        for (int i = 0; i < relations.size(); i++) {
            if (relations.get(i).r == Relationship.successorof) {
                if (productlisttwo.contains(relations.get(i).b) == false) {
                    productlisttwo.add(relations.get(i).b);
                    getSuccessorOf(relations.get(i).b, productlisttwo);
                }
            }
        }

    }

}
