package br.cefetrj.sisgee.view.AJAX;

import java.io.IOException;
import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.sisgee.control.EmpresaServices;
import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.Empresa;
import br.cefetrj.sisgee.model.entity.Pessoa;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Usuario
 */
@WebServlet("/BuscaConvenioBotaoServlet")
public class BuscaConvenioBotaoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String numeroConvenio = request.getParameter("numeroConvenio");
        String nome = request.getParameter("nomeConvenio");        
        String idConvenio = "";
        Empresa empresa = null;
        
        Empresa empresaNome = null;
        Pessoa pessoaNome = null;
        
        //boolean agenteIntegracao = false;
        String agenteIntegracao = "checked";
        String CPF = "", CNPJ = "";
        
        Convenio convenio = null;
        List<Convenio> convenios = null;
        List<Empresa> empresas = null;
        List<Pessoa> pessoas = null;
        
        
        /**
         * Buscar pelo numero do Convenio
         */
        if (numeroConvenio != null) {
            System.out.println("CHEGOU NO BUSCACONVENIOSERVLET AJAX por numeroConvenio");
            convenio = ConvenioServices.buscarConvenioByNumeroConvenio(numeroConvenio.trim());
            if (convenio != null) {
                empresaNome = convenio.getEmpresa();
                if(empresaNome != null){
                    CNPJ = formatString(empresaNome.getCnpjEmpresa(),"##.###.###/####-##");
                    if (empresaNome.isAgenteIntegracao()) {
                        agenteIntegracao = "checked";
                    }
                }

                pessoaNome = convenio.getPessoa();
                if (pessoaNome != null) {
                    pessoaNome.getNome();
                    CPF = formatString(pessoaNome.getCpf(),"###.###.###-##");
                } 
            }        
        }
        /**
         * Buscar pelo nome da Empresa/Pessoa
         */
        if (nome != null) {
            empresa = EmpresaServices.buscarEmpresaByNome(nome.trim());
        }
        if (empresa != null) {
            convenio = ConvenioServices.buscarConvenioByEmpresa(empresa);
            if (convenio != null) {
                empresaNome = convenio.getEmpresa();
                if(empresaNome != null){
                    CNPJ = formatString(empresaNome.getCnpjEmpresa(),"##.###.###/####-##");
                    if (empresaNome.isAgenteIntegracao()) {
                        agenteIntegracao = "checked";
                    }
                }

                pessoaNome = convenio.getPessoa();
                if (pessoaNome != null) {
                    pessoaNome.getNome();
                    CPF = formatString(pessoaNome.getCpf(),"###.###.###-##");
                } 
            }
        }

        System.out.println("agente de integracao ->" + agenteIntegracao);
       //JSON
        if(empresaNome != null){
            JsonObject model = Json.createObjectBuilder()
                    .add("razaoSocial", empresaNome.getRazaoSocial())
                    .add("tipoConvenio", "pj")
                    .add("agenteSim", agenteIntegracao)
                    .add("cnpjEcpf", CNPJ)
                    .add("nomeEmpresaPessoa", empresaNome.getRazaoSocial())
                    .add("nomeConvenio", empresaNome.getRazaoSocial())
                    .add("numeroConvenio", convenio.getNumeroConvenio())
                    .build();

            StringWriter stWriter = new StringWriter();
            JsonWriter jsonWriter = Json.createWriter(stWriter);
            jsonWriter.writeObject(model);
            jsonWriter.close();
            String jsonData = stWriter.toString();
        
            response.setContentType("application/json");
            response.getWriter().print(jsonData);
        }else if(pessoaNome != null){
            JsonObject model = Json.createObjectBuilder()
                    .add("razaoSocial", pessoaNome.getNome())
                    .add("tipoConvenio", "pf")
                    .add("agenteSim", agenteIntegracao)
                    .add("cnpjEcpf", CPF)
                    .add("nomeEmpresaPessoa", pessoaNome.getNome())
                    .add("nomeConvenio", pessoaNome.getNome())
                    .add("numeroConvenio", convenio.getNumeroConvenio())
                    .build();

            StringWriter stWriter = new StringWriter();
            JsonWriter jsonWriter = Json.createWriter(stWriter);
            jsonWriter.writeObject(model);
            jsonWriter.close();
            String jsonData = stWriter.toString();
        
            response.setContentType("application/json");
            response.getWriter().print(jsonData);            
        }else{
            JsonObject model = Json.createObjectBuilder()
                    .add("razaoSocial", "")
                    .add("nomeAgenciada", "")
                    .build();  
            
            StringWriter stWriter = new StringWriter();
            JsonWriter jsonWriter = Json.createWriter(stWriter);
            jsonWriter.writeObject(model);
            jsonWriter.close();
            String jsonData = stWriter.toString();
        
            response.setContentType("application/json");
            response.getWriter().print(jsonData);              
        }        
    }

   public static String formatString(String value, String pattern) {
        MaskFormatter mf;
        try {
            mf = new MaskFormatter(pattern);
            mf.setValueContainsLiteralCharacters(false);
            return mf.valueToString(value);
        } catch (ParseException ex) {
            return null;
        }
    }
    
}
