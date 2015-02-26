package riso.consulta.controller;

import br.com.caelum.vraptor.Resource;
import riso.consulta.entity.Consulta;

@Resource
public class ConsultaController {
	
	public Consulta padroniza(Consulta consulta){
		consulta.padroniza();
		return consulta;
	}
	
	public void form(){
		
	}
	
	
}
