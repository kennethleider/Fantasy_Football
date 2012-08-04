
<%@ page import="leider.ken.nfl.stats.PlayerWeekStats" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'playerWeekStats.label', default: 'PlayerWeekStats')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-playerWeekStats" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-playerWeekStats" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list playerWeekStats">
			
				<g:if test="${playerWeekStatsInstance?.week}">
				<li class="fieldcontain">
					<span id="week-label" class="property-label"><g:message code="playerWeekStats.week.label" default="Week" /></span>
					
						<span class="property-value" aria-labelledby="week-label"><g:link controller="week" action="show" id="${playerWeekStatsInstance?.week?.id}">${playerWeekStatsInstance?.week?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerWeekStatsInstance?.passing}">
				<li class="fieldcontain">
					<span id="passing-label" class="property-label"><g:message code="playerWeekStats.passing.label" default="Passing" /></span>
					
						<span class="property-value" aria-labelledby="passing-label"><g:fieldValue bean="${playerWeekStatsInstance}" field="passing"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerWeekStatsInstance?.player}">
				<li class="fieldcontain">
					<span id="player-label" class="property-label"><g:message code="playerWeekStats.player.label" default="Player" /></span>
					
						<span class="property-value" aria-labelledby="player-label"><g:link controller="player" action="show" id="${playerWeekStatsInstance?.player?.id}">${playerWeekStatsInstance?.player?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerWeekStatsInstance?.rushing}">
				<li class="fieldcontain">
					<span id="rushing-label" class="property-label"><g:message code="playerWeekStats.rushing.label" default="Rushing" /></span>
					
						<span class="property-value" aria-labelledby="rushing-label"><g:fieldValue bean="${playerWeekStatsInstance}" field="rushing"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${playerWeekStatsInstance?.id}" />
					<g:link class="edit" action="edit" id="${playerWeekStatsInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
