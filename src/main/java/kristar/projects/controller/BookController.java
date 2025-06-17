package kristar.projects.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kristar.projects.dto.bookdto.BookDto;
import kristar.projects.dto.bookdto.BookSearchParametersDto;
import kristar.projects.dto.bookdto.CreateBookRequestDto;
import kristar.projects.dto.bookdto.UpdateBookRequestDto;
import kristar.projects.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book management", description = "Endpoints for managing books")
@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    @Operation(summary = "Get all books", description = "Get a list of all available books")
    public Page<BookDto> findAll(@ParameterObject Pageable pageable) {
        return bookService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by id", description = "Getting the book found by id")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create  new book", description = "Creation a new book")
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.save(requestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the book by id", description = "Deletion the book by id")
    public void delete(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @GetMapping("/search")
    @Operation(summary = "Search all books by parameters", description = "Searching booksDto/books"
            + " by parameters as a list")
    public Page<BookDto> search(
            @ParameterObject BookSearchParametersDto searchParameters,
            @ParameterObject Pageable pageable
    ) {
        return bookService.search(searchParameters, pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    @Operation(summary = "Update book by id", description = "Updating book entity by id")
    public BookDto updateById(@PathVariable Long id,
                              @RequestBody @Valid UpdateBookRequestDto requestDto) {
        return bookService.updateById(id, requestDto);
    }
}
