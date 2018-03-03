Ext.define('App.Application', {
    extend: 'Ext.app.Application',

    name: 'App',

    stores: [
        'Accounts', 'ClaimStatuses', 'Claims'
    ],

    launch: function () {
        // TODO - Launch the application
    },

    onAppUpdate: function () {
        Ext.Msg.confirm('Application Update',
            'This application has an update, reload?',
            function (choice) {
                if (choice === 'yes') {
                    window.location.reload();
                }
            }
        );
    }
});