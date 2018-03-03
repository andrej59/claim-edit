Ext.define('App.store.Accounts', {
    extend: 'Ext.data.Store',
    alias: 'store.accounts',
    model: 'App.model.Account',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        url: rootService + 'accounts',
        reader: {
            type: 'json',
            rootProperty: 'accounts'
        }
    }
});