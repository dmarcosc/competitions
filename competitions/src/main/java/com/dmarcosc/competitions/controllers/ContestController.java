package com.dmarcosc.competitions.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dmarcosc.competitions.models.Contest;
import com.dmarcosc.competitions.repository.ContestRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ContestController {

  @Autowired
  ContestRepository contestRepository;

  @GetMapping("/contests")
  public ResponseEntity<List<Contest>> getAllContests(@RequestParam(required = false) String title) {
    try {
      List<Contest> contests = new ArrayList<Contest>();

      if (title == null)
        contestRepository.findAll().forEach(contests::add);
      else
        contestRepository.findByTitleContaining(title).forEach(contests::add);

      if (contests.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(contests, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/contests/{id}")
  public ResponseEntity<Contest> getContestById(@PathVariable("id") long id) {
    Optional<Contest> contestData = contestRepository.findById(id);

    if (contestData.isPresent()) {
      return new ResponseEntity<>(contestData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/contests")
  public ResponseEntity<Contest> createContest(@RequestBody Contest contest) {
    try {
      Contest _contest = contestRepository
          .save(new Contest(contest.getTitle(), contest.getDescription(), false));
      return new ResponseEntity<>(_contest, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/contests/{id}")
  public ResponseEntity<Contest> updateContest(@PathVariable("id") long id, @RequestBody Contest contest) {
    Optional<Contest> contestData = contestRepository.findById(id);

    if (contestData.isPresent()) {
      Contest _contest = contestData.get();
      _contest.setTitle(contest.getTitle());
      _contest.setDescription(contest.getDescription());
      _contest.setStatus(contest.isStatus());
      return new ResponseEntity<>(contestRepository.save(_contest), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/contests/{id}")
  public ResponseEntity<HttpStatus> deleteContest(@PathVariable("id") long id) {
    try {
      contestRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/contests")
  public ResponseEntity<HttpStatus> deleteAllContests() {
    try {
      contestRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @GetMapping("/contests/status")
  public ResponseEntity<List<Contest>> findByStatus() {
    try {
      List<Contest> contests = contestRepository.findByStatus(true);

      if (contests.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(contests, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}