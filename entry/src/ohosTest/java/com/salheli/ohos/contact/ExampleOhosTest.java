package com.salheli.ohos.contact;

import com.salheli.ohos.contact.db.ContactDb;
import com.salheli.ohos.contact.entity.Contact;
import com.salheli.ohos.contact.slice.MainAbilitySlice;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import ohos.data.DatabaseHelper;
import ohos.data.orm.OrmContext;
import ohos.data.orm.OrmPredicates;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExampleOhosTest extends AbilitySlice {

    private static final HiLogLabel TAG = new HiLogLabel(0, 0, MainAbilitySlice.class.getName());

    @Test
    public void testBundleName() {
        final String actualBundleName = AbilityDelegatorRegistry.getArguments().getTestBundleName();
        assertEquals("com.salheli.ohos.contact", actualBundleName);
    }

    @Test
    public void testCreateContact() {
        // 这个单元测试没法跑
        // 为ContactDb自动生成的实现类没有包含在测试中
        // 暂时不知道为什么
        // 可能是鸿蒙OS给Gradle的插件的问题
        // 暂时也没有在官网找到相关资料
        DatabaseHelper helper = new DatabaseHelper(this);
        OrmContext ormContext = helper.getOrmContext("ContactDb", "ContactDb", ContactDb.class);

        Contact contact = new Contact();
        contact.setName("SalHe");
        contact.setCompany("Wuhan Unversity");
        contact.setNumber("13811112222");
        contact.setEmail("SalHe@qq.com");
        ormContext.insert(contact);
        ormContext.flush();

        List<Contact> contacts = ormContext.query(new OrmPredicates(Contact.class));
        contacts.forEach(c -> HiLog.debug(TAG, c.toString()));
    }
}