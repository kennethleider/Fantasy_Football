
<%@ page import="leider.ken.nfl.armchairanalysis.Play" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'play.label', default: 'Play')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-play" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-play" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="defense" title="${message(code: 'play.defense.label', default: 'Defense')}" />
					
						<g:sortableColumn property="duration" title="${message(code: 'play.duration.label', default: 'Duration')}" />
					
						<th><g:message code="play.game.label" default="Game" /></th>
					
						<g:sortableColumn property="offense" title="${message(code: 'play.offense.label', default: 'Offense')}" />
					
						<g:sortableColumn property="type" title="${message(code: 'play.type.label', default: 'Type')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${playInstanceList}" status="i" var="playInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${playInstance.id}">${fieldValue(bean: playInstance, field: "defense")}</g:link></td>
					
						<td>${fieldValue(bean: playInstance, field: "duration")}</td>
					
						<td>${fieldValue(bean: playInstance, field: "game")}</td>
					
						<td>${fieldValue(bean: playInstance, field: "offense")}</td>
					
						<td>${fieldValue(bean: playInstance, field: "type")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${playInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
