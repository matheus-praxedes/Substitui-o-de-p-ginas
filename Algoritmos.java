/******************************************
*Universidade Federal da Paraíba - UFPB
*Centro de Informática - CI
*
*Sistemas Operacionais 1 - 2016.2
*
*Aluno: Matheus Maranhão Rêgo Praxedes
*Matrícula: 11403744
*
*Trabalho 2
*Data de entrega: 21/05/2017
*******************************************/

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

public class Algoritmos{

	private int numero_de_erros;
 	private int numero_de_quadros;
 	ArrayList<Entradas> quadros;

 	public Algoritmos(int numero_de_quadros){

 		this.numero_de_quadros = numero_de_quadros;
 		numero_de_erros = 0;
 	}

 	public void FIFO(List<Entradas> lista){

 		int INSERIR = 0;
		this.quadros = new ArrayList<>();
		
	  	for(int i = 0; i < lista.size(); i++){ 
	  		
	  		Entradas numero_da_pagina = lista.get(i); 

	  		if (!quadros.contains(numero_da_pagina)) {
	  			
	   			if (quadros.size() < numero_de_quadros) {
	    				
	    				quadros.add(numero_da_pagina);
	    				numero_de_erros++;
	    				continue;
	   			
	   			} else {
	    				quadros.remove(INSERIR);
	    				quadros.add(INSERIR, numero_da_pagina);
	    				INSERIR++;
	    				numero_de_erros++;
	    		
			    		if (INSERIR == numero_de_quadros) {
			     			INSERIR = 0;
			    		}
	   			}
	   	
	   		}	
	    }

	    System.out.println("FIFO " + numero_de_erros);
	}

	public void OTM (List<Entradas> lista){

		int BASE  	      = 0;
		int max_index     = 0;		
		int j 		      = 0;
		int tamanho       = lista.size();
		int frame_index   = 0;

		Entradas valor;
		quadros           = new ArrayList<>(); 
		
		for(int i = 0; i < tamanho; i++){
			
			Entradas numero_da_pagina = lista.get(0);
			lista.remove(BASE);
			
			if(quadros.size() < numero_de_quadros){
				
				quadros.add(numero_da_pagina);
				numero_de_erros++;
				continue;
			}
			
			if(quadros.contains(numero_da_pagina)){

				continue;
			}
			
			max_index = -1;
			
			
			for(j = 0; j < quadros.size(); j++){
			
				if(lista.indexOf(quadros.get(j)) == -1){
					frame_index = j;
					break;
				}

				if(lista.indexOf(quadros.get(j)) > max_index){
					frame_index = j;
					max_index = lista.indexOf(quadros.get(j));
					 
				}
			}
			
			quadros.set(frame_index, numero_da_pagina);
			numero_de_erros++;
			
		}
		
		System.out.println("OTM " + numero_de_erros);
			
	}

	public void LRU(List<Entradas> lista){

		boolean aux = false;
		ArrayList<Entradas> pilha = new ArrayList<>(numero_de_quadros);
		final int BASE = numero_de_quadros-1;
		final int TOPO = 0;
		printList(lista);
		for (Entradas entrada : lista) {
			aux = false;
			
			for (Entradas quadro : pilha) {
				
				if (quadro.getValor() == entrada.getValor()){
					pilha.remove(quadro);
					pilha.add(TOPO,entrada);
					aux = true;
					break;
				}
			}
			
			if(!aux){
				if (pilha.size() != numero_de_quadros){
					numero_de_erros++;
					pilha.add(TOPO,entrada);
				} else {
					numero_de_erros++;
					pilha.remove(BASE);
					pilha.add(TOPO,entrada);
				}
			}
		}
					
		System.out.println("LRU " + numero_de_erros);

	}
	
	public void printList(List<Entradas> lista){
		
		if(lista.isEmpty()){

			System.out.println("Lista vazia");
		}
		for (Entradas entrada : lista) {
			
			System.out.println(entrada.getValor()+" ");
		}
	}
}
