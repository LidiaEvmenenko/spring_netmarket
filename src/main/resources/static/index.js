(function () {
    angular
        .module('app-front', ['ngRoute','ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',

                controller: 'welcomeController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/edit_product/:productId', {
                templateUrl: 'edit_product/edit_product.html',
                controller: 'editProductController'
            })
            .when('/create_product', {
                templateUrl: 'create_product/create_product.html',
                controller: 'createProductController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartProductController'
            })
            .when('/auth', {
                templateUrl: 'auth/auth.html',
                controller: 'authProductController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.webMarketUser) {//если в локальном хранилище есть юзер, то он восстанавливается при входе
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.webMarketUser.token;
        }
    }
})();

angular.module('app-front').controller('indexController', function ($rootScope, $scope, $http, $localStorage, $location) {
    const contextPath = 'http://localhost:8189/app/api/v1/';

var aaa = document.getElementById("userName");
var stompClient = null;

window.onload = function() {
connect();
}
window.onclose = function() {
disconnect();
}
function setConnected(connected){
   if(connected){
      $("#conversation").show();
   }else{
      $("#conversation").hide();
   }
   $("#greetings").html("");
}
function connect(){
   var socket = new SockJS('/gs-guide-websocket');
   stompClient = Stomp.over(socket);
   stompClient.connect({}, function(frame) {
   setConnected(true);
   stompClient.subscribe('/topic/greetings', function(greeting){
      showGreeting(JSON.parse(greeting.body).content);
   });
   });
}
function showGreeting(message){
    let a = 'http://';
    for(let i=0; i<message.length-1; i++){
       a += message[i] + '/';
    }
    a += message[message.length-1];
   $("#greetings").append("<tr><td>" + a + "</td></tr>");
}

function disconnect(){
   if (stompClient != null){
      stompClient.disconnect();
   }
   setConnected(false);
}

   $scope.tryToAuth = function () {

        $http.post(contextPath + 'auth', $scope.user)
            .then(function successCallback(response) {
            console.log("response.data.token = "+response.data.token);

                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.webMarketUser = {username: $scope.user.username, token: response.data.token};

                    aaa.value = $scope.user.username;
                    $scope.user.username = null;
                    $scope.user.password = null;
                  //  alert("Вошли");


                }
            }, function errorCallback(response) {
             alert(response.data.messages);
            });
    };

    $scope.tryToLogout = function () {
console.log("$scope.user = "+$scope.user);
//        if ($scope.user.username) {
//            $scope.user.username = null;
//        }
//        if ($scope.user.password) {
//            $scope.user.password = null;
//        }
        $scope.clearUser();
        aaa.value = '';
        $location.path('welcome');
    };

    $scope.clearUser = function () {
        delete $localStorage.webMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.webMarketUser) {
            return true;
        } else {
            return false;
        }
    };
});