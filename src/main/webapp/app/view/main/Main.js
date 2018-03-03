Ext.define('App.view.main.Main', {
    extend: 'Ext.container.Viewport',
    xtype: 'app-main',

    requires: [
        'Ext.plugin.Viewport',
        'Ext.window.MessageBox',
        'Ext.grid.plugin.RowEditing',
        'Ext.form.ComboBox',
        'Ext.grid.*',
        'App.view.main.ClaimForm',
        'App.view.main.ClaimList',
        'App.view.main.MainController',
        'App.view.main.MainModel'
    ],

    controller: 'main',
    viewModel: 'main',

    defaults: {
        bodyPadding: 10,
    },
    layout: {
        type: 'hbox',
        align: 'stretch'
    },
    items: [
        {
            title: 'Редактор заявок',
            flex: 1,
            layout: {
                type: 'hbox',
                align: 'stretch'
            },
            items: [
                {
                    itemId: 'form',
                    xtype: 'writerform',
                    manageHeight: false,
                    margin: '0 0 0 0'
                }, {
                    imemId: 'grid',
                    title: 'Список заявок',
                    flex: 1,
                    xtype: 'writergrid'
                }]
        }]
});