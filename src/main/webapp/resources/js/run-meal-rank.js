///////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Defines the javascript files that need to be loaded and their dependencies.
//
///////////////////////////////////////////////////////////////////////////////////////////////////////////

require.config({
	waitSeconds: 200,
    paths: {
        angular: '../bower_components/angular/angular',
        angularMessages: '../bower_components/angular-messages/angular-messages',
        angularRoute: '../bower_components/angular-route/angular-route',
        angularAnimate: '../bower_components/angular-animate/angular-animate',
        angularFancyModal: '../bower_components/angular-fancy-modal/dist/angular-fancy-modal',
        angularDropdowns: '../bower_components/angular-dropdowns/dist/angular-dropdowns',
        d3: '../bower_components/d3/d3.min',
        nvd3: '../bower_components/nvd3/build/nv.d3.min',
        angularjsNvd3Directives: '../bower_components/angularjs-nvd3-directives/dist/angularjs-nvd3-directives',
        angularGoogleChart: '../bower_components/angular-google-chart/ng-google-chart',

        ngFileUpload: '../bower_components/ng-file-upload/ng-file-upload',
        ngFileUploadShim: '../bower_components/ng-file-upload/ng-file-upload-shim',
        
        csrfInterceptor: '../bower_components/spring-security-csrf-token-interceptor/dist/spring-security-csrf-token-interceptor.min',
        lodash: "../bower_components/lodash/dist/lodash",
        mealList: '../public/js/meal-list',        
        frontendServices: 'frontend-services',
        angularDatepicker: 'angular-datepicker',
        exif: '../bower_components/exif-js/exif',
        
        mealRankApp: "meal-rank-app"
    },
    shim: {
        angular: {
            exports: "angular"
        },
        csrfInterceptor: {
            deps: ['angular']
        },
        angularMessages: {
            deps: ['angular']
        },
        angularRoute: {
            deps: ['angular']
        },
        angularAnimate: {
            deps: ['angular']
        },
        angularFancyModal: {
            deps: ['angular']
        },
        angularDropdowns: {
            deps: ['angular']
        },
        d3:{
        	deps: ['angular']
        },
        nvd3:{
        	deps: ['angular', 'd3']
        },
        angularjsNvd3Directives: {
            deps: ['angular', 'd3', 'nvd3']
        },
        ngFileUpload:{
        	deps: ['angular']
        },
        ngFileUploadShim:{
        	deps: ['angular']
        },        
        frontendServices: {
            deps: ['angular', 'lodash', 'csrfInterceptor']
        },
        angularDatepicker: {
            deps: ['angular', 'lodash']
        },
        angularGoogleChart:{
        	deps: ['angular']
        },

        mealList: {
            deps: [ 'lodash', 'angular', 'angularMessages', 'angularRoute', 'angularAnimate', 'frontendServices']
        },
        
        mealRankApp: {
            deps: [ 'lodash', 'angular', 'angularGoogleChart', 'exif', 'angularjsNvd3Directives', 'angularDropdowns', 'ngFileUpload', 'ngFileUploadShim', 'angularMessages', 'angularRoute', 'angularAnimate', 'angularDatepicker', 'angularFancyModal', 'frontendServices', 'mealList']
        }
    }
});

require(['mealRankApp'], function () {

    angular.bootstrap(document.getElementById('mealRankApp'), ['ngRoute', 'googlechart', 'ngAnimate', 'nvd3ChartDirectives', 'vesparny.fancyModal', 'ngDropdowns', 'ngFileUpload', 'mealRankApp', 'angular-datepicker']);

});