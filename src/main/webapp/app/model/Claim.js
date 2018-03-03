Ext.define('App.model.Claim', {
    extend: 'Ext.data.Model',
    fields: [{
        name: 'id',
        type: 'int',
        useNull: true
    }, {
        name: 'name',
        type: 'string',
        useNull: false
    }, {
        name: 'fromAccountId',
        type: 'int',
        useNull: false
    }, {
        name: 'fromAccount'
    }, {
        name: 'toAccountId',
        type: 'int',
        useNull: false
    }, {
        name: 'toAccount'
    }, {
        name: 'statusId',
        type: 'int',
        useNull: false
    }, {
        name: 'status'
    }],
    validators: {
        name: {
            type: 'length',
            max: 255
        }
    }
});