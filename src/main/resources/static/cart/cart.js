angular.module('app-front').controller('cartProductController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/app/api/v1/';
let a;
let totalPagesCart;
  $scope.loadProducts = function(pageIndex = 1){
               currentPageIndex = pageIndex;
               $http({
               url: contextPath + 'products/cart',
               method: 'GET',
              params: {
             p: pageIndex
           }
       }).then(function(response) {
            console.log(response);
          $scope.productsCart = response.data;
          a = response.data.content;
          totalPagesCart = $scope.productsCart.totalPages;
         $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.productsCart.totalPages);
         $scope.itog();
        });
  }
 $scope.itog = function(){
       $http.get(contextPath + 'products/cart/sum')
          .then(function successCallback(response) {
           console.log(response.data);
           if(currentPageIndex == totalPagesCart && response.data != 0){
           document.getElementById('trItog').style.display = 'table-row';
            document.getElementById("sumItog").innerHTML = response.data;
           }else{
             document.getElementById('trItog').style.display = 'none';
           }
     }, function failCallback(response) {
                alert(response.data.messages);
       });
 }

     $scope.deleteProduct = function (productId) {
         $http.delete(contextPath + 'products/cart/'+ productId)
                     .then(function successCallback(response) {
                         $scope.productsCart = response.data;
                         $scope.loadProducts(currentPageIndex);
                         }, function failCallback(response) {
                              alert(response.data.messages);
                     });
     }

     $scope.deleteProductAll = function() {
      $http.delete(contextPath + 'products/cart')
                          .then(function successCallback(response) {
                              $scope.productsCart = response.data;
                               $location.path('store');
                            //  $scope.loadProducts(currentPageIndex);
                              }, function failCallback(response) {
                                   alert(response.data.messages);
                          });
     }


     $scope.generatePagesIndexes = function (startPage, endPage) {
                     let arr = [];
                     for (let i = startPage; i < endPage + 1; i++) {
                         arr.push(i);
                     }
                     return arr;
                 }

      $scope.nextPage = function () {
                     currentPageIndex++;
                     if (currentPageIndex > $scope.productsCart.totalPages) {
                         currentPageIndex = $scope.productsCart.totalPages;
                     }
                     $scope.loadProducts(currentPageIndex);
      }

      $scope.prevPage = function () {
                     currentPageIndex--;
                     if (currentPageIndex < 1) {
                         currentPageIndex = 1;
                     }
                     $scope.loadProducts(currentPageIndex);
      }

      $scope.loadProducts();

});