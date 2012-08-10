<%@ page import="leider.ken.nfl.stats.league.analysis.PlayerSeasonAnalysis" %>



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

<div class="fieldcontain ${hasErrors(bean: playerSeasonAnalysisInstance, field: 'twentyFifthPercentile', 'error')} required">
	<label for="twentyFifthPercentile">
		<g:message code="playerSeasonAnalysis.twentyFifthPercentile.label" default="Twenty Fifth Percentile" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="twentyFifthPercentile" name="twentyFifthPercentile.id" from="${leider.ken.nfl.stats.Occurance.list()}" optionKey="id" required="" value="${playerSeasonAnalysisInstance?.twentyFifthPercentile?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerSeasonAnalysisInstance, field: 'fiftiethPercentile', 'error')} required">
	<label for="fiftiethPercentile">
		<g:message code="playerSeasonAnalysis.fiftiethPercentile.label" default="Fiftieth Percentile" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="fiftiethPercentile" name="fiftiethPercentile.id" from="${leider.ken.nfl.stats.Occurance.list()}" optionKey="id" required="" value="${playerSeasonAnalysisInstance?.fiftiethPercentile?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerSeasonAnalysisInstance, field: 'hundrethPercentile', 'error')} required">
	<label for="hundrethPercentile">
		<g:message code="playerSeasonAnalysis.hundrethPercentile.label" default="Hundreth Percentile" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="hundrethPercentile" name="hundrethPercentile.id" from="${leider.ken.nfl.stats.Occurance.list()}" optionKey="id" required="" value="${playerSeasonAnalysisInstance?.hundrethPercentile?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerSeasonAnalysisInstance, field: 'playables', 'error')} required">
	<label for="playables">
		<g:message code="playerSeasonAnalysis.playables.label" default="Playables" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="playables" name="playables.id" from="${leider.ken.nfl.stats.Occurance.list()}" optionKey="id" required="" value="${playerSeasonAnalysisInstance?.playables?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerSeasonAnalysisInstance, field: 'league', 'error')} required">
	<label for="league">
		<g:message code="playerSeasonAnalysis.league.label" default="League" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="league" name="league.id" from="${leider.ken.nfl.stats.league.League.list()}" optionKey="id" required="" value="${playerSeasonAnalysisInstance?.league?.id}" class="many-to-one"/>
</div>

