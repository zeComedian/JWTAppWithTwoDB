angular.module('JWTDemoApp')
// Creating the Angular Controller
    .controller('MyOwnController', function ($http, $scope, AuthService) {

        console.log('111');
        $http({
            method : "GET",
            url : "api/messages"
        }).then(function mySuccess(response) {
            $scope.userMsg = []
            console.log(response);
            $scope.userMsg.push(response.data);
            }).catch(function (error) {
                $scope.message = error.message;
            });


        // var init = function () {
        //     $http.get('api/messages').success(function (res) {
        //         $scope.msg = res;
        //         console.log(res);
        //     }).error(function (error) {
        //         $scope.message = error.message;
        //     });
        // };
    });