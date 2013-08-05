<%@ page import="leider.ken.nfl.fantasy.SeasonScore" %>



<div class="fieldcontain ${hasErrors(bean: seasonScoreInstance, field: 'season', 'error')} required">
	<label for="season">
		<g:message code="seasonScore.season.label" default="Season" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="season" name="season.id" from="${leider.ken.nfl.stats.Season.list()}" optionKey="id" required="" value="${seasonScoreInstance?.season?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: seasonScoreInstance, field: 'player', 'error')} required">
	<label for="player">
		<g:message code="seasonScore.player.label" default="Player" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="player" name="player.id" from="${leider.ken.nfl.stats.Player.list()}" optionKey="id" required="" value="${seasonScoreInstance?.player?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: seasonScoreInstance, field: 'total', 'error')} required">
	<label for="total">
		<g:message code="seasonScore.total.label" default="Total" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="total" value="${fieldValue(bean: seasonScoreInstance, field: 'total')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: seasonScoreInstance, field: 'average', 'error')} required">
	<label for="average">
		<g:message code="seasonScore.average.label" default="Average" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="average" value="${fieldValue(bean: seasonScoreInstance, field: 'average')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: seasonScoreInstance, field: 'standardDeviation', 'error')} required">
	<label for="standardDeviation">
		<g:message code="seasonScore.standardDeviation.label" default="Standard Deviation" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="standardDeviation" value="${fieldValue(bean: seasonScoreInstance, field: 'standardDeviation')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: seasonScoreInstance, field: 'max', 'error')} required">
	<label for="max">
		<g:message code="seasonScore.max.label" default="Max" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="max" value="${fieldValue(bean: seasonScoreInstance, field: 'max')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: seasonScoreInstance, field: 'league', 'error')} required">
	<label for="league">
		<g:message code="seasonScore.league.label" default="League" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="league" name="league.id" from="${leider.ken.nfl.fantasy.League.list()}" optionKey="id" required="" value="${seasonScoreInstance?.league?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: seasonScoreInstance, field: 'min', 'error')} required">
	<label for="min">
		<g:message code="seasonScore.min.label" default="Min" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="min" value="${fieldValue(bean: seasonScoreInstance, field: 'min')}" required=""/>
</div>

