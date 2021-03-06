/*
 * File: app/view/ListPanel.js
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

Ext.define('IdiomesApp.view.ListPanel', {
    extend: 'Ext.Panel',
    alias: 'widget.listpanel',

    config: {
        id: 'listPanel',
        layout: {
            type: 'fit'
        },
        items: [
            {
                xtype: 'list',
                id: 'paraulesList',
                itemId: 'mylist',
                emptyText: '<h1>Diccionari buit</h1>',
                itemTpl: [
                    '<div style="height:60px;">',
                    '    <h3>{textcat}</h3>',
                    '    <h4>{textjap}</h4>',
                    '</div>'
                ],
                loadingText: 'Carregant...',
                store: 'paraulaJson',
                grouped: true,
                onItemDisclosure: false,
                indexBar: true
            }
        ],
        listeners: [
            {
                fn: 'onParaulesListItemTap',
                event: 'itemtap',
                delegate: '#paraulesList'
            }
        ]
    },

    onParaulesListItemTap: function(dataview, index, target, record, e, options) {
        Ext.getCmp('listPanel').setHidden(true);
        Ext.getCmp('enrere').setHidden(false);
        Ext.getCmp('novaParaula').setHidden(true);
        Ext.getCmp('editarParaula').setHidden(false);
        IdiomesApp.titol="Paraula";
        IdiomesApp.titolAux="Paraula";

        var tarjeta = Ext.getCmp('DetallParaula');

        if (tarjeta) {
            Ext.getCmp('DetallParaula').destroy();
        }

        Ext.getCmp('diccionari').setActiveItem({
            xclass: 'IdiomesApp.view.DetallParaula'
        });

        Ext.getCmp('paraulaTarjeta').setRecord(record);

        //Atributs per a la posterior càrrega de dades en el formulari d'edició
        IdiomesApp.paraula=record.get("id");
        IdiomesApp.paraulaTextCat=record.get("textcat");
        IdiomesApp.paraulaTextJap=record.get("textjap");
        IdiomesApp.paraulaPronJap=record.get("pronjap");
        IdiomesApp.paraulaLlista=record.get("idLlista");
        //console.log(record);
    }

});