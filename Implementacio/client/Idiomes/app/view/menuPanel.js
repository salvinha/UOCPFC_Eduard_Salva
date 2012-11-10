/*
 * File: app/view/menuPanel.js
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

Ext.define('IdiomesApp.view.menuPanel', {
    extend: 'Ext.tab.Panel',
    alias: 'widget.menupanel',

    requires: [
        'IdiomesApp.view.ListPanel'
    ],

    config: {
        id: 'menuPanel',
        ui: 'light',
        modal: false,
        tabBar: {
            docked: 'bottom',
            itemId: 'mytabbar',
            ui: 'light',
            layout: {
                pack: 'center',
                type: 'hbox'
            }
        },
        items: [
            {
                xtype: 'container',
                title: 'Diccionari',
                badgeText: '374',
                iconCls: 'bookmarks',
                id: 'diccionari',
                width: '100%',
                layout: {
                    type: 'fit'
                },
                items: [
                    {
                        xtype: 'listpanel',
                        itemId: 'listPanel',
                        width: '100%'
                    }
                ]
            },
            {
                xtype: 'container',
                title: 'Llistes d\'estudi',
                badgeText: '10',
                iconCls: 'favorites',
                cls: [
                    'card3'
                ],
                html: '<h1>Llistes d\'estudi</h1>',
                id: 'llistesdestudi'
            },
            {
                xtype: 'container',
                title: 'Flashcards',
                iconCls: 'organize',
                cls: [
                    'card4'
                ],
                html: '<h1>Flashcards</h1>',
                id: 'flashcards'
            },
            {
                xtype: 'toolbar',
                docked: 'top',
                height: 35,
                id: 'myToolBar',
                itemId: 'myToolBar',
                style: 'font-size:10px;',
                ui: 'light',
                scrollable: false,
                title: 'Diccionari'
            }
        ],
        listeners: [
            {
                fn: 'onMenuPanelActiveItemChange',
                event: 'activeitemchange'
            }
        ]
    },

    onMenuPanelActiveItemChange: function(container, value, oldValue, options) {
        //Ext.Msg.alert('activeitemchange', 'Current tab: ' + value.config.title);
        IdiomesApp.titol=value.config.title;
        //Auxiliar que ens servirà quan tornem de crear una nova instrucció
        IdiomesApp.titolAux=value.config.title;

        Ext.getCmp('myToolBar').setTitle(IdiomesApp.titol);
    }

});