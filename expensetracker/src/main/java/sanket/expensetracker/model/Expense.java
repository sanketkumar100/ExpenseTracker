package sanket.expensetracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense
{
    private UUID id;
    private String title;
    private BigDecimal amount;
    private String category;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
