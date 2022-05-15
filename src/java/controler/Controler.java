
package controler;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.JavaBeans;

/**
 *
 * @author Andre Francisco
 */
@WebServlet(name = "Controler", urlPatterns = {"/Controler","/main","/insert","/select","/update","/delete","/report"})
public class Controler extends HttpServlet {
private static final long serialVersionIDI = 1L;
   DAO dao = new DAO();
   JavaBeans produto = new JavaBeans();
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
        String action = request.getServletPath();
        System.out.println(action);
        if(action.equals("/main")){
            produtos(request,response);
        }else if(action.equals("/insert")){
            novoProduto(request,response);  
        }else if(action.equals("/select")){
            listarProduto(request,response);
        }else if(action.equals("/update")){
            editarProduto(request,response);
        }else if(action.equals("/delete")){
            removerProduto(request,response);
        }else if(action.equals("/report")){
            gerarRelatorio(request,response);
        }else{
            response.sendRedirect("index.html");
        }
    }
        
        }
    protected void produtos(HttpServletRequest request, HttpServletResponse response) 
                throws ServletException, IOException {
        //objeto q ira receber os dados da classe JavaBeans
            ArrayList<JavaBeans> lista = dao.listarProdutos();
               request.setAttribute("produtos", lista);
            RequestDispatcher rd = request.getRequestDispatcher("produto.jsp");
            rd.forward(request, response);
            }
    
    protected void novoProduto(HttpServletRequest request, HttpServletResponse response) 
                throws ServletException, IOException {
            
            produto.setNome(request.getParameter("nome"));
            produto.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
            produto.setValor(Double.parseDouble(request.getParameter("valor")));
            produto.setValorVenda(Double.parseDouble(request.getParameter("valorVenda")));
            
            dao.inserirContato(produto);
            //redirecionar
            response.sendRedirect("main");
    }
    //editar contato
    protected void listarProduto(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        //recebimento do id do contato editado
            String id = request.getParameter("id");
            //setar a variavel
            produto.setId(Integer.parseInt(id));
            //executar o selecionarProduto
            dao.selecionarProdutos(produto);
            //setar
            request.setAttribute("id", produto.getId());
            request.setAttribute("nome", produto.getNome());
            request.setAttribute("quantidade", produto.getQuantidade());
            request.setAttribute("valor", produto.getValor());
            request.setAttribute("valorVenda", produto.getValorVenda());
            //encaminhar
            RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
            rd.forward(request, response);
           }
           //editar produtos
            protected void editarProduto(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
                //setar as variaveis
                produto.setId(Integer.parseInt(request.getParameter("id")));
                produto.setNome(request.getParameter("nome"));
                produto.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
                produto.setValor(Double.parseDouble(request.getParameter("valor")));
                produto.setValorVenda(Double.parseDouble(request.getParameter("valorVenda")));
                //executar metodo alteração
                dao.alterarProduto(produto);
                //redirecinar cadastro alterado
                response.sendRedirect("main");
                
            }
            //remover contato
            protected void removerProduto(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
                int id = Integer.parseInt(request.getParameter("id"));
                produto.setId(id);
                dao.deletarContato(produto);
                response.sendRedirect("main");
            }
            
            //gerar lista de produtos em  PDF 
            protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
                Document documento = new Document();
                try {
                    response.setContentType("apllication/pdf");
                    response.addHeader("Content-Disposition","inline; filename="+"produtos.pdf");
                    PdfWriter.getInstance(documento, response.getOutputStream());
                    documento.open();
                    documento.add(new Paragraph("Lista de produtos:"));
                    documento.add(new Paragraph(" "));
                    PdfPTable tabela = new PdfPTable(5);
                    PdfPCell col1 = new PdfPCell(new Paragraph("Id"));
                    PdfPCell col2 = new PdfPCell(new Paragraph("Nome"));
                    PdfPCell col3 = new PdfPCell(new Paragraph("Quantidade"));
                    PdfPCell col4 = new PdfPCell(new Paragraph("Valor"));
                    PdfPCell col5 = new PdfPCell(new Paragraph("Valor de Venda"));
                    tabela.addCell(col1);
                    tabela.addCell(col2);
                    tabela.addCell(col3);
                    tabela.addCell(col4);
                    tabela.addCell(col5);
                    ArrayList<JavaBeans> lista = dao.listarProdutos();
                    for(int i =0; i<lista.size(); i++){
                        tabela.addCell(lista.get(i).getId()+ " ");
                        tabela.addCell(lista.get(i).getNome());
                        tabela.addCell(lista.get(i).getQuantidade()+"");
                        tabela.addCell(lista.get(i).getValor()+"");
                        tabela.addCell(lista.get(i).getValorVenda()+"");
                    }
                    documento.add(tabela);
                    documento.close();
                } catch (Exception e) {
                    System.out.println(e);
                    documento.close();
                }
            }
            
            
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}