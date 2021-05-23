package com.salheli.ohos.contact.db;

import com.salheli.ohos.contact.entity.Contact;
import ohos.data.orm.OrmDatabase;
import ohos.data.orm.annotation.Database;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactDb extends OrmDatabase {
}
