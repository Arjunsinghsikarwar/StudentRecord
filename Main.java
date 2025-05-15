import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

class Main {
    public static void main(String[] args) {
        Side student = new Side();
        // System.out.println(student);  
        // After seeing the result of this, try to understand why we override the toString() method.

        student.setName("Munish");
        student.setEducation("B.Tech");
        student.setRool_Number(69);

        Configuration configuration = new Configuration().configure().addAnnotatedClass(Side.class); 
        // Add the annotated class whose data we want to save.

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();  
        // To make permanent changes in the database, we need a transaction.

        session.save(student);
        transaction.commit(); 
        // Changes are successfully committed here.

        // We usually wrap session.save(student) in a try-catch block,
        // because the transaction object has a rollback() method which ensures
        // that if any exception occurs, the changes will be rolled back.

        System.out.println(student);

        // <property name="hibernate.hbm2ddl.auto">update</property>
        // This property is used to automatically create a table
