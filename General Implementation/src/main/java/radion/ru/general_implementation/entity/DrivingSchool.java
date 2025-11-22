package radion.ru.general_implementation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import radion.ru.general_implementation.util.Category;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DrivingSchool {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @Column(nullable = false)
    private String legalName;          // Полное наименование
    @Column(unique = true, nullable = false)
    private String INN;              // ИНН
    @Column(nullable = false)
    private String registrationNumber; // ОГРН
    @Column(nullable = false)
    private String licenseNumber;      // № лицензии на образовательную деятельность
    @Column(nullable = false)
    private Date licenseIssueDate;     // Дата выдачи лицензии
    @Column(nullable = false)
    private String licenseIssuer;      // Кем выдана лицензия

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "driving_categories",
            joinColumns = @JoinColumn(
                name = "user_id"
            )
    )
    @Column(name = "categories")
    private List<Category> categories; // Категории обучения (A, B, C, D, BE, CE и т.д.)
    @Column(nullable = false)
    private String programName;        // Наименование образовательной программы
    @Column(nullable = false)
    private Integer programDuration;   // Продолжительность обучения (часов)
    @Column(nullable = false)
    private BigDecimal programCost;    // Стоимость обучения

}
