<%@ page import="leider.ken.nfl.stats.league.League" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'league.label', default: 'League')}" />
  <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
  <a href="#show-league" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
  <div class="nav" role="navigation">
    <ul>
      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
      <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
      <li><g:link class="score" action="computeScores" id="${leagueInstance?.id}"><g:message code="default.score.label" default="Compute Scores"/></g:link></li>
      <li><g:link class="analyze" action="analyze" id="${leagueInstance?.id}"><g:message code="default.analyze.label" default="Analyze"/></g:link></li>
    </ul>
  </div>
  <div id="show-league" class="content scaffold-show" role="main">
    <h1>
      <g:if test="${leagueInstance?.name}">
        <g:fieldValue bean="${leagueInstance}" field="name"/>
      </g:if>
    </h1>
    <g:if test="${flash.message}">
      <div class="message" role="status">${flash.message}</div>
    </g:if>
    
    <g:if test="${params.position}">
      <h1>${params.position}</h1>
      <table>
        <thead>
          <tr>
            <th><g:message code="league.position.season.label" default="Season" /></th>
            <th>Player</th>
            <th>Season</th>
            <th>Rank</th>
            <th>Times</th>
            <th>Percentage</th>
          </tr>
        </thead>
        <tbody>
        <g:each in="${playerAnalysisInstanceList}"  var="playerAnalysisInstance">
          <g:if test="${playerAnalysisInstance.season.year == 2011}">
            <g:each in="${playerAnalysisInstance.rankings}" status="i" var="ranking">
              
              <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${playerAnalysisInstance.player}</td>
                <td>${playerAnalysisInstance.season}</td>
                <td>${ranking.rank}</td>
                <td>${ranking.times}</td>
                <td>${ranking.percentage}</td>
              </tr>
              
            </g:each>
          </g:if>
        </g:each>
            </tbody>
      </table>
    </g:if>
    <g:else>
      <table>
        <thead>
          <tr>
            <th>Player</th>
             <th>Position</th>
            <th>Season</th>
            <th>Rank</th>
            <th>Times</th>
            <th>Percentage</th>
            
          </tr>
        </thead>
        <tbody>
        <g:each in="${playerAnalysisInstanceList}"  var="playerAnalysisInstance">
          <g:if test="${playerAnalysisInstance.season.year == 2011}">
            <g:each in="${playerAnalysisInstance.rankings}" status="i" var="ranking">
              
              <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${playerAnalysisInstance.player}</td>
                <td>${playerAnalysisInstance.player.position}</td>
                <td>${playerAnalysisInstance.season}</td>
                <td>${ranking.rank}</td>
                <td>${ranking.times}</td>
                <td>${ranking.percentage}</td>
              </tr>
              
            </g:each>
          </g:if>
           </g:each>
        </tbody>
      </table>
    </g:else>


    <g:form>
      <fieldset class="buttons">
        <g:hiddenField name="id" value="${leagueInstance?.id}" />
        <g:link class="edit" action="edit" id="${leagueInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
      </fieldset>
    </g:form>
  </div>
</body>
</html>
