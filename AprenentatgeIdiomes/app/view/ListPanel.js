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
                style: 'background-color:#FFEFD5;',
                itemTpl: [
                    '<div style="height:30px;">',
                    '    <p>{textcat}</p>',
                    '</div>'
                ],
                loadingText: 'Carregant...',
                store: 'paraulaJson'
            }
        ]
    }

});