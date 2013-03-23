
<%@ page import="leider.ken.nfl.Matchup" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'matchup.label', default: 'Matchup')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-matchup" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-matchup" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="matchup.week.label" default="Week" /></th>
					
						<th><g:message code="matchup.away.label" default="Away" /></th>
					
						<g:sortableColumn property="awayScore" title="${message(code: 'matchup.awayScore.label', default: 'Away Score')}" />
					
						<th><g:message code="matchup.home.label" default="Home" /></th>
					
						<g:sortableColumn property="homeScore" title="${message(code: 'matchup.homeScore.label', default: 'Home Score')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${matchupInstanceList}" status="i" var="matchupInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${matchupInstance.id}">${fieldValue(bean: matchupInstance, field: "week")}</g:link></td>
					
						<td>${fieldValue(bean: matchupInstance, field: "away")}</td>
					
						<td>${fieldValue(bean: matchupInstance, field: "awayScore")}</td>
					
						<td>${fieldValue(bean: matchupInstance, field: "home")}</td>
					
						<td>${fieldValue(bean: matchupInstance, field: "homeScore")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${matchupInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
