import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Fetch {
    public static void main(String[] args) {
        System.out.println("In this example, we fetch data using Hibernate.");

        Configuration configuration = new Configuration().configure().addAnnotatedClass(Side.class);  
        // We add the annotated entity class here.

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        // Note: We don't need a transaction here since we are not making any changes to the table,
        // we are only reading (fetching) data.

        // Remember this: Hibernate provides two methods to fetch data from the database:
        // 1. get()  --> Executes the query immediately, whether you use the data or not.
        // 2. load() --> Returns a proxy and delays the query execution until you actually use the object.

        Side student = session.get(Side.class, 12); // Fetches the student with
