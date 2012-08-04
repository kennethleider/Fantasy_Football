<%@ page import="leider.ken.nfl.armchairanalysis.Game" %>



<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'day', 'error')} ">
	<label for="day">
		<g:message code="game.day.label" default="Day" />
		
	</label>
	<g:textField name="day" value="${gameInstance?.day}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'host', 'error')} ">
	<label for="host">
		<g:message code="game.host.label" default="Host" />
		
	</label>
	<g:textField name="host" value="${gameInstance?.host}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'hostScore', 'error')} required">
	<label for="hostScore">
		<g:message code="game.hostScore.label" default="Host Score" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="hostScore" type="number" value="${gameInstance.hostScore}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'humidity', 'error')} ">
	<label for="humidity">
		<g:message code="game.humidity.label" default="Humidity" />
		
	</label>
	<g:textField name="humidity" value="${gameInstance?.humidity}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'overUnder', 'error')} required">
	<label for="overUnder">
		<g:message code="game.overUnder.label" default="Over Under" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="overUnder" value="${fieldValue(bean: gameInstance, field: 'overUnder')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'stadium', 'error')} ">
	<label for="stadium">
		<g:message code="game.stadium.label" default="Stadium" />
		
	</label>
	<g:textField name="stadium" value="${gameInstance?.stadium}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'surface', 'error')} ">
	<label for="surface">
		<g:message code="game.surface.label" default="Surface" />
		
	</label>
	<g:textField name="surface" value="${gameInstance?.surface}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'temperature', 'error')} ">
	<label for="temperature">
		<g:message code="game.temperature.label" default="Temperature" />
		
	</label>
	<g:textField name="temperature" value="${gameInstance?.temperature}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'visitor', 'error')} ">
	<label for="visitor">
		<g:message code="game.visitor.label" default="Visitor" />
		
	</label>
	<g:textField name="visitor" value="${gameInstance?.visitor}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'visitorPointSpread', 'error')} required">
	<label for="visitorPointSpread">
		<g:message code="game.visitorPointSpread.label" default="Visitor Point Spread" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="visitorPointSpread" value="${fieldValue(bean: gameInstance, field: 'visitorPointSpread')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'visitorScore', 'error')} required">
	<label for="visitorScore">
		<g:message code="game.visitorScore.label" default="Visitor Score" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="visitorScore" type="number" value="${gameInstance.visitorScore}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'weatherConditions', 'error')} ">
	<label for="weatherConditions">
		<g:message code="game.weatherConditions.label" default="Weather Conditions" />
		
	</label>
	<g:textField name="weatherConditions" value="${gameInstance?.weatherConditions}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'week', 'error')} required">
	<label for="week">
		<g:message code="game.week.label" default="Week" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="week" name="week.id" from="${leider.ken.nfl.stats.Week.list()}" optionKey="id" required="" value="${gameInstance?.week?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'windDirection', 'error')} ">
	<label for="windDirection">
		<g:message code="game.windDirection.label" default="Wind Direction" />
		
	</label>
	<g:textField name="windDirection" value="${gameInstance?.windDirection}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'windSpeed', 'error')} ">
	<label for="windSpeed">
		<g:message code="game.windSpeed.label" default="Wind Speed" />
		
	</label>
	<g:textField name="windSpeed" value="${gameInstance?.windSpeed}"/>
</div>

