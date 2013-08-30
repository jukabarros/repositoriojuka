package pressao_arterial;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import pressao_arterial.PressaoArterialService;
import pressao_arterial.PressaoArterial;


@ManagedBean(name="pressaoArterialBean")
@ViewScoped
public class PressaoArterialController implements Serializable{

	private static final long serialVersionUID = 1438825125881451743L;

	private PressaoArterial pressaoArterial = new PressaoArterial();
	private PressaoArterialService pressaoArterialService = PressaoArterialService.getInstance();	// Nao existe Get e Set pois nao eh chamado nos arquivos JSF 

	private List<PressaoArterial> listaPressaoArterial = new ArrayList<PressaoArterial>();	

	public PressaoArterialController() {

		atualizarTela();
	} 

	/**
	 * Limpa os campos input e atualiza a lista de pressaoArterials cadastrados
	 */

	private void atualizarTela() {
		System.out.println("\n*** Refresh da Pagina / Consultando Todos os Registro da Tabela PressaoArterial\n");
		pressaoArterial = new PressaoArterial();
		listaPressaoArterial = pressaoArterialService.buscarTodos();

	}

	public PressaoArterial getPressaoArterial() {
		return pressaoArterial;
	}

	public void setPressaoArterial(PressaoArterial pressaoArterial) {
		this.pressaoArterial = pressaoArterial;
	}

	public List<PressaoArterial> getListaPressaoArterial() {
		return listaPressaoArterial;
	}

	public void setListaPressaoArterial(List<PressaoArterial> listaPressaoArterial) {
		this.listaPressaoArterial = listaPressaoArterial;
	}
	
	
}
