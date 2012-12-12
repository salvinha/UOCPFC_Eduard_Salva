/*
 * File: app/view/ListPanel2.js
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

Ext.define('IdiomesApp.view.ListPanel2', {
    extend: 'Ext.Panel',
    alias: 'widget.listpanel2',

    config: {
        id: 'listPanel2',
        layout: {
            type: 'fit'
        },
        items: [
            {
                xtype: 'list',
                id: 'llistesestudiList',
                itemId: 'mylist2',
                itemTpl: [
                    '<div class="deleteplaceholder">',
                    '    <h3>{nom}</h3>',
                    '</div>'
                ],
                loadingText: 'Carregant...',
                store: 'llistaJson',
                disableSelection: false,
                grouped: true,
                onItemDisclosure: false,
                indexBar: true
            }
        ],
        listeners: [
            {
                fn: 'onLlistesestudiListItemSwipe',
                event: 'itemswipe',
                delegate: '#llistesestudiList'
            }
        ]
    },

    onLlistesestudiListItemSwipe: function(dataview, index, target, record, e, options) {
        if (e.direction == "left") {
            var del = Ext.create("Ext.Button", {
                ui: "decline",
                text: "Esborra",
                style: "position:relative;width:100px;",
                handler: function() {
                    Ext.Msg.confirm("Alerta", "Estàs segur que vols esborrar aquesta llista d'estudi?", 
                    function ( answer ) { 
                        if ( answer == 'yes') {
                            record.stores[0].remove(record);
                            //record.stores[0].sync();
                        } 
                    }
                    );
                }
            });
            var removeDeleteButton = function() {
                Ext.Anim.run(del, 'fade', {
                    after: function() {
                        del.destroy();
                    },
                    out: true
                });
            };
            del.renderTo(Ext.DomQuery.selectNode(".deleteplaceholder", target.dom));
            dataview.on({
                single: true,
                buffer: 250,
                itemtouchstart: removeDeleteButton
            });
            dataview.element.on({
                single: true,
                buffer: 250,
                touchstart: removeDeleteButton
            });
        }
    }

});