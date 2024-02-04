package benediktvitek.javajobsearcher.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class CocumaOffer {

    @Id
    @Column(name = "link", nullable = false)
    private String link;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CocumaOffer that = (CocumaOffer) object;
        return Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link);
    }
}
