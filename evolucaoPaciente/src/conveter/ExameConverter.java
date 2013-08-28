package conveter;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import exame.Exame;
import exame.ExameService;


@FacesConverter(value="ExameConverter", forClass=Exame.class)
public class ExameConverter implements Converter{
	
	private ExameService exameService = ExameService.getInstance();
	private List<Exame> listaExame = new ArrayList<Exame>();
	
	@Override  
    public Object getAsObject(FacesContext context, UIComponent component,	String value){
    	try{
    		if (value.equals(null) || value.equals("Nenhum") || value.equals("")){
    			Exame exame = null;
    			return exame;
    			
    		}
    		else{
    			listaExame = exameService.buscarPorNomeConverter(value); // Capturando o Objeto de acordo com Nome
    			Exame exame = listaExame.get(0);  // Pegando o Objeto dentro da lista
    			return exame;
    		}
    	}catch(Exception e){
    		System.out.println("Erro na conversao!!");
    		return value;
    	}
    	
	}
	
	@Override  	 
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof Exame) {
			
			Exame exame = (Exame) value;		
			return String.valueOf(exame.getNome()); // Valor que vai ser exibido nos datatable e que vai ser usado no metodo acima p/ converter p/ obj.
		}
		return null;
		}

}
