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
            "#enrere": {
                tap: 'onEnrereTap'
            }
        }
    },

    onEnrereTap: function(button, e, options) {
        if (IdiomesApp.titol.substr(0,7)=="Paraula"){
            IdiomesApp.titol="Diccionari";
            Ext.getCmp('listPanel').setHidden(false);
            Ext.getCmp('enrere').setHidden(true);
            Ext.getCmp('novaParaula').setHidden(false);
            Ext.getCmp('editarParaula').setHidden(true);
            Ext.getCmp('diccionari').remove(Ext.getCmp('DetallParaula'),true);
            //Carrega de nou la petició de la llista per refrescar els elements
            Ext.getStore('paraulaJson').load();
            if (Ext.getStore('paraulaJson').getCount() === 0){
                Ext.getCmp('avisDiccionariBuit').setHidden(false);
                Ext.getCmp('avisDiccionariBuit').showBy(Ext.getCmp('novaParaula'));
            }else{
                Ext.getCmp('avisDiccionariBuit').setHidden(true);
            }
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
            //Carrega de nou la petició de la llista per refrescar els elements
            Ext.getStore('paraulaJson').load();
            if (Ext.getStore('paraulaJson').getCount() === 0){
                Ext.getCmp('avisDiccionariBuit').setHidden(false);
                Ext.getCmp('avisDiccionariBuit').showBy(Ext.getCmp('novaParaula'));
            }else{
                Ext.getCmp('avisDiccionariBuit').setHidden(true);
            }
        }else if (IdiomesApp.titol=="Nova llista d'estudi" || IdiomesApp.titol=="Edició de llista d'estudi"){
            if (IdiomesApp.titol=="Edició de llista d'estudi"){
                Ext.getCmp('llistesdestudi').remove(Ext.getCmp('editLlista'),true);
            }else{
                Ext.getCmp('llistesdestudi').remove(Ext.getCmp('addLlista'),true);
            }
            IdiomesApp.titol="Llistes d'estudi";
            Ext.getCmp('listPanel2').setHidden(false);
            Ext.getCmp('enrere').setHidden(true);
            //No es permeten donar d'alta noves llistes perquè van en funció d'un fitxer json local
            //Ext.getCmp('novaLlista').setHidden(false);
            //Carrega de nou la petició de la llista per refrescar els elements
            Ext.getStore('llistaJson').load();
        }else{
            console.log('Cas no contemplat mentre es prem el botó Enrere');
        }

        Ext.getCmp('myToolBar').setTitle(IdiomesApp.titol);
    },

    init: function(application) {
        IdiomesApp.titol="Diccionari";
        IdiomesApp.titolAux="Diccionari";
    }

});