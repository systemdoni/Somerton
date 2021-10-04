<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'sportSession.label', default: 'Sport Session')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-sport-session" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
                    <form class="row mt-3 mb-3" id="search_form" action="sportSession" method="GET">
                        <div class="col-sm">
                            <input type="text" class="form-control" placeholder="Location" name="location"/>
                        </div>
                        <div class="col-sm">
                            <g:select class="form-control" name="sport"
                                      from="${com.somerton.SportType.list()}"
                                      optionKey="id"/>
                        </div>
                        <div class="col-sm">
                            <input type="submit" class="btn btn-primary form-control" name="Search" value = "Search"/>
                        </div>
                    </form>
                <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5">
                    <g:each in="${sportSessionList}" >
                        <div class="col mb-4">
                            <div class="card">
                                <img src="${it.picture}" class="card-img-top" >
                                <div class="card-body">
                                    <h5 class="card-title">${it.name}</h5>
                                    <p class="card-text"><b>Location</b>: ${it.location}
                                        <br/><b>Coach</b>: ${it.coach}
                                        <br/><b>Sport</b>: ${it.sportType}</p>
                                </div>
                            </div>
                        </div>
                    </g:each>
                </div>

        </div>
    </body>
</html>