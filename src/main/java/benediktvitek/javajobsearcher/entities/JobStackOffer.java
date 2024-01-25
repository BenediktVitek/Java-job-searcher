package benediktvitek.javajobsearcher.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter @Setter
public class JobStackOffer {


    @Id
    @Column(name = "link", nullable = false)
    private String link;

    public JobStackOffer(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        JobStackOffer that = (JobStackOffer) object;
        return Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link);
    }

}
