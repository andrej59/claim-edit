Ext.define('App.view.main.MainController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.main',

    requires: [
        'App.model.Claim'
    ],

    onCreateItem: function (sender, record) {
        console.debug("onCreateItem");
        console.debug('sender');
        var claimStore = Ext.getStore('claimStore');
        if (sender.itemId = 'form') {
            claimStore.insert(0, record);
        } else {
            var claim = new App.model.Claim();
            claim.name = 'Новая заявка';
            claimStore.insert(0, claim);
        }
    },

    onCancelItem: function (sender, record) {
        console.debug("onCancelItem");
        //var claimStore = Ext.getStore('claimStore');
        //claimStore.reset();
    },

    onItemSelected: function (sender, record) {
        //console.debug(record);
        //Ext.Msg.confirm('Confirm', 'Are you sure?', 'onConfirm', this);
    },

    onItemChange: function (sender, record) {
        var claimForm = Ext.ComponentQuery.query('#form')[0];
        claimForm.setActiveRecord(record[0] || null);
    },

});