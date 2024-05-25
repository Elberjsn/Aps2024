package model;

import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;


public class GeradorGrafico {
	
	ModelMain mM = new ModelMain();

	
	File file ;
	
	public static void main(String[] args) {
		new GeradorGrafico();
		
	}
	
	public GeradorGrafico() {
		graficoAmbieltal();
		graficoSocial();
	}
	
	public void graficoAmbieltal() {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();

		
		for (Metas meta : mM.listaMetas) {
			
			if(meta.getMeta().equals("Ambiental")) {
				
				lineDataset.addValue((mM.valoresJson[0]/12) , "Objetivo", meta.getMesNome() ) ;
				
				dataset.addValue(meta.getValor(), meta.getMeta(),  meta.getMesNome() );
				
			}
		}

		CategoryPlot plot = new CategoryPlot();
		plot.setDataset(0, lineDataset);
		plot.setRenderer(0, new LineAndShapeRenderer());

		plot.setDataset(1, dataset);
		plot.setRenderer(1, new BarRenderer());

		plot.setDomainAxis(new CategoryAxis("Meses"));
		plot.setRangeAxis(new NumberAxis("Valores"));

		plot.setOrientation(PlotOrientation.VERTICAL);
		plot.setRangeGridlinesVisible(true);
		plot.setDomainGridlinesVisible(true);

		JFreeChart chart = new JFreeChart ("Metas Ambientais por Mes", null,plot, true);

		
		file = new File(System.getProperty("user.dir")+"\\src\\main\\webapp\\resources\\dynamic\\ambiental.jpeg");
		
		try {
			ChartUtils.saveChartAsJPEG(file, chart, 800, 400);
		} catch (IOException e) {
			e.printStackTrace();
		}

		

	}
	
	public void graficoSocial() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();

		
		for (Metas meta : mM.listaMetas) {
			
			if(meta.getMeta().equals("Social")) {
				lineDataset.addValue(1.3, "Objetivo",  meta.getMesNome());
				dataset.addValue(meta.getValor(), meta.getMeta(), meta.getMesNome());
			}
		}

		CategoryPlot plot = new CategoryPlot();
		plot.setDataset(0, lineDataset);
		plot.setRenderer(0, new LineAndShapeRenderer());

		plot.setDataset(1, dataset);
		plot.setRenderer(1, new BarRenderer());

		plot.setDomainAxis(new CategoryAxis("Meses"));
		plot.setRangeAxis(new NumberAxis("Valores"));

		plot.setOrientation(PlotOrientation.VERTICAL);
		plot.setRangeGridlinesVisible(true);
		plot.setDomainGridlinesVisible(true);

		JFreeChart chart = new JFreeChart("Metas Sociais por Mes", null,plot,true);

		file = new File(System.getProperty("user.dir")+"\\src\\main\\webapp\\resources\\dynamic\\social.jpeg");
		
		try {
			ChartUtils.saveChartAsJPEG(file, chart, 800, 400);
		} catch (IOException e) {
			e.printStackTrace();
		}

		

	}
	
	

}
