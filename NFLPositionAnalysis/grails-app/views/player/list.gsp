
<%@ page import="leider.ken.nfl.Player" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'player.label', default: 'Player')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-player" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-player" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'player.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="position" title="${message(code: 'player.position.label', default: 'Position')}" />
					
						<g:sortableColumn property="team" title="${message(code: 'player.team.label', default: 'Team')}" />
					
						<g:sortableColumn property="firstYear" title="${message(code: 'player.firstYear.label', default: 'First Year')}" />
					
						<g:sortableColumn property="draftPick" title="${message(code: 'player.draftPick.label', default: 'Draft Pick')}" />
					
						<g:sortableColumn property="alternatePosition" title="${message(code: 'player.alternatePosition.label', default: 'Alternate Position')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${playerInstanceList}" status="i" var="playerInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${playerInstance.id}">${fieldValue(bean: playerInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: playerInstance, field: "position")}</td>
					
						<td>${fieldValue(bean: playerInstance, field: "team")}</td>
					
						<td>${fieldValue(bean: playerInstance, field: "firstYear")}</td>
					
						<td>${fieldValue(bean: playerInstance, field: "draftPick")}</td>
					
						<td>${fieldValue(bean: playerInstance, field: "alternatePosition")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${playerInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
