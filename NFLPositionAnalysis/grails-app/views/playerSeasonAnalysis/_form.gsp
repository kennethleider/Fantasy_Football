<%@ page import="leider.ken.nfl.fantasy.analysis.PlayerSeasonAnalysis" %>



<div class="fieldcontain ${hasErrors(bean: playerSeasonAnalysisInstance, field: 'player', 'error')} required">
	<label for="player">
		<g:message code="playerSeasonAnalysis.player.label" default="Player" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="player" name="player.id" from="${leider.ken.nfl.stats.Player.list()}" optionKey="id" required="" value="${playerSeasonAnalysisInstance?.player?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerSeasonAnalysisInstance, field: 'season', 'error')} required">
	<label for="season">
		<g:message code="playerSeasonAnalysis.season.label" default="Season" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="season" name="season.id" from="${leider.ken.nfl.stats.Season.list()}" optionKey="id" required="" value="${playerSeasonAnalysisInstance?.season?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerSeasonAnalysisInstance, field: 'league', 'error')} required">
	<label for="league">
		<g:message code="playerSeasonAnalysis.league.label" default="League" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="league" name="league.id" from="${leider.ken.nfl.fantasy.League.list()}" optionKey="id" required="" value="${playerSeasonAnalysisInstance?.league?.id}" class="many-to-one"/>
</div>

