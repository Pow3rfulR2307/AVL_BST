/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.avaliacao_avl;

import java.util.Random;
import java.util.Scanner;

public class Avaliacao_AVL {
    
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
    
    public static Node procurar(Node atual, int infoProcurar) {
        while (atual != null) {
            if (atual.info == infoProcurar) {
                System.out.println("Valor "+infoProcurar+" encontrado na arvore AVL.");
                break;
            }
            atual = atual.info < infoProcurar ? atual.direita : atual.esquerda;
        }
        return atual;
    }
    
    public static void main(String[] args) {
        
        Random random = new Random();
        
        //1000, 10000, 20000
        int[] quantValores = {100, 500, 1000, 10000, 20000}; //executa tudo pra uma, limpa arvore, repete pra outra, grande for
        ArvoreAVL arvore = new ArvoreAVL();
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
                
                int valor = random.nextInt(2000 - 1 + 1) + 1;               
                //System.out.println(valor);               
                arvore.criar_entrada(arvore.raiz, valor);
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
            System.out.printf("Tempo para procura na arvore de %d valores: %.4f ms\n", 
                    quantValores[i], (double)finishSearch/1000000);
            //-------------------------------------------------------
  
            //-------------------------------------------------------        
            System.out.println("Digite um valor para remover: ");
            
            selected = sc.nextInt();
            
            while(!checkValue(arvore.raiz, selected)){
                System.out.println("Digite outro valor "+selected+" nao encontrado:");
                selected = sc.nextInt();
            }
            
            startRemove = System.nanoTime();
            
            arvore.deletar(arvore.raiz, selected);
            
            finishRemove = System.nanoTime() - startRemove;
            
            System.out.printf("Tempo para execucao da tarefa de remocao na arvore de %d valores: %.4f ms\n", 
                    quantValores[i], (double)finishRemove/1000000);
            //-------------------------------------------------------
            
           arvore.raiz = null;
        }
    }
}