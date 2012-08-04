<%@ page import="leider.ken.nfl.armchairanalysis.Play" %>



<div class="fieldcontain ${hasErrors(bean: playInstance, field: 'defense', 'error')} ">
	<label for="defense">
		<g:message code="play.defense.label" default="Defense" />
		
	</label>
	<g:textField name="defense" value="${playInstance?.defense}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playInstance, field: 'duration', 'error')} required">
	<label for="duration">
		<g:message code="play.duration.label" default="Duration" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="duration" type="number" value="${playInstance.duration}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: playInstance, field: 'game', 'error')} required">
	<label for="game">
		<g:message code="play.game.label" default="Game" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="game" name="game.id" from="${leider.ken.nfl.armchairanalysis.Game.list()}" optionKey="id" required="" value="${playInstance?.game?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playInstance, field: 'offense', 'error')} ">
	<label for="offense">
		<g:message code="play.offense.label" default="Offense" />
		
	</label>
	<g:textField name="offense" value="${playInstance?.offense}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playInstance, field: 'type', 'error')} ">
	<label for="type">
		<g:message code="play.type.label" default="Type" />
		
	</label>
	<g:textField name="type" value="${playInstance?.type}"/>
</div>

