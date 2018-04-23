/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ir.servlet;

import com.ir.Class.MyDocument;
import com.ir.Indexing.MyIndexReader;
import com.ir.Search.QueryRetrievalModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dongguo
 */
@WebServlet(name = "SearchPageServ", urlPatterns = {"/SearchPageServ"})
public class SearchPageServ extends HttpServlet {
    
    private MyIndexReader ixreader;
    private QueryRetrievalModel model;

//    public SearchPageServ() throws IOException {
//        this.ixreader = new MyIndexReader();
//        this.model = new QueryRetrievalModel(ixreader);
//    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String query = request.getParameter("query");
        try{
//            MyIndexReader ireader = new MyIndexReader();
        ixreader = new MyIndexReader();
	model = new QueryRetrievalModel(ixreader);
        List<MyDocument> docs = model.retrieveQuery(query, 100,"all");
        request.setAttribute("query", query);
        request.setAttribute("docs", docs);
        String p = request.getParameter("page");
        int page;
        try {
            //当前页数
            page = Integer.valueOf(p);
        } catch (NumberFormatException e) {
            page = 1;
        }
        int totalDocs = docs.size();
        //每页doc数
        int docsPerPage = 10;
        //总页数
        int totalPages = totalDocs % docsPerPage == 0 ? totalDocs / docsPerPage : totalDocs / docsPerPage + 1;
        //本页起始用户序号
        int beginIndex = (page - 1) * docsPerPage;
        //本页末尾用户序号的下一个
        int endIndex = beginIndex + docsPerPage;
        if (endIndex > totalDocs)
            endIndex = totalDocs;
        request.setAttribute("query",query);
        request.setAttribute("totalDocs", totalDocs);
        request.setAttribute("docsPerPage", docsPerPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("beginIndex", beginIndex);
        request.setAttribute("endIndex", endIndex);
        request.setAttribute("page", page);
        request.setAttribute("cate", "all");
        request.getRequestDispatcher("ShowResult.jsp").forward(request, response);
//        request.getRequestDispatcher("/ShowResultServ").forward(request, response);
        }catch(Exception e) {
            response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShowResultServ</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Got a Exception：" + e.getMessage()+ "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SearchPageServ.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SearchPageServ.class.getName()).log(Level.SEVERE, null, ex);
        }
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
