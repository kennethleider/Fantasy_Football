
<%@ page import="leider.ken.nfl.armchairanalysis.Play" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'play.label', default: 'Play')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-play" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-play" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list play">
			
				<g:if test="${playInstance?.defense}">
				<li class="fieldcontain">
					<span id="defense-label" class="property-label"><g:message code="play.defense.label" default="Defense" /></span>
					
						<span class="property-value" aria-labelledby="defense-label"><g:fieldValue bean="${playInstance}" field="defense"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${playInstance?.duration}">
				<li class="fieldcontain">
					<span id="duration-label" class="property-label"><g:message code="play.duration.label" default="Duration" /></span>
					
						<span class="property-value" aria-labelledby="duration-label"><g:fieldValue bean="${playInstance}" field="duration"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${playInstance?.game}">
				<li class="fieldcontain">
					<span id="game-label" class="property-label"><g:message code="play.game.label" default="Game" /></span>
					
						<span class="property-value" aria-labelledby="game-label"><g:link controller="game" action="show" id="${playInstance?.game?.id}">${playInstance?.game?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${playInstance?.offense}">
				<li class="fieldcontain">
					<span id="offense-label" class="property-label"><g:message code="play.offense.label" default="Offense" /></span>
					
						<span class="property-value" aria-labelledby="offense-label"><g:fieldValue bean="${playInstance}" field="offense"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${playInstance?.type}">
				<li class="fieldcontain">
					<span id="type-label" class="property-label"><g:message code="play.type.label" default="Type" /></span>
					
						<span class="property-value" aria-labelledby="type-label"><g:fieldValue bean="${playInstance}" field="type"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${playInstance?.id}" />
					<g:link class="edit" action="edit" id="${playInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
