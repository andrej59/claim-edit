Ext.define('App.store.Claims', {
    extend: 'Ext.data.Store',
    alias: 'store.claims',
    model: 'App.model.Claim',
    requires: [
        'Ext.data.*',
        'Ext.window.MessageBox'
    ],
    storeId: 'claimStore',
    autoSync: true,
    autoLoad: true,
    proxy: {
        type: 'ajax',
        api: {
            read: rootService + 'claims/view',
            create: rootService + 'claims/create',
            update: rootService + 'claims/update',
            destroy: rootService + 'claims/delete'
        },
        reader: {
            type: 'json',
            successProperty: 'success',
            root: 'data',
            messageProperty: 'message'
        },
        writer: {
            type: 'json',
            writeAllFields: true,
            root: 'data'
        },
        listeners: {
            exception: function (proxy, response, operation) {
                var result = Ext.decode(
                    operation.getError().response.responseText);
                var errors = '';
                Ext.Array.each(result.errors, function (value) {
                    errors = errors + '<br/>' + value;
                });
                console.debug(errors);
                Ext.MessageBox.show({
                    title: 'REMOTE EXCEPTION',
                    msg: errors,
                    icon: Ext.MessageBox.ERROR,
                    buttons: Ext.Msg.OK
                });
            }
        }
    },
    listeners: {
        write: function (proxy, operation) {
            Ext.example.msg(operation.action, operation.getResultSet().message);
        }
    }
});