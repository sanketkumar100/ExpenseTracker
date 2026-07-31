# AI_NOTES.md

## AI Tools Used

- ChatGPT
- GitHub Copilot (Free)

---

## 1. Which parts of the code were AI-generated vs. written by me

### AI Assistance

- Used GitHub Copilot to generate the initial version of the `ExpenseRepository`.
- Used GitHub Copilot to generate the OpenAPI/Swagger configuration because I had no prior experience with Swagger.
- Took help from ChatGPT and GitHub Copilot to create the Mockito unit tests and MockMvc controller tests.
- Used AI suggestions while preparing the `README.md` and API documentation.

### Written/Modified by Me

- Designed the project architecture (Controller → Service → Repository).
- Created the Expense model, DTOs, and REST API endpoints.
- Implemented the business logic in the service layer.
- Implemented expense filtering, total expense calculation, and delete functionality.
- Added request validation using Jakarta Validation.
- Tested all API endpoints manually using Postman.
- Reviewed and modified AI-generated code to match my coding style and assignment requirements.

---

## 2. What I validated, tested, or changed in the AI output, and why

- Simplified the repository implementation to make the code easier to understand and maintain.
- Replaced constructor injection with Lombok's `@RequiredArgsConstructor`.
- Used Lombok's `@Builder` pattern instead of manually creating objects.
- Renamed methods and endpoints to make them more RESTful and readable.
- Removed unnecessary code suggested by AI that was not required for this assignment.
- Verified every API endpoint manually using Postman.
- Tested JSON file persistence by restarting the application and ensuring the data remained intact.
- Ran Maven tests and fixed issues before finalizing the project.

---

## 3. Any AI suggestion I decided not to use, and why

- I did not use a database because the assignment specifically required data to be stored in a local JSON file.
- I did not implement additional features beyond the assignment requirements, except Swagger/OpenAPI documentation as the bonus feature.
- I avoided using some complex repository implementations suggested by AI and instead used a simpler approach for better readability and easier maintenance.
- I did not copy AI-generated code directly. I reviewed, modified, and tested every AI-generated suggestion before including it in the final project.

---

## Summary

AI was used as a learning and productivity tool throughout this project. It helped me understand unfamiliar concepts such as Swagger/OpenAPI configuration and writing Mockito and MockMvc tests. However, I reviewed, modified, integrated, and tested the final implementation myself to ensure it met the assignment requirements and followed clean coding practices.
