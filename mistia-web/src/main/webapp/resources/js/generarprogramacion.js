var solicitudarray =null;

$(document).ready(function() {

			
			var cuadrillasarray;
			var oper;
			var accion =  $("#accion").val();
			var ACCION_NUEVA_PROGRAMACION =  $("#accion_nueva_programacion").val();
			var ACCION_EDITA_PROGRAMACION =  $("#accion_edita_programacion").val();
			
			var objetdraget;
			var solicitudesselect ;
			var cantgrpupos;
			
			//var grupoconfi = false;
			//var grupoasign = false; 
		
			$( ".arrastable" ).draggable({ revert: "invalid" });
			$( ".arrastable" ).live("drag",function(event){
				objetdraget = $(this);	
				//console.log(objetdraget.attr('id'));
				$("#cdaselected").val(objetdraget.attr('id'));
			});
			
			
			$( ".arrastable" ).live("dragstop",function(event){
				console.log("#### drag end #### ");
				//objetdraget = $(this);	
				//console.log(objetdraget.attr('id'));
				asisgnarcuadrilla();
			});
			
			
									
			$(".boton").button().css({width:"200"});
			$("#btnEnvia").button().css({width:"100"});
			$("#btnejecutar").button().css({width:"100"});
			$("#btnejecutar").addClass("ui-state-error");
			//$("#btnSalir").button().css({width:"100"});
		
			//$("#btnGuardaProg").attr("disabled","disabled");
			//$("#btnReiniProgra").attr("disabled","disabled");
			$("#btnConfiGrupo").attr("disabled","disabled");
			$("#btnEnvia").attr("disabled","disabled");	
			
					
			$("#btnGeneGrup").click(function(){

				if(solicitudarray==null||  solicitudarray.length==0){
					
					$("#dialog").html("No existen solicitudes pendientes para poder programar");
					$( "#dialog" ).dialog( "open" );
					
				}else if(cuadrillasarray==null||  cuadrillasarray.length==0){
					
					
					$("#dialog").html("No existe cuadrillas generadas para poder realizar la programación");
					$( "#dialog" ).dialog( "open" );
					
				}else{
					
					//$("#dialog").html("Generando Cuadrillas");

					var solicitudesselect = $("#lstsolicitud").jqGrid('getGridParam','selarrrow');	
					
					if(solicitudesselect!=null && solicitudesselect.length == 0){
						
						$("#dialog").html("Seleccione solictudes para generar grupos de atención");
						$("#dialog").dialog( "open" );
						
					}else{
					
						// obtener los grupos sugeridos
						$.ajax({
							url:"rest/programacion/cantgrupossugeridos.html",
							type:"POST",
							data: {"psolicitudesselect":solicitudesselect.toString()},
							datatype:"json",
							success:function(result){
								
								var cantsugeridos =  0;
								if(result.codigo == 0){
									cantsugeridos =  result.data;
								}
								//$( "#txtnumerogrupos" ).spinner({min: 0, max: 15}).val(cantsugeridos);
								$("#txtnumerogrupos").val(cantsugeridos);
								$("#dglgencuad").dialog("open");
								$("#msgene").html("");
								
								$("#txtnumerogrupos").attr("disabled",true);
									
				      	}});
					}
				}
			});
			
			
			$("#btnejecutar").click(function(){
				
				$("#confirmar").html("¿Está seguro que desea ejecutar programación?");
				$("#confirmar").dialog("open");
				oper="ejecutar"
					
			});
			
			$( "#dialog" ).dialog({
				autoOpen: false,
				width: 400,
				modal: true,
				buttons: [
					{
						text: "Aceptar",
						click: function() {
							
							if(oper == "salir" ||  oper == "ejecutar"){
								window.location.href= "programacion_consulta.xhtml";
							}else{
								oper ="";
								$( this ).dialog( "close" );
							}
						}
					}
				]
			});
			
			
			
			
			
			$( "#confirmar" ).dialog({
				autoOpen: false,
				width: 400,
				modal: true,
				buttons: [
					{
						text: "Si",
						click: function() {
					
							
							if( oper == "confirmgrup"){
								
								
								$("#btnGeneGrup").attr("disabled","disabled");
								$("#btnConfiGrupo").attr("disabled","disabled");
								$( this ).dialog( "close" );
								
								//grupoconfi = true;
								
							}else if(oper =="confirmasig"){
								
								$("#btnAsignar").attr("disabled","disabled");
								$("#btnConfirmarAsignacion").attr("disabled","disabled");
								
								$( this ).dialog( "close" );
								
								
								
							}else if (oper == "asignargrup"){
								asingargrupos()
								//grupoasign = true;
								$( this ).dialog( "close" );
								
							}else if(oper == "guardarasig"){
							
								
								 guardarprogramacion();
								 $( this ).dialog( "close" );
								
								 
								 
							}else if(oper=="reiniprogra"){
								
								$.ajax({
									url:"re	st/programacion/reiniciarprogramacion.html",
									type:"POST",
									datatype:"json",
									success:function(data){
										window.location.reload(true);
										$( this ).dialog( "close" );
									}
								});
								 
								
							}else if(oper=="ejecutar"){
								
								ejecutarprogramacion();
								$( this ).dialog( "close" );
								
							}else if(oper=="salir"){
								
								// guardar
								guardarprogramacion();
								
								
							}
							
						}
					},
					{
						text: "No",
						click: function() {
							
							if(oper=="salir"){
								window.location.href= "programacion_consulta.xhtml";
							}
							
							$( this ).dialog( "close" );
						}
					}
				]
			});
			
			
			$( "#dglgencuad" ).dialog({
				autoOpen: false,
				width: 400,
				modal: true,
				buttons: [
					{
						text: "Aceptar",
						click: function() {
							
							
							var numsugegru = $( "#txtnumerogrupos" ).val();
							var numrealcua =  cuadrillasarray.length;
							if(numrealcua < numsugegru ){
								alert("Solo existen "+numrealcua+ " cuadrillas. El número de grupos sugeridos debe ser menor o igual al numero de cuadrillas" );
								return ;
							}
						 
							var solicitudesselect;
							solicitudesselect = $("#lstsolicitud").jqGrid('getGridParam','selarrrow');		
							loadMap(solicitudesselect, $( "#txtnumerogrupos" ).val()) ;
							$( this ).dialog( "close" );
							$( "#confirmarreasig" ).css({visibility:"hidden"});
							$("#btnGeneGrup").attr("disabled", "disabled");
					
						}
					},
					{
						text: "Cancelar",
						click: function() {
							$( this ).dialog( "close" );
							$( "#confirmarreasig" ).css({visibility:"hidden"});
							$("#msgene").html("");
						}
					}
				]
			});
		
			
			$( "#dialogplan" ).dialog({
				autoOpen: false,
				width: 600,
				modal: true,
				buttons: [
					{
						text: "Salir",
						click: function() {
						
							$( this ).dialog( "close" );
						}
					}
				]
			});
			

			$('a[alt=detaplan]').live('click', function (){
				
				numerocuadrilla  =    $(this).attr("lkcuad");
				descuadrilla  =  $(this).attr("lkdesc");
				desgrupo =  $(this).attr("lkgrupo");
				_color =  $(this).attr("lkcolor");
				
		
				 var $obj = $(".cuadrillas-list  tr").find("div[drcua="+numerocuadrilla+"]");
				 $(".cuadrilla-asig tr").find("div[alt='cuaasig']").html("<p align='center' >"+descuadrilla+"</p>");
				 $(".cuadrilla-asig tr").find("div[alt='gruasig']").html("<p align='center' >"+desgrupo+"</p>");
				 $(".cuadrilla-asig tr").find("div[alt='gruasig']").css({background:_color, opacity:0.35, color: "#000000"});
				 //alert($obj.html());
				
				
				
				$.ajax({
					url:"rest/programacion/detalleplan.html",
					data:{"pnumerocuadrilla": numerocuadrilla},
					type:"POST",
					datatype:"json",
					success:function(data){
						
						$('#lstdetalleplan').jqGrid('clearGridData')
						$('#lstdetalleplan').trigger('reloadGrid');
						
						for(var i in data){	
							$("#lstdetalleplan").jqGrid('addRowData', data[i].numeroSolicitud, data[i]);
						}

						$( "#dialogplan" ).dialog("open");
						
				}});
			
			});
			
			
			//asignacion 
			
			$("#btnAsignar").live("click", function(){
				
				//var grupoconfi = false;
				//var grupoasign = false; 
				
					/*if(grupoconfi==false){
						
						$( "#dialog" ).html("Debe realizar la confirmación de los grupos antes de realizar una asinación ");
						$( "#dialog" ).dialog("open");
						return;
					}*/
				

					oper = "asignargrup"
					$("#confirmar").html("Se realizará la asignación de los grupos. ¿Desea continuar?");
					$( "#confirmar" ).dialog( "open" );
				
			});
			
			
			$("#btnGuardaProg").live("click", function(){
				
			
				/*if( grupoasign==false && accion == ACCION_NUEVA_PROGRAMACION){
					$( "#dialog" ).html("Debe realizar asignaciones");
					$( "#dialog" ).dialog("open");
					return true;
				}*/
				oper = "guardarasig";
				if(accion == ACCION_NUEVA_PROGRAMACION){
					$("#confirmar").html("Se realizará la generación de una nueva programación. ¿Desea continuar?");
				}else if (accion == ACCION_EDITA_PROGRAMACION){
					$("#confirmar").html("Se registraran los cambios realizados. ¿Desea continuar?");
				}
				$( "#confirmar" ).dialog( "open" );
				
	
			});
			
			
			$("#btnReiniProgra").live("click", function(){
				

				
				oper = "reiniprogra";
				$("#confirmar").html("Se reiniciara los cambios realizados para la programación. ¿Desea continuar?");
				$( "#confirmar" ).dialog( "open" );
				
				
			});
		
			$("#btnSalir").live("click", function(){
				
				if($("#progeditada").val()=='true'){
					oper = "salir";
					$("#confirmar").html("Se realizaron cambios en la programación. ¿Desea guardar?");
					$( "#confirmar" ).dialog("open");
				
				}else if($("#proggenerada").val()=='true'){
					oper = "salir";
					$("#confirmar").html("Existe una programación generarada. ¿Desea guardar?");
					$( "#confirmar" ).dialog( "open" );
				}else{
					window.location.href= "programacion_consulta.xhtml";
				}
			});
			
			function confirmargrupos(){
				
			}
			
			function generargrupos(){
								
			}
			
			
			function guardarprogramacion(){
				$.ajax({
					url:"rest/programacion/guardarprogramacion.html",
					type:"POST",
					datatype:"json",
					success:function(data){
						
						$("#dialog").html(data.mensaje);
						$( "#dialog" ).dialog( "open" );
						$("#btnAsignar").attr("disabled","disabled");
						
					}});
			}
			
			
			function ejecutarprogramacion(){
				
				var  np =  $("#numeroprogramacion").val()==""?0:$("#numeroprogramacion").val() ;
				$.ajax({
					url:"rest/programacion/ejecutarprogramacion.html",
					data:{numeroProgramacion:np},
					type:"POST",
					datatype:"json",
					success:function(data){
						
						$("#dialog").html(data.mensaje);
						$( "#dialog" ).dialog( "open" );
						
					}});

			}
			
			
			
			//observacion
			function asingargrupos(){
				
				// asignando cuadrillas a grupos y creando planes de trabajo
				$.ajax({
					url:"rest/programacion/asignarcuadrillas.html",
					type:"POST",
					datatype:"json",
					success:function(data){
						mostrarAsignaciones(data);
						// Cargamos nuevamente las solictudes
						cargarsolicitudes(null);
		      	}});
				
			}
			
			

			console.log("LOAD 1 ");

			$("#lstsolicitud").jqGrid({
				datatype : "local",
				height : 600,
				multiselect: true,
			   	
				/*pgbuttons: false,
			   	pgtext: false,
			   	pginput:false,*/
				
				rowNum: 10000,
				sortable: true,
			   	
			   	
				colNames : ['Solicitud','Solicitud',  'Grupo', 'Prioridad','','','' ],
				colModel : [ 
					{name : 'numeroSolicitud',index : 'numeroSolicitud',width : 0,	sorttype : "int", sortable:false},
					{name : 'tag',index : 'tag',width : 150,	sorttype : "text", align:"right", sortable:true}, 
					{name : 'descripcionGrupo',	index : 'descripcionGrupo',	width : 90,sorttype : "text", sortable:true}, 
					{name : 'prioridad',index : 'prioridad',width : 100, sorttype : "text", sortable:true},
					{name : 'longitud',index : 'longitud',width :0, sortable:false},
					{name : 'latitud',index : 'latitud',width :0, sortable:false},
					{name : 'tipo',index : 'tipo',width :0, sortable:false}
					],
					
					
				caption : "Lista de Solicitudes Pendientes",
				loadComplete: function(data){
					$("#lstsolicitud").hideCol("numeroSolicitud");
					$("#lstsolicitud").hideCol("longitud");
					$("#lstsolicitud").hideCol("latitud");
					$("#lstsolicitud").hideCol("tipo");
				},
			
				onSelectRow: function(id, rowid){
					
					if($("#btnGeneGrup").attr("disabled")==null){
						var row = $("#lstsolicitud").jqGrid('getRowData',id);
						var solicitudesselect = $("#lstsolicitud").jqGrid('getGridParam','selarrrow');						
						//añadiendo marcadores al mapa
						if(!isselected(solicitudesselect, id)){
							removeMark(row.numeroSolicitud);
						}else{
							addMark(row.tag,row.numeroSolicitud, parseFloat(row.latitud), parseFloat(row.longitud),row.tipo );
						}												
					}
			    }
			});
			
			
			
			function isselected(solicitudesselect, id ){
				for (key in  solicitudesselect ){
					if(solicitudesselect[key]==id){
						return true;
					}
				}
				return false;
			}
			
			
			
			$("#lstdetalleplan").jqGrid({
				datatype : "local",
				height : 350,
				autowidth: true,
				colNames : [ 'Orden', '# Solicitud','Tipo' ,'Hora'/*, 'Quitar' */],
				colModel : [ 
					{name : 'numeroSecuencia',index : 'numeroSecuencia',width : 60}, 
					{name : 'tag',	index : 'tag',	width : 120}, 
					{name : 'tipoSolicitud',	index : 'tipoSolicitud',	width : 150}, 
					{name : 'horaAtencion',index : 'horaAtencion',width : 100}/*,
					{name : 'eliminar',index : 'eliminar',width : 100}*/
					],
					
				caption : "Lista de solictudes de servicios"
			});
			
			
			//cambio
			/*$("#lstresulasig").jqGrid({
				datatype : "local",
				height : 100,
				autowidth: true,
				colNames : [ 'Cuadrilla', 'Grupo1','Grupo2' ,'Grupo3', 'Grupo4'],
				colModel : [ 
					{name : 'nombcua',index : 'nombcua',width : 80}, 
					{name : 'grupo1',	index : 'grupo1',	width : 80}, 
					{name : 'grupo2',	index : 'grupo2',	width : 80}, 
					{name : 'grupo3',index : 'grupo3',width : 80},
					{name : 'grupo4',index : 'grupo4',width : 80}
					
					],
				caption : "Grados de asignaciónes"
			});
		*/
			
			
			
			// ######## valore por defecto 
			
		
			
			// cargando solicitudes
			if(accion == ACCION_NUEVA_PROGRAMACION){
				
				limpiarcache();// posteriormente se cargan las solitudes

			}else if(accion == ACCION_EDITA_PROGRAMACION){
				cargarsolicitudes(null);
				
				$("#btnGeneGrup").attr("disabled","disabled");
				$("#btnGuardaProg").attr("disabled","disabled");
				$("#btnAsignar").attr("disabled","disabled");
				
			}
			
			
			

			
            function limpiarcache(){

            	$.ajax({
					url:"re	st/programacion/reiniciarprogramacion.html",
					type:"POST",
					datatype:"json",
					success:function(data){
						cargarsolicitudes(null);
					}
				});

            }

			
			// cargando cuadrillas, grupos asignados y grupos de trabajo
			$.ajax({
				
				url:"rest/programacion/cuadrillas.html",
				type:"POST",
				datatype:"json",
				success:function(data){
					cuadrillasarray = data;
					var $row  =  null;
					// clonamos registro por default
					$(".cuadrillas-list-default").each(function(i, row){
						$row =  $(row).clone(); 
					});
					
					var html ="";
					for( var idx in data){
						
						if($row!=null ){
				
							var numeroCuadrilla =  data[idx].numeroCuadrilla;
							var nombreCuadrilla =  data[idx].nombreCuadrilla;
							$row.find("div[alt='drcua']").attr("id", numeroCuadrilla);
							$row.find("div[alt='drcua']").html(" <p align='center' >"+ numeroCuadrilla +" - "   +nombreCuadrilla   +"</p>");
							
							
							
							var desgrupo = data[idx].descripcionGrupo;
							desgrupo = (desgrupo==null || desgrupo=='')?'&nbsp;':desgrupo;
							$row.find("div[alt='drgrup']").attr("drcua",numeroCuadrilla);
							$row.find("div[alt='drdet']").attr("drcualk",numeroCuadrilla);
							
							$row.find("div[alt='drgrup']").html(" <p align='center' >"+desgrupo+"</p>");
							$row.find("div[alt='drdet']").find("a[alt='detaplan']").attr("lkcuad",numeroCuadrilla);
							$row.find("div[alt='drdet']").find("a[alt='detaplan']").attr("lkdesc",nombreCuadrilla);
							
							
							//$(".cuadrillas-list  tr").find("a[alt='detaplan']").attr("lkgrupo",grupoAtencion.numeroGrupoAtencion);
							
							
							html=html+$row.html();
						}
					}
					
					
					$(".cuadrillas-list").html(html);
					$( ".arrastable" ).draggable({ revert: "invalid" });
					
					if(data!=null){
						
						cantgrpupos =  data.length;
						$( "#txtnumerogrupos" ).spinner({min: 1, max: cantgrpupos
							,spin: function( event, ui ){
								
								var solicitudesselect = $("#lstsolicitud").jqGrid('getGridParam','selarrrow');
								if(solicitudesselect.length < ui.value){
									var html = 	"<span class='ui-icon ui-icon-alert' style='float: left; margin-right: .3em; '></span> <strong>Alerta: Debe seleccionar más solicitudes </strong>";
									$("#msgene").html(html);
									return false;
								}else if (cantgrpupos==ui.value){
									var html = 	"<span class='ui-icon ui-icon-alert' style='float: left; margin-right: .3em; '></span> <strong>Alerta: Debe crear más cuadrillas si desea generar más grupos</strong>";
									$("#msgene").html(html);
								}else{
									$("#msgene").html("");
								}
					         
							 }	
						
					})
						
						
						
					}
					
	      	}});

			
			
			
			

		/*	$.ajax({
				url:"rest/programacion/reasignarsolicitud.html",
				type:"GET",
				data:{numerosoli:numerosoli, numerogrup: numerogrup},
				datatype:"json",
				success:function(grupos){

					pintarmapa(grupos);
					      

	      	}});*/
			
			
		/*	
			for (var i = 0; i <= lstcuadrillas.length; i++)
				$("#lstcuadrillas").jqGrid('addRowData', i + 1, lstcuadrillas[i]);*/
		
			});



// muestra las
function mostrarAsignaciones( data){
	// limpiar 
	$(".cuadrillas-list  tr").find("div[alt='drgrup']").html("<p align='center' >&nbsp;</p>");
	$(".cuadrillas-list  tr").find("div[alt='drgrup']").css({background:"", opacity:"", color: ""});

	// asignacion de grupos 
	for(var i in data){						
		 var ncua =	data[i].cuadrilla.numeroCuadrilla;
		 var $obj = $(".cuadrillas-list  tr").find("div[drcua="+ncua+"]");
		 var $objlk = $(".cuadrillas-list  tr").find("div[drcualk="+ncua+"]");
		 
		 
		 if($obj!=null){
			 var grupoAtencion =  data[i].grupoAtencion
			 if(grupoAtencion!=null){
				
				//var _html =  "<div class='back' >&nbsp;</div>";
				//_html +=  "<div> <p drgrup='"+grupoAtencion.numeroGrupoAtencion+"'  align='center' >"+data[i].grupoAtencion.descripcion+ "</p> </div>"
				///alert(_html);
				 
				var _html =  " <p drgrup='"+grupoAtencion.numeroGrupoAtencion+"'  align='center' >"+data[i].grupoAtencion.descripcion+ "</p> "
			 	$obj.html(_html);
				//$obj.find("div[class='back']").css({background:grupoAtencion.color, opacity:0.35, color: "#000000" });
				//$obj.css({background:grupoAtencion.color, opacity:0.35, color: "#000000",  filter: "alpha(opacity=80)"; });
				$obj.css({background:grupoAtencion.color, opacity:0.35,filter:"alpha(opacity=35)", "z-index": "1000", color: "#000"});
				//$obj.addClass("ui-widget");
				
				$objlk.find("a[alt='detaplan']").attr("lkgrupo",grupoAtencion.descripcion);
				$objlk.find("a[alt='detaplan']").attr("lkcolor",grupoAtencion.color);
				

				
		 	 }
		 }
	}
}

// asignarcuadrilla


function asisgnarcuadrilla(){
	
	console.log("###  asisgnarcuadrilla  ### ");

	console.log(" cuadrilla seleccionado!! :"+$("#cdaselected").val());
	var cdaselected = $("#cdaselected").val();
	
	if(cdaselected==null || $.trim(cdaselected) == ""){
		return;
	}
	var $grupfound;
	var idxasig = null;
	var idxgrup = null;
	var $obj = null;
	
	$(".cuadrillas-list  tr").each(function(i, row){
		 var $row = $(row);
		 
		 // buscando cuadrilla a asignar
		 $obj = $row.find('div[drcua ='+cdaselected+' ]');
		 //console.log($obj );
		 if($obj!=null){
			 
			 idxasig =  i;
			 
			 
	    	   
	    	    
			var gruptarget = $("#grupotarget").val();
			$grupfound = $(".cuadrillas-list  tr").find('p[drgrup='+gruptarget+']');
			
			$("#cdaselected").val("");	
			var _ngrupo =   $("#ngrupotarget").val();
			console.log("ncuadrilla :"+cdaselected);
			console.log("grupo :"+_ngrupo);
			$("#switch1").val('true');
			
			if(cdaselected!=null && $.trim(cdaselected)!="" && _ngrupo!=null &&  $.trim(_ngrupo)!=""){
				
					$.ajax({
						url:"rest/programacion/reasignarcuadrillas.html",
						data:{ncuadrilla:cdaselected,ngrupo:_ngrupo},
						type:"GET",
						datatype:"json",
						success:function(data){
							
							if(data!=null && data.length>0){
							
								mostrarAsignaciones(data);
								cargarsolicitudes(null);
								$("#progeditada").val(true);
								console.log("### EDITANDO PLAN");
							}
							
						},
					    error: function (request, status, error) {
					    	
					    }
					});
				
			}
				
				
			return false;
		 }
		
	}) ;
	
    cdaselected = null;
	$("#cdaselected").val("");
}



function cargarsolicitudes(grupos){
	
	$("#lstsolicitud").jqGrid("clearGridData", true).trigger("reloadGrid");
	
	
	$.ajax({
		url:"rest/programacion/solicitudespendientes.html",
		type:"POST",
		datatype:"json",
		success:function(data){
			//alert("datos "+data);
			solicitudarray = data

			for (var i = 0; i <= data.length-1; i++){
				$("#lstsolicitud").jqGrid('addRowData', data[i].numeroSolicitud, data[i]);
				//$("#lstsolicitud").jqGrid('setSelection',data[i].numeroSolicitud);
			}
			
			
			
			//seleccionamos las solicitudes que estan programadas
			if(grupos!=null){
				mostrarsolicitudesprogramadas(grupos)	
			}

			
  	}});
}



function mostrarsolicitudesprogramadas(grupos){
	
	console.log("MOSTRANDO SOLICTUDES PROGRAMADAS ");
	//console.log(grupos);
	//console.log(grupos);

	for (var i=0;i< grupos.length;i++) {
		var g   = grupos[i];
 		for (var j in g.detalles) {
			d =  g.detalles[j];
			$('#lstsolicitud').jqGrid('setSelection',d.numeroSolicitud); 
		}
	}

	console.log("RESETANDO LOS PARAMETROS  y columnas 1");
	//$("#lstsolicitud").jqGrid('setGridParam', {datatype : "local",height : 600,multiselect: true,rowNum: 10000,sortable: true,multiselect: false})
	/*
	 
	 					{name : 'tag',index : 'tag',width : 150,	sorttype : "text", align:"right", sortable:true}, 
					{name : 'descripcionGrupo',	index : 'descripcionGrupo',	width : 90,sorttype : "text", sortable:true}, 
					{name : 'prioridad',index : 'prioridad',width : 100, sorttype : "text", sortable:true},
	 
	 * */
	$("#lstsolicitud").jqGrid('setGridParam', {multiselect: false, sortable: false})
	$('#lstsolicitud').setColProp('tag', {sortable: false});
	$('#lstsolicitud').setColProp('descripcionGrupo', {sortable: false});
	$('#lstsolicitud').setColProp('prioridad', {sortable: false});
	$('input[class=cbox]').attr("disabled","disabled");
	
	//$("#lstsolicitud").trigger("reloadGrid");
	
}








