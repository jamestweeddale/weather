package server.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="reading_key")
public class ReadingKeyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String typeName;

    public ReadingKeyEntity() {
        //for hibernate
    }

    public ReadingKeyEntity(String name, String typeName) {
        this.name = name;
        this.typeName = typeName;
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadingKeyEntity that = (ReadingKeyEntity) o;
        return getName().equals(that.getName()) &&
                Objects.equals(getTypeName(), that.getTypeName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getTypeName());
    }
}
