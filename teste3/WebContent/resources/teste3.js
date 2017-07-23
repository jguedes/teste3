/**
 * 
 */
angular.module("Teste3App", [])
	.value("urlBase", "http://localhost:8080/teste3/rest/")
	.controller("Teste3Controller", function($http,urlBase) {//dizer ao controller que vai usuar o service $http do angular
		var self = this;
		self.usuario = "João Guedes";
		self.tipo = undefined;
		self.gerConta = undefined;
		self.gerMovs = undefined;
		self.conta = undefined; //objeto vazio
		self.contas = [];//array vazio
		self.contaSelecionada = undefined;
		self.salvar = function() {
			var metodo = !self.conta.id ? 'POST': 'PUT';
			if(!self.conta.tipo){
				self.conta.tipo = self.tipo.value;
			}
			$http({
				method: metodo,
				url: urlBase + "contas/",
				data: self.conta
			}).then(function successCallback(response){
				self.atualizarTabela();
			}, function errorCallback(response) {
				self.ocorreuErro("salvar");
			});
		};
		self.cancelar = function() {
			
		};
		self.nova = function() {
			self.conta = {};//objeto nao vazio
		};
		self.alterar = function(conta) {
			self.conta = conta;//objeto carregado
		};
		self.excluir = function(conta) {
			self.conta = conta;
			
			$http({
				method: "DELETE",
				url: urlBase + "contas/" + self.conta.id + "/"
			}).then(function successCallback(response){
				self.atualizarTabela();
			}, function errorCallback(response) {
				self.ocorreuErro("excluir");
			});
		};
		self.concluir = function() {
			alert("TO DO");
		};
		self.atualizarTabela = function() {
			$http({
				method: "GET",
				url: urlBase + "contas/listar/" + self.tipo.value + "/"
			}).then(function successCallback(response){
				self.contas = response.data;
				self.conta = undefined;
			}, function errorCallback(response) {
				self.ocorreuErro("atualizarTabela");
			});
		};
		self.alterarTipo = function() {
			self.tipo.nome = self.tipo.value == 0 ? 'ENTRADA' : 'SAÍDA';
			self.tipo.bkgColor = self.tipo.value == 0 ? 'SkyBlue' : 'yellow';
			self.carregarAmbiente();
		};
		self.ocorreuErro = function(funcao) {
			alert("Ocorreu erro ao tentar " + funcao);
		};
		self.gerenciarAmbiente = function(ambiente) {
			if(ambiente == "gerConta"){
				self.gerConta = {};
				self.gerMovs = undefined;
			}else if(ambiente == "gerMovs"){
				self.gerConta = undefined;
				self.gerMovs = {};
			}
		};
		self.carregarAmbiente = function() {
			if(self.gerConta){
				self.conta = undefined;
			}else if(self.gerMovs){
				self.contaSelecionada = undefined;
			}
			self.atualizarTabela();
		};
		self.activate = function() {
			self.tipo = {};
			self.tipo.value = 0;
			self.gerenciarAmbiente("gerConta");
			self.alterarTipo();
		};
		self.activate();//abrir a pagina ja com a tabela carregada
		
});