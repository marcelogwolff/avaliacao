angular.module("cadastroClientes", ["ngMessages"]);
angular.module("cadastroClientes").controller("cadastroClientesCtrl", function ($scope, $http) {
	$scope.app = "Cadastro de clientes";
	$scope.contatos = [];
	$scope.operadoras = [];

	var carregarContatos = function () {
		$http.get("http://localhost:8080/clientes/cliente").success(function (data) {
			$scope.contatos = data;
		}).error(function (data, status) {
			$scope.message = "Aconteceu um problema: " + data;
		});
	};

	$scope.adicionarContato = function (contato) {
		console.log($scope.contato);
		$http.post("http://localhost:8080/clientes/cliente", contato).success(function (data) {
			delete $scope.contato;
			$scope.contatoForm.$setPristine();
			carregarContatos();
		});
	};
	$scope.apagarContatos = function (contatos) {
		$scope.contatos = contatos.filter(function (contato) {
			if (!contato.selecionado) return contato;
			$http.delete("http://localhost:8080/clientes/cliente/"+  contato.id).success(function (data) {
				delete $scope.contato;
				$scope.contatoForm.$setPristine();
				carregarContatos();
			})
		});
	};
	$scope.isContatoSelecionado = function (contatos) {
		return contatos.some(function (contato) {
			return contato.selecionado;
		});
	};
	$scope.ordenarPor = function (campo) {
		$scope.criterioDeOrdenacao = campo;
		$scope.direcaoDaOrdenacao = !$scope.direcaoDaOrdenacao;
	};

	carregarContatos();
});