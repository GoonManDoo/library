package com.test.library.book.web;

import com.test.library.book.dto.BookDTO;
import com.test.library.book.entity.BookEntity;
import com.test.library.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor // final을 가진 생성자 자동 생성
@Tag(name = "Book", description = "책 정보 API")
public class BookController {

    private final BookService bookService; // 생성자 주입 방식

    /* 전체 조회 */
    @GetMapping("/selectBookList")
    @Operation(summary = "책 전체 조회", description = "등록된 책을 전체 조회한다.")
    public ResponseEntity<List<BookDTO>> getAllBooks() {

        // Service에서 모든 책 정보를 조회하여 List<BookEntity>로 받아옴
        List<BookEntity> bookEntityList = bookService.getAllBooks();

        List<BookDTO> bookDTOList = bookEntityList
                .stream() // List -> stream()으로 변환
                .map(BookDTO::toDTO) // 각각의 Entity를 DTO로 변환
                .collect(Collectors.toList()); // stream() -> List 변환

        return new ResponseEntity<>(bookDTOList, HttpStatus.OK);
    }

    /* 단건 조회 */
    @GetMapping("/selectBook/{bookUid}")
    @Operation(summary = "책 단건 조회", description = "책 고유번호를 통해 책을 조회한다.")
    public ResponseEntity<BookDTO> getBookById(@Parameter(name = "bookUid", description = "책의 uid", in = ParameterIn.PATH,
                                                          example = "1", required = true)
                                               @PathVariable Long bookUid) {
        // Service에 getBookById(Long bookUid)를 호출하여 단일 정보를 가져오고 DTO에 저장.
        // Service에서 Controller로 넘겨줄 때 Entity -> DTO로 변환하여 넘겨줌
        BookDTO bookDTO = bookService.getBookById(bookUid);

        if (bookDTO == null) { // bookDTO가 null이면 해당 404 에러 코드 반환
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            // 성공 시 200 코드 반환
            return new ResponseEntity<>(bookDTO, HttpStatus.OK);
        }
    }

    /* 등록 */
    @PostMapping("/insertBook")
    @Operation(summary = "책 등록", description = "책을 등록한다.")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        /* bookService.createBook(bookDTO)을 호출하여
		   등록 정보가 담긴 DTO를 사용하여 책을 등록하고, 등록된 정보를 createBook 저장 */
        BookDTO createBook = bookService.createBook(bookDTO);

        // 책 정보를 ResponseEntity로 감싸서 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(createBook);
    }

    /* 수정 */
    @PutMapping("/updateBook/{bookUid}")
    @Operation(summary = "책 수정", description = "등록된 책을 수정한다.")
    public ResponseEntity<BookDTO> updateBook(@Parameter(name = "bookUid", description = "책의 uid", in = ParameterIn.PATH,
                                                         example = "1", required = true)
                                              @PathVariable Long bookUid, @RequestBody BookDTO bookDTO) {

		/* BookService.updateBook(bookUid, bookDTO)을 호출하여
		   등록 정보가 담긴 DTO를 사용하여 책을 등록하고, 등록된 정보를 updatedBook에 저장 */
        BookDTO updateBook = bookService.updateBook(bookUid, bookDTO);

        // 책 정보를 ResponseEntity로 감싸서 반환
        return new ResponseEntity<>(updateBook, HttpStatus.OK);
    }

    /* 삭제 */
    @DeleteMapping("/deleteBook/{bookUid}")
    @Operation(summary = "책 삭제", description = "등록된 책을 삭제한다.")
    public ResponseEntity<Void> deleteBook(@Parameter(name = "bookUid", description = "책의 uid", in = ParameterIn.PATH,
                                                      example = "1", required = true)
                                           @PathVariable Long bookUid) {
        bookService.deleteBook(bookUid);
        return ResponseEntity.noContent().build();
    }


}
