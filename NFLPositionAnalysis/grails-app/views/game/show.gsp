
<%@ page import="leider.ken.nfl.armchairanalysis.Game" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'game.label', default: 'Game')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-game" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-game" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list game">
			
				<g:if test="${gameInstance?.day}">
				<li class="fieldcontain">
					<span id="day-label" class="property-label"><g:message code="game.day.label" default="Day" /></span>
					
						<span class="property-value" aria-labelledby="day-label"><g:fieldValue bean="${gameInstance}" field="day"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.host}">
				<li class="fieldcontain">
					<span id="host-label" class="property-label"><g:message code="game.host.label" default="Host" /></span>
					
						<span class="property-value" aria-labelledby="host-label"><g:fieldValue bean="${gameInstance}" field="host"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.hostScore}">
				<li class="fieldcontain">
					<span id="hostScore-label" class="property-label"><g:message code="game.hostScore.label" default="Host Score" /></span>
					
						<span class="property-value" aria-labelledby="hostScore-label"><g:fieldValue bean="${gameInstance}" field="hostScore"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.humidity}">
				<li class="fieldcontain">
					<span id="humidity-label" class="property-label"><g:message code="game.humidity.label" default="Humidity" /></span>
					
						<span class="property-value" aria-labelledby="humidity-label"><g:fieldValue bean="${gameInstance}" field="humidity"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.overUnder}">
				<li class="fieldcontain">
					<span id="overUnder-label" class="property-label"><g:message code="game.overUnder.label" default="Over Under" /></span>
					
						<span class="property-value" aria-labelledby="overUnder-label"><g:fieldValue bean="${gameInstance}" field="overUnder"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.stadium}">
				<li class="fieldcontain">
					<span id="stadium-label" class="property-label"><g:message code="game.stadium.label" default="Stadium" /></span>
					
						<span class="property-value" aria-labelledby="stadium-label"><g:fieldValue bean="${gameInstance}" field="stadium"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.surface}">
				<li class="fieldcontain">
					<span id="surface-label" class="property-label"><g:message code="game.surface.label" default="Surface" /></span>
					
						<span class="property-value" aria-labelledby="surface-label"><g:fieldValue bean="${gameInstance}" field="surface"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.temperature}">
				<li class="fieldcontain">
					<span id="temperature-label" class="property-label"><g:message code="game.temperature.label" default="Temperature" /></span>
					
						<span class="property-value" aria-labelledby="temperature-label"><g:fieldValue bean="${gameInstance}" field="temperature"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.visitor}">
				<li class="fieldcontain">
					<span id="visitor-label" class="property-label"><g:message code="game.visitor.label" default="Visitor" /></span>
					
						<span class="property-value" aria-labelledby="visitor-label"><g:fieldValue bean="${gameInstance}" field="visitor"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.visitorPointSpread}">
				<li class="fieldcontain">
					<span id="visitorPointSpread-label" class="property-label"><g:message code="game.visitorPointSpread.label" default="Visitor Point Spread" /></span>
					
						<span class="property-value" aria-labelledby="visitorPointSpread-label"><g:fieldValue bean="${gameInstance}" field="visitorPointSpread"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.visitorScore}">
				<li class="fieldcontain">
					<span id="visitorScore-label" class="property-label"><g:message code="game.visitorScore.label" default="Visitor Score" /></span>
					
						<span class="property-value" aria-labelledby="visitorScore-label"><g:fieldValue bean="${gameInstance}" field="visitorScore"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.weatherConditions}">
				<li class="fieldcontain">
					<span id="weatherConditions-label" class="property-label"><g:message code="game.weatherConditions.label" default="Weather Conditions" /></span>
					
						<span class="property-value" aria-labelledby="weatherConditions-label"><g:fieldValue bean="${gameInstance}" field="weatherConditions"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.week}">
				<li class="fieldcontain">
					<span id="week-label" class="property-label"><g:message code="game.week.label" default="Week" /></span>
					
						<span class="property-value" aria-labelledby="week-label"><g:link controller="week" action="show" id="${gameInstance?.week?.id}">${gameInstance?.week?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.windDirection}">
				<li class="fieldcontain">
					<span id="windDirection-label" class="property-label"><g:message code="game.windDirection.label" default="Wind Direction" /></span>
					
						<span class="property-value" aria-labelledby="windDirection-label"><g:fieldValue bean="${gameInstance}" field="windDirection"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.windSpeed}">
				<li class="fieldcontain">
					<span id="windSpeed-label" class="property-label"><g:message code="game.windSpeed.label" default="Wind Speed" /></span>
					
						<span class="property-value" aria-labelledby="windSpeed-label"><g:fieldValue bean="${gameInstance}" field="windSpeed"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${gameInstance?.id}" />
					<g:link class="edit" action="edit" id="${gameInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
