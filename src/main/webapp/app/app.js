var rootService = 'service/rest/';
Ext.application({
    name: 'App',
    extend: 'App.Application',
    requires: [
        'App.view.main.Main'
    ],
    mainView: 'App.view.main.Main'
});