package com.salheli.ohos.contact.slice;

import com.salheli.ohos.contact.ResourceTable;
import com.salheli.ohos.contact.db.ContactDb;
import com.salheli.ohos.contact.entity.Contact;
import com.salheli.ohos.contact.ui.provider.ContactItemProvider;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.ListContainer;
import ohos.data.DatabaseHelper;
import ohos.data.orm.OrmContext;
import ohos.data.orm.OrmPredicates;
import ohos.hiviewdfx.HiLogLabel;

import java.util.List;

public class MainAbilitySlice extends AbilitySlice {

    private static final HiLogLabel TAG = new HiLogLabel(0, 0, MainAbilitySlice.class.getName());

    private ListContainer contactListContainer;
    private ContactItemProvider contactItemProvider;
    private OrmContext contactDbContext;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        initContactDb();
        initContactListContainer();
    }

    private void initContactListContainer() {
        contactListContainer = (ListContainer) findComponentById(ResourceTable.Id_contact_list);
        List<Contact> contacts = contactDbContext.query(new OrmPredicates(Contact.class));
        contactItemProvider = ContactItemProvider.create(contacts, this);
        contactListContainer.setItemProvider(contactItemProvider);
    }

    private void initContactDb() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        contactDbContext = databaseHelper.getOrmContext("contact_db", "contact_db", ContactDb.class);

        // 生成测试数据
        if (contactDbContext.count(new OrmPredicates(Contact.class)) <= 0) {
            String[] name = new String[]{
                    "SalHe", "Letty", "Baby", "张三",
                    "李四", "王五", "赵六", "邓紫棋",
                    "林俊杰", "爸爸"
            };
            for (int i = 0; i < 10; i++) {
                Contact contact = new Contact();
                contact.setId(i); // 自动生成ID的注解貌似没什么用？
                contact.setName(name[i]);
                contact.setNumber("1381111222" + i);
                contact.setEmail(name[i] + "@mama.com");
                contact.setAddress("幸福大街" + i + "号路");
                contactDbContext.insert(contact);
            }
            contactDbContext.flush();
        }
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
