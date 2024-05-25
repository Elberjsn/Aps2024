package controller;

import model.GeradorGrafico;
import model.ModelMain;

public class ControllerMain {

	ModelMain mM;
	
	public ControllerMain() {
		mM = new ModelMain();
	}
	
	public boolean adicionar(String meta, String mes, Double valor) {
		
		return mM.adicionar(meta,Integer.parseInt(mes), valor);
		
	}
	
	public String buscar() {
		return mM.buscarTodos().toString();
	}
	
	public String buscarMes(String mes) {
		int meses= Integer.parseInt(mes);
		return mM.buscarMes(meses).toString();
	}
	
	public String buscarMeta(String meta) {
		return mM.buscarMeta(meta).toString();
	}
	
	public String buscarMesEMeta(String meta, String mes) {
		int meses= Integer.parseInt(mes);;
		
		return mM.buscarMetaMes(meta, meses).toString();
	}
	
	public String remover(int index) {
		if (mM.remover(index)) {
			alterJson();
			return mM.buscarTodos().toString();
		}else {
			return "3";
		}
	}
	
	public String alter(String meta, String mes, Double valor,int index) {
		int meses= Integer.parseInt(mes);
		if(mM.modificar(index, mes, meses, valor)) {
			alterJson();
			return mM.buscarTodos().toString();
		}else {
			return "2";
		}
	}
	
	public void alterJson() {
		mM.salvarJson();
	}
	
	public String mediasMetas() {
		return mM.mediaMetas();
	}
	public Double[] setValores(Double a, Double s) {
		mM.setValores(a, s);
		return getValores();
	}
	public Double[] getValores() {
		return mM.getValores();
	}
	
	public void atualizarGrafico() {
		new GeradorGrafico();
	}
	
	public void salvar() {
		mM.salvarJson();
	}

}
