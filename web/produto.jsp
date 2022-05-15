<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans" %>
<%@ page import="java.util.ArrayList"%>
<%
    ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("produtos");
    
%>
         <!DOCTYPE html>

<html lang="pt-br">
    <head>
        <title>Cadastro de produtos</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        
        <h1>Cadastro de produtos</h1>
        <a href="novo.html" class="Botao1">Novo Produto</a>
        <a href="report" class="Botao2">Relatório</a>
       
        <table id="tabela">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nome</th>
                    <th>Quantidade</th>
                    <th>Valor</th>
                    <th>Valor de Venda</th>
                    <th><center>Opções</center></th>
                </tr>
            </thead>
            <tbody>
                <%for(int i=0;i<lista.size();i++){%>
                <tr>
                    <td><%=lista.get(i).getId()%></td>
                    <td><%=lista.get(i).getNome()%></td>
                    <td><%=lista.get(i).getQuantidade()%></td>
                    <td><%=lista.get(i).getValor()%></td>
                    <td><%=lista.get(i).getValorVenda()%></td>
                    <td>
                        <a href="select?id=<%=lista.get(i).getId()%>" class="Botao1">Editar</a>
                        <a href="javascript: confirmar(<%=lista.get(i).getId()%>)" class="Botao2">Excluir</a>
                    </td>
                </tr>
                <%} %>
            </tbody>
        </table>
            <script src="scripts/confirmador.js"></script>
    </body>
</html>
