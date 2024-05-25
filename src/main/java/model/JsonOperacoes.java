package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonOperacoes {

	ClassLoader classLoader = JsonOperacoes.class.getClassLoader();
	File file = new File(classLoader.getResource("db.json").getFile());
	
	Double[] valoresJson= {0.0,0.0};
	
	public static void main(String[] args) {
		new JsonOperacoes();
		
	}
	public JsonOperacoes() {
		fileToJson();
	}
	
	public List<Metas> fileToJson() {

		List<Metas> listaMetas = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();

		try {
			
			JsonNode json = mapper.readTree(file);

			List<String> metaName = new ArrayList<>();
			List<String> mesesMes = new ArrayList<>();

			Iterator<String> metas = json.fieldNames();

			metas.forEachRemaining( e -> metaName.add(e));
			
			for (String meta : metaName){
				JsonNode valores = json.get(meta);
				metas = valores.fieldNames();
				metas.forEachRemaining(e-> mesesMes.add(e));
				
				for(String mes : mesesMes){
					JsonNode meses = json.get(mes);
					System.out.println(meses);
				}

			}

		} catch (Exception e) {
			System.out.println("Error File To Json :\n" + e);
		}
		
		return listaMetas;
	}

	public void jsonToFile(List<Metas> listaMetas) {

		ObjectMapper mapper = new ObjectMapper();
		
        ObjectNode jsonNode = mapper.createObjectNode(); 
        System.out.println(valoresJson);
        jsonNode.put("valores", "{Ambiental:"+valoresJson[0]+",Social:"+valoresJson[1]+"}");
        
		int i = 1;
		for (Metas metas : listaMetas) {
			
			jsonNode.put(String.valueOf(i), metas.toString());
			
			i++;
		}
		
		try (OutputStream out = new FileOutputStream(file);) {
				mapper.writeValue(out, jsonNode); 

		} catch (JsonProcessingException ex) {
			System.out.println("Errou ao Abir arquivo! :\n" + ex.getMessage());
		} catch (Exception e) {
			System.out.println("Error Json to File :\n" + e.getMessage());
		}

	}

}
