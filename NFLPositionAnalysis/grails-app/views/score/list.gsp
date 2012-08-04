
<%@ page import="leider.ken.nfl.stats.league.Score" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'score.label', default: 'Score')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-score" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-score" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="score.league.label" default="League" /></th>
					
						<th><g:message code="score.player.label" default="Player" /></th>
					
						<g:sortableColumn property="points" title="${message(code: 'score.points.label', default: 'Points')}" />
					
						<th><g:message code="score.week.label" default="Week" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${scoreInstanceList}" status="i" var="scoreInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${scoreInstance.id}">${fieldValue(bean: scoreInstance, field: "league")}</g:link></td>
					
						<td>${fieldValue(bean: scoreInstance, field: "player")}</td>
					
						<td>${fieldValue(bean: scoreInstance, field: "points")}</td>
					
						<td>${fieldValue(bean: scoreInstance, field: "week")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${scoreInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
