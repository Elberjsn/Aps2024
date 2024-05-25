package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ManipulacaoFile {

	ClassLoader classLoader = ManipulacaoFile.class.getClassLoader();
	File file = new File(classLoader.getResource("db.json").getPath());
	
	
	public Double[] valores= {0.0,0.0} ;
	
	public static void main(String[] args) {
		new ManipulacaoFile();
		
	}
	
	public ManipulacaoFile() {
		fileToArray();
	}
	
	
	
	public List<Metas> fileToArray() {
		
		List<Metas> listaMetas = new ArrayList<>();
		
		List<String> metas = new ArrayList<>();
		List<String> meses = new ArrayList<>();
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode json = mapper.readTree(file);
			
			metas= chaves(json);
			
			for (String meta : metas) {
				if(meta.equals("Ambiental") || meta.equals("Social")) {
					meses = chaves(json.get(meta));
					int i=1;
					for (String mes : meses) {
						JsonNode j = json.get(meta).path(mes);
						
						listaMetas.add(new Metas(meta, i , j.findValue("valor").asDouble(), j.findValue("ano").asInt()));
						
						i++;
					}
				}else {
					JsonNode j = json.get(meta);
					
					valores[0] = j.get("Ambiental").asDouble();
					valores[1] = j.get("Social").asDouble();
				}
			}
					
		} catch (Exception e) {
			System.err.println("Erro Open File = "+e);
		}
		
		return listaMetas;

	}
	
	public void arrayToFile(List<Metas> listaMetas) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"Valores\":{\"Ambiental\":"+valores[0]+",\"Social\":"+valores[0]+"},");
		int i =0;
		for (Metas metas : listaMetas) {
			
			if(metas.getMeta().equals("Ambiental")) {
				sb.append(metas.stringForFile());
				
			}else {
				sb.append(metas.stringForFile());
			}
			i++;
			if(i!=listaMetas.size()) {
				sb.append(",");
			}
		}
		sb.append("}");
		
		try  {
			
	        OutputStream os = new FileOutputStream(file); 
	        Writer wr = new OutputStreamWriter(os); 
	        BufferedWriter br = new BufferedWriter(wr);
	        
	        br.write(sb.toString());
	        br.close();
		    
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}

	public List<String> chaves(JsonNode json) {
		List<String> list = new ArrayList<>();

		Iterator<String> i = json.fieldNames();

		i.forEachRemaining(e -> list.add(e));
		
		return list;

	}
}
