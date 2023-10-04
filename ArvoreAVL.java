package com.mycompany.avaliacao_avl;

public class ArvoreAVL {
    
    protected Node raiz;
    
    public ArvoreAVL(){
    
        this.raiz = null;
    }
    
    public void atualizarAltura(Node n){
    
        if (n != null) {
        int alturaEsquerda = (n.esquerda != null) ? n.esquerda.altura : -1;
        int alturaDireita = (n.direita != null) ? n.direita.altura : -1;
        n.altura = 1 + Math.max(alturaEsquerda, alturaDireita);
    }
    }
    
    public int altura(Node n){
    
        return n == null ? -1 : n.altura;
        /*if (n == null) {
            return -1;
        }else {
        return n.altura;
        }*/
    }
    
    public int getBalanceamento(Node n){
    
        return n == null ? 0 : altura(n.direita) - altura(n.esquerda);
    }
    
    public Node moverDireita(Node n){
    
        Node n1 = n.esquerda;
        Node n2 = n1.direita;
        
        n1.direita = n;
        n.esquerda = n2;
        
        atualizarAltura(n);
        atualizarAltura(n1);
        
        return n1;
    }
    
    public Node moverEsquerda(Node n){
    
        Node n1 = n.direita;
        Node n2 = n1.esquerda;
        
        n1.esquerda = n; //gira pra esquerda
        n.direita = n2; //filho de n1 vira de n
        
        atualizarAltura(n);
        atualizarAltura(n1);
        
        return n1; // toma o lugar de n
    }
    
    public Node rebalancear(Node n){
        if (n == null) {
            return null; 
        }

        atualizarAltura(n);

        int b = getBalanceamento(n);

        if (b > 1) {
            if (n.direita != null && n.esquerda != null) {
                if (altura(n.direita.direita) > altura(n.esquerda.direita)) {
                    n = moverEsquerda(n);
                } else {
                    n.direita = moverDireita(n.direita);
                    n = moverEsquerda(n);
                }
            }
        } else if (b < -1) {
            if (n.esquerda != null && n.direita!= null) {
                if (altura(n.esquerda.esquerda) > altura(n.esquerda.direita)) {
                    n = moverDireita(n);
                } else {
                    n.esquerda = moverEsquerda(n.esquerda);
                    n = moverDireita(n);
                }
            }
        }
        return n;
    }

    
    public Node deletar(Node n, Integer info) {
        
        if (n == null) {
            return n;
        } else if (n.info > info) {

            n.esquerda = deletar(n.esquerda, info);

        } else if (n.info < info) {

            n.direita = deletar(n.direita, info);
        } else {
            if (n.esquerda == null || n.direita == null) {
                n = (n.esquerda == null) ? n.direita : n.esquerda;
            } else {
                Node menor = menor(n.direita);
                n.info = menor.info;
                n.direita = deletar(n.direita, n.info);
            }
        }
        if (n != null) {
            n = rebalancear(n);
        }
        return n;
}
    
    public static Node menor(Node atual) {
        Node menor = atual;
        while (atual.esquerda != null) {
            menor = atual.esquerda;
            atual = atual.esquerda;
        }
        return menor;
    }
    
    public void criar_entrada(Node pai, int info) {       
        if (info < pai.info) { 
            
            if (pai.esquerda != null) { 
                
                criar_entrada(pai.esquerda, info);
            } 
            else {
                
                Node n = new Node();
                n.info = info;
                pai.esquerda = n;       
            }
        }               
        else{
            
          if (pai.direita != null) {
            criar_entrada(pai.direita, info);
          } 
          
          else {
            Node n = new Node();
            n.info = info;
            pai.direita = n;
          }
        }
    }
    
}
