<%@page import="controller.JogadorController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar Todos Jogadores</title>
</head>
<body>
<label><b>Lista de Todos os Jogadores</b></label><br />
<hr />
<form action="consultarNome.jsp" method="post">
Consultar Nome: <input type="text" name="nome">
<input type="submit" value="Pesquisar">
<a href="inserirJogador.jsp">Inserir Novo Jogador</a><br /><hr />
</form>
<%
	JogadorController controller = new JogadorController();
	controller.listarTodosController();
	
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


%>
<HR />
<b><i>Desenvolvido por Juccelino Barros</i></b>
</body>
</html>