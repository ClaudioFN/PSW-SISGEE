<%@page import="br.cefetrj.sisgee.model.entity.Convenio"%>
<%@page import="java.util.List"%>
<%@page import="br.cefetrj.sisgee.control.ConvenioServices"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <%@include file="import_head.jspf"%>

        <title>
            <fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_titulo"/>
        </title>

    </head>

    <body>
        <%@include file="import_navbar.jspf"%>

        <%
           
            ConvenioServices.listarConvenios();
            
       


        %>

        <table id="myTable" class="table table-info table-bordered container table-hover table-striped " >
            <thead>
                <tr>

                    <th scope="col">Vigência</th>
                    <th scope="col">Convênio</th>
                    <th scope="col">Razão Social/Nome</th>
                    <th scope="col">CNPJ/CPF</th>
                    <th scope="col">Email</th>
                    <th scope="col">Telefone</th>
                    <th scope="col">Pessoa de contato</th>

                </tr>
            </thead>


            <tr>

                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>${filtro.telefone}</td>
                <td>${filtro.pessoacontato}</td>


            </tr>
              
        </table>

        <button type="button" onclick="javascript:location.href = 'index.jsp'" class="btn btn-primary offset-lg-5">Voltar</button>

        <%@include file="import_footer.jspf"%>
        <%@include file="import_finalbodyscripts.jspf"%>

    </body>
</html>
