<!DOCTYPE html>
<html lang="en">
    <head>

        <%@include file="import_head.jspf"%>

        <title>
            <fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_titulo"/>
        </title>

    </head>

    <body style="font-family: 'Roboto Slab', Helvetica">
        <%@include file="import_navbar.jspf"%>

        <div class="container">
            <c:if test="${ not empty msg }">
                <div class="alert alert-warning" role="alert">
                    ${ msg }
                </div>
            </c:if>

            <p class="tituloForm">
            <h5 class="offset-5"><fmt:message key="br.cefetrj.sisgee.form_empresa.msg_titulo" /></h5>		

            <form id="meuForm" action="ValidaCadastroEmpresaServlet" method="POST">
                <fieldset class="form-group">
                    

                    <div class="form-row  " >
                        <div class="form-group col-md-2 mt-2 " >
                            <label for="Tipo"><fmt:message key="br.cefetrj.sisgee.form_empresa.msg_Tipo" /></label>
                        </div>

                        <div class="custom-controls-stacked d-block my-3 ">
                            <label class="custom-control custom-radio"> 
                                <input id="pessoaJuridica" name="tipoPessoa" type="radio"   ${ not empty isEmpresa ? isEmpresa eq 'sim'? 'checked' : '' :''} class="custom-control-input ehAgenteCheck ${ not empty isAgenteIntegracaoMsg ? 'is-invalid' : '' }" ${ not empty tipoPessoaMsg ? '' : param.tipoPessoa == 'sim' ? 'checked' : '' } required value = "sim" > 
                                <span class="custom-control-indicator"></span> 
                                <span class="custom-control-description" ><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_pessoaJuridica"/></span>
                            </label> 
                            <label class="custom-control custom-radio"> 
                                <input id="pessoaFisica" name="tipoPessoa" type="radio" ${ not empty isEmpresa ? isEmpresa eq 'nao'? 'checked' : '' :''} class="custom-control-input ehAgenteCheck ${ not empty isAgenteIntegracaoMsg ? 'is-invalid' : '' }"  ${ not empty tipoPessoaMsg ? '' : param.tipoPessoa == 'nao' ? 'checked' : '' }required value = "nao" > 
                                <span class="custom-control-indicator"></span> 
                                <span class="custom-control-description"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_pessoaFisica"/></span>
                            </label>
                        </div>
                    </div>


                            
                    <div class=" isAI AI" ${ empty param.tipoPessoa ? "style='display:none'" : param.tipoPessoa == "nao" ? "style='display:none'" : "" } >     
                        
                        <div class="form-row "  >
                            <div class="form-group col-md-2 mt-2 mr-4" >
                                <label for="agenteIntegracao"><fmt:message key="br.cefetrj.sisgee.form_empresa.msg_agente_integracao" /></label>
                            </div>
                            
                            <div class="custom-controls-stacked d-block my-3 mr-5 ml-5 ">
                                <label class="custom-control custom-radio"> 
                                    <input id="SimAgente" name="agenteIntegracao" type="radio" class="custom-control-input agenteIntegracaoNotAI "   ${ not empty agenteIntegracaoMsg ? '' : param.agenteIntegracao == 'true' ? 'checked' : '' } value="true"> 
                                    <span class="custom-control-indicator"></span> 
                                    <span class="custom-control-description" ><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_sim"/></span>
                                </label> 
                                <label class="custom-control custom-radio"> 
                                    <input id="NaoAgente" name="agenteIntegracao" type="radio" class="custom-control-input agenteIntegracaoNotAI" ${ not empty agenteIntegracaoMsg ? '' : param.agenteIntegracao == 'false' ? 'checked' : '' } value="false"> 
                                    <span class="custom-control-indicator"></span> 
                                    <span class="custom-control-description"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_nao"/></span>
                                </label>
                            </div>
                            


                            					
                        </div>
                        <div class="form-group col-md-6">
                            <label for="cnpjEmpresa"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_cnpj"/></label>
                            <input type="text" class="form-control ${ not empty cnpjEmpresaMsg ? 'is-invalid': 'is-valid' }" id="cnpjEmpresa" name="cnpjEmpresa" value="${ param.cnpjEmpresa }">
                            <c:if test="${ not empty cnpjEmpresaMsg }">
                                <div class="invalid-feedback">${ cnpjEmpresaMsg }</div>
                            </c:if>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="nomeEmpresa"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_razao_social"/></label>
                            <input type="text" class="form-control ${ not empty nomeEmpresaMsg ? 'is-invalid': 'is-valid' }" id="nomeEmpresa" name="nomeEmpresa" maxlength="100" value="${ param.nomeEmpresa }">
                            <c:if test="${ not empty nomeEmpresaMsg }">
                                <div class="invalid-feedback">${ nomeEmpresaMsg }</div>
                            </c:if>
                        </div>

                        <div class="form-group col-md-6">

                            <label for="dataAssinaturaConvenio"><fmt:message key = "br.cefetrj.sisgee.resources.form.dataAssinatura"/></label>
                            <input type="text" class=" form-control col-sm-4 ${ not empty nomeEmpresaMsg ? 'is-invalid': 'is-valid' }" id="dataAssinaturaConvenioEmpresa"  name="dataAssinaturaConvenioEmpresa" value="" >
                            <c:if test="${ not empty dataInicioMsg }">
                                <div class="invalid-feedback">${ dataInicioMsg }</div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="emailEmpresa"><fmt:message key = "br.cefetrj.sisgee.resources.form.emailEmpresa"/></label>
                            <input type="text" class="form-control ${ not empty nomeEmpresaMsg ? 'is-invalid': 'is-valid' }" id="emailEmpresa" name="emailEmpresa" maxlength="100" value="">
                            <c:if test="${ not empty nomeEmpresaMsg }">
                                <div class="invalid-feedback">${ nomeEmpresaMsg }</div>
                            </c:if>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="telefoneEmpresa"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_telefone"/></label>
                            <input type="text" class="form-control ${ not empty nomeEmpresaMsg ? 'is-invalid': 'is-valid' }" id="telefoneEmpresa" name="telefoneEmpresa" maxlength="100"  value="">
                            <c:if test="${ not empty nomeEmpresaMsg }">
                                <div class="invalid-feedback">${ nomeEmpresaMsg }</div>
                            </c:if>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="contatoEmpresa"><fmt:message key = "br.cefetrj.sisgee.resources.form.contatoEmpresa"/></label>
                            <input type="text" class="form-control ${ not empty nomeEmpresaMsg ? 'is-invalid': 'is-valid' }" id="contatoEmpresa" name="contatoEmpresa" maxlength="100"  value="">
                            <c:if test="${ not empty nomeEmpresaMsg }">
                                <div class="invalid-feedback">${ nomeEmpresaMsg }</div>
                            </c:if>
                        </div>

                    </div>         

                    <div class=" notAI AI" ${ empty param.tipoPessoa ? "style='display:none'" : param.tipoPessoa == "nao" ? "style='display:none'" : "" } >

                        <div class="form-group col-md-6">
                            <label for="cpfPessoa"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_cpf"/></label>
                            <input type="text" class="form-control ${ not empty cnpjEmpresaMsg ? 'is-invalid': 'is-valid' }" id="cpfPessoa" name="cpfPessoa"   value="${ param.cpfPessoa }">
                            <c:if test="${ not empty cnpjEmpresaMsg }">
                                <div class="invalid-feedback">${ cnpjEmpresaMsg }</div>
                            </c:if>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="nomePessoa"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_nome"/></label>
                            <input type="text" class="form-control ${ not empty nomeEmpresaMsg ? 'is-invalid': 'is-valid' }" id="nomePessoa" name="nomePessoa" maxlength="100"  value="${ param.nomePessoa }">
                            <c:if test="${ not empty nomeEmpresaMsg }">
                                <div class="invalid-feedback">${ nomeEmpresaMsg }</div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-6">

                            <label for="dataAssinaturaConvenio"><fmt:message key = "br.cefetrj.sisgee.resources.form.dataAssinatura"/></label>
                            <input type="text" class=" form-control col-sm-4 ${ not empty nomeEmpresaMsg ? 'is-invalid': 'is-valid' }" id="dataAssinaturaConvenioPessoa"  name="dataAssinaturaConvenioPessoa" value="" >
                            <c:if test="${ not empty dataInicioMsg }">
                                <div class="invalid-feedback">${ dataInicioMsg }</div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="emailPessoa"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_email"/></label>
                            <input type="text" class="form-control ${ not empty nomeEmpresaMsg ? 'is-invalid': 'is-valid' }" id="emailPessoa" name="emailPessoa" maxlength="100"  value="">
                            <c:if test="${ not empty nomeEmpresaMsg }">
                                <div class="invalid-feedback">${ nomeEmpresaMsg }</div>
                            </c:if>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="telefonePessoa"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_telefone"/></label>
                            <input type="text" class="form-control ${ not empty nomeEmpresaMsg ? 'is-invalid': 'is-valid' }" id="telefonePessoa" name="telefonePessoa" maxlength="100"  value="">
                            <c:if test="${ not empty nomeEmpresaMsg }">
                                <div class="invalid-feedback">${ nomeEmpresaMsg }</div>
                            </c:if>
                        </div>

                    </div>

                </fieldset>

                <button type="submit" class="btn btn-primary" onclick="JavaScript:validaCampo()"><i class="far fa-save"></i> <fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_salvar"/></button>
                <button type="button" class="btn btn-secondary" onclick="javascript:location.href = 'index.jsp'"><i class="far fa-times-circle"></i> <fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_cancelar"/></button>

            </form>
        </div>
        <%@include file="import_footer.jspf"%>
        <%@include file="import_finalbodyscripts.jspf"%>
        <%@include file="import_scripts.jspf"%>

        <script language="javascript">
            function validaCampo() {
                
                
                    alert(document.getElementsByName("ehAgenteIntegracao").value);
                }
            }
        </script>
        <script>
            $(document).ready(function () {
                $('#cnpjEmpresa').mask('99.999.999/9999-99');
                $('#cpfPessoa').mask('999.999.999-99');
                $('#dataAssinaturaConvenio').mask('99/99/9999');
            });
        </script>



    </body>