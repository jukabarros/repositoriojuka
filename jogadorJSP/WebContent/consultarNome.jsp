<%@page import="controller.JogadorController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consultar Nome</title>
</head>
<body>
<a href="listarTodos.jsp">Voltar</a><br />
<label><b>Resultado da Consulta</b></label><br /> <hr />
<%
	
	String nome = request.getParameter("nome");
	JogadorController controller = new JogadorController();
	controller.consultarNomeController(nome);
	
	if (controller.getListaJogador().isEmpty()){
		out.println("Jogador nao encontrado.");
	}else{
		for (int i = 0; i < controller.getListaJogador().size(); i++) {
			out.println("ID: "+controller.getListaJogador().get(i).getId()+"<BR />");
			out.println("NOME: "+controller.getListaJogador().get(i).getNome()+"<BR />");
			out.println("POSICAO: "+controller.getListaJogador().get(i).getPosicao()+"<BR />");
			out.println("IDADE: "+controller.getListaJogador().get(i).getIdade()+"<BR />");
			out.println("TIME: "+controller.getListaJogador().get(i).getTime()+"<BR />");
			out.println("<a href=excluirJogador.jsp?jogador="+controller.getListaJogador().get(i).getId()+"> Excluir</a>");
			out.println("<a href=editarJogador.jsp?jogador="+controller.getListaJogador().get(i).getId()+">Editar</a>");
			out.println("<BR /><HR />");
		}
		
	}
	


%>
</body>
</html>