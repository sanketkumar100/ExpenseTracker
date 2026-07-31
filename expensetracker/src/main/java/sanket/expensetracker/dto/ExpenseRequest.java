package sanket.expensetracker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseRequest
{

    @NotBlank(message = "title must not be empty")
    private String title;

    @NotNull(message = "amount is required")
    @Positive(message = "amount must be greater than 0")
    private BigDecimal amount;

    @NotBlank(message = "category must not be empty")
    private String category;

    @NotNull(message = "date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
