
<%@ page import="leider.ken.nfl.fantasy.analysis.PointsAgainst" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pointsAgainst.label', default: 'PointsAgainst')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-pointsAgainst" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-pointsAgainst" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list pointsAgainst">
			
				<g:if test="${pointsAgainstInstance?.franchise}">
				<li class="fieldcontain">
					<span id="franchise-label" class="property-label"><g:message code="pointsAgainst.franchise.label" default="Franchise" /></span>
					
						<span class="property-value" aria-labelledby="franchise-label"><g:link controller="franchise" action="show" id="${pointsAgainstInstance?.franchise?.id}">${pointsAgainstInstance?.franchise?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${pointsAgainstInstance?.factor}">
				<li class="fieldcontain">
					<span id="factor-label" class="property-label"><g:message code="pointsAgainst.factor.label" default="Factor" /></span>
					
						<span class="property-value" aria-labelledby="factor-label"><g:fieldValue bean="${pointsAgainstInstance}" field="factor"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pointsAgainstInstance?.league}">
				<li class="fieldcontain">
					<span id="league-label" class="property-label"><g:message code="pointsAgainst.league.label" default="League" /></span>
					
						<span class="property-value" aria-labelledby="league-label"><g:link controller="league" action="show" id="${pointsAgainstInstance?.league?.id}">${pointsAgainstInstance?.league?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${pointsAgainstInstance?.points}">
				<li class="fieldcontain">
					<span id="points-label" class="property-label"><g:message code="pointsAgainst.points.label" default="Points" /></span>
					
						<span class="property-value" aria-labelledby="points-label"><g:link controller="mean" action="show" id="${pointsAgainstInstance?.points?.id}">${pointsAgainstInstance?.points?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${pointsAgainstInstance?.position}">
				<li class="fieldcontain">
					<span id="position-label" class="property-label"><g:message code="pointsAgainst.position.label" default="Position" /></span>
					
						<span class="property-value" aria-labelledby="position-label"><g:fieldValue bean="${pointsAgainstInstance}" field="position"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pointsAgainstInstance?.season}">
				<li class="fieldcontain">
					<span id="season-label" class="property-label"><g:message code="pointsAgainst.season.label" default="Season" /></span>
					
						<span class="property-value" aria-labelledby="season-label"><g:link controller="season" action="show" id="${pointsAgainstInstance?.season?.id}">${pointsAgainstInstance?.season?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${pointsAgainstInstance?.id}" />
					<g:link class="edit" action="edit" id="${pointsAgainstInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
