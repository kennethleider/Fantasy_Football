
<%@ page import="leider.ken.nfl.armchairanalysis.Game" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'game.label', default: 'Game')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-game" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-game" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="day" title="${message(code: 'game.day.label', default: 'Day')}" />
					
						<g:sortableColumn property="host" title="${message(code: 'game.host.label', default: 'Host')}" />
					
						<g:sortableColumn property="hostScore" title="${message(code: 'game.hostScore.label', default: 'Host Score')}" />
					
						<g:sortableColumn property="humidity" title="${message(code: 'game.humidity.label', default: 'Humidity')}" />
					
						<g:sortableColumn property="overUnder" title="${message(code: 'game.overUnder.label', default: 'Over Under')}" />
					
						<g:sortableColumn property="stadium" title="${message(code: 'game.stadium.label', default: 'Stadium')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${gameInstanceList}" status="i" var="gameInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${gameInstance.id}">${fieldValue(bean: gameInstance, field: "day")}</g:link></td>
					
						<td>${fieldValue(bean: gameInstance, field: "host")}</td>
					
						<td>${fieldValue(bean: gameInstance, field: "hostScore")}</td>
					
						<td>${fieldValue(bean: gameInstance, field: "humidity")}</td>
					
						<td>${fieldValue(bean: gameInstance, field: "overUnder")}</td>
					
						<td>${fieldValue(bean: gameInstance, field: "stadium")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${gameInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
