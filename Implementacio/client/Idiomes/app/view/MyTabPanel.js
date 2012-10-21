/*
 * File: app/view/MyTabPanel.js
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

Ext.define('MyApp.view.MyTabPanel', {
    extend: 'Ext.tab.Panel',

    config: {
        ui: 'light',
        defaults: {
            scrollable: true
        },
        tabBar: {
            docked: 'bottom',
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
                cls: [
                    'card2'
                ],
                html: '<h1>Diccionari</h1>'
            },
            {
                xtype: 'container',
                title: 'Llistes d\'estudi',
                badgeText: '10',
                iconCls: 'favorites',
                cls: [
                    'card3'
                ],
                html: '<h1>Llistes d\'estudi</h1>'
            },
            {
                xtype: 'container',
                title: 'Flashcards',
                iconCls: 'organize',
                cls: [
                    'card4'
                ],
                html: '<h1>Flashcards</h1>'
            }
        ]
    }

});