package com.mycompany.avaliacao_binaria;

public class Node {
    
    protected Node esquerda;
    protected Node direita;
    protected Integer info;
   
    public Node(){
    
        this.esquerda = null;
        this.direita = null;
        this.info = null;
    }
    
    public void setInfo(Integer info){
    
        this.info = info;
    };
    
    public void getInfo(){
    
        System.out.println(this.info);
    };
    
    public void setEsquerda(Node esquerda){
    
        this.esquerda = esquerda;
    };
    
    public void setDireita(Node direita){
    
        this.direita = direita;
    };
}