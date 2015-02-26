<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta charset="utf-8"/>
<title>RISO</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<meta name="description" content=""/>
<meta name="author" content=""/>


<script type="text/javascript" src="<c:url value="/js/jquery-1.7.2.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/bootstrap.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>"/>

</head>

<body>

	<div class="row">
		<div class="span6">
			<form class="well form-search" action="<c:url value="/consulta/padroniza"/>">
				<input type="text" class="input-medium search-query" name="consulta.texto"/>
				<button type="submit" class="btn"><i class="icon-search icon-thumbs-up"></i></button>
			</form>
			
		</div>
		<div class="row">
				<div class="span3">Resultados
				<p>
        			<span class="label label-info">Heads up!</span>
				<p>
				<p>
        			<span class="label label-info">Heads up!</span>
				<p>
				
				</div>
				<div class="span3">Grafo
				
				</div>
			</div>
	</div>


</body>
</html>