package br.com.inottec.calc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {
	
	private enum TipoComando{
		// CRINADO UM TIOPO DE COMANDO PARA NOMEAR OS BOTÕES DE COMANDO 
		ZERAR, NUMERO, DIV, MULT, SUB, SOMA, IGUAL, VIRGULA;
	};

	private static final Memoria instancia = new Memoria();
	
	private final List<MemoriaObsevador> observadores = 
			new ArrayList<>();//criando um observador

	private TipoComando ultimaOperacao = null;// variavel que vai amazena o ultimo resultado
	private boolean substituir = false;//variavel que vai substituir o primero numero para entrada do segundo 
	private String textoGuadado = ""; // variavel que vai guarda o primero valor digitado
	private String textoAtual = "";// variavel que vai crinado texto atual 
	
	private Memoria() {
		
	}

	public static Memoria getInstancia() {
		return instancia;
	}
	
	public void adicionarObeservador(MemoriaObsevador observador) {//adicionando um obiservador
		observadores.add(observador);//toda ves que tiver uma alteração no testo ele va bassa
	}

	public String getTextoAtual() {
		return textoAtual.isEmpty() ? "0" : textoAtual;
		//se o textoAtual for vazio ele esibira 0 ou imprima textoAtual
	}
	
	public void processarComando(String texto) {
		
		TipoComando tipoComando = detectarTipoComando(texto);// se for um comando passa para tipo de comando
		
		if(tipoComando == null) {//se a tipo de comando for nulo retona nada 
			return;
			// se o tipo de comando for zerar que é o botão "AC",zerar tudo
		}else if(tipoComando == TipoComando.ZERAR) {
			textoAtual = "";
			textoGuadado = "";
			ultimaOperacao = null;
			substituir = false;
		}else if(tipoComando == TipoComando.NUMERO 
				|| tipoComando == TipoComando.VIRGULA) {
			//se substituir for verdadeiro recebe texto se não recebe textoAtual + texto 
			textoAtual = substituir ? texto : textoAtual + texto;
			//se substituir for verdadeiro troca para falso
			substituir = false;
		}else {
			//se as opições de cima der falsa o substituir fica verdadeiro, quando digita outor
			//volta par o inicio do if desse
			//meto  se o tipo do comando for "AC" ele zera tudo se não passa para o procimo if 
			//que vai substituir por qualsa da função (textoAtual = substituir ? texto : textoAtual + texto)
			substituir = true;
			//meto passa o resultado  da operação só quando passa aqui aparti da segunda operação 
			textoAtual = obterResultadoOperacao();
			textoGuadado = textoAtual;//guarda o texto atual antes de substituir
			
			//recebe o operado DIV, MULT, SUB, SOMA ou IGUAL e amazena antes de substituir
			ultimaOperacao = tipoComando;
		}
		
		observadores.forEach(o -> o.valorAlterado(getTextoAtual()));//observador receber textoAtual
	}
    //meto que faz a operação matematica
	private String obterResultadoOperacao() {
		// se a ultima operação for nula retona o texto que ta no display
		// as vezes o igual da nulo ai ive que cola ele aqui tbm resovido 
		if(ultimaOperacao == null || ultimaOperacao == TipoComando.IGUAL) {
			return textoAtual;
		}
		//convetendo o valor de texto guardado para double e passando vigula para ponto
		double numeroGuadado = Double.parseDouble(textoGuadado.replace(",","."));
		
		//convetendo o valor de texto Atual para double e passando vigula para ponto
		double numeroAtula = Double.parseDouble(textoAtual.replace(",","."));
		
		double resultado = 0;//variavel local
		
		if(ultimaOperacao == TipoComando.SOMA) {//operação soma 
			resultado = numeroGuadado + numeroAtula;
		}else if(ultimaOperacao == TipoComando.SUB) {//operação subitração
			resultado = numeroGuadado - numeroAtula;
		}else if(ultimaOperacao == TipoComando.MULT) {//operação multiplicação
			resultado = numeroGuadado * numeroAtula;
		}else if(ultimaOperacao == TipoComando.DIV) {//operação divisão
			resultado = numeroGuadado / numeroAtula;
		}
		
		//convetendo o resultado para String porque o display so aceita String
		String resultadoString = Double.toString(resultado).replace(".",",");
		
		//se resultadoString termina com ",0" variavel inteiro receber true
		boolean inteiro = resultadoString.endsWith(",0");
		
		//se inteiro for true  retorna resultadoString.replace(",0","") que remove a vigula e o zero do final
		//se inteiro for false retorna resultadoString que retona completo com a vigula e o zero no final 
		//é só para diferencia o visual do inteiro do bouble 
		return inteiro ? resultadoString.replace(",0","") : resultadoString ;
	}

	private TipoComando detectarTipoComando(String texto) {//verifica se é um comando 
		
		//se o texto atual for vazil e o texto que foi passadpo for ingual a zero 
		// que é para o usuario não pode passa o valor zero 
		if(textoAtual.isEmpty() && texto == "0") {
			return null;
		}
		//mapiamento dos botões 
		
		// se consergi passa o texto passado para Integer então  o TipoComando retona NUMERO
		// se não converte pode gera um exceção
		try {
			Integer.parseInt(texto);
			return TipoComando.NUMERO;			
		} catch (Exception e) {
			// qundo o texto não for número ..
			
		}if("AC".equals(texto)) {// se texto for igual a AC ele retona zero
			return TipoComando.ZERAR;
		}else if("/".equals(texto)) {//se texto for igual a / ele retona div
			return TipoComando.DIV;
		}else if("*".equals(texto)) { //se texto for igual a * ele retona mult
			return TipoComando.MULT;
		}else if("-".equals(texto)) { //se texto for igual a - ele retona sub
			return TipoComando.SUB;
		}else if("+".equals(texto)){ //se texto for igual a + ele retona soma
			return TipoComando.SOMA;
		}else if("=".equals(texto)) { //se texto for igual a = ele retona igual
			return TipoComando.IGUAL;
			//se texto for igual a , ele retona virgula na posima vez ja contem armzenado um virgula 
			//no texto entao ai pula para o nulo
		}else if(",".equals(texto) 
				&& !textoAtual.contains(",")){ 
			return TipoComando.VIRGULA;
		}
				
		return null;
	}
	
	
	
}
