package model;

public class Metas {
	private String meta;
	private Integer mes;
	private Double valor;
	private Integer ano;

	public Metas(String meta, Integer mes, Double valor) {
		this.meta = meta;
		this.mes = mes;
		this.valor = valor;
	}

	public Metas(String meta, Integer mes, Double valor, Integer ano) {
		this.meta = meta;
		this.mes = mes;
		this.valor = valor;
		this.ano = ano;
	}


	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}
	
	public String getMesNome() {
		return getMesNome(mes);
	}

	@Override
	public String toString() {
		return "{nome:" + this.meta + ",data:" + this.mes + ",valor:" + this.valor + "}";
	}
	
	public String stringForFile() {
		return "{\"" + this.getMesNome() + "\":{"+"\"ano\":"+this.ano+",\"valor\":"+this.valor+"}}";
	}

	public StringBuilder stringFormatTable(int i) {

		StringBuilder sb = new StringBuilder();
		int j = i + 1;
		sb.append("<tr class=\"linetable\" id=\"tr-" + i + "\">");
		sb.append("<th scope=\"row\" name=id>" + j + "</th>");
		sb.append("<td name=\"meta\">" + this.meta + "</td>");
		sb.append("<td name=\"data\">" + getMesNome(this.mes) + "</td>");
		sb.append("<td name=\"value\">" + this.valor + "</td>");
		sb.append("<td><button class=\"btn\"  onclick=\"clickBtnDelete(this)\" value=" + i
				+ "><i class=\"bi bi-trash3\"></i></button>");
		sb.append("<td><button class=\"btn\"  onclick=\"clickBtnAlter(this)\" value=" + i
				+ "><i class=\"bi bi-pencil-square\"></i></i></button>");
		sb.append("</tr>");
		return sb;
	}

	public String getMesNome(int mes) {

		String[] meses = { "mes", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Julho", "Junho", "Agosto",
				"Setembro", "Outubro", "Novenbro", "Dezembro" };

		return meses[mes];

	}
	public int getMesInt(String mesString) {

		String[] meses = { "Mes", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Julho", "Junho", "Agosto",
				"Setembro", "Outubro", "Novenbro", "Dezembro" };
		int i=0;
		
		for(String mes : meses) {
			if(meses.equals(mes)) {
				return i;
			}
			i++;
		}
		
		return i;
		

	}

}
