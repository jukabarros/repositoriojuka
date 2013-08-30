package conveter;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import medicamento.Medicamento;
import medicamento.MedicamentoService;


@FacesConverter(value="MedicamentoConverter", forClass=Medicamento.class)
public class MedicamentoConverter implements Converter{
	
	private MedicamentoService medicamentoService = MedicamentoService.getInstance();
	private List<Medicamento> listaMedicamento = new ArrayList<Medicamento>();
	
	@Override  
    public Object getAsObject(FacesContext context, UIComponent component,	String value){
    	try{
    		if (value.equals(null) || value.equals("Nenhum") || value.equals("")){
    			Medicamento medicamento = null;
    			return medicamento;
    			
    		}
    		else{
    			listaMedicamento = medicamentoService.buscarPorNomeConverter(value); // Capturando o Objeto de acordo com Nome
    			Medicamento medicamento = listaMedicamento.get(0);  // Pegando o Objeto dentro da lista
    			return medicamento;
    		}
    	}catch(Exception e){
    		System.out.println("Erro na conversao!!");
    		return value;
    	}
    	
	}
	
	@Override  	 
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof Medicamento) {
			
			Medicamento medicamento = (Medicamento) value;		
			return String.valueOf(medicamento.getNome()); // Valor que vai ser exibido nos datatable e que vai ser usado no metodo acima p/ converter p/ obj.
		}
		return null;
		}


}
