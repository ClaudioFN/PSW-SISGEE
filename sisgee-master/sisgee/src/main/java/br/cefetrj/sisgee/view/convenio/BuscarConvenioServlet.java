/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.convenio;

import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.control.EmpresaServices;
import br.cefetrj.sisgee.control.PessoaServices;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.Empresa;
import br.cefetrj.sisgee.model.entity.Pessoa;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucas Lima
 */
public class BuscarConvenioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nome = "";
        String numero = "";
        request.setAttribute("filtro", null);
        System.out.println("ENTROU AQUI");

        numero = request.getParameter("numeroConvenio");
        nome = request.getParameter("razaoSocial");

        String idEmpresa = "";
        request.setAttribute("selecao", null);

        Convenio convenio = null;
        List<Convenio> convenios = new ArrayList();
        List<Empresa> empresas = new ArrayList();
        Empresa empresa = null;
        List<Pessoa> pessoas = new ArrayList();

        System.out.println("----->>>>>" + numero);
        System.out.println("--->>>>>>" + nome);
        /**
         * Buscar pelo numero do Convenio
         */
        if (!numero.equals("")) {
            System.out.println("VAI BUSCAR PELO NUMERO DO CONVENIO");
            convenio = ConvenioServices.buscarConvenioByNumeroConvenio(numero.trim());
        }
        if (convenio != null) {
            convenios.add(convenio);
            System.out.println("ENVIOU ISSOO---->>>>>" + convenios);
            System.out.println("ACHOU O CONVENIO POR NUMERO CONVENIO");

        }

        /**
         * Buscar pelo nome da Empresa/Pessoa
         */
        if (!nome.equals("")) {

            System.out.println("ENTROU AQUIIIIIIIIIIIIIIIIii");
            pessoas = PessoaServices.buscarPessoaByNomeList(nome.trim());

            empresas = EmpresaServices.buscarEmpresaByNomeList(nome.trim());
            System.out.println(empresas);

            if (pessoas != null) {

                System.out.println("ACHOU PEssoa PELO NOME");
                for (Pessoa x : pessoas) {
                    convenio = ConvenioServices.buscarConvenioByPessoa(x);
                    System.out.println("pessoa--->>" + x);
                    System.out.println("---->>>>> CONVENIO PESSOA " + convenio);

                    convenios.add(convenio);

                }

            }

            if (empresas != null) {

                System.out.println("ACHOU EMPRESAAA PELO NOME");
                for (Empresa x : empresas) {
                    convenio = ConvenioServices.buscarConvenioByEmpresa(x);
                    System.out.println("empresa--->>>" + x);
                    System.out.println("---->>>>> CONVENIO EMPRESA " + convenio);
                    convenios.add(convenio);

                }

            }
        }

        if (convenios != null) {

            request.setAttribute("filtro", convenios);
            System.out.println("---------->>>>>>>>>" + convenios);
        }

        System.out.println("AQUI QUE DESPACHA---->>>>>>>>7" + convenios);
        request.getRequestDispatcher("form_renovar_convenio.jsp").forward(request, response);

    }

}
