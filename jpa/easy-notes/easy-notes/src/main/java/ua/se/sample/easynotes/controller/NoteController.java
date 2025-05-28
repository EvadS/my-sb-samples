package ua.se.sample.easynotes.controller;

import com.github.javafaker.Faker;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.se.sample.easynotes.entity.Note;
import ua.se.sample.easynotes.exception.ResourceNotFoundException;
import ua.se.sample.easynotes.repository.NoteRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    // Get All Notes
    @GetMapping("/notes")
    public Page<Note> getAllNotes(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection) {

        Page<Note> result = findAll(pageNo, pageSize, sortBy, sortDirection);
        return result;
    }

    @GetMapping("/fetch/{one_date}/{two_date}")
    public Page<Note> getData_between(@PathVariable(value = "one_date")
                                      @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
                                      @PathVariable(value = "two_date")
                                      @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) {
        //------------------------------------------------------------------------

        LocalDate date = LocalDate.parse("2017-06-22");
        System.out.println("LocalDate is: "+date);

        LocalDateTime localDateTime1 = date.atStartOfDay();
        System.out.println("LocalDateTime Start of the Day: "+
                localDateTime1);

        LocalDateTime dt1 = fromDate.atTime(LocalTime.MIN);
        LocalDateTime dt2 = toDate.atTime(LocalTime.MAX);

       //  List<Note> result  = noteRepository.findByCreatedAtBetween(dt1, dt2);
        int pageNumber = 0;
        int pageSize = 3;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "title"));
        //List<Note> result  = noteRepository.getAllBetweenDates(dt1, dt2);
        Page<Note> paged = noteRepository.findByCreatedAtBetween(dt1, dt2, pageable);
        return  paged;
    }

    // Get a Single Note
    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(value = "id") Long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    // Create a new Note
    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note) {
        return noteRepository.save(note);
    }

    private final          Faker faker = new Faker();
    @GetMapping("/generate")
    public List<Note> generateNotes(
                                    @RequestParam(defaultValue = "20") int count) {

        List<Note> notesList = IntStream.range(0, count)
                .mapToObj(i -> generateNote()) // or x -> new Object(x).. or any other constructor
                .collect(Collectors.toList());

        return noteRepository.saveAll(notesList);

    }

    Note generateNote(){

       Note note = new Note();
       note.setTitle( faker.book().title());
       note.setContent(faker.chuckNorris().fact());

       return  note;
    }

    // Update a Note
    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable(value = "id") Long noteId,
                           @Valid @RequestBody Note noteDetails) {

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Note updatedNote = noteRepository.save(note);
        return updatedNote;
    }

    // Delete a Note
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }

    public Page<Note> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        return noteRepository.findAll(pageable);
    }
}
