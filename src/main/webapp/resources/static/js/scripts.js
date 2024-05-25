let id = null;
let hideMsg;
$(document).ready(function() {

//Requisição Inicail inicio do site
	$.ajax({
		url: "/Aps/controller",
		data: "opc=0",
		method: "GET",
		dataType: "text",
		statusCode: {
			404: function() {
				alert("Pagina não Encontrada");
			},
			500: function() {
				alert("Erro Code");
			}

		},
		error: function() {
			console.log("Erro Inicio");
		}
	}).done(function(e) {
		buscaMediaMetas();
		$("#IdBodyTable").html(e);
	});
// fim requisição inicial

//Modal Adicionar Meta Mensal
	$("#modalAddBtn").click(() => {
		let modal = new bootstrap.Modal("#modalAdd", focus = false);
		$("#btnPrincipal").html("Adicionar");
		$("#modalAddLabel").html("Adicionar Meta");
		$("#btnAlterar").css("display", "none")
		$("#btnAdicionar").css("display", "block")
		modal.show('slow');
	});
//Fim Modal

//Buscar Metas por Mes e Meta
	$("#btnBusca").click(() => {
		var mes;
		var meta;
		if ($("#buscaMes ").find(":selected").val()) mes = $("#buscaMes ").find(":selected").val()

		if ($("#buscaMeta ").find(":selected").val()) meta = $("#buscaMeta ").find(":selected").val();

		var sertializeData = "opc=4&mes=" + mes + "&meta=" + meta;

		enviaRequisicao(sertializeData)
	});
//Fim Meta

//Botão Adicionar tratamento
	$('#btnAdicionar').click(function(e) {
		enviaRequisicao($("#form").serialize() + "&opc=1")
	});
//Fim Botão add

//Botão Alternr tratamento
	$('#btnAlterar').click(function(e) {

		enviaRequisicao($("#form").serialize() + "$opc=2")
	});
//Fim Botão Alter

//Limpar busca
	$("#btnLimparBusca").click(() => {
		var mes;
		var meta;
		$("#buscaMes").empty();
		$("#buscaMeta ").empty();

		var sertializeData = "opc=4&mes=" + mes + "&meta=" + meta;

		enviaRequisicao(sertializeData)
	});
//fim busca

//Trocar SubTelas
	$("#btnTrocarTela").click(()=>{
		$("#divTable").toggle()
		$("#divGrafico").toggle()
		
		if($("#divTable").is( ":visible" )){
			$("#btnTrocarTela").html("Ver Grafico");
		}else{
			atualizarGrafico();
			$("#btnTrocarTela").html("Ver Tabela");
		}
	});
//Fim subTelas

});



function buscaMediaMetas() {
	$.ajax({
		url: "/Aps/controller",
		data: "opc=5",
		method: "GET",
		dataType: "text",
		statusCode: {
			404: function() {
				alert("Pagina não Encontrada");
			},
			500: function() {
				alert("Erro Code");
			}

		},
	}).done(function(e) {
		infosInJson(e);
		tdColorida();
	});
	
	
}


function infosInJson(e) {
	const json = JSON.parse(e);
	
	$("#objAmbiental").html(json.Ambiental.Medias);
	$("#objMesAmbiental").html(json.Ambiental.Meses);

	$("#objSocial").html(json.Social.Medias);
	$("#objMesSocial").html(json.Social.Meses);

	$("#inpMetaAmb").val(json.Ambiental.valor + "%");
	$("#inpMetasoc").val(json.Social.valor + "%");

	
	atualizarGrafico()

}
function atualizarGrafico(){	
	novosGraficos("opc=8");
	$("#img1").attr("\\dynamic\\ambiental.png");
	$("#img2").attr("\\dynamic\\social.png");

}

function novosGraficos(data) {

	$.ajax({
		url: "/Aps/controller",
		data: data,
		method: "POST",	
		dataType: "text",
		statusCode: {
			404: function() {
				alert("Pagina não Encontrada");
			},
			500: function() {
				alert("Erro Code");
			}

		},
		error: function() {
			console.log("Erros Novas Metas");
		}
	})
}

function alterMedias() {
	var valoramb = $("#inpMetaAmb").val().replace("%", "");
	var valorsoc = $("#inpMetasoc").val().replace("%", "");

	var amb = window.prompt("Digite o novo valor para a meta Ambiental", valoramb);
	var soc = window.prompt("Digite o novo valor para a meta Social", valorsoc);

	$("#inpMetaAmb").val(amb + "%");
	$("#inpMetasoc").val(soc + "%");

	novasMetas("opc=7&a=" + amb + "&s=" + soc);
}


function novasMetas(data) {

	$.ajax({
		url: "/Aps/controller",
		data: data,
		method: "POST",
		dataType: "text",
		statusCode: {
			404: function() {
				alert("Pagina não Encontrada");
			},
			500: function() {
				alert("Erro Code");
			}

		},
		error: function() {
			console.log("Erros Novas Metas");
		}
	}).done(function(e) {
		console.log(e)
	});
}

function hideModal() {
	$("#errorModal").toggle(5000);
	clearTimeout(this.hideMsg);
}

function clickBtnAlter(e) {
	$("#btnAlterar").css("display", "block")
	$("#btnAdicionar").css("display", "none")
	modelAlter(e.value)
}
function clickBtnDelete(e) {
	serializeData = "id=" + e.value + "&opc=3";

	enviaRequisicao(serializeData)
}

function tdColorida(){
	var linha =$("tr.linetable > td[name='value']");
	
	var valoramb = $("#inpMetaAmb").val().replace("%", "") / 12;
	var valorsoc = $("#inpMetasoc").val().replace("%", "") / 12;
	
	
	if (linha.lenght != 0){
		
		$.each(linha, function(k,v){
		var tr= $("#tr-"+k+" > td[name='meta']")
		
		if(tr[0].textContent == "Ambiental"){
			if(v.textContent<= valoramb){
				$(this).toggleClass("text-danger");
			}else{
				$(this).toggleClass("text-sucess");
			}
		}
		
		if(tr[0].textContent == "Social"){
			if(v.textContent<= valorsoc){
				$(this).toggleClass("text-bg-danger");
			}
			else{
				$(this).toggleClass("text-sucess");
			}
		}
		
	});
	}
}

function modelAlter(e) {

	let subId = "#tr-" + e + " td";
	this.id = e;
	let linha = $(subId);

	$("#mes option:contains(" + linha[1].textContent + ")").attr('selected', true);
	$("#radio" + linha[0].textContent).prop("checked", true)
	$("#value").val(linha[2].textContent);
	$("#btnPrincipal").html("Alterar");
	let modal = new bootstrap.Modal("#modalAdd", focus = false);
	$("#opc").val(2);

	modal.show('slow');
}


function enviaRequisicao(data) {
	let dt = data;

	if (this.id != null) {
		dt += "&id=" + this.id;
	}
	
console.log(dt);

	$.ajax({
		url: "/Aps/controller",
		data: dt,
		method: "POST",
		dataType: "html",
		error: function(data) {
			console.log("error" + data);
		},
		statusCode: {
			404: function() {
				alert("Recarregue a pagina");
			}
		},
	}).done(function(e) {
		if (e == "1" || e == "2") {
			$("#errorModal").toggle();
			$("#form").trigger("reset");
			this.hideMsg = setTimeout(hideModal, 5000)
		} else if (e == "3") {
			$("#errormsg").html("Erro ao Deletar");
			$("#errorPrincipal").toggle();
			$("#form").trigger("reset");
			this.hideMsg = setTimeout(hideModal, 5000)
		}
		else {
			$("#IdBodyTable").html(e);
		}


		buscaMediaMetas();
	});



}