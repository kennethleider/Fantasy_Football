
<%@ page import="leider.ken.nfl.fantasy.analysis.PlayerSeasonAnalysis" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'playerSeasonAnalysis.label', default: 'PlayerSeasonAnalysis')}" />
  <title><g:message code="default.list.label" args="[entityName]" /></title>
 <r:require module="export"/> 
</head>
<body>
  <a href="#list-playerSeasonAnalysis" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
  <div class="nav" role="navigation">
    <ul>
      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
  </div>
  <div id="list-playerSeasonAnalysis" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
      <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
      <thead>
        <tr>

          <th><g:message code="playerSeasonAnalysis.player.label" default="Player" /></th>

          <th><g:message code="playerSeasonAnalysis.season.label" default="Season" /></th>

          <th><g:message code="playerSeasonAnalysis.league.label" default="League" /></th>

          <th><g:message code="playerSeasonAnalysis.apr.label" default="APR" /></th>
          
          <th><g:message code="playerSeasonAnalysis.aprdev.label" default="APR - STDDEV" /></th>
          
          <th><g:message code="playerSeasonAnalysis.aapr.rank" default="AAPR" /></th>
          
          <th><g:message code="playerSeasonAnalysis.aaprdev.label" default="AAPR - STDDEV" /></th>
      </tr>
      </thead>
      <tbody>
      <g:each in="${playerSeasonAnalysisInstanceList}" status="i" var="playerSeasonAnalysisInstance">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

          <td><g:link action="show" id="${playerSeasonAnalysisInstance.id}">${fieldValue(bean: playerSeasonAnalysisInstance, field: "player")}</g:link></td>

          <td>${fieldValue(bean: playerSeasonAnalysisInstance, field: "season")}</td>

          <td>${fieldValue(bean: playerSeasonAnalysisInstance, field: "league")}</td>
        
          <td>${playerSeasonAnalysisInstance.rank.average}</td>

          <td>${playerSeasonAnalysisInstance.rank.standardDeviation}</td>

          <td>${playerSeasonAnalysisInstance.adjustedRank.average}</td>

          <td>${playerSeasonAnalysisInstance.adjustedRank.standardDeviation}</td>
        </tr>
      </g:each>
      </tbody>
    </table>
    <div class="pagination">
      <g:paginate total="${playerSeasonAnalysisInstanceTotal}" />
    </div>
    <export:formats />
  </div>
</body>
</html>
