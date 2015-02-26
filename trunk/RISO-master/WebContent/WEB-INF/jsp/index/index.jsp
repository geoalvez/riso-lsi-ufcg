<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>RISO</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<script type="text/javascript"
	src="<c:url value="/js/jquery-1.7.2.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/bootstrap.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/riso.css"/>" />

</head>

<body>

	<div class="container">
		<header class="jumbotron subhead" id="overview">
			<h1>RISO</h1>
			<p class="lead">Recuperação de Informação Semântica de Objetos.
			</p>

			<div class="row-fluid">
				<div class="span12">
					<div class="risoSearch">
						<form class="well form-search"
							action="<c:url value="/consulta/padroniza"/>">
							<input type="text" class="input-xxlarge search-query"
								name="consulta.texto" />
							<button type="submit" class="btn">
								<i class="icon-search"></i>
							</button>
						</form>
					</div>
				</div>

			</div>
		</header>
	</div>
</body>
</html>