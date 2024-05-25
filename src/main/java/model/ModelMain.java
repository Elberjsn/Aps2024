package model;

import java.util.ArrayList;
import java.util.List;

public class ModelMain {
	List<Metas> listaMetas = new ArrayList<>();
	ManipulacaoFile mF = new ManipulacaoFile();
	Double[] valoresJson;

	public ModelMain() {
		valoresJson = mF.valores;
		listaMetas = mF.fileToArray();
	}

	public void salvarJson() {
		mF.arrayToFile(listaMetas);
	}

	public boolean adicionar(String name, int data, Double valor) {

		if (checarExistencia(name, data)) {
			return listaMetas.add(new Metas(name, data, valor));

		}
		return false;
	}

	public boolean remover(int index) {
		int tamahoLista = listaMetas.size();
		listaMetas.remove(index);
		return tamahoLista != listaMetas.size();
	}

	public boolean modificar(int index, String name, int data, Double valor) {

		if (checarExistencia(name, data)) {
			listaMetas.set(index, new Metas(name, data, valor));
			return true;
		}

		return false;
	}

	public StringBuilder buscarTodos() {
		StringBuilder sb = new StringBuilder();

		int i = 0;
		for (Metas metas : listaMetas) {

			sb.append(metas.stringFormatTable(i));
			i++;
		}

		return sb;
	}

	public StringBuilder buscarMes(int mes) {
		StringBuilder sb = new StringBuilder();

		int i = 1;
		for (Metas metas : listaMetas) {
			if (metas.getMes().equals(mes)) {
				sb.append(metas.stringFormatTable(i));
			}
			i++;
		}

		return sb;
	}

	public StringBuilder buscarMeta(String meta) {
		StringBuilder sb = new StringBuilder();

		int i = 1;
		for (Metas metas : listaMetas) {
			if (metas.getMeta().equals(meta)) {
				sb.append(metas.stringFormatTable(i));
			}
			i++;
		}
		sb.append("</tbody></table>");

		return sb;
	}

	public StringBuilder buscarMetaMes(String meta, int mes) {
		StringBuilder sb = new StringBuilder();

		int i = 1;
		for (Metas metas : listaMetas) {

			if (metas.getMeta().equals(meta) && metas.getMes().equals(mes)) {
				sb.append(metas.stringFormatTable(i));
			}

			i++;
		}
		return sb;
	}

	public String mediaMetas() {
		int mesAmbiental = 0;
		int mesSocial = 0;
		double valorAmbiental = 0.0;
		double valorSocial = 0.0;

		for (Metas metas : listaMetas) {

			if (metas.getMeta().equals("Ambiental")) {
				mesAmbiental++;
				valorAmbiental += metas.getValor();
			}

			if (metas.getMeta().equals("Social")) {
				mesSocial++;
				valorSocial += metas.getValor();
			}
		}

		String jsonBasico = "{\"Ambiental\":{\"Meses\":" + mesAmbiental + ",\"Medias\":" + valorAmbiental
				+ ",\"valor\":" + valoresJson[0] + "}," + "\"Social\":{\"Meses\":" + mesSocial + ",\"Medias\":"
				+ valorSocial + ",\"valor\":" + valoresJson[1] + "}}";

		return jsonBasico;
	}

	public boolean checarExistencia(String name, int data) {

		for (Metas metas : listaMetas) {
			if (metas.getMes().equals(data) && metas.getMeta().equals(name)) {
				return false;
			}
		}
		return true;

	}

	public Double[] getValores() {
		return valoresJson;
	}

	public void setValores(Double ambiental, Double social) {
		valoresJson[0] = ambiental;
		valoresJson[1] = social;
	}

}
