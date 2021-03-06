<%-- File: grails-app/views/templates/homepage.gsp --%>
<g:applyLayout name="twoblocks">
    <head>
      <title><g:message code="stats.homepage"/></title>
    </head>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
    <content tag="banner">
      <div class="nav" role="navigation">
        <ul>
          <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        </ul>
      </div>
    </content>

    <content tag="left1">
        <h1>Armchair anaylsis</h1>
        <g:if test="${commandHistory.containsKey('stats.armchair')}">
          <div style="margin-left: 3em;">Loaded ${commandHistory['stats.armchair']}</div>
        </g:if>

        <a href="http://www.armchairanalysis.com/nfl-play-by-play-data.php" target="_blank">Armchair analysis</a> 
        provides a database of historic play-by-play statistics.  This is not the most accurate data, but it is the most
        complete I can find.  (If you know a better source, 
        <a href="https://github.com/kennethleider/Fantasy_Football/issues">create an issue</a>)
        
        <ol>
          <li>Download the <a href="http://www.armchairanalysis.com/NFL_Data_Historical.zip">Database</a></li>
          <li>Extract the zip file to <code>${grailsApplication.config.armchairanalysis.dir}</code>
            (Modify <code>armchairanalysis.dir</code> in <code>Config.groovy</code> and restart grails 
            if you want to use a different directory)
          </li>
          <li><g:link action="armchair">Load the data.</g:link> This will take several minutes.  Check the log for progress</li>
        </ol>

      <h1>Yahoo sports</h1>
        <g:if test="${commandHistory.containsKey('stats.yahoo')}">
          <div style="margin-left: 3em;">Loaded ${commandHistory['stats.yahoo']}</div>
        </g:if>

        <a href="http://sports.yahoo.com/nfl/stats/byposition" target="_blank">Yahoo sports</a> 
        provides data in line with the official NFL data back to 2001.  Unfortunately there are a lot of gaps
        in the statistics.  (If you know a better source, 
        <a href="https://github.com/kennethleider/Fantasy_Football/issues">create an issue</a>)
        
        <ol>
          <li><g:link action="yahoo">Load the data.</g:link> This will take several minutes.  Check the log for progress</li>
        </ol>

        <h1>NFL.com</h1>
        <g:if test="${commandHistory.containsKey('stats.nfl')}">
            <div style="margin-left: 3em;">Loaded ${commandHistory['stats.nfl']}</div>
        </g:if>

        <a href="http://www.nfl.com/stats/categorystats" target="_blank">nfl.com</a>
        provides data in line with the official NFL data back to 1932.  Does not have weekly stats or targets.
        (If you know a better source,
        <a href="https://github.com/kennethleider/Fantasy_Football/issues">create an issue</a>)

        <ol>
            <li><g:link action="nfl">Load the data.</g:link> This will take several minutes.  Check the log for progress</li>
        </ol>
      
    </content>


    <content tag="right1">
      <h1>Loaded Stats</h1>
      <ul>
        <li>Players - ${playerCount}</li>
        <li>Season Stats - ${seasonStatCount}</li>
        <li>Weekly Stats - ${weeklyStatCount}</li>
        
        <g:if test="${!seasons.isEmpty()}"> 
          <li>Seasons - ${seasons.first()} - ${seasons.last()}</li>
        </g:if>   
        <li>Last Week - ${lastWeek}</li>
      </ul>
    </content>
</g:applyLayout>