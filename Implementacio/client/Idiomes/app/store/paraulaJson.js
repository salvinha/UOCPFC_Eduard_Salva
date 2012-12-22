/*
 * File: app/store/paraulaJson.js
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

Ext.define('IdiomesApp.store.paraulaJson', {
    extend: 'Ext.data.Store',

    requires: [
        'IdiomesApp.model.paraulaModel'
    ],

    config: {
        autoLoad: true,
        autoSync: false,
        groupDir: 'ASC',
        groupField: 'textcat',
        model: 'IdiomesApp.model.paraulaModel',
        storeId: 'paraulaJson',
        proxy: {
            type: 'ajax',
            enablePagingParams: false,
            filterParam: 'false',
            groupParam: 'false',
            limitParam: 'false',
            pageParam: 'false',
            sortParam: 'false',
            startParam: 'false',
            url: 'http://eduardcapell.com/pfc2012/get_words',
            reader: {
                type: 'json',
                rootProperty: 'data'
            }
        },
        sorters: {
            id: 'ordenaParaula',
            property: 'textcat'
        },
        listeners: [
            {
                fn: 'onJsonstoreAddrecords',
                event: 'addrecords'
            },
            {
                fn: 'onJsonstoreRemoverecords',
                event: 'removerecords'
            },
            {
                fn: 'onJsonstoreLoad',
                event: 'load'
            }
        ],
        grouper: {
            groupFn: function(record) {
                return record.get('textcat')[0];
            }
        }
    },

    onJsonstoreAddrecords: function(store, records, eOpts) {
        Ext.getCmp('menuPanel').getTabBar().getComponent(0);

        var comptadorParaules = Ext.getCmp('menuPanel').getTabBar().getComponent(0)._badgeText; 

        Ext.getCmp('menuPanel').getTabBar().getComponent(0).setBadgeText(comptadorParaules+1);
    },

    onJsonstoreRemoverecords: function(store, records, indices, eOpts) {
        Ext.getCmp('menuPanel').getTabBar().getComponent(0);

        var comptadorParaules = Ext.getCmp('menuPanel').getTabBar().getComponent(0)._badgeText; 

        Ext.getCmp('menuPanel').getTabBar().getComponent(0).setBadgeText(comptadorParaules-1);
    },

    onJsonstoreLoad: function(store, records, successful, operation, eOpts) {
        /*if (this.getCount() === 0){
        Ext.getCmp('avisDiccionariBuit').setHidden(false);
        Ext.getCmp('avisDiccionariBuit').showBy(Ext.getCmp('novaParaula'));
        }else{
        Ext.getCmp('avisDiccionariBuit').setHidden(true);
        }*/

        Ext.getCmp('menuPanel').getTabBar().getComponent(0);

        var comptadorParaules = this.getCount();

        Ext.getCmp('menuPanel').getTabBar().getComponent(0).setBadgeText(comptadorParaules);
    }

});