<%@page import="com.umariana.directorio.infContacto"%>
<%@page import="com.umariana.directorio.Arbol_b"%>
<%@include file="templates/header.jsp" %>
<%@include file="templates/navbar.jsp" %>
<head>
    <meta charset="UTF-8">
    <title>Tareas</title>
     <style>
        .background-radial-gradient {
            background-color: #30391F;
            color: #464646;
            
        }
        .container {
            margin-top: 50px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-label {
            font-weight: bold;
            color: #464646;
        }

        .form-control {
            background-color: #668A4C;
            color: #30391F;
            border-color: #ACCC7B;
        }

        .form-control:focus {
            background-color: #ACCC7B;
            color: #30391F;
            border-color: #FFFFFF;
        }

        .btn-primary {
            background-color: #ACCC7B;
            border-color: #ACCC7B;
        }

        .btn-primary:hover {
            background-color: #ACCC7B;
            border-color: #ACCC7B;
        }

        .table-container {
            margin-top: 30px;
        }

        .table {
            background-color: #FFFFFF;
            color: #464646; 
        }

        .table th, .table td {
            border: 1px solid #dee2e6;
        }

        .table th {
            background-color: #ACCC7B;
            color: #30391F;
        }

        .table tbody tr:nth-child(odd) {
            background-color: #F2F2F2;
        }
        
        #radius-shape-1 {
            height: 220px;
            width: 220px;
            top: -60px;
            left: -130px;
            background: radial-gradient(#44006b, #ad1fff);
            overflow: hidden;
        }

        #radius-shape-2 {
            border-radius: 38% 62% 63% 37% / 70% 33% 67% 30%;
            bottom: -60px;
            right: -110px;
            width: 300px;
            height: 300px;
            background: radial-gradient(#44006b, #ad1fff);
            overflow: hidden;
        }

        .bg-glass {
            background-color: hsla(0, 0%, 100%, 0.9) !important;
            backdrop-filter: saturate(200%) blur(25px);
        }
        .white-table {
            background-color: white;
        }
    </style>
</head>
<!-- Barra de busqueda -->
<body class="background-radial-gradient">
    <div class="container">
    <div class="input-group mb-3">
        <div class="form-outline" data-mdb-input-init>
            <input id="search-focus" type="search" id="form1" class="form-control" 
                   placeholder="Buscar el nombre de contacto" />
        </div>

    </div>
<!-- Formulario de agregar contacto -->
    <div class="row">
        <div class="col-md-4">  <!-- clase division por 4 columnas -->
            <div class="card card-body"> 
                <h3>Añadir contacto! <i class="fas fa-user"></i>
</h3>
                <form action="SvDirectorio" method="POST">     
                    
                    <!-- Input para el id-->
                    <div class="input-group mb-3">
                        <label class="input-group-text" for="id">Id: </label>
                        <input type="text" name ="id" class="form-control">
                    </div>                                            
                    <div class="input-group mb-3">
                        <label class="input-group-text" for="id">Nombres: </label>
                        <input type="text" name ="nombre" class="form-control">
                    </div> 
                    <div class="input-group mb-3">
                        <label class="input-group-text" for="id">Apellidos: </label>
                        <input type="text" name ="apellido" class="form-control">
                    </div> 
                    <div class="input-group mb-3">
                        <label class="input-group-text" for="id">Correo: </label>
                        <input type="email" name ="correo" class="form-control">
                    </div> 
                    <div class="input-group mb-3">
                        <label class="input-group-text" for="id">Direccion: </label>
                        <input type="text" name ="direccion" class="form-control">
                    </div> 
                    <div class="input-group mb-3">
                        <label class="input-group-text" for="id">Telefono: </label>
                        <input type="tel" name ="telefono" class="form-control">
                    </div> 


                     <button type="submit" class="btn btn-primary">Agregar Contacto</button>
                </form><br>
            </div>    
        </div> 
        
        <!-- Tabla de datos -->
        <div class="col-md-8">
            <%
                ArrayList<infContacto> lC =(ArrayList<infContacto>) session.getAttribute("lC");
            %>
            <table class="table white-table table-bordered table-dark">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Correo</th>
                        <th>Direccion</th>
                        <th>N° Telefono</th>
                        <th>Acciones</th>

                    </tr>
                </thead>
                <tbody>
                    <%
                        
                        if (lC != null) {
                           for(infContacto contac : lC){
                    %>
                        <tr id="<%= contac.getNombre() %>">
                            <td><%= contac.getId()%></td>
                            <td><%= contac.getNombre()%></td>
                            <td><%= contac.getApellido()%></td>
                            <td><%= contac.getCorreo()%></td>
                            <td><%= contac.getDireccion()%></td>
                            <td><%= contac.getTelefono()%></td>
                            <td>
                            
                                
                            <a href="#" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalV"
                               data-nombre="<%= contac.getNombre()%>"
                               data-apellido="<%= contac.getApellido()%>"
                               data-correo="<%= contac.getCorreo()%>"
                               data-direccion="<%= contac.getDireccion()%>"
                               data-telefono="<%= contac.getTelefono()%>">
                            <i class="fas fa-eye"></i>
                            </a>

                            <a href="#" title="Eliminar" class="btn btn-danger" onclick="eliminarContacto('<%= contac.getNombre()%>')">
                                <i class="fas fa-trash-alt"></i>
                            </a>

                        </td>
                        </tr>
                    <%
                                
                            }
                        } else {
                            out.println("No hay contactos disponibles.");
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>  
</div>  
</body>
<%@include file="templates/footer.jsp" %>

<!-- Ventana Modal para ver informacion del contacto-->
<div class="modal fade" id="modalV" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content " style="background-color: #30391F; color: #FFFFFF;">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Detalles del contacto</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="modal-body">
                    <div id="contacto-details">
                        <p><strong>Nombre: </strong><span id="modal-nombre"></span></p>
                        <p><strong>Apellido: </strong><span id="modal-apellido"></span></p>
                        <p><strong>Correo: </strong><span id="modal-correo"></span></p>
                        <p><strong>Direccion: </strong><span id="modal-direccion"></span></p>
                        <p><strong>Telefono: </strong><span id="modal-telefono"></span></p>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
            </div>
        </div>
    </div>
</div>
<!-- script de la barra de busqueda -->
<script>
    $(document).ready(function () {
        // Manejar el evento de entrada en la caja de búsqueda
        $('#search-focus').on('input', function () {
            var searchTerm = $(this).val().toLowerCase();

            // Filtrar las filas de la tabla basándonos en el término de búsqueda
            $('tbody tr').each(function () {
                var nombre = $(this).find('td:eq(1)').text().toLowerCase();

                // Mostrar u ocultar la fila según si coincide con el término de búsqueda
                if (nombre.includes(searchTerm)) {
                    $(this).show();
                } else {
                    $(this).hide();
                }
            });
        });
    });
</script>

<script>
   // Actualiza la informacion
    $(document).ready(function () {
        $('#modalV').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget);
            var nombre = button.data('nombre');
            var apellido = button.data('apellido');
            var correo = button.data('correo');
            var direccion = button.data('direccion');
            var telefono = button.data('telefono');
            

            // Update the content of the span elements with the contact details
            $('#modal-nombre').text(nombre);
            $('#modal-apellido').text(apellido);
            $('#modal-correo').text(correo);
            $('#modal-direccion').text(direccion);
            $('#modal-telefono').text(telefono);
            
        });
    });
</script>

<script>
    // Script para eliminar un contacto
    function eliminarContacto(nombree) {
        if (confirm("¿Desea eliminar el contacto" + nombree + "?")) {
            $.ajax({
                type: "GET",
                url: "SvDirectorio",
                data: {action: "eliminarC", nombre: nombree},
                success: function (data) {
                    console.log("Contacto Eliminado");
                    $("#" + nombree).remove();
                }, 
                error: function (error) {
                    console.error("Error, no se pudo eliminar el contacto", error);
                }
            });
        } else {
            // No hacer nada si se cancela la eliminación
        }
    }
</script>
