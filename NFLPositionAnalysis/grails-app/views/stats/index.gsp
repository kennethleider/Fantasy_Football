<%-- File: grails-app/views/templates/homepage.gsp --%>
<g:applyLayout name="twoblocks">
    <head>
      <title><g:message code="stats.homepage"/></title>
    </head>

    <content tag="banner">
      <div class="nav" role="navigation">
        <ul>
          <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        </ul>
      </div>
    </content>

    <content tag="left1">
      <h1>Armchair anaylsis</h1>
    </content>


    <content tag="right1">
      <h1>Loaded Stats</h1>
      <ul>
        <li>Players - ${playerCount}</li>
      </ul>
    </content>
</g:applyLayout>