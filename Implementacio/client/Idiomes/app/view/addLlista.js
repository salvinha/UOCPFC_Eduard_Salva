/*
 * File: app/view/addLlista.js
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

Ext.define('IdiomesApp.view.addLlista', {
    extend: 'Ext.form.Panel',
    alias: 'widget.addllista',

    config: {
        id: 'addLlista',
        itemId: 'addLlista',
        layout: {
            pack: 'center',
            type: 'vbox'
        },
        scrollable: 'vertical',
        items: [
            {
                xtype: 'fieldset',
                id: 'formLlista',
                itemId: 'fieldsetllista',
                title: 'Nova llista d\'estudi',
                items: [
                    {
                        xtype: 'textfield',
                        label: 'Nom',
                        name: 'nom',
                        required: true,
                        autoCapitalize: true,
                        placeHolder: 'Introdueixi el nom de la nova Llista d\'Estudi'
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
        var form = Ext.getCmp('addLlista'),
            store = Ext.getCmp('llistesestudiList').getStore(),
            llistaRecord = form.getValues();

        if (llistaRecord.nom!==""){

            if (!IdiomesApp.idNovaLlista){
                IdiomesApp.idNovaLlista=Ext.getStore('llistaJson').max('id')+1;
            }else{
                IdiomesApp.idNovaLlista=IdiomesApp.idNovaLlista+1;
            }

            //Nou registre en l'emmagatzematge local
            llistaRecord.id=IdiomesApp.idNovaLlista;
            store.add(llistaRecord);

            //Confirmation
            form.reset();
            Ext.getCmp('llistesestudiList').deselectAll();

            IdiomesApp.titol=IdiomesApp.titolAux;

            Ext.getCmp('enrere').setHidden(true);
            Ext.getCmp('llistesdestudi').remove(form,true);

            Ext.getCmp('listPanel2').setHidden(false);
            Ext.getCmp('novaLlista').setHidden(false);

            Ext.getCmp('myToolBar').setTitle(IdiomesApp.titol);

            Ext.getCmp('llistesdestudi').removeAt(1);

        }else{

            Ext.Msg.alert('Error',"El nom de la Llista d'Estudi és obligatori");

        }
    }

});