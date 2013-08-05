
<%@ page import="leider.ken.nfl.fantasy.Score" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'score.label', default: 'Score')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-score" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-score" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list score">
			
				<g:if test="${scoreInstance?.league}">
				<li class="fieldcontain">
					<span id="league-label" class="property-label"><g:message code="score.league.label" default="League" /></span>
					
						<span class="property-value" aria-labelledby="league-label"><g:link controller="league" action="show" id="${scoreInstance?.league?.id}">${scoreInstance?.league?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${scoreInstance?.player}">
				<li class="fieldcontain">
					<span id="player-label" class="property-label"><g:message code="score.player.label" default="Player" /></span>
					
						<span class="property-value" aria-labelledby="player-label"><g:link controller="player" action="show" id="${scoreInstance?.player?.id}">${scoreInstance?.player?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${scoreInstance?.points}">
				<li class="fieldcontain">
					<span id="points-label" class="property-label"><g:message code="score.points.label" default="Points" /></span>
					
						<span class="property-value" aria-labelledby="points-label"><g:fieldValue bean="${scoreInstance}" field="points"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scoreInstance?.week}">
				<li class="fieldcontain">
					<span id="week-label" class="property-label"><g:message code="score.week.label" default="Week" /></span>
					
						<span class="property-value" aria-labelledby="week-label"><g:link controller="week" action="show" id="${scoreInstance?.week?.id}">${scoreInstance?.week?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${scoreInstance?.id}" />
					<g:link class="edit" action="edit" id="${scoreInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
