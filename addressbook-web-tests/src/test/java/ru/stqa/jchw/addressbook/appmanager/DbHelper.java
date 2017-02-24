package ru.stqa.jchw.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.type.IntegerType;
import ru.stqa.jchw.addressbook.model.ContactData;
import ru.stqa.jchw.addressbook.model.Contacts;
import ru.stqa.jchw.addressbook.model.GroupData;
import ru.stqa.jchw.addressbook.model.Groups;

import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData", GroupData.class).list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'", ContactData.class).list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }

    public List<ContactData> getContactsByFirstName(String firstName) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<ContactData> result = session.createQuery("from ContactData where firstname = :firstname", ContactData.class)
                .setParameter("firstname", firstName).list();

        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<GroupData> getGroupByName(String groupName) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<GroupData> result = session.createQuery("from GroupData where group_name = :group_name", GroupData.class)
                .setParameter("group_name", groupName).list();

        session.getTransaction().commit();
        session.close();
        return result;
    }

    public boolean isContactAddedToGroup(int contactId, int groupId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        int relationsCount = (int) session.createNativeQuery("SELECT COUNT(id) AS count FROM address_in_groups WHERE id = :contactId AND group_id = :groupId")
                .setParameter("contactId", contactId)
                .setParameter("groupId", groupId)
                .addScalar("count", new IntegerType())
                .uniqueResult();

        session.getTransaction().commit();
        session.close();

        return relationsCount > 0;
    }
}