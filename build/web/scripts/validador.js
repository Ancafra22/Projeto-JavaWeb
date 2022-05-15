/* 
Validação de formulário
@author Andre Francisco
 */

/* global frmProduto */

function validar(){
    let nome = frmProduto.nome.value;
    let quantidade = frmProduto.quantidade.value;
    let valor = frmProduto.valor.value;
    if(nome === ""){
        alert('Preencha o campo Nome');
        frmProduto.nome.focus();
        return false;
    }else if (quantidade === ""){
        alert('Preencha o campo Quantidade');
        frmProduto.quantidade.focus();
        return false;
    }else if (valor === ""){
        alert('Preencha o campo Valor');
        frmProduto.valor.focus();
        return false;
    }else{
        document.forms["frmProduto"].submit();
    }
}