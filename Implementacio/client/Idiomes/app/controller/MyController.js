/*
 * File: app/controller/MyController.js
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

Ext.define('IdiomesApp.controller.MyController', {
    extend: 'Ext.app.Controller',

    config: {
        control: {
            "#paraulesList": {
                itemtap: 'onListpanelTap'
            },
            "#enrere": {
                tap: 'onEnrereTap'
            }
        }
    },

    onListpanelTap: function(dataview, index, target, record, e, options) {
        //IdiomesApp.titol=record.get('textcat');
        console.log(Ext.getCmp('myToolBar').getTitle());
        IdiomesApp.titolAux=Ext.getCmp('myToolBar').getTitle();

        //Establim el títol amb el nom de la paraula en la barra superior
        //Ext.getCmp('myToolBar').setTitle(IdiomesApp.titol);

        console.log('onListpanelTap');
    },

    onEnrereTap: function(button, e, options) {
        if (IdiomesApp.titol.substr(0,7)=="Paraula"){
            IdiomesApp.titol="Diccionari";
            Ext.getCmp('listPanel').setHidden(false);
            Ext.getCmp('enrere').setHidden(true);
            Ext.getCmp('novaParaula').setHidden(false);
            Ext.getCmp('editarParaula').setHidden(true);
            Ext.getCmp('diccionari').remove(Ext.getCmp('DetallParaula'),true);
        }else if (IdiomesApp.titol=="Nova paraula" || IdiomesApp.titol=="Edició de paraula"){
            if (IdiomesApp.titol=="Edició de paraula"){
                Ext.getCmp('diccionari').remove(Ext.getCmp('editParaula'),true);
            }else{
                Ext.getCmp('diccionari').remove(Ext.getCmp('addParaula'),true);
            }
            Ext.getCmp('diccionari').remove(Ext.getCmp('DetallParaula'),true);
            IdiomesApp.titol="Diccionari";
            Ext.getCmp('listPanel').setHidden(false);
            Ext.getCmp('enrere').setHidden(true);
            Ext.getCmp('novaParaula').setHidden(false);    
        }else{
            console.log('Cas no contemplat mentre es prem el botó Enrere');
        }

        Ext.getCmp('myToolBar').setTitle(IdiomesApp.titol);
    }

});