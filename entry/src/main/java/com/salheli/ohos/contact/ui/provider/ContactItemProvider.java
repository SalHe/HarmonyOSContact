package com.salheli.ohos.contact.ui.provider;

import com.salheli.ohos.contact.ResourceTable;
import com.salheli.ohos.contact.entity.Contact;
import ohos.agp.components.*;
import ohos.app.Context;

import java.util.ArrayList;
import java.util.List;

public class ContactItemProvider extends BaseItemProvider {

    private List<Contact> contacts;
    private Context context;

    public static ContactItemProvider create(List<Contact> contacts, Context context) {
        return new ContactItemProvider(contacts, context);
    }

    private ContactItemProvider(List<Contact> contacts, Context context) {
        this.contacts = new ArrayList<>(contacts);
        this.context = context;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return contacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return contacts.get(i).getId();
    }

    @Override
    public Component getComponent(int i, Component convertComponent, ComponentContainer componentContainer) {
        Component component = LayoutScatter.getInstance(context).parse(ResourceTable.Layout_item_contact, componentContainer, false);
        Contact item = (Contact) getItem(i);
        ((Text) component.findComponentById(ResourceTable.Id_name_text)).setText(item.getName());
        ((Text) component.findComponentById(ResourceTable.Id_phone_text)).setText(item.getNumber());
        ((Text) component.findComponentById(ResourceTable.Id_email_text)).setText(item.getEmail());
        ((Text) component.findComponentById(ResourceTable.Id_address_text)).setText(item.getAddress());
        return component;
    }
}
