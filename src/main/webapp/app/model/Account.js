Ext.define('App.model.Account', {
    extend: 'Ext.data.Model',
    fields: [{
        name: 'id',
        type: 'int'
    }, {
        name: 'email',
        type: 'string'
    }, {
        name: 'lastName',
        type: 'string'
    }, {
        name: 'firstName',
        type: 'string'
    }, {
        name: 'middleName',
        type: 'string'
    }]
});