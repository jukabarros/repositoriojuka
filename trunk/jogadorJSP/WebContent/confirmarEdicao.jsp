<%@page import="controller.JogadorController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirmar Edicao</title>
</head>
<body>
<a href="listarTodos.jsp">Voltar</a><br />
<%
	try{
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String posicao = request.getParameter("posicao");
		String time = request.getParameter("time");
		int idade = Integer.parseInt(request.getParameter("idade"));
		
		JogadorController controller = new JogadorController();
		controller.getJogador().setId(id);
		controller.getJogador().setNome(nome);
		controller.getJogador().setPosicao(posicao);
		controller.getJogador().setTime(time);
		controller.getJogador().setIdade(idade);
		
		controller.editarController(controller.getJogador());
		
		out.println("Jogador Editado com sucesso!!");
		
		
	}catch(Exception e){
		out.println("Erro ao editar o jogador: "+e.getMessage());
	}


%>
</body>
</html>