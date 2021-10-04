<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'sportSession.label', default: 'Sport session')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-sport-session" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-sport-session" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.sportSession}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.sportSession}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:uploadForm resource="${this.sportSession}" method="POST">
                <fieldset class="form">
                    <f:all bean="sportSession"/>
                    <input type="hidden" value="" name="sportSessionPic"/>
                    <input type="file" name="sportSessionPicFile">
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save btn btn-dark" value="Create" />
                </fieldset>
            </g:uploadForm>
        </div>
    </body>
</html>
