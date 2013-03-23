<%@ page import="leider.ken.nfl.Matchup" %>



<div class="fieldcontain ${hasErrors(bean: matchupInstance, field: 'week', 'error')} required">
	<label for="week">
		<g:message code="matchup.week.label" default="Week" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="week" name="week.id" from="${leider.ken.nfl.Week.list()}" optionKey="id" required="" value="${matchupInstance?.week?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: matchupInstance, field: 'away', 'error')} required">
	<label for="away">
		<g:message code="matchup.away.label" default="Away" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="away" name="away.id" from="${leider.ken.nfl.Franchise.list()}" optionKey="id" required="" value="${matchupInstance?.away?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: matchupInstance, field: 'awayScore', 'error')} ">
	<label for="awayScore">
		<g:message code="matchup.awayScore.label" default="Away Score" />
		
	</label>
	<g:field name="awayScore" type="number" value="${matchupInstance.awayScore}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: matchupInstance, field: 'home', 'error')} required">
	<label for="home">
		<g:message code="matchup.home.label" default="Home" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="home" name="home.id" from="${leider.ken.nfl.Franchise.list()}" optionKey="id" required="" value="${matchupInstance?.home?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: matchupInstance, field: 'homeScore', 'error')} ">
	<label for="homeScore">
		<g:message code="matchup.homeScore.label" default="Home Score" />
		
	</label>
	<g:field name="homeScore" type="number" value="${matchupInstance.homeScore}"/>
</div>

