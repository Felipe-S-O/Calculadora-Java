package br.com.inottec.calc.visao;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import br.com.inottec.calc.modelo.Memoria;

@SuppressWarnings("serial")
public class Teclado extends JPanel implements ActionListener{ 
	//atrves do ActionListener posso imprementa metodo  actionPerformed que gera um a ação peformada
	
	private final Color COR_CINZA_ESCURO = new Color(68, 68, 68);
	private final Color COR_CINZA_CLARO = new Color(99, 99, 99);
	private final Color COR_LARANJA = new Color(242, 163, 60);
	
	
	public Teclado() {
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
				
		setLayout(layout); // crinado um teclado
		
		c.weightx = 1;// o peso x que é para espandi toda lateral
		c.weighty = 1;// o peso y que é para espandi toda altura
		c.fill = GridBagConstraints.BOTH;// para espandi o Grid
		
		//PRIMEIRA LINA DO TECLADO
		c.gridwidth = 3; //para deixa todos botões oculpando três espaços
		//masi eu so quero que esse botão oculpe três espaço então tenho que cria outro comando abaixo dele
		adicionarBotao("AC", COR_CINZA_ESCURO, c, 0, 0);//ADICIONANDO COR E TEXTO DO BOTÃO
		c.gridwidth = 1;//colocando todos os botões para oculpa um espaco de novo menos o de cima
		adicionarBotao("/", COR_LARANJA, c, 3, 0);//ADICIONANDO COR E TEXTO DO BOTÃO
		
			
		//SEGUNDA LINA DO TECLADO
		adicionarBotao("7", COR_CINZA_CLARO, c, 0, 1);//ADICIONANDO COR E TEXTO DO BOTÃO
		adicionarBotao("8", COR_CINZA_CLARO, c, 1, 1);//ADICIONANDO COR E TEXTO DO BOTÃO
		adicionarBotao("9", COR_CINZA_CLARO, c, 2, 1);//ADICIONANDO COR E TEXTO DO BOTÃO
		adicionarBotao("*", COR_LARANJA, c, 3, 1);//ADICIONANDO COR E TEXTO DO BOTÃO
		
		//TECEIRA LINA DO TECLADO
		adicionarBotao("4", COR_CINZA_CLARO, c, 0, 2);//ADICIONANDO COR E TEXTO DO BOTÃO
		adicionarBotao("5", COR_CINZA_CLARO, c, 1, 2);//ADICIONANDO COR E TEXTO DO BOTÃO
		adicionarBotao("6", COR_CINZA_CLARO, c, 2, 2);//ADICIONANDO COR E TEXTO DO BOTÃO
		adicionarBotao("-", COR_LARANJA, c, 3, 2);//ADICIONANDO COR E TEXTO DO BOTÃO
		
		//QUARTA LINA DO TECLADO
		adicionarBotao("1", COR_CINZA_CLARO, c, 0, 3);//ADICIONANDO COR E TEXTO DO BOTÃO
		adicionarBotao("2", COR_CINZA_CLARO, c, 1, 3);//ADICIONANDO COR E TEXTO DO BOTÃO
		adicionarBotao("3", COR_CINZA_CLARO, c, 2, 3);//ADICIONANDO COR E TEXTO DO BOTÃO
		adicionarBotao("+", COR_LARANJA, c, 3, 3);//ADICIONANDO COR E TEXTO DO BOTÃO
		
		//QUINTA LINA DO TECLADO
		c.gridwidth = 2;//fazendo o zero oculpa dois espaços
		adicionarBotao("0", COR_CINZA_CLARO, c, 0, 4);//ADICIONANDO COR E TEXTO DO BOTÃO
		c.gridwidth = 1;//colocando esse botões de baixo para oculpa um espaço novamente
		adicionarBotao(",", COR_CINZA_CLARO, c, 2, 4);//ADICIONANDO COR E TEXTO DO BOTÃO
		adicionarBotao("=", COR_LARANJA, c, 3, 4);//ADICIONANDO COR E TEXTO DO BOTÃO		
		
	}


	private void adicionarBotao(String texto, Color cor,
			GridBagConstraints c, int x, int y) { // função de adiciona botão na posição		
		c.gridx = x;//posição da coluna 
		c.gridy = y;//posição da linha
		Botao botao = new  Botao(texto, cor);//criando um botão
		botao.addActionListener(this);// cria um evento para cada botão criado
		add(botao, c);	//adicionando um botão	
		
	}
	
	public void actionPerformed(ActionEvent e) {
	    //se o getSource instancia um  JButton faça isso
		if(e.getSource() instanceof JButton) {
			
			//capitura o botão o nome que gero um evento retorna:objeto
			JButton botao = (JButton)e.getSource();
			
			//passa o objeto que foi capiturado, o texto do botão			
			Memoria.getInstancia().processarComando(botao.getText());
		}
		
	}

}
