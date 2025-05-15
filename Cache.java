import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Cache {
    public static void main(String[] args) {
        System.out.println("In this class, we are learning about caching in Hibernate.");

        // We use caching so that if a query is executed once, its result can be stored in memory (cache),
        // reducing the load on the database.
        // With caching, response time improves and fewer queries are sent to the database.

        Configuration configuration = new Configuration().configure().addAnnotatedClass(Side.class);  
        // We register the entity class we want to work with.

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // First session
        Session session = sessionFactory.openSession();

        // Level 1 cache is enabled by default in Hibernate and is session-specific.
        Side student = session.get(Side.class, 12);
        Side student1 = session.get(Side.class, 12);  
        // Even though we call get() twice, the query will be executed only once 
        // because the result is cached in the session.

        System.out.println(student1);
        System.out.println(student);    

        session.close();  // Always close sessions — it's good practice.

        // Now, if we create a new session, the cache from the previous session won't be used.
        // The query will execute again.

        Session session1 = sessionFactory.openSession();
        Side student2 = session1.get(Side.class, 12);
        System.out.println(student2);  // Since this is a new session, the query will be executed again.

        session1.close();

        // To avoid this repeated execution in different sessions, we use **Level 2 cache** with providers like Ehcache.

        // Note:
        // Even after adding the Ehcache dependency in pom.xml, we must configure some properties 
        // in hibernate.cfg.xml to enable second-level caching.

        // Also, enabling second-level cache requires us to:
        // - Annotate the entity class with @Cacheable
        // - Add @Cache(usage = CacheConcurrencyStrategy.READ_ONLY or NONSTRICT_READ_WRITE, etc.)

        // Remember: Ehcache version must be compatible with the Hibernate version.
    }
}
