package com.mycompany.avaliacao_binaria;

import java.util.Random;
import java.util.Scanner;

public class Avaliacao_binaria {
  
//--------------------------------------------------------------------------
    public static Node deletar(Node atual, int valor) {
        
        /*if (atual == null) {
        System.out.println("Valor " + valor + " nao encontrado na arvore para remocao.");
        return null;
        }*/

        if (valor < atual.info) {
    
            atual.esquerda = deletar(atual.esquerda, valor);
        } 
        else if (valor > atual.info) {
            atual.direita = deletar(atual.direita, valor);
        } 
        else {
            //System.out.println("Valor " + valor + " foi encontrado e sera removido da arvore.");
            if (atual.esquerda == null) {
    
                return atual.direita;
            } 
            else if (atual.direita == null){
                return atual.esquerda;
            }
            atual.info = filhoInOrder(atual.direita);
            atual.direita = deletar(atual.direita, atual.info);
        }

        return atual;

    }

    public static boolean checkValue(Node atual, int value) {
        if (atual == null) {
            return false; 
        }

        if (atual.info == value) {
            return true; 
        }
        boolean procuraEsq = checkValue(atual.esquerda, value);
        boolean procuraDir = checkValue(atual.direita, value);

        return procuraEsq || procuraDir; 
    }

    public static int filhoInOrder(Node atual) {
        int menor = atual.info;
        while (atual.esquerda != null) {
            menor = atual.esquerda.info;
            atual = atual.esquerda;
        }
        return menor;
    }
 //------------------------------------------------------------------------   
    public static void procurar(Node atual, int valor){

        if(atual!=null){
            
            if(atual.info == valor){
        
                System.out.println("Valor "+valor+" encontrado.");
            }
            else{
                procurar(atual.esquerda, valor);
                procurar(atual.direita, valor);
            }
        }
    }
    
    public static void criar_entrada(Node pai, int info) {       
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
    
    public static void main(String[] args) {
        
        Random random = new Random();
        
        int[] quantValores = {100, 500, 1000, 10000, 20000};
        ArvoreBinaria arvore = new ArvoreBinaria();
        Scanner sc = new Scanner(System.in);
        int selected;
        
        long startInsert;
        long finishInsert;

        long startSearch;
        long finishSearch;

        long startRemove;
        long finishRemove;

        for(int i = 0; i<quantValores.length; i++){
            
            int valorRaiz = random.nextInt(500 - 1 + 1) + 1;
            Node n = new Node();
            n.info = valorRaiz;
            
            arvore.raiz = n;
            
            System.out.println("Criando arvore com "+quantValores[i]+" valores, raiz "+arvore.raiz.info);
            
            //-------------------------------------------------------
            startInsert = System.nanoTime();
            
            for(int j = 0; j < quantValores[i]; j++){
                
                int valor = random.nextInt(500 - 1 + 1) + 1;               
                //System.out.println(valor);               
                criar_entrada(arvore.raiz, valor);
            }

            finishInsert = System.nanoTime() - startInsert;
            
            System.out.printf("Tempo para insercao de %d valores: %.4f ms\n", quantValores[i], (double)finishInsert/1000000);
            //-------------------------------------------------------
            
            
            //-------------------------------------------------------
            System.out.println("Digite um valor para buscar:");
            int valor = sc.nextInt();
            
            startSearch = System.nanoTime();
            
            procurar(arvore.raiz, valor);
            
            finishSearch = System.nanoTime() - startSearch;
            
            System.out.printf("Tempo para procura na arvore de %d valores: %.4f ms\n", quantValores[i], (double)finishSearch/1000000);
            //-------------------------------------------------------
  
            //-------------------------------------------------------        
            System.out.println("Digite um valor para remover: ");
            
            selected = sc.nextInt();
            
            while(!checkValue(arvore.raiz, selected)){
                System.out.println("Digite outro valor "+selected+" nao encontrado:");
                selected = sc.nextInt();
            }
            
            startRemove = System.nanoTime();
            
            deletar(arvore.raiz, selected);
            
            finishRemove = System.nanoTime() - startRemove;
            
            System.out.printf("Tempo para execucao da tarefa de remocao na arvore de %d valores: %.4f ms\n", 
                    quantValores[i], (double)finishRemove/1000000);
            //-------------------------------------------------------
            
           arvore.raiz = null;
        }
    }
}