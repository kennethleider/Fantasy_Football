<%@ page import="leider.ken.nfl.Player" %>



<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="player.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${playerInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'position', 'error')} ">
	<label for="position">
		<g:message code="player.position.label" default="Position" />
		
	</label>
	<g:textField name="position" value="${playerInstance?.position}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'team', 'error')} ">
	<label for="team">
		<g:message code="player.team.label" default="Team" />
		
	</label>
	<g:textField name="team" value="${playerInstance?.team}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'firstYear', 'error')} ">
	<label for="firstYear">
		<g:message code="player.firstYear.label" default="First Year" />
		
	</label>
	<g:textField name="firstYear" value="${playerInstance?.firstYear}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'draftPick', 'error')} ">
	<label for="draftPick">
		<g:message code="player.draftPick.label" default="Draft Pick" />
		
	</label>
	<g:field name="draftPick" type="number" value="${playerInstance.draftPick}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'alternatePosition', 'error')} ">
	<label for="alternatePosition">
		<g:message code="player.alternatePosition.label" default="Alternate Position" />
		
	</label>
	<g:textField name="alternatePosition" value="${playerInstance?.alternatePosition}"/>
</div>

