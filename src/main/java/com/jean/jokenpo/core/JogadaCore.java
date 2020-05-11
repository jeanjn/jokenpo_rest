package com.jean.jokenpo.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jean.jokenpo.exceptions.CoreException;
import com.jean.jokenpo.model.Acao;
import com.jean.jokenpo.model.Jogada;

@Service
public class JogadaCore {
	/**
	 * <jogador, jogada>
	 */
	private static HashMap<String, Jogada> jogadas = new HashMap<String, Jogada>();
	
	public Jogada cadastrar(Jogada jogada) {
		if(jogadas.containsKey(jogada.getJogador())) {
			jogadas.replace(jogada.getJogador(), jogada);
			return jogada;
		}
		
		jogadas.put(jogada.getJogador(), jogada);
		return jogada;
	}
	
	public Collection<Jogada> listar(){
		return jogadas.values();
	}
	
	public Optional<Jogada> consultar(String jogador) {
		return Optional.ofNullable(jogadas.get(jogador));
	}
	
	public void remover(String jogador) throws CoreException {
		if(!jogadas.containsKey(jogador)) {
			throw new CoreException("Jogada não encontrada");
		}
		
		jogadas.remove(jogador);
	}
	
	public String processar() throws CoreException {
		if(jogadas.size() == 1) {
			throw new CoreException("Você está sozinho neste jogo");
		}
		
		
		ArrayList<Jogada> jogadasArray = new ArrayList<Jogada>(jogadas.values());
		ArrayList<Integer> perdedores = new ArrayList<>();
		int index = 0;
		int indexVencedor = 0;
		Jogada vencedor = null;
		
		while(index != jogadasArray.size()){
			index++;
			
			int indexResultado = getVencedor(indexVencedor, jogadasArray);
			
			if(indexResultado == indexVencedor) {
				break;
			}
			
			if(indexResultado > -1) {
				perdedores.add(indexVencedor);
				if(!perdedores.contains(indexResultado)) {
					indexVencedor = indexResultado;
					continue;
				}
			}
			
			indexVencedor = index;
		}
		
		if(indexVencedor != jogadasArray.size()) {
			vencedor = jogadasArray.get(indexVencedor);
		}
		
		jogadas.clear();
		return vencedor == null ? "Empate" : "Vencedor: " + vencedor.getJogador();
	}
	
	private int getVencedor(int jogadaRefIndex, ArrayList<Jogada> jogadasArray) {
		
		Jogada jogadaRef = jogadasArray.get(jogadaRefIndex);
		for (int i = 0; i < jogadasArray.size(); i++) {
			if(i == jogadaRefIndex) {
				continue;
			}
			
			Jogada jogada = jogadasArray.get(i);
			int resultado = processarJogada(jogadaRef, jogada);
			if(resultado == -1) {
				return i;
			}
			
			if(resultado == 0) {
				return -1;
			}
		}
		
		return jogadaRefIndex;
	}
	
	/***
	 * Compara duas jogadas e retorna o resultado
	 * @param a
	 * @param b
	 * @return 1 A venceu, 0 empate, -1 A perdeu
	 */
	private int processarJogada(Jogada a, Jogada b) {
		/**
		 * Spock -> Tesoura, Pedra
		 * Tesoura -> Papel, Lagarto
		 * Papel -> Spock, Pedra
		 * Pedra -> Tesoura, Lagarto
		 * Lagarto -> Spock, Papel
		 */
		
		if(a.getAcao().equals(b.getAcao())){
			return 0;
		}
		
		switch (a.getAcao()) {
		case Spock:
			return (b.getAcao() == Acao.Tesoura || b.getAcao() == Acao.Pedra) ? 1 : -1;
		case Tesoura:
			return (b.getAcao() == Acao.Papel || b.getAcao() == Acao.Lagarto) ? 1 : -1;
		case Papel:
			return (b.getAcao() == Acao.Spock || b.getAcao() == Acao.Pedra) ? 1 : -1;
		case Pedra:
			return (b.getAcao() == Acao.Tesoura || b.getAcao() == Acao.Lagarto) ? 1 : -1;
		case Lagarto:
			return (b.getAcao() == Acao.Spock || b.getAcao() == Acao.Papel) ? 1 : -1;
		default:
			return 0;
		}
	}
	
	public void limparJogadas() {
		jogadas.clear();
	}
	
}
