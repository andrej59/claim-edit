Ext.define('App.view.main.ClaimForm', {
    extend: 'Ext.form.Panel',
    xtype: 'writerform',
    controller: 'main',

    requires: ['Ext.form.field.Text'],
    initComponent: function () {
        Ext.apply(this, {
            activeRecord: null,
            reference: 'claimForm',
            iconCls: 'icon-user',
            frame: true,
            title: 'Заявка',
            width: 360,
            defaultType: 'textfield',
            bodyPadding: 5,
            fieldDefaults: {
                anchor: '100%',
                labelAlign: 'right'
            },
            items: [{
                name: 'name',
                fieldLabel: 'Наименование',
                allowBlank: false
            }, {
                itemId: 'fromAccount',
                xtype: 'combobox',
                name: 'fromAccountId',
                fieldLabel: 'От кого',
                allowBlank: false,
                valueField: 'id',
                displayField: 'lastName',
                autoRender: false,
                store: {
                    type: 'accounts'
                }
            },
                {
                    itemId: 'toAccount',
                    xtype: 'combobox',
                    name: 'toAccountId',
                    fieldLabel: 'Кому',
                    allowBlank: false,
                    valueField: 'id',
                    displayField: 'lastName',
                    autoRender: true,
                    store: {
                        type: 'accounts'
                    }
                },
                {
                    itemId: 'status',
                    xtype: 'combobox',
                    name: 'statusId',
                    fieldLabel: 'Status',
                    allowBlank: false,
                    valueField: 'id',
                    displayField: 'name',
                    autoRender: true,
                    store: {
                        type: 'statuses'
                    }
                }
            ],
            dockedItems: [{
                xtype: 'toolbar',
                dock: 'bottom',
                ui: 'footer',
                items: ['->',
                    {
                        iconCls: 'icon-add',
                        itemId: 'create',
                        text: 'Создать',
                        disabled: false,
                        scope: this,
                        handler: this.onCreate
                    }, {
                        iconCls: 'icon-save',
                        itemId: 'save',
                        text: 'Сохранить',
                        disabled: true,
                        scope: this,
                        handler: this.onSave
                    }, {
                        iconCls: 'icon-reset',
                        itemId: 'reset',
                        text: 'Отмена',
                        disabled: false,
                        scope: this,
                        handler: this.onReset
                    }]
            }]
        });
        this.callParent();
    },
    setActiveRecord: function (record) {
        this.activeRecord = record;
        if (record) {
            this.down('#save').enable();
            this.getForm().loadRecord(record);
            console.debug(record);
            var fromAccount = this.down('#fromAccount');
            fromAccount.reset();
            fromAccount.setValue(record.data.fromAccountId);

            var toAccount = this.down('#toAccount');
            toAccount.reset();
            toAccount.setValue(record.data.toAccountId);

            console.debug(record.data)
            var status = this.down('#status');
            status.reset();
            status.setValue(record.data.statusId);
        } else {
            this.down('#save').disable();
            this.getForm().reset();
        }
    },
    onSelectStatus: function () {
        console.debug('onSelectStatus');
    },
    onCreate: function () {
        var form = this.getForm();
        if (form.isValid()) {
            this.fireEvent('createItem', this, form.getValues());
            form.reset();
        }
    },
    onSave: function () {
        var active = this.activeRecord,
            form = this.getForm();

        if (!active) {
            return;
        }
        if (form.isValid()) {
            form.updateRecord(active);
            //this.onReset();
        }
    },
    onReset: function () {
        var form = this.getForm();
        this.setActiveRecord(null);
        this.fireEvent('cancelItem', this, form.getValues());
        form.resetOriginalValue();
    },
    listeners: {
        createItem: 'onCreateItem',
        cancelItem: 'onCancelItem'
    }
});