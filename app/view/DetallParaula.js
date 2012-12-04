/*
 * File: app/view/DetallParaula.js
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

Ext.define('IdiomesApp.view.DetallParaula', {
    extend: 'Ext.Container',

    config: {
        id: 'DetallParaula',
        itemId: 'DetallParaula',
        minHeight: 500,
        style: 'overflow: hidden;',
        layout: {
            align: 'start',
            type: 'vbox'
        },
        items: [
            {
                xtype: 'container',
                flex: 0,
                id: 'paraulaTarjeta',
                tpl: [
                    '<div>',
                    '    <p><h1>{textcat}</h1></p>',
                    '    <hr/>',
                    '    <p><h1>{textjap}</h1></p>',
                    '    <p><h2>{pronjap}</h2></p>',
                    '</div>'
                ]
            }
        ]
    }

});