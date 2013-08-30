package conveter;

import java.util.ArrayList;
import java.util.List;
import javax.faces.convert.FacesConverter;
import javax.faces.component.UIComponent;  
import javax.faces.context.FacesContext;  
import javax.faces.convert.Converter;

import pressao_arterial.PressaoArterial;
import pressao_arterial.PressaoArterialService;

@FacesConverter(value="PressaoArterialConverter", forClass=PressaoArterial.class) 
public class PressaoArterialConverter implements Converter {
	
	private PressaoArterialService pressaoArterialService = PressaoArterialService.getInstance();
	private List<PressaoArterial> listaPressaoArterial = new ArrayList<PressaoArterial>();
	
	@Override  
    public Object getAsObject(FacesContext context, UIComponent component,	String value){
    	try{
    		if (value.equals(null) || value.equals("Nenhum") || value.equals("")){
    			PressaoArterial pressaoArterial = null;
    			return pressaoArterial;
    			
    		}else{
    			int valor = Integer.parseInt(value);
    			listaPressaoArterial = pressaoArterialService.pressaoArterialConverter(valor); // Capturando o Objeto de acordo com ID
    			PressaoArterial pressaoArterial = listaPressaoArterial.get(0);  // Pegando o Objeto dentro da lista
    			return pressaoArterial;
    		}
    	}catch(Exception e){
    		System.out.println("ERRO NA CONVERSAO!");
    	}
    	return value;
	}
	
	@Override  	 
		
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof PressaoArterial) {
			
			PressaoArterial pressaoArterial = (PressaoArterial) value;		
			return String.valueOf(pressaoArterial.getSistolica()); // Valor do ID que vai ser usado no metodo acima p/ converter p/ obj.
		}
		return null;
		}


}
