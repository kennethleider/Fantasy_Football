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
        complete I can find.  (It you know a better source, 
        <a href="https://github.com/kennethleider/Fantasy_Football/issues">create an issue</a>)
        
        <ol>
          <li>Download the <a href="http://www.armchairanalysis.com/NFL_Data_Historical.zip">Database</a></li>
          <li>Extract the zip file to <code>${grailsApplication.config.armchairanalysis.dir}</code>
            (Modify <code>armchairanalysis.dir</code> in <code>Config.groovy</code> and restart grails 
            if you want to use a different directory)
          </li>
          <li><g:link action="armchair">Load the data.</g:link> This will take several minutes.  Check the log for progress</li>
        </ol>
      </div>
    </content>


    <content tag="right1">
      <h1>Loaded Stats</h1>
      <ul>
        <li>Players - ${playerCount}</li>
      </ul>
    </content>
</g:applyLayout>