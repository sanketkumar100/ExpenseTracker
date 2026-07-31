package sanket.expensetracker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseResponse
{
    private UUID id;

    private String title;

    private BigDecimal amount;

    private String category;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

}
