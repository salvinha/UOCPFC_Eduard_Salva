/*
 * File: app/view/OpcionsParaula.js
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

Ext.define('IdiomesApp.view.OpcionsParaula', {
    extend: 'Ext.ActionSheet',
    alias: 'widget.opcionsParaula',

    config: {
        items: [
            {
                xtype: 'button',
                id: 'btnEsborrarParaula',
                itemId: 'btnEsborrarParaula',
                ui: 'decline-round',
                iconCls: 'trash',
                iconMask: true,
                text: 'Esborrar'
            },
            {
                xtype: 'button',
                id: 'btnEditarParaula',
                itemId: 'btnEditarParaula',
                ui: 'action-round',
                iconCls: 'compose',
                iconMask: true,
                text: 'Editar'
            },
            {
                xtype: 'button',
                id: 'btnCancelarParaula',
                itemId: 'btnCancelarParaula',
                ui: 'round',
                iconMask: true,
                text: 'Cancelar'
            }
        ],
        listeners: [
            {
                fn: 'onBtnEsborrarParaulaTap',
                event: 'tap',
                delegate: '#btnEsborrarParaula'
            },
            {
                fn: 'onBtnEditarParaulaTap',
                event: 'tap',
                delegate: '#btnEditarParaula'
            },
            {
                fn: 'onBtnCancelarParaulaTap',
                event: 'tap',
                delegate: '#btnCancelarParaula'
            }
        ]
    },

    onBtnEsborrarParaulaTap: function(button, e, options) {
        this.hide();

        Ext.Msg.confirm("Alerta", "Estàs segur que vols esborrar aquesta paraula?", 
        function ( answer ) { 
            if ( answer == 'yes') {

                //Esborrem la instrucció del JSON
                //Ext.getStore('paraulaJson').removeAt(Ext.getStore('paraulaJson').find('id',IdiomesApp.paraula)); 

                Ext.Ajax.request({
                    url: 'http://eduardcapell.com/pfc2012/delete_word',
                    params: {
                        id: IdiomesApp.paraula
                    },
                    success: function(response, opts){
                        var obj = Ext.decode(response.responseText);
                        if(obj.success !== true){
                            //console.log(obj);
                            //console.log(obj.errorMessage);
                            Ext.Msg.alert("Informació", obj.errorMessage);
                        }
                    },
                    failure: function(response){
                        console.log('server-side failure with status code: ' + response.status);
                        Ext.Msg.alert("No és possible esborrar la paraula del diccionari");
                    }
                });

                IdiomesApp.titol="Diccionari";
                Ext.getCmp('editarParaula').setHidden(true);
                Ext.getCmp('listPanel').setHidden(false);
                Ext.getCmp('diccionari').remove(Ext.getCmp('DetallParaula'),true);
                IdiomesApp.paraula=-1;
                Ext.getCmp('myToolBar').setTitle(IdiomesApp.titol);
                Ext.getCmp('novaParaula').setHidden(false);
                //Carrega de nou la petició de la llista per refrescar els elements
                Ext.getStore('paraulaJson').load();
            } 
        }
        );
    },

    onBtnEditarParaulaTap: function(button, e, options) {
        this.hide();

        Ext.getCmp('listPanel').setHidden(true);
        Ext.getCmp('enrere').setHidden(false);
        Ext.getCmp('novaParaula').setHidden(true);
        Ext.getCmp('editarParaula').setHidden(true);
        IdiomesApp.titol="Edició de paraula";
        Ext.getCmp('myToolBar').setTitle(IdiomesApp.titol);

        var formedita = Ext.getCmp('editParaula');

        if (formedita) {
            Ext.getCmp('editParaula').destroy();
        }

        Ext.getCmp('diccionari').setActiveItem({
            xclass: 'IdiomesApp.view.editParaula'
        });

        //console.log(IdiomesApp.paraulaTextCat);
    },

    onBtnCancelarParaulaTap: function(button, e, options) {
        this.hide();
    }

});