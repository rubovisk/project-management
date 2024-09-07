<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="br.com.codegroup.utils.DateUtils"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Project Management</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.9.1/font/bootstrap-icons.min.css">
<style>
body {
	padding-top: 20px;
}

.container {
	max-width: 600px;
}
</style>
</head>
<body>
	<div class="container">
		<h1 class="mb-4">Novo projeto</h1>
		<form action="<c:url value='/api/projects/add' />" method="post">
			<div class="mb-3">
				<label for="nome" class="form-label">Nome projeto:</label> <input
					type="text" id="nome" name="nome" class="form-control" required />
			</div>
			<div class="mb-3">
				<label for="dtInicio" class="form-label">Data inicio:</label> <input
					type="date" id="dtInicio" name="dtInicio" class="form-control"
					required />
			</div>
			<div class="mb-3">
				<label for="gerenteResponsavel" class="form-label">Gerente
					responsável:</label> <input type="text" id="gerenteResponsavel"
					name="gerenteResponsavel" class="form-control" required />
			</div>
			<div class="mb-3">
				<label for="dtPrvTermino" class="form-label">Data término
					estimada:</label> <input type="date" id="dtPrvTermino" name="dtPrvTermino"
					class="form-control" />
			</div>
			<div class="mb-3">
				<label for="dtRealTermino" class="form-label">Data término
					real:</label> <input type="date" id="dtRealTermino" name="dtRealTermino"
					class="form-control" />
			</div>
			<div class="mb-3">
				<label for="orcamentoTotal" class="form-label">Orçamento
					total:</label> <input type="text" id="orcamentoTotal" name="orcamentoTotal"
					class="form-control" required />
			</div>
			<div class="mb-3">
				<label for="descricao" class="form-label">Descrição:</label> <input
					type="text" id="descricao" name="descricao" class="form-control"
					required />
			</div>
			<div class="mb-3">
				<label for="status" class="form-label">Status:</label> <select
					id="status" name="status" class="form-select">
					<c:forEach var="status" items="${projectStatuses}">
						<option value="${status.code}">${status.description}</option>
					</c:forEach>
				</select>
			</div>
			<div class="mb-3">
				<label for="risk" class="form-label">Risco:</label> <select
					id="risk" name="risk" class="form-select">
					<c:forEach var="risk" items="${projectRisks}">
						<option value="${risk.code}">${risk.description}</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<button type="submit" class="btn btn-primary">Salvar</button>
			</div>
		</form>

		<h2 class="my-4">Projetos</h2>
		<table class="table table-striped table-bordered table-hover">
			<thead class="text-start">
				<tr>
					<th class="align-top">Nome</th>
					<th class="align-top">Data Inicio</th>
					<th class="align-top">Gerente</th>
					<th class="align-top">Data Fim Estimada</th>
					<th class="align-top">Data Fim Real</th>
					<th class="align-top">Orçamento total</th>
					<th class="align-top">Descricao</th>
					<th class="align-top">Status</th>
					<th class="align-top">Risco</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="project" items="${projects}">
					<tr>
						<td>${project.nome}</td>
						<td>${DateUtils.formatDate(project.dtInicio)}</td>
						<td>${project.gerenteResponsavel}</td>
						<td>${DateUtils.formatDate(project.dtPrvTermino)}</td>
						<td>${DateUtils.formatDate(project.dtRealTermino)}</td>
						<td>${project.orcamentoTotal}</td>
						<td>${project.descricao}</td>
						<td>${project.status}</td>
						<td>${project.risk}</td>
						<td>
							<button type="button" class="btn btn-warning btn-sm"
								data-bs-toggle="modal" data-bs-target="#editModal-${project.id}"
								data-id="${project.id}" data-nome="${project.nome}"
								data-dtInicio="${project.dtInicio}"
								data-gerenteResponsavel="${project.gerenteResponsavel}"
								data-dtPrvTermino="${project.dtPrvTermino}"
								data-dtRealTermino="${project.dtRealTermino}"
								data-status="${project.status}"
								data-risk="${project.risk}">
								<i class="bi bi-pencil"></i>
							</button>
						</td>
						<td>
							<form action="<c:url value='/api/projects/delete' />"
								method="post" style="display: inline;">
								<input type="hidden" name="id" value="${project.id}" />
								<button type="submit" class="btn btn-danger btn-sm"
									onclick="return confirm('Are you sure you want to delete this project?');">
									<i class="bi bi-trash"></i>
								</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<c:forEach var="project" items="${projects}">
		<div class="modal fade" id="editModal-${project.id}" tabindex="-1"
			aria-labelledby="editModalLabel-${project.id}" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="editModalLabel-${project.id}">Edit
							Project</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<form action="<c:url value='/api/projects/update' />" method="post">
						<div class="modal-body">
							<input type="hidden" name="id" value="${project.id}" />
							<div class="mb-3">
								<label for="nome-${project.id}" class="form-label">Nome:</label>
								<input type="text" class="form-control" id="nome-${project.id}"
									name="nome" value="${project.nome}" required />
							</div>
							<div class="mb-3">
								<label for="dtInicio-${project.id}" class="form-label">Data
									Inicio:</label> <input type="date" class="form-control"
									id="dtInicio-${project.id}" name="dtInicio"
									value="${project.dtInicio}" required />
							</div>
							<div class="mb-3">
								<label for="gerenteResponsavel-${project.id}" class="form-label">Gerente:</label>
								<input type="text" class="form-control"
									id="gerenteResponsavel-${project.id}" name="gerenteResponsavel"
									value="${project.gerenteResponsavel}" required />
							</div>
							<div class="mb-3">
								<label for="dtPrvTermino-${project.id}" class="form-label">Data
									fim estimada:</label> <input type="date" class="form-control"
									id="dtPrvTermino-${project.id}" name="dtPrvTermino"
									value="${project.dtPrvTermino}" />
							</div>
							<div class="mb-3">
								<label for="dtRealTermino-${project.id}" class="form-label">Data
									fim real:</label> <input type="date" class="form-control"
									id="dtRealTermino-${project.id}" name="dtRealTermino"
									value="${project.dtRealTermino}" />
							</div>
							<div class="mb-3">
								<label for="orcamentoTotal-${project.id}" class="form-label">Orçamento
									Total:</label> <input type="text" class="form-control"
									id="orcamentoTotal-${project.id}" name="orcamentoTotal"
									value="${project.orcamentoTotal}" required />
							</div>
							<div class="mb-3">
								<label for="descricao-${project.id}" class="form-label">Descricao:</label>
								<input type="text" class="form-control"
									id="descricao-${project.id}" name="descricao"
									value="${project.descricao}" required />
							</div>
							
							<div class="mb-3">
    							<label for="status-${project.id}" class="form-label">Status:</label>
    							<select id="status-${project.id}" name="status" class="form-select">
        						<c:forEach var="status" items="${projectStatuses}">
            						<option value="${status.code}" ${status.description == project.status ? 'selected' : ''}>
                						${status.description}
            						</option>
        						</c:forEach>
    							</select>
							</div>
							
							<div class="mb-3">
    							<label for="risk-${project.id}" class="form-label">Risco:</label>
    							<select id="risk-${project.id}" name="risk" class="form-select">
        						<c:forEach var="risk" items="${projectRisks}">
            						<option value="${risk.code}" ${risk.description == project.status ? 'selected' : ''}>
                						${risk.description}
            						</option>
        						</c:forEach>
    							</select>
							</div>
							

						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Save
								changes</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</c:forEach>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>
