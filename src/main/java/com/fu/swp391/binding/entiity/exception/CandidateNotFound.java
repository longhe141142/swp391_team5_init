package com.fu.swp391.binding.entiity.exception;

public class CandidateNotFound extends Exception {
  String message = "notfound Candidate by Id: ";
  Long candidateId = null;

  public CandidateNotFound(Long id){
    this.candidateId = id;
  }

}
