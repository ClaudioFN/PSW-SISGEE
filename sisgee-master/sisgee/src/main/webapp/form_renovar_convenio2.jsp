<!DOCTYPE html>
<html lang="en">
<head>

<%@include file="import_head.jspf"%>

<title>
	<fmt:message key = "br.cefetrj.sisgee.resources.form_termo_rescisao.registro_termo"/>
</title>

</head>
<body>
	<%@include file="import_navbar.jspf"%>
	
	<div class="container">
		<c:if test="${ not empty msg }">
			<div class="alert alert-warning" role="alert">
				${ msg }
			</div>
		</c:if>

		<p class="tituloForm">
		<h5>
			Renovar Convênio ${param.numeroConvenio}
		</h5>		
		
		<form action="FormTermoRescisaoServlet" method="post">
			
			
			
			<fieldset class="form-group">
				
				<div class="form-row">
					<div class="form-group col-md-4">


						<label for="dataRescisao"><fmt:message key = "br.cefetrj.sisgee.resources.form_termo_rescisao.data_rescisao"/></label>
						<input type="text" class="form-control ${ not empty dataTermoRescisaoMsg ? 'is-invalid': not empty periodoMsg ? 'is-invalid' : 'is-valid' }" id="dataRescisao"  name="dataTermoRescisao" value="${ param.dataRescisao }" >
					<c:if test="${ not empty dataTermoRescisaoMsg }">
				    	<div class="invalid-feedback">${ dataTermoRescisaoMsg }</div>
		        	</c:if>
					</div>					
				</div>
			</fieldset>
		
			<button type="submit" class="btn btn-primary">Salvar</button>
			<button type="button" class="btn btn-secondary">Cancelar</button>			
		</form>
		
		
	</div>
	<%@include file="import_footer.jspf"%>
	<%@include file="import_finalbodyscripts.jspf"%>
	<%@include file="import_scripts.jspf"%>
    
</body>
</html>
