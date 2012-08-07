
<%@ page import="leider.ken.nfl.stats.league.SeasonScore" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'seasonScore.label', default: 'SeasonScore')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-seasonScore" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-seasonScore" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list seasonScore">
			
				<g:if test="${seasonScoreInstance?.season}">
				<li class="fieldcontain">
					<span id="season-label" class="property-label"><g:message code="seasonScore.season.label" default="Season" /></span>
					
						<span class="property-value" aria-labelledby="season-label"><g:link controller="season" action="show" id="${seasonScoreInstance?.season?.id}">${seasonScoreInstance?.season?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${seasonScoreInstance?.player}">
				<li class="fieldcontain">
					<span id="player-label" class="property-label"><g:message code="seasonScore.player.label" default="Player" /></span>
					
						<span class="property-value" aria-labelledby="player-label"><g:link controller="player" action="show" id="${seasonScoreInstance?.player?.id}">${seasonScoreInstance?.player?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${seasonScoreInstance?.total}">
				<li class="fieldcontain">
					<span id="total-label" class="property-label"><g:message code="seasonScore.total.label" default="Total" /></span>
					
						<span class="property-value" aria-labelledby="total-label"><g:fieldValue bean="${seasonScoreInstance}" field="total"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${seasonScoreInstance?.average}">
				<li class="fieldcontain">
					<span id="average-label" class="property-label"><g:message code="seasonScore.average.label" default="Average" /></span>
					
						<span class="property-value" aria-labelledby="average-label"><g:fieldValue bean="${seasonScoreInstance}" field="average"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${seasonScoreInstance?.standardDeviation}">
				<li class="fieldcontain">
					<span id="standardDeviation-label" class="property-label"><g:message code="seasonScore.standardDeviation.label" default="Standard Deviation" /></span>
					
						<span class="property-value" aria-labelledby="standardDeviation-label"><g:fieldValue bean="${seasonScoreInstance}" field="standardDeviation"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${seasonScoreInstance?.max}">
				<li class="fieldcontain">
					<span id="max-label" class="property-label"><g:message code="seasonScore.max.label" default="Max" /></span>
					
						<span class="property-value" aria-labelledby="max-label"><g:fieldValue bean="${seasonScoreInstance}" field="max"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${seasonScoreInstance?.league}">
				<li class="fieldcontain">
					<span id="league-label" class="property-label"><g:message code="seasonScore.league.label" default="League" /></span>
					
						<span class="property-value" aria-labelledby="league-label"><g:link controller="league" action="show" id="${seasonScoreInstance?.league?.id}">${seasonScoreInstance?.league?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${seasonScoreInstance?.min}">
				<li class="fieldcontain">
					<span id="min-label" class="property-label"><g:message code="seasonScore.min.label" default="Min" /></span>
					
						<span class="property-value" aria-labelledby="min-label"><g:fieldValue bean="${seasonScoreInstance}" field="min"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${seasonScoreInstance?.id}" />
					<g:link class="edit" action="edit" id="${seasonScoreInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
