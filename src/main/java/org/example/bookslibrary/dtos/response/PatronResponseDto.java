package org.example.bookslibrary.dtos.response;
import lombok.Builder;

@Builder
public record PatronResponseDto (
         Long id,
         String name,
         String email,
         String phoneNumber,
         String contactInformation
){
}
