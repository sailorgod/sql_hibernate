import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        HibernateSession session = new HibernateSession();

        CriteriaBuilder criteriaBuilder = session.getSession().getCriteriaBuilder();
        CriteriaQuery<PurchaseList> purchaseListSelector = criteriaBuilder.createQuery(PurchaseList.class);
        Root<PurchaseList> purchaseListRoot = purchaseListSelector.from(PurchaseList.class);
        purchaseListSelector.select(purchaseListRoot);

        CriteriaQuery<Subscriptions> subscriptionsSelector = criteriaBuilder.createQuery(Subscriptions.class);
        Root<Subscriptions> subscriptionsRoot = subscriptionsSelector.from(Subscriptions.class);
        subscriptionsSelector.select(subscriptionsRoot);

        List<PurchaseList> purchaseListResults =
                session.getSession().createQuery(purchaseListSelector).getResultList();
        Collections.sort(purchaseListResults, (p1, p2) -> {
            int cmp = p1.getKey().getStudentName().compareTo(p2.getKey().getStudentName());
            if (cmp != 0) {
                return cmp;
            }
            return Integer.valueOf(p1.getKey().getCourseName().compareTo(p2.getKey().getCourseName()));
        });
        List<Subscriptions> subscriptionsResults =
                session.getSession().createQuery(subscriptionsSelector).getResultList();
        Collections.sort(subscriptionsResults, (s1, s2) ->{
                int cmp = s1.getStudent().getName().compareTo(s2.getStudent().getName());
                if(cmp != 0) {
                    return cmp;
                }
                return Integer.valueOf(s1.getCourse().getName().compareTo(s2.getCourse().getName()));
                }
                  );

        int index = 0;
        for (PurchaseList purchaseList:
             purchaseListResults) {
            LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
            LinkedPurchaseListPK linkedPurchaseListPK = new LinkedPurchaseListPK();
            Subscriptions subscriptions = subscriptionsResults.get(index);
            if(subscriptions.getStudent().getName().equals(purchaseList.getKey().getStudentName())
                    && subscriptions.getCourse().getName().equals(purchaseList.getKey().getCourseName())
            ) {
                linkedPurchaseListPK.setCourseId(subscriptions.getCourse().getId());
                linkedPurchaseListPK.setStudentId(subscriptions.getStudent().getId());
            }
            linkedPurchaseList.setId(linkedPurchaseListPK);
            linkedPurchaseList.setPurchaseList(purchaseList);
            linkedPurchaseList.setPrice(purchaseList.getPrice());
            session.getSession().merge(linkedPurchaseList);
            index++;
        }

        session.getTransaction().commit();
        session.getSession().close();
    }

}
