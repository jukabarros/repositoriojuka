package conveter;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import refeicao.Refeicao;
import refeicao.RefeicaoService;

@FacesConverter(value="RefeicaoConverter", forClass=Refeicao.class)
public class RefeicaoConverter implements Converter {
	
	private RefeicaoService refeicaoService = RefeicaoService.getInstance();
	private List<Refeicao> listaRefeicao = new ArrayList<Refeicao>();
	
	@Override  
    public Object getAsObject(FacesContext context, UIComponent component,	String value){
    	try{
    		if (value.equals(null) || value.equals("Nenhum") || value.equals("")){
    			Refeicao refeicao = null;
    			return refeicao;
    			
    		}
    		else{
    			listaRefeicao = refeicaoService.buscarPorPratoConverter(value); // Capturando o Objeto de acordo com Nome
    			Refeicao refeicao = listaRefeicao.get(0);  // Pegando o Objeto dentro da lista
    			return refeicao;
    		}
    	}catch(Exception e){
    		System.out.println("Erro na conversao!!");
    		return value;
    	}
    	
	}
	
	@Override  	 
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof Refeicao) {
			
			Refeicao refeicao = (Refeicao) value;		
			return String.valueOf(refeicao.getPrato()); // Valor que vai ser exibido nos datatable e que vai ser usado no metodo acima p/ converter p/ obj.
		}
		return null;
		}
	

}
