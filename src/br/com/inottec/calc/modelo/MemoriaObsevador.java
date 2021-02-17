package br.com.inottec.calc.modelo;

@FunctionalInterface
public interface MemoriaObsevador {
	
	public void valorAlterado(String novoValor);//inteface para esibir resutado no dysplay

}
