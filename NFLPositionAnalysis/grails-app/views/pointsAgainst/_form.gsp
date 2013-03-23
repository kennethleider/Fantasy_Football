<%@ page import="leider.ken.nfl.fantasy.analysis.PointsAgainst" %>



<div class="fieldcontain ${hasErrors(bean: pointsAgainstInstance, field: 'franchise', 'error')} required">
	<label for="franchise">
		<g:message code="pointsAgainst.franchise.label" default="Franchise" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="franchise" name="franchise.id" from="${leider.ken.nfl.Franchise.list()}" optionKey="id" required="" value="${pointsAgainstInstance?.franchise?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pointsAgainstInstance, field: 'factor', 'error')} required">
	<label for="factor">
		<g:message code="pointsAgainst.factor.label" default="Factor" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="factor" value="${fieldValue(bean: pointsAgainstInstance, field: 'factor')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: pointsAgainstInstance, field: 'league', 'error')} required">
	<label for="league">
		<g:message code="pointsAgainst.league.label" default="League" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="league" name="league.id" from="${leider.ken.nfl.fantasy.League.list()}" optionKey="id" required="" value="${pointsAgainstInstance?.league?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pointsAgainstInstance, field: 'points', 'error')} required">
	<label for="points">
		<g:message code="pointsAgainst.points.label" default="Points" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="points" name="points.id" from="${leider.ken.nfl.stats.Mean.list()}" optionKey="id" required="" value="${pointsAgainstInstance?.points?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pointsAgainstInstance, field: 'position', 'error')} ">
	<label for="position">
		<g:message code="pointsAgainst.position.label" default="Position" />
		
	</label>
	<g:textField name="position" value="${pointsAgainstInstance?.position}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pointsAgainstInstance, field: 'season', 'error')} required">
	<label for="season">
		<g:message code="pointsAgainst.season.label" default="Season" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="season" name="season.id" from="${leider.ken.nfl.Season.list()}" optionKey="id" required="" value="${pointsAgainstInstance?.season?.id}" class="many-to-one"/>
</div>

