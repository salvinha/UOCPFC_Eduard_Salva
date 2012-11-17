/*
 * File: app/view/editParaula.js
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

Ext.define('IdiomesApp.view.editParaula', {
    extend: 'Ext.form.Panel',
    alias: 'widget.editparaula',

    config: {
        id: 'editParaula',
        itemId: 'editParaula',
        layout: {
            pack: 'center',
            type: 'vbox'
        },
        scrollable: 'vertical',
        items: [
            {
                xtype: 'fieldset',
                id: 'formParaulaEdit',
                itemId: 'fieldsetparaulaedit',
                title: 'Edició de paraula',
                items: [
                    {
                        xtype: 'textfield',
                        id: 'textCatEdit',
                        itemId: 'textCatEdit',
                        label: 'Català',
                        name: 'textcat',
                        required: true
                    },
                    {
                        xtype: 'textfield',
                        id: 'textJapEdit',
                        itemId: 'textJapEdit',
                        label: 'Kanji',
                        name: 'textjap',
                        required: true
                    },
                    {
                        xtype: 'textfield',
                        id: 'pronJapEdit',
                        itemId: 'pronJapEdit',
                        label: 'Pronunciació (Jap)',
                        name: 'pronjap',
                        required: true
                    },
                    {
                        xtype: 'selectfield',
                        id: 'llistaEdit',
                        itemId: 'llistaEdit',
                        label: 'Llista',
                        name: 'llista',
                        displayField: 'nom',
                        store: 'llistaJson',
                        valueField: 'id'
                    }
                ],
                listeners: [
                    {
                        fn: function(component, options) {
                            var rec = Ext.create('IdiomesApp.model.paraulaModel', {
                                id: IdiomesApp.paraula,
                                textcat: IdiomesApp.paraulaTextCat,
                                textjap: IdiomesApp.paraulaTextJap,
                                pronjap: IdiomesApp.paraulaPronJap,
                                llista: IdiomesApp.paraulaLlista
                            });

                            console.log(Ext.getCmp('editParaula'));

                            //No funciona
                            //Ext.getCmp('editParaula').setRecord(rec);

                            //Carregarem les dades així:
                            Ext.getCmp('textCatEdit').setValue(rec.get('textcat'));
                            Ext.getCmp('textJapEdit').setValue(rec.get('textjap'));
                            Ext.getCmp('pronJapEdit').setValue(rec.get('pronjap'));

                            console.log(rec.get('llista'));

                            Ext.getCmp('llistaEdit').setValue(rec.get('llista'));
                        },
                        event: 'initialize'
                    }
                ]
            },
            {
                xtype: 'button',
                itemId: 'submit',
                ui: 'confirm',
                iconCls: 'add',
                iconMask: true,
                text: 'Guardar'
            }
        ],
        listeners: [
            {
                fn: 'onSubmitTap',
                event: 'tap',
                delegate: '#submit'
            }
        ]
    },

    onSubmitTap: function(button, e, options) {
        var form = Ext.getCmp('editParaula'),
            store = Ext.getCmp('paraulesList').getStore(),
            paraulaRecord = form.getValues();

        //console.log('*****');
        //console.log(paraulaRecord.textcat);
        //console.log('*****');

        //Primer eliminam el registre antic
        Ext.getStore('paraulaJson').removeAt(Ext.getStore('paraulaJson').find('id',IdiomesApp.paraula)); 

        //Guardam el registre nou
        //get the record 
        var record = form.getRecord();
        //get the form values
        var values = form.getValues();
        //if a new record
        if(!record){
            var newRecord = new IdiomesApp.model.paraulaModel(values);
            Ext.getStore('paraulaJson').add(newRecord);
        }else{
            //existing record
            record.set(values);
        }
        Ext.getStore('paraulaJson').sync();


        Ext.getCmp('diccionari').remove(Ext.getCmp('editParaula'),true);
        Ext.getCmp('diccionari').remove(Ext.getCmp('DetallParaula'),true);

        //Confirmation
        form.reset();
        Ext.getCmp('paraulesList').deselectAll();

        IdiomesApp.titol="Diccionari";

        Ext.getCmp('listPanel').setHidden(false);
        Ext.getCmp('novaParaula').setHidden(false);

        Ext.getCmp('diccionari').remove(form,true);

        Ext.getCmp('myToolBar').setTitle(IdiomesApp.titol);
    }

});