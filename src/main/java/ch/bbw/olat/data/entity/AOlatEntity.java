package ch.bbw.olat.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
public abstract class AOlatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "in_use")
    private Boolean inUse;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AOlatEntity that)) return false;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
