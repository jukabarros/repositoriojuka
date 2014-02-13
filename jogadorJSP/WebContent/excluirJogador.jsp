<%@page import="controller.JogadorController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Excluir Jogador</title>
</head>
<body>
<a href="listarTodos.jsp">Voltar</a><br />
<%
	try{
		int id = Integer.parseInt(request.getParameter("jogador"));
		
		JogadorController controller = new JogadorController();
		controller.excluirController(id);
		out.println("Jogador excluido com sucesso!!");
		
	}catch(Exception e){
		out.println("Erro ao excluir o jogador: "+e.getMessage());
	}


%>
</body>
</html>