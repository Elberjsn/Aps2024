package controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(name = "ControllerView",urlPatterns = "/controller")
public class ControllerView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ControllerMain cM = new ControllerMain();

	
	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {

	     
		int opc = Integer.parseInt(req.getParameter("opc"));
		PrintWriter out = resp.getWriter();
		
		
		if (opc == 0) {	
			out.print(cM.buscar());
			
		}else if (opc==1) {
			String meta  = req.getParameter("meta");
			String mes  = req.getParameter("mes");
			Double valor = Double.parseDouble(req.getParameter("valor"));
			if(cM.adicionar(meta, "2024-"+mes+"-01", valor)) {
				out.print(cM.buscar());
			}else {
				out.print("1");
			}
			
		}else if (opc==2) {
			
			
			String meta  = req.getParameter("meta");
			String mes  = req.getParameter("mes");
			Double valor = Double.parseDouble(req.getParameter("valor"));
			int index = Integer.parseInt(req.getParameter("valor"));
			
			out.print(cM.alter(meta, mes, valor, index));
			
		}else if (opc==3) {
			int index  =Integer.valueOf(req.getParameter("id"));
			out.print(cM.remover(index));
		}else if(opc==4) {
			
			String meta  = req.getParameter("meta");
			String mes  = req.getParameter("mes");
			
			if(!mes.equals("undefined") && !meta.equals("undefined")) {
				out.print(cM.buscarMesEMeta(meta, "2024-"+mes+"-01"));
				
			}else if (!mes.equals("undefined")) {
				out.print(cM.buscarMes("2024-"+mes+"-01"));
				
			}else if(!meta.equals("undefined")) {
				out.print(cM.buscarMeta(meta));
			}else {
				out.print(cM.buscar());
			}
			
		}else if(opc==5) {
			out.print(cM.mediasMetas());
		}else if(opc==6) {
			out.print(cM.getValores());
		}else if(opc==7) {
			Double a  = Double.parseDouble(req.getParameter("a"));
			Double s  = Double.parseDouble(req.getParameter("s"));
			cM.setValores(a, s);
		}else if(opc==8) {
			cM.atualizarGrafico();
		}
		else {
			System.out.println("nada");
		}
		
		//cM.salvar();

		//cM.alterJson();
		
	}
	




	
	
}
