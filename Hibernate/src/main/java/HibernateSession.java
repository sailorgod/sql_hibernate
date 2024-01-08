import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateSession {

    private  final StandardServiceRegistry registry
            = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    private final Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
    private final SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
    @Getter
    private  final Session session = sessionFactory.openSession();
    @Getter
    private  final Transaction transaction = session.beginTransaction();

}
