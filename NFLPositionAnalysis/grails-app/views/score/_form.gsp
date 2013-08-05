<%@ page import="leider.ken.nfl.fantasy.Score" %>



<div class="fieldcontain ${hasErrors(bean: scoreInstance, field: 'league', 'error')} required">
	<label for="league">
		<g:message code="score.league.label" default="League" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="league" name="league.id" from="${leider.ken.nfl.fantasy.League.list()}" optionKey="id" required="" value="${scoreInstance?.league?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scoreInstance, field: 'player', 'error')} required">
	<label for="player">
		<g:message code="score.player.label" default="Player" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="player" name="player.id" from="${leider.ken.nfl.stats.Player.list()}" optionKey="id" required="" value="${scoreInstance?.player?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scoreInstance, field: 'points', 'error')} required">
	<label for="points">
		<g:message code="score.points.label" default="Points" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="points" value="${fieldValue(bean: scoreInstance, field: 'points')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: scoreInstance, field: 'week', 'error')} required">
	<label for="week">
		<g:message code="score.week.label" default="Week" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="week" name="week.id" from="${leider.ken.nfl.stats.Week.list()}" optionKey="id" required="" value="${scoreInstance?.week?.id}" class="many-to-one"/>
</div>

