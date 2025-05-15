// The reason we are using `javax` here is because we need to include the hibernate-ehcache dependency in the pom.xml with the same version.
// However, there is no new version of Ehcache compatible with Hibernate 6.x that uses `javax.persistence`.
// Hibernate 6.x now supports only `jakarta.persistence`. That's why we are sticking with `javax`.

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)  // READ_ONLY ensures the data is not modified. It's intended for data that is only read.
// This is best suited for static data that never changes (e.g., country names, currency codes).
// If you try to update data marked as READ_ONLY, Hibernate will throw an exception.
public class Side
{
    @Id
    @Column
    private int rool_Number;

    @Column
    private String Name;

    @Column
    private String Education;

    public String getEducation() {
        return Education;
    }

    public void setEducation(String education) {
        Education = education;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getRool_Number() {
        return rool_Number;
    }

    public void setRool_Number(int rool_Number) {
        this.rool_Number = rool_Number;
    }

    @Override
    public String toString() {
        return this.getEducation() + " " + this.getRool_Number() + " " + this.getName();
    }
}

