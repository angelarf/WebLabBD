/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.DAOException;
import dao.SubdominioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ângela
 */
public class buscaSubdominio extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet buscaSubdominio</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet buscaSubdominio at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
        //processRequest(request, response);
    
        try {
            SubdominioDAO sdao = new SubdominioDAO();
            ArrayList s_list;
  
            String subdom = request.getParameter("inputNome");
            //System.out.println(subdom);
            String m = request.getParameter("mes");
            int mes = Integer.valueOf(m);
            
            s_list = sdao.buscarSubdominioPorMes(subdom, mes); //ARRUMAR
            
            request.setAttribute("s_list", s_list);

            RequestDispatcher d = null;
            d = request.getRequestDispatcher("/paginas/resultadoSub.jsp");
            d.forward(request, response);
        } catch (DAOException exception) {
            System.out.println("DAO Exception");
          //Logger.getLogger(GetUsers.class.getName()).log(Level.SEVERE, null, exception);
          throw new ServletException(exception.getMessage());
        } catch (SQLException exception) {
            
            System.out.println("SQL Exception");
          //Logger.getLogger(GetUsers.class.getName()).log(Level.SEVERE, null, exception);
          throw new ServletException(exception.getMessage());
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
