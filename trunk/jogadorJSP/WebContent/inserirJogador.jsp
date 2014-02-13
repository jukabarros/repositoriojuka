<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inserir Jogador</title>
</head>
<body>
<a href="listarTodos.jsp">Voltar</a><br />
<label><b>Inserir Novo Jogador</b></label>
<form action="confirmarInserir.jsp" method="post">
Nome: <input type="text" name="nome" maxlength="40"><br />
Posicao: <input type="text" name="posicao" maxlength="40"><br />
Idade: <input type="text" name="idade" maxlength="10"><br />
Time: <input type="text" name="time" maxlength="40"><br />
<input type="submit" value="Inserir">
</form>
</body>
</html>