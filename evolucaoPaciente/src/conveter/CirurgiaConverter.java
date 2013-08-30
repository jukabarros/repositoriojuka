package conveter;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import cirurgia.Cirurgia;
import cirurgia.CirurgiaService;

@FacesConverter(value="CirurgiaConverter", forClass=Cirurgia.class)
public class CirurgiaConverter implements Converter {
	
	private CirurgiaService cirurgiaService = CirurgiaService.getInstance();
	private List<Cirurgia> listaCirurgia = new ArrayList<Cirurgia>();
	
	@Override  
    public Object getAsObject(FacesContext context, UIComponent component,	String value){
    	try{
    		if (value.equals(null) || value.equals("Nenhum") || value.equals("")){
    			Cirurgia cirurgia = null;
    			return cirurgia;
    			
    		}
    		else{
    			listaCirurgia = cirurgiaService.buscarPorNomeConverter(value); // Capturando o Objeto de acordo com Nome
    			Cirurgia cirurgia = listaCirurgia.get(0);  // Pegando o Objeto dentro da lista
    			return cirurgia;
    		}
    	}catch(Exception e){
    		System.out.println("Erro na conversao!!");
    		return value;
    	}
    	
	}
	
	@Override  	 
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof Cirurgia) {
			
			Cirurgia cirurgia = (Cirurgia) value;		
			return String.valueOf(cirurgia.getNome()); // Valor que vai ser exibido nos datatable e que vai ser usado no metodo acima p/ converter p/ obj.
		}
		return null;
		}

}
