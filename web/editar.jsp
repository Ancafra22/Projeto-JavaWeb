<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title>Cadastro de produtos</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <h1>Editar Produto</h1>
        <form name="frmProduto" action="update">
            <table>
                <tr>
                    <td><input type="text" name="id" id="Caixa3" readonly value="<%out.print(request.getAttribute("id"));%>"></td>
                </tr>
                <tr>
                    <td><input type="text" name="nome" class="Caixa1" value="<%out.print(request.getAttribute("nome"));%>"></td>
                </tr>
                <tr>
                   <td><input type="text" name="quantidade" class="Caixa2" value="<%out.print(request.getAttribute("quantidade"));%>"></td> 
                </tr>
                <tr>
                    <td><input type="text" name="valor" class="Caixa2" value="<%out.print(request.getAttribute("valor"));%>"></td>
                </tr>
                <tr>
                    <td><input type="text" name="valorVenda" class="Caixa2" value="<%out.print(request.getAttribute("valorVenda"));%>"></td>
                </tr>
            </table>
            <input type="submit" value="Salvar" class="Botao1">
        </form>
        <script src="scripts/validador.js"></script>
    </body>
</html>
