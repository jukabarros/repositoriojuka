package conveter;

import java.util.ArrayList;
import java.util.List;
import javax.faces.convert.FacesConverter;
import javax.faces.component.UIComponent;  
import javax.faces.context.FacesContext;  
import javax.faces.convert.Converter;
import paciente.Paciente;
import paciente.PacienteService;


@FacesConverter(value="PacienteConverter", forClass=Paciente.class) 
public class PacienteConverter implements Converter {
	
	private PacienteService pacienteService = PacienteService.getInstance();
	private List<Paciente> listaPaciente = new ArrayList<Paciente>();
	
	@Override  
    public Object getAsObject(FacesContext context, UIComponent component,	String value){
    	try{
    		if (value.equals(null) || value.equals("")){
    			return value;
    			
    		}
    		else{
    			listaPaciente = pacienteService.buscarPorNomeConverter(value); // Capturando o Objeto de acordo com Nome
    			Paciente paciente = listaPaciente.get(0);  // Pegando o Objeto dentro da lista
    			return paciente;
    		}
    	}catch(Exception e){
    		return value;
    	}
    	
	}
	
	@Override  	 
		
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof Paciente) {
			
			Paciente paciente = (Paciente) value;		
			return String.valueOf(paciente.getNome()); // Valor que vai ser exibido nos datatable e que vai ser usado no metodo acima p/ converter p/ obj.
		}
		return null;
		}

}
