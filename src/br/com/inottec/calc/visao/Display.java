package br.com.inottec.calc.visao;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JPanel;

import br.com.inottec.calc.modelo.Memoria;
import br.com.inottec.calc.modelo.MemoriaObsevador;

@SuppressWarnings("serial")
public class Display extends JPanel implements MemoriaObsevador{
	
	private final JLabel label;
	
	public Display() {
		//avisa ao display que teve uma alteração e altera o label do display , o texto do display
		Memoria.getInstancia().adicionarObeservador(this);
		
		setBackground(new Color(46,49,50));// adicionando um cor no display
		
		//adicionando texto no display,atraves de uma extancia do meto getTextoAtual da classe
		label = new JLabel(Memoria.getInstancia().getTextoAtual());
		
		label.setForeground(Color.WHITE);//adicionando cor na letra 
		label.setFont(new Font("courier", Font.PLAIN,30));//adicionado uma fonte e um tamanho a texto
		
		//gerenciamento de layout para fazer o alinhamento da letra
		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 25));
		
		add(label);
	}

	@Override
	public void valorAlterado(String novoValor) {//é um obisevandr de evento
		label.setText(novoValor);//alterando o valor impreso no display
		
	}

}
