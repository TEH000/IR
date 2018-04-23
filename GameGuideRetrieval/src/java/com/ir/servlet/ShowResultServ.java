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
import java.util.ArrayList;
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
@WebServlet(name = "ShowResultServ", urlPatterns = {"/ShowResultServ"})
public class ShowResultServ extends HttpServlet {
    private List<MyDocument> docs;
    private MyIndexReader ixreader;
    private QueryRetrievalModel model;
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
        String category = request.getParameter("category");
        ixreader = new MyIndexReader();
	model = new QueryRetrievalModel(ixreader);
        if(category!=null){
             docs = model.retrieveQuery(query, 100,category);
             request.setAttribute("cate",category);
        }else{
            docs = model.retrieveQuery(query, 100,"all");
            request.setAttribute("cate","all");
        }
        
        String p = request.getParameter("page");
        int page;
        try {
            //当前页数
            page = Integer.valueOf(p);
        } catch (NumberFormatException e) {
            page = 1;
        }
        //doc总数
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
        request.setAttribute("docs", docs);
        
        request.getRequestDispatcher("ShowResult.jsp").forward(request,response);

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
            Logger.getLogger(ShowResultServ.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ShowResultServ.class.getName()).log(Level.SEVERE, null, ex);
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
