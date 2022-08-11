angular.module('app-front').controller('createProductController', function ($rootScope, $scope, $http, $location) {
    const contextPath = 'http://localhost:8189/app/api/v1/';

var objSel = document.getElementById("selectCategory");

     $scope.createNewProduct = function () {
         if($scope.new_product == null){
           alert("Заполните форму!!!");
           return;
         }
         if ( objSel.selectedIndex != -1) {
           $scope.new_product.categoryTitle = objSel.options[objSel.selectedIndex].value;
         }
         $http.post(contextPath + 'products', $scope.new_product)
            .then(function successCallback(response) {
                $scope.new_product = null;
                alert("Продукт создан!!!");
                $location.path('/scope');
            }, function failCallback(response) {
                alert(response.data.messages);
            }
            );
     }
     $scope.loadCategory = function(){
           $http({
              url: contextPath + 'category',
              method: 'GET',

           }).then(function(response) {


           objSel.options.length=1;
           for (let i = 0; i < response.data.length; i++) {
               objSel.options[i+1] = new Option(response.data[i].title, response.data[i].title);
           }

          //    $scope.categoryList = response.data;
//console.log("categoryList.length = "+$scope.categoryList.length);
//console.log("response.data.length = "+response.data[1].title);
           });
           }

      $scope.loadCategory();
});