
<%@ page import="leider.ken.nfl.fantasy.analysis.PlayerSeasonAnalysis" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'playerSeasonAnalysis.label', default: 'PlayerSeasonAnalysis')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-playerSeasonAnalysis" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-playerSeasonAnalysis" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list playerSeasonAnalysis">
			
				<g:if test="${playerSeasonAnalysisInstance?.player}">
				<li class="fieldcontain">
					<span id="player-label" class="property-label"><g:message code="playerSeasonAnalysis.player.label" default="Player" /></span>
					
						<span class="property-value" aria-labelledby="player-label"><g:link controller="player" action="show" id="${playerSeasonAnalysisInstance?.player?.id}">${playerSeasonAnalysisInstance?.player?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerSeasonAnalysisInstance?.season}">
				<li class="fieldcontain">
					<span id="season-label" class="property-label"><g:message code="playerSeasonAnalysis.season.label" default="Season" /></span>
					
						<span class="property-value" aria-labelledby="season-label"><g:link controller="season" action="show" id="${playerSeasonAnalysisInstance?.season?.id}">${playerSeasonAnalysisInstance?.season?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerSeasonAnalysisInstance?.league}">
				<li class="fieldcontain">
					<span id="league-label" class="property-label"><g:message code="playerSeasonAnalysis.league.label" default="League" /></span>
					
						<span class="property-value" aria-labelledby="league-label"><g:link controller="league" action="show" id="${playerSeasonAnalysisInstance?.league?.id}">${playerSeasonAnalysisInstance?.league?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${playerSeasonAnalysisInstance?.id}" />
					<g:link class="edit" action="edit" id="${playerSeasonAnalysisInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
