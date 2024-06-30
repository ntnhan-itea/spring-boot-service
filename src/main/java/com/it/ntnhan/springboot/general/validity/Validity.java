package com.it.ntnhan.springboot.general.validity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public class Validity {
    @Column(name = "valid_from", nullable = false)
    @Builder.Default
    private LocalDate validFrom = LocalDate.now();

    @Column(name = "valid_to", nullable = true)
    private LocalDate validTo;
}
