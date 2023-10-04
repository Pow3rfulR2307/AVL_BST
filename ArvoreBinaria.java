package com.mycompany.avaliacao_binaria;

public class ArvoreBinaria {
 
    public Node raiz = null;
    
    public ArvoreBinaria(){this.raiz = null;};
    
    public void printInOrder(Node atual){

        if(atual != null){
            printInOrder(atual.esquerda);
            System.out.println(" "+atual.info);
            printInOrder(atual.direita);
        }
    }
    
    public void printPreOrder(Node atual){

        if(atual != null){
            System.out.println(" "+atual.info);
            printPreOrder(atual.esquerda);
            printPreOrder(atual.direita);
        }
    }
    
    public void printPostOrder(Node atual){

        if(atual != null){
            printPostOrder(atual.esquerda);
            printPostOrder(atual.direita);
            System.out.println(" "+atual.info);
        }
    }
}