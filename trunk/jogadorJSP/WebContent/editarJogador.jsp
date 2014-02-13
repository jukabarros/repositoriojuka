<%@page import="controller.JogadorController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar Jogador</title>
</head>
<body>
<a href="listarTodos.jsp">Voltar</a><br />
<label><b>Editar Jogador</b></label>
<%
	int id = Integer.parseInt(request.getParameter("jogador"));
	JogadorController controller = new JogadorController();
	controller.consultarIdController(id);	
		
	if (controller.getListaJogador().isEmpty()){
		out.println("Jogador nao encontrado.");
		}
%>
<form action="confirmarEdicao.jsp" method="post">
ID: <input type="text" name="id" readonly="readonly" value=<%out.println(controller.getListaJogador().get(0).getId()); %>><br />
Nome: <input type="text" name="nome" maxlength="40" value=<%out.println(controller.getListaJogador().get(0).getNome()); %>><br />
Posicao: <input type="text" name="posicao" maxlength="40" value=<%out.println(controller.getListaJogador().get(0).getPosicao()); %>><br />
Idade: <input type="text" name="idade" maxlength="10" value=<%out.println(controller.getListaJogador().get(0).getIdade()); %>><br />
Time: <input type="text" name="time" maxlength="40" value=<%out.println(controller.getListaJogador().get(0).getTime()); %>><br />
<input type="submit" value="Editar">
</form>
</body>
</html>