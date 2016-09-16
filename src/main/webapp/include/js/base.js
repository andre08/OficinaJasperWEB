angular.module("ListaClientes", []);
angular.module("ListaClientes").controller("ListaClientesCtrl", function ($scope, $http) {

    $scope.clientes = [];

    var carregarClientes = function () {
        $http.get("http://localhost:8080/OficinaJasperWeb/rest/cliente/").success(function (data, status) {
            $scope.clientes = data;
        });
    };

    $scope.adicionarCliente = function (cliente) {

        if (!cliente.id) {

            $http({
                method: 'POST',
                url: "http://localhost:8080/OficinaJasperWeb/rest/cliente/",
                data: cliente
            }).then(function successCallBack(response) {
                carregarClientes();
                $scope.limparCliente();
            }, function erroCallBack(response) {
                alert('Erro ao salvar a cliente!');
            });
            
        } else {

            $http({
                method: 'PUT',
                url: "http://localhost:8080/OficinaJasperWeb/rest/cliente/",
                data: cliente
            }).then(function successCallBack(response) {
                carregarClientes();
                $scope.limparCliente();
            }, function erroCallBack(response) {
                alert('Erro ao salvar a cliente!');
            });
        }


    };

    $scope.apagarCliente = function (cliente) {
        $http({
            method: 'DELETE',
            url: "http://localhost:8080/OficinaJasperWeb/rest/cliente/" + cliente.id
        }).then(function successCallBack(response) {
            carregarCliente();
            $scope.limparCliente();
        }, function erroCallBack(response) {
            alert('Erro ao excluir a cliente!');
        });
    };

    $scope.editarCliente = function (cliente) {
        $http.get("http://localhost:8080/OficinaJasperWeb/rest/cliente/" + cliente.id).success(function (data, status) {
            $scope.cliente = data;
        });
    };

    $scope.limparCliente = function () {
        delete $scope.cliente;
    };

    $scope.imprimirCliente = function (cliente) {
            alert('http://localhost:8080/OficinaJasperWeb/rest/cliente/print/' + cliente.id);
        $http({
            method: 'GET',
            url: "http://localhost:8080/OficinaJasperWeb/rest/cliente/print/" + cliente.id
        }).then(function successCallBack(response) {
            window.open(response.toString());
        }, function erroCallBack(response) {
            alert('Erro ao imprimir o cliente!');
        });
    };

    carregarClientes();
});