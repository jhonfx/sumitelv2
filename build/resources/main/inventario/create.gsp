<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'inventario.label', default: 'Inventario')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-inventario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        
        <div id="create-inventario" class="content scaffold-create" role="main">
            <h1>NUEVO REGISTRO</h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.inventario}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.inventario}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="save">


            <div class="container"> 
              <div class="col-md-6">
                <div class="row">
                    <div class="form-group">
                      <label>DESCRIPCIÓN</label>
                      <input type="text" id="descripcionInventario" name="descripcionInventario" class="descripcionInventario form-control" style="text-transform: uppercase"/>
                    </div>
                    <div class="form-group">
                        <button type="submit" id="create" name="create" class="btn pull-right save" style="text-transform: uppercase;">guardar</button>
                    </div>
                </div>
              </div>

            </div>
            </div>
            </g:form>
        </div>
    </body>
</html>
