<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
    <meta name="layout" content="main" />
    <asset:stylesheet src="application.css"/>
    <g:set var="entityName" value="${message(code: 'articulo.label', default: 'Articulo')}" />
    
    <script type="text/javascript" src="${resource(dir: 'javascripts', file: 'jquery-1.8.3.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'javascripts', file: 'underscore.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'javascripts/utils', file: 'sumitel_utils.js')}"></script>


    

    <link rel="stylesheet" href="${resource(dir: 'css', file: 'jsgrid.min.css')}"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'jsgrid-theme.min.css')}"/>





    <title>CARGA DE ARTICULOS</title>
    <script type="text/javascript">
      $(document).ready( function() {

    //     $.fn.serializeObject = function()
    // {
    //     var o = {};
    //     var a = this.serializeArray();
    //     $.each(a, function() {
    //         if (o[this.name] !== undefined) {
    //             if (!o[this.name].push) {
    //                 o[this.name] = [o[this.name]];
    //             }
    //             o[this.name].push(this.value || '');
    //         } else {
    //             o[this.name] = this.value || '';
    //         }
    //     });
    //     return o;
    // };

        console.log("cargando");
        var toTable = [];
        

        $('#aplicar').click( function(e) {
          var frm = $('#formulario');
          var producto = $('#producto').val();
          var data = JSON.stringify(frm.serializeObject());
          var format = JSON.parse(data);  //data parse

          var newobject = format.code.split(/\s+/);  //array of serie/IMEI
          console.log(newobject);
          if (newobject == [""] || newobject == null || newobject.length == 0 || newobject == "") {
            console.log("esta vacio")
          } else {
            _.each(newobject, function(obj) {
              toTable.push({
                'series': obj,
                'factura': format.fact,
                'producto': format.prod
              });
            });

            $('#codigos').val('');
            console.log(toTable);

            /* load data using filter controller */
            (function() {
              
              var db = {

                loadData: function(filter) {
                    return $.grep(this.tuplas, function(tupla) {
                        return (!filter.series || tupla.series.indexOf(filter.series) > -1)
                        && (!filter.producto || tupla.producto.indexOf(filter.producto) > -1);
                    });
                },

                insertItem: function(insertingClient) {
                    this.clients.push(insertingClient);
                },

                updateItem: function(updatingClient) { },

                deleteItem: function(deletingTuplas) {
                    var tuplasIndex = $.inArray(deletingTuplas, this.tuplas);
                    this.tuplas.splice(tuplasIndex, 1);
                }

              };

              window.db = db;
              db.tuplas = toTable;
          }());

            $( function() {

              /* generate data table */
              $("#jsgrid_table").jsGrid({
                width: "100%",
                height: "400px",

                confirmDeleting: true,
                deleteConfirm: "¿ Deseas borrar este artículo ?",

                filtering: true,
                editing: true,
                inserting: true,
                sorting: true,
                paging: true,
                autoload: true,
                pageSize: 15,
                pageButtonCount: 5,
                
                // data: toTable,
                controller: db,
         
                fields: [
                    { name: "factura", type: "text", width: 50, filtering: false, editing: false},
                    { name: "series", type: "text", width: 50, editing: false},
                    { name: "producto", type: "text", width: 150, editing: false},
                    { type: "control", editButton: false, filtering: false}
                ]

                
              });
            });
          }

        });

        $('#limpiar').click( function(e) {
          console.log("limpiando");
          toTable = [];
          $('#table_here').html("");
          // $("#jsgrid_table").jsGrid("refresh");
          // $('#table_here').html(template({toTable}));
        });



        $('#save_data').click( function(e) {
          console.log(toTable);
          var datos = {name: 'juan', age: 30};
          console.log(datos);
          var testjson = JSON.stringify({series: toTable})
          console.log(testjson);

          var arraydemo = [{id: 1}, {id: 2}]

         // return false;

          $.ajax({
                url:"${createLink(controller:'articulo', action:'saveDataTest')}",
                data: {'tuplas': JSON.stringify(toTable)},
                type:"POST",
                success:function (callback) {
                    
                    
                    if (callback.success) {
                        console.log("todo ok")
                        console.log(callback)
                    }
                    else {
                        console.log("no funciona")
                    }
                    // $(callback.text).dialog({
                    //     resizable:false,
                    //     modal:true,
                    //     hide:"none",
                    //     buttons:{
                    //         "Ok":okFunction
                    //     }
                    // });
                },
                error:function (json) {
                    console.log("pos hubo un error" + json)
                },
                dataType:"json"
            });
        })
      });
    </script>
    
</head>
<body>
<div class="container"> 
  <div class="col-md-12">
    <div class="row">
      <h1>AGREGAR PRODUCTOS</h1>
    </div>
    <div class="row">
      <form id="formulario">
        <div class="form-group">
          <label>FACTURA</label>
          <input type="text" class="form-control" id="factura" name="fact">
        </div>
        <div class="form-group">
          <label>IMEI / SERIE</label>
          <textarea type="textarea" class="form-control" id="codigos" name="code"></textarea>
        </div>
        <div class="form-group">
          <label>TIPO PRODUCTO</label>
          <select class="form-control" id="producto" name="prod">
            <option id="1">tarjeta amigo chip 100</option>
            <option id="2">tarjeta amigo chip 200</option>
            <option id="3">tarjeta amigo chip 300</option>
            <option id="4">tarjeta amigo chip 500</option>
          </select>
        </div>
        <div class="form-group">
          <button type="button" id="aplicar" class="btn">AGREGAR</button>
          <button type="button" id="limpiar" class="btn">BORRAR</button>
        </div>
      </form>
    </div>
    <div class="row" id="jsgrid_table">
    </div>
    <div class="row">
      <div class="col-md-8"></div>
      <div class="col-md-4">
        ${name}
        <button type="button" id="save_data" class="btn pull-right">GENERAR ORDEN</button>
      </div>
    </div>
  </div>
</div>
  

  <script type="text/javascript" src="${resource(dir: 'javascripts', file: 'jsgrid.core.js')}"></script>
  <script type="text/javascript" src="${resource(dir: 'javascripts', file: 'jsgrid.load-indicator.js')}"></script>
  <script type="text/javascript" src="${resource(dir: 'javascripts', file: 'jsgrid.load-strategies.js')}"></script>
  <script type="text/javascript" src="${resource(dir: 'javascripts', file: 'jsgrid.sort-strategies.js')}"></script>
  <script type="text/javascript" src="${resource(dir: 'javascripts', file: 'jsgrid.field.js')}"></script>

  <script type="text/javascript" src="${resource(dir: 'javascripts', file: '/fields/jsgrid.field.text.js')}"></script>
  <script type="text/javascript" src="${resource(dir: 'javascripts', file: '/fields/jsgrid.field.number.js')}"></script>
  <script type="text/javascript" src="${resource(dir: 'javascripts', file: '/fields/jsgrid.field.select.js')}"></script>
  <script type="text/javascript" src="${resource(dir: 'javascripts', file: '/fields/jsgrid.field.checkbox.js')}"></script>
  <script type="text/javascript" src="${resource(dir: 'javascripts', file: '/fields/jsgrid.field.control.js')}"></script>
  
</body>
</html>