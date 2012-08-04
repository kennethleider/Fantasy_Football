<%@ page import="leider.ken.nfl.stats.PlayerWeekStats" %>



<div class="fieldcontain ${hasErrors(bean: playerWeekStatsInstance, field: 'week', 'error')} required">
	<label for="week">
		<g:message code="playerWeekStats.week.label" default="Week" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="week" name="week.id" from="${leider.ken.nfl.stats.Week.list()}" optionKey="id" required="" value="${playerWeekStatsInstance?.week?.id}" class="many-to-one"/>
</div>
<fieldset class="embedded"><legend><g:message code="playerWeekStats.passing.label" default="Passing" /></legend>
<div class="fieldcontain ${hasErrors(bean: playerWeekStatsInstance, field: 'passing.TDs', 'error')} required">
	<label for="passing.TDs">
		<g:message code="playerWeekStats.passing.TDs.label" default="TD s" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="TDs" type="number" value="${passingStatsInstance.TDs}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerWeekStatsInstance, field: 'passing.attempts', 'error')} required">
	<label for="passing.attempts">
		<g:message code="playerWeekStats.passing.attempts.label" default="Attempts" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="attempts" type="number" value="${passingStatsInstance.attempts}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerWeekStatsInstance, field: 'passing.completions', 'error')} required">
	<label for="passing.completions">
		<g:message code="playerWeekStats.passing.completions.label" default="Completions" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="completions" type="number" value="${passingStatsInstance.completions}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerWeekStatsInstance, field: 'passing.conversions', 'error')} required">
	<label for="passing.conversions">
		<g:message code="playerWeekStats.passing.conversions.label" default="Conversions" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="conversions" type="number" value="${passingStatsInstance.conversions}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerWeekStatsInstance, field: 'passing.id', 'error')} required">
	<label for="passing.id">
		<g:message code="playerWeekStats.passing.id.label" default="Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="id" type="number" value="${passingStatsInstance.id}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerWeekStatsInstance, field: 'passing.interceptions', 'error')} required">
	<label for="passing.interceptions">
		<g:message code="playerWeekStats.passing.interceptions.label" default="Interceptions" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="interceptions" type="number" value="${passingStatsInstance.interceptions}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerWeekStatsInstance, field: 'passing.qbRating', 'error')} required">
	<label for="passing.qbRating">
		<g:message code="playerWeekStats.passing.qbRating.label" default="Qb Rating" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="qbRating" value="${fieldValue(bean: passingStatsInstance, field: 'qbRating')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerWeekStatsInstance, field: 'passing.sackYards', 'error')} required">
	<label for="passing.sackYards">
		<g:message code="playerWeekStats.passing.sackYards.label" default="Sack Yards" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="sackYards" type="number" value="${passingStatsInstance.sackYards}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerWeekStatsInstance, field: 'passing.sacks', 'error')} required">
	<label for="passing.sacks">
		<g:message code="playerWeekStats.passing.sacks.label" default="Sacks" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="sacks" type="number" value="${passingStatsInstance.sacks}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerWeekStatsInstance, field: 'passing.version', 'error')} required">
	<label for="passing.version">
		<g:message code="playerWeekStats.passing.version.label" default="Version" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="version" type="number" value="${passingStatsInstance.version}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerWeekStatsInstance, field: 'passing.yards', 'error')} required">
	<label for="passing.yards">
		<g:message code="playerWeekStats.passing.yards.label" default="Yards" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="yards" type="number" value="${passingStatsInstance.yards}" required=""/>
</div>
</fieldset>
<div class="fieldcontain ${hasErrors(bean: playerWeekStatsInstance, field: 'player', 'error')} required">
	<label for="player">
		<g:message code="playerWeekStats.player.label" default="Player" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="player" name="player.id" from="${leider.ken.nfl.stats.Player.list()}" optionKey="id" required="" value="${playerWeekStatsInstance?.player?.id}" class="many-to-one"/>
</div>
<fieldset class="embedded"><legend><g:message code="playerWeekStats.rushing.label" default="Rushing" /></legend>
<div class="fieldcontain ${hasErrors(bean: playerWeekStatsInstance, field: 'rushing.TDs', 'error')} required">
	<label for="rushing.TDs">
		<g:message code="playerWeekStats.rushing.TDs.label" default="TD s" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="TDs" type="number" value="${rushingStatsInstance.TDs}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerWeekStatsInstance, field: 'rushing.attempts', 'error')} required">
	<label for="rushing.attempts">
		<g:message code="playerWeekStats.rushing.attempts.label" default="Attempts" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="attempts" type="number" value="${rushingStatsInstance.attempts}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerWeekStatsInstance, field: 'rushing.conversions', 'error')} required">
	<label for="rushing.conversions">
		<g:message code="playerWeekStats.rushing.conversions.label" default="Conversions" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="conversions" type="number" value="${rushingStatsInstance.conversions}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerWeekStatsInstance, field: 'rushing.fumbles', 'error')} required">
	<label for="rushing.fumbles">
		<g:message code="playerWeekStats.rushing.fumbles.label" default="Fumbles" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="fumbles" type="number" value="${rushingStatsInstance.fumbles}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerWeekStatsInstance, field: 'rushing.fumblesLost', 'error')} required">
	<label for="rushing.fumblesLost">
		<g:message code="playerWeekStats.rushing.fumblesLost.label" default="Fumbles Lost" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="fumblesLost" type="number" value="${rushingStatsInstance.fumblesLost}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerWeekStatsInstance, field: 'rushing.id', 'error')} required">
	<label for="rushing.id">
		<g:message code="playerWeekStats.rushing.id.label" default="Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="id" type="number" value="${rushingStatsInstance.id}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerWeekStatsInstance, field: 'rushing.version', 'error')} required">
	<label for="rushing.version">
		<g:message code="playerWeekStats.rushing.version.label" default="Version" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="version" type="number" value="${rushingStatsInstance.version}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerWeekStatsInstance, field: 'rushing.yards', 'error')} required">
	<label for="rushing.yards">
		<g:message code="playerWeekStats.rushing.yards.label" default="Yards" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="yards" type="number" value="${rushingStatsInstance.yards}" required=""/>
</div>
</fieldset>
