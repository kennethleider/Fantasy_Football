<%@ page import="leider.ken.nfl.stats.league.League" %>



<div class="fieldcontain ${hasErrors(bean: leagueInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="league.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${leagueInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: leagueInstance, field: 'scoring', 'error')} required">
	<label for="scoring">
		<g:message code="league.scoring.label" default="Scoring" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="scoring" name="scoring.id" from="${leider.ken.nfl.stats.league.ScoringRules.list()}" optionKey="id" required="" value="${leagueInstance?.scoring?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: leagueInstance, field: 'url', 'error')} ">
	<label for="url">
		<g:message code="league.url.label" default="Url" />
		
	</label>
	<g:textField name="url" value="${leagueInstance?.url}"/>
</div>

