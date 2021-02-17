package br.com.inottec.calc.visao;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Calculadora  extends JFrame{
	
	public Calculadora() {	//construto
		
		organizaLayout();
		
		
		setSize(232, 318);//tamanho da calculadora
		setDefaultCloseOperation(EXIT_ON_CLOSE);//para finaloiza o proceço quando feicha a janela
		setLocationRelativeTo(null);//para centralizar callculadora na tela
		setVisible(true);//criando janela		
	}
	
	private void organizaLayout() {
		setLayout(new BorderLayout());//um  gerenciado de layout
		
		Display display = new Display();
		display.setPreferredSize(new Dimension(233, 60));//definindo o tamanho do display
		add(display, BorderLayout.NORTH);// adicionando o display no norte do layout , encima do layout
		
		Teclado teclado = new Teclado();
		add(teclado);// adicionando o teclado 
		
	}

	public static void main (String[] args) {
		new Calculadora();		
	}

}
