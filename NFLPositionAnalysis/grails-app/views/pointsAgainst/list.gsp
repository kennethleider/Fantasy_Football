
<%@ page import="leider.ken.nfl.fantasy.analysis.PointsAgainst" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pointsAgainst.label', default: 'PointsAgainst')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-pointsAgainst" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-pointsAgainst" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="pointsAgainst.franchise.label" default="Franchise" /></th>
					
						<g:sortableColumn property="factor" title="${message(code: 'pointsAgainst.factor.label', default: 'Factor')}" />
					
						<th><g:message code="pointsAgainst.league.label" default="League" /></th>
					
						<th><g:message code="pointsAgainst.points.label" default="Points" /></th>
					
						<g:sortableColumn property="position" title="${message(code: 'pointsAgainst.position.label', default: 'Position')}" />
					
						<th><g:message code="pointsAgainst.season.label" default="Season" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${pointsAgainstInstanceList}" status="i" var="pointsAgainstInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${pointsAgainstInstance.id}">${fieldValue(bean: pointsAgainstInstance, field: "franchise")}</g:link></td>
					
						<td>${fieldValue(bean: pointsAgainstInstance, field: "factor")}</td>
					
						<td>${fieldValue(bean: pointsAgainstInstance, field: "league")}</td>
					
						<td>${fieldValue(bean: pointsAgainstInstance, field: "points")}</td>
					
						<td>${fieldValue(bean: pointsAgainstInstance, field: "position")}</td>
					
						<td>${fieldValue(bean: pointsAgainstInstance, field: "season")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${pointsAgainstInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
