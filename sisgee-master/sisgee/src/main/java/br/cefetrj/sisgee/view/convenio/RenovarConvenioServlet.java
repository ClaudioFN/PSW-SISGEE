/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.convenio;

import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.model.entity.Convenio;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucas Lima
 */
@WebServlet(name = "RenovarConvenioServlet", urlPatterns = {"/RenovarConvenioServlet"})
public class RenovarConvenioServlet extends HttpServlet {

   
    
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        System.out.println("ENTROU NO RENOVAR CONVENIO SERVLEEEEEEET");
        String numeroConvenio = req.getParameter("convenio");
        System.out.println(numeroConvenio);
        
        Convenio convenio = ConvenioServices.buscarConvenioByNumeroConvenio(numeroConvenio);
        
        req.setAttribute("Renovar", convenio);
        if(convenio.getEmpresa()==null){
            req.setAttribute("isEmpresa", "nao");
        }else{
            req.setAttribute("isEmpresa", "sim");
        }
        
        req.getRequestDispatcher("form_empresa.jsp").forward(req, resp);
        
    }

   

}
