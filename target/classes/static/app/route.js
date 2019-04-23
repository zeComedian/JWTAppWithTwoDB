angular.module('JWTDemoApp').config(function ($stateProvider, $urlRouterProvider) {

    // the ui router will redirect if a invalid state has come.
    $urlRouterProvider.otherwise('/page-not-found');
    // parent view - navigation state
    $stateProvider.state('nav', {
        abstract: true,
        url: '',
        views: {
            'nav@': {
                templateUrl: 'app/views/nav.html',
                controller: 'NavController'
            }
        }
    }).state('login', {
        parent: 'nav',
        url: '/login',
        views: {
            'content@': {
                templateUrl: 'app/views/login.html',
                controller: 'LoginController'
            }
        }
    }).state('users', {
        parent: 'nav',
        url: '/users',
        data: {
            role: 'ADMIN'
        },
        views: {
            'content@': {
                templateUrl: 'app/views/users.html',
                controller: 'UsersController',
            }
        }
    }).state('home', {
        parent: 'nav',
        url: '/',
        views: {
            'content@': {
                templateUrl: 'app/views/home.html',
                controller: 'HomeController'
            }
        }
    }).state('page-not-found', {
        parent: 'nav',
        url: '/page-not-found',
        views: {
            'content@': {
                templateUrl: 'app/views/page-not-found.html',
                controller: 'PageNotFoundController'
            }
        }
    }).state('access-denied', {
        parent: 'nav',
        url: '/access-denied',
        views: {
            'content@': {
                templateUrl: 'app/views/access-denied.html',
                controller: 'AccessDeniedController'
            }
        }
    }).state('upload', {
        parent: 'nav',
        url: '/upload',
        views: {
            'content@': {
                templateUrl: 'app/views/upload.html',
                controller: 'UploadController'
            }
        }
    }).state('messages', {
        parent: 'nav',
        url: '/messages',
        data: {
            role: 'ADMIN'
        },
        views: {
            'content@': {
                templateUrl: 'app/views/messages.html',
                controller: 'MyOwnController'
            }
        }
    }).state('register', {
        parent: 'nav',
        url: '/register',
        data: {
            role: 'ADMIN'
        },
        views: {
            'content@': {
                templateUrl: 'app/views/register.html',
                controller: 'RegisterController'
            }
        }
    });
});
