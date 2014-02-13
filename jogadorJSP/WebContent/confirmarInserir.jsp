<%@page import="controller.JogadorController"%>
<%@page import="entidade.Jogador"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirmar Insercao</title>
</head>
<body>
<a href="listarTodos.jsp">Voltar</a><br/>
<%
	try{
		String nome = request.getParameter("nome");
		String posicao = request.getParameter("posicao");
		int idade = Integer.parseInt(request.getParameter("idade"));
		String time = request.getParameter("time");
		
		JogadorController controller = new JogadorController();
		controller.getJogador().setNome(nome);
		controller.getJogador().setPosicao(posicao);
		controller.getJogador().setIdade(idade);
		controller.getJogador().setTime(time);
		
		controller.inserirController(controller.getJogador());
		out.println("Jogador inserido com sucesso!!");
		
	}catch(Exception e){
		out.println("Erro ao inserir o jogador: "+e.getMessage());
	}


%>
</body>
</html>