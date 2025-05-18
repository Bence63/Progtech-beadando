package org.habittracker.service;

import org.habittracker.repository.HabitRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

class HabitServiceTest {

    @Test
    void addHabit_shouldInsertHabitIntoRepository() {
        HabitRepository mockRepo = mock(HabitRepository.class);
        HabitService service = new HabitService(mockRepo);

        service.addHabit("Edzés", "daily", true, false);

        verify(mockRepo).insert(Mockito.argThat(habit ->
                habit.getName().equals("Edzés") &&
                        habit.getType().equals("daily") &&
                        habit.isImportant() &&
                        !habit.isRewarded() &&
                        habit.getCreatedAt().equals(LocalDate.now())
        ));
    }
}
