Ext.define('App.view.main.ClaimList', {
    extend: 'Ext.grid.Panel',
    xtype: 'writergrid',
    controller: 'main',
    requires: [
        'Ext.grid.plugin.CellEditing',
        'Ext.form.field.*',
        'Ext.toolbar.TextItem',
        'App.store.Claims',
        'App.store.ClaimStatuses',
        'App.store.Accounts'
    ],
    selModel: 'rowmodel',
    /*
     plugins: [{
     ptype: 'rowediting',
     clicksToEdit: 2
     }],*/
    store: {
        type: 'claims'
    },

    initComponent: function () {
        this.editing = Ext.create('Ext.grid.plugin.RowEditing', {
            listeners: {
                cancelEdit: function (rowEditing, context) {
                    console.debug("cancelEdit");
                    if (context.record.phantom) {
                        context.store.remove(context.record);
                    }
                }
            }
        });
        Ext.apply(this, {
            iconCls: 'icon-grid',
            frame: true,
            plugins: [this.editing],
            dockedItems: [{
                xtype: 'toolbar',
                items: [{
                    iconCls: 'icon-add',
                    text: 'Создать',
                    scope: this,
                    handler: this.onAddClick
                }, {
                    iconCls: 'icon-delete',
                    text: 'Удалить',
                    disabled: true,
                    itemId: 'delete',
                    scope: this,
                    handler: this.onDeleteClick
                }]
            }],
            columns: [{
                text: 'ID',
                width: 50,
                sortable: true,
                dataIndex: 'id',
                renderer: function (v, meta, rec) {
                    return rec.phantom ? '' : v;
                }
            }, {
                header: 'Наименование',
                flex: 1,
                sortable: true,
                dataIndex: 'name',
                editor: 'textfield'
            }, {
                header: 'От кого',
                width: 150,
                sortable: true,
                dataIndex: 'fromAccountId',
                xtype: 'templatecolumn',
                tpl: '{fromAccount.lastName} {fromAccount.firstName}',
                editor: {
                    xtype: 'combobox',
                    valueField: 'id',
                    displayField: 'lastName',
                    store: {
                        type: 'accounts'
                    }
                }
            }, {
                header: 'Кому',
                width: 150,
                sortable: true,
                dataIndex: 'toAccountId',
                xtype: 'templatecolumn',
                tpl: '{toAccount.lastName} {toAccount.firstName}',
                editor: {
                    xtype: 'combobox',
                    valueField: 'id',
                    displayField: 'lastName',
                    store: {
                        type: 'accounts'
                    }
                }
            }, {
                header: 'Статус',
                width: 150,
                sortable: true,
                dataIndex: 'statusId',
                xtype: 'templatecolumn',
                tpl: '{status.name}',
                editor: {
                    xtype: 'combobox',
                    valueField: 'id',
                    displayField: 'name',
                    store: {
                        type: 'statuses'
                    }
                }
            }]
        });
        this.callParent();
        this.getSelectionModel().on('selectionchange', this.onSelectChange,
            this);
    },

    listeners: {
        select: 'onItemSelected',
        selectionchange: 'onItemChange'
    },

    onSelectChange: function (selModel, selections) {
        console.debug("onSelectChange")
        this.down('#delete').setDisabled(selections.length === 0);
    },

    onAddClick: function () {
        console.debug("onAddClick");
        // add empty record
        this.store.autoSync = false;
        var rec = new App.model.Claim();
        var edit = this.editing;
        edit.cancelEdit();
        this.store.insert(0, rec);
        edit.startEdit(rec, 0);
        this.store.autoSync = true;
    },

    onDeleteClick: function () {
        Ext.Msg.confirm('Внимание', 'Удалить заявку?', 'onDeleteConfirm', this);
    },

    onDeleteConfirm: function (choice) {
        if (choice === 'yes') {
            var selection = this.getView().getSelectionModel().getSelection()[0];
            if (selection) {
                this.store.remove(selection);
            }
        }
    }
});