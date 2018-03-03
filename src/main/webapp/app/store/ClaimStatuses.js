Ext.define('App.store.ClaimStatuses', {
    extend: 'Ext.data.Store',
    alias: 'store.statuses',
    model: 'App.model.ClaimStatus',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        url: rootService + 'claims/statuses',
        reader: {
            type: 'json',
            rootProperty: 'statuses'
        }
    }
});