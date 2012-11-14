/*
 * File: app/view/editParaula.js
 *
 * This file was generated by Sencha Architect version 2.1.0.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Sencha Touch 2.0.x library, under independent license.
 * License of Sencha Architect does not include license for Sencha Touch 2.0.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

Ext.define('IdiomesApp.view.editParaula', {
    extend: 'Ext.form.Panel',
    alias: 'widget.editparaula',

    config: {
        id: 'editParaula',
        itemId: 'editParaula',
        layout: {
            pack: 'center',
            type: 'vbox'
        },
        scrollable: 'vertical',
        items: [
            {
                xtype: 'fieldset',
                id: 'formParaulaEdit',
                itemId: 'fieldsetparaulaedit',
                title: 'Edició de paraula',
                items: [
                    {
                        xtype: 'textfield',
                        id: 'textCatEdit',
                        itemId: 'textCatEdit',
                        label: 'Català',
                        name: 'textcat',
                        required: true
                    },
                    {
                        xtype: 'textfield',
                        id: 'textJapEdit',
                        itemId: 'textJapEdit',
                        label: 'Kanji',
                        name: 'textjap',
                        required: true
                    },
                    {
                        xtype: 'textfield',
                        id: 'pronJapEdit',
                        itemId: 'pronJapEdit',
                        label: 'Pronunciació (Jap)',
                        name: 'pronjap',
                        required: true
                    }
                ]
            },
            {
                xtype: 'button',
                itemId: 'submit',
                ui: 'confirm',
                iconCls: 'add',
                iconMask: true,
                text: 'Guardar'
            }
        ],
        listeners: [
            {
                fn: 'onSubmitTap',
                event: 'tap',
                delegate: '#submit'
            }
        ]
    },

    onSubmitTap: function(button, e, options) {
        var form = Ext.getCmp('addParaula'),
            store = Ext.getCmp('paraulesList').getStore(),
            paraulaRecord = form.getValues();


        //Store to local storage
        store.add(paraulaRecord);

        //Confirmation
        form.reset();
        Ext.getCmp('paraulesList').deselectAll();

        IdiomesApp.titol=IdiomesApp.titolAux;

        Ext.getCmp('listPanel').setHidden(false);

        Ext.getCmp('diccionari').remove(form,true);

        Ext.getCmp('myToolBar').setTitle(IdiomesApp.titol);
    }

});