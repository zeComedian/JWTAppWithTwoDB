angular.module('JWTDemoApp')
// Creating the Angular Controller
    .controller('UploadController', function ($http, $scope, AuthService) {
        $scope.upload = function () {
            var file = document.getElementById('file').files[0];
            var fd = new FormData();
            fd.append('text', $scope.text);
            fd.append('email', $scope.email);
            fd.append('file', file);
            console.log(fd);
            $http.post('api/upload', fd
                ,{
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
            }
            )
                .success(function (res) {

                //$scope.text = null;
           //     $scope.file = null;
                $scope.message = "Upload successfull !";
            }).error(function (error) {
                $scope.message = error.message;
            });
        };
    });