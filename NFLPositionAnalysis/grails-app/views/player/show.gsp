
<%@ page import="leider.ken.nfl.Player" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'player.label', default: 'Player')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-player" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-player" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list player">
			
				<g:if test="${playerInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="player.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${playerInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.position}">
				<li class="fieldcontain">
					<span id="position-label" class="property-label"><g:message code="player.position.label" default="Position" /></span>
					
						<span class="property-value" aria-labelledby="position-label"><g:fieldValue bean="${playerInstance}" field="position"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.team}">
				<li class="fieldcontain">
					<span id="team-label" class="property-label"><g:message code="player.team.label" default="Team" /></span>
					
						<span class="property-value" aria-labelledby="team-label"><g:fieldValue bean="${playerInstance}" field="team"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.firstYear}">
				<li class="fieldcontain">
					<span id="firstYear-label" class="property-label"><g:message code="player.firstYear.label" default="First Year" /></span>
					
						<span class="property-value" aria-labelledby="firstYear-label"><g:fieldValue bean="${playerInstance}" field="firstYear"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.draftPick}">
				<li class="fieldcontain">
					<span id="draftPick-label" class="property-label"><g:message code="player.draftPick.label" default="Draft Pick" /></span>
					
						<span class="property-value" aria-labelledby="draftPick-label"><g:fieldValue bean="${playerInstance}" field="draftPick"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.alternatePosition}">
				<li class="fieldcontain">
					<span id="alternatePosition-label" class="property-label"><g:message code="player.alternatePosition.label" default="Alternate Position" /></span>
					
						<span class="property-value" aria-labelledby="alternatePosition-label"><g:fieldValue bean="${playerInstance}" field="alternatePosition"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${playerInstance?.id}" />
					<g:link class="edit" action="edit" id="${playerInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
