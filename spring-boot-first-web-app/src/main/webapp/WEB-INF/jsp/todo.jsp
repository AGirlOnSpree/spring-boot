<%@include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>
<div class="container">
	<form:form method="post" modelAttribute="todotemp">
		<fieldset class="form-group">
			<%-- <form:input type="hidden" path="id"/> --%>
			<form:label path="desc">Desc :</form:label>
			<form:input path="desc" type="text" name="desc" class="form-control"
				required="required" />
			<form:errors path="desc" class="text-warning" />
		</fieldset>
		<fieldset class="form-group">
			<%-- <form:input type="hidden" path="id"/> --%>
			<form:label path="targetDate">Target Date :</form:label>
			<form:input path="targetDate" type="text" name="desc"
				class="form-control" required="required" />
			<form:errors path="targetDate" class="text-warning" />
		</fieldset>
		<button type="submit" class="btn btn-success">Add</button>
	</form:form>
</div>
<%@include file="common/footer.jspf"%>