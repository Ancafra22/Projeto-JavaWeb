/* 
 confirmação de exclusão de produto
@author: andre francisco
 */


function confirmar(id){
    let resposta = confirm("confirma a exclusão dete produto?");
    if(resposta === true){
       window.location.href = "delete?id="+id;
    }
}