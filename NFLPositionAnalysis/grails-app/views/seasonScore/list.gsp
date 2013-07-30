
<%@ page import="leider.ken.nfl.fantasy.SeasonScore" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'seasonScore.label', default: 'SeasonScore')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-seasonScore" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-seasonScore" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="seasonScore.season.label" default="Season" /></th>
					
						<th><g:message code="seasonScore.player.label" default="Player" /></th>
					
						<g:sortableColumn property="total" title="${message(code: 'seasonScore.total.label', default: 'Total')}" />
					
						<g:sortableColumn property="average" title="${message(code: 'seasonScore.average.label', default: 'Average')}" />
					
						<g:sortableColumn property="standardDeviation" title="${message(code: 'seasonScore.standardDeviation.label', default: 'Standard Deviation')}" />
					
						<g:sortableColumn property="max" title="${message(code: 'seasonScore.max.label', default: 'Max')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${seasonScoreInstanceList}" status="i" var="seasonScoreInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${seasonScoreInstance.id}">${fieldValue(bean: seasonScoreInstance, field: "season")}</g:link></td>
					
						<td>${fieldValue(bean: seasonScoreInstance, field: "player")}</td>
					
						<td>${fieldValue(bean: seasonScoreInstance, field: "total")}</td>
					
						<td>${fieldValue(bean: seasonScoreInstance, field: "average")}</td>
					
						<td>${fieldValue(bean: seasonScoreInstance, field: "standardDeviation")}</td>
					
						<td>${fieldValue(bean: seasonScoreInstance, field: "max")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${seasonScoreInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
