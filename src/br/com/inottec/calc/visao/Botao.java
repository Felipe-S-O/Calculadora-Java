package br.com.inottec.calc.visao;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Botao extends JButton{
	
	public Botao(String texto, Color cor) {
		
		setText(texto);// adicionando um texto
		setFont(new Font("courier", Font.PLAIN, 25));//adicionado um fonte e um tamanho para o texto 
		setOpaque(true);//para funciona o setBackground 
		setBackground(cor);//adicionado um cor 
		setForeground(Color.WHITE);//adicionando a cor do texto do teclado fiça
		setBorder(BorderFactory.createLineBorder(Color.black));//adicionando borda preta nos botões
		
	}
	
	
	

}
