
<%@ page import="leider.ken.nfl.Matchup" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'matchup.label', default: 'Matchup')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-matchup" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-matchup" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list matchup">
			
				<g:if test="${matchupInstance?.week}">
				<li class="fieldcontain">
					<span id="week-label" class="property-label"><g:message code="matchup.week.label" default="Week" /></span>
					
						<span class="property-value" aria-labelledby="week-label"><g:link controller="week" action="show" id="${matchupInstance?.week?.id}">${matchupInstance?.week?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${matchupInstance?.away}">
				<li class="fieldcontain">
					<span id="away-label" class="property-label"><g:message code="matchup.away.label" default="Away" /></span>
					
						<span class="property-value" aria-labelledby="away-label"><g:link controller="franchise" action="show" id="${matchupInstance?.away?.id}">${matchupInstance?.away?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${matchupInstance?.awayScore}">
				<li class="fieldcontain">
					<span id="awayScore-label" class="property-label"><g:message code="matchup.awayScore.label" default="Away Score" /></span>
					
						<span class="property-value" aria-labelledby="awayScore-label"><g:fieldValue bean="${matchupInstance}" field="awayScore"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${matchupInstance?.home}">
				<li class="fieldcontain">
					<span id="home-label" class="property-label"><g:message code="matchup.home.label" default="Home" /></span>
					
						<span class="property-value" aria-labelledby="home-label"><g:link controller="franchise" action="show" id="${matchupInstance?.home?.id}">${matchupInstance?.home?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${matchupInstance?.homeScore}">
				<li class="fieldcontain">
					<span id="homeScore-label" class="property-label"><g:message code="matchup.homeScore.label" default="Home Score" /></span>
					
						<span class="property-value" aria-labelledby="homeScore-label"><g:fieldValue bean="${matchupInstance}" field="homeScore"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${matchupInstance?.id}" />
					<g:link class="edit" action="edit" id="${matchupInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
