package org.habittracker.service;

import org.habittracker.decorator.ImportantHabitDecorator;
import org.habittracker.decorator.RewardedHabitDecorator;
import org.habittracker.model.Habit;
import org.habittracker.model.HabitRecord;
import org.habittracker.repository.HabitRecordRepository;
import org.habittracker.repository.HabitRepository;
import org.habittracker.strategy.CompletionStrategy;
import org.habittracker.strategy.CompletionStrategyFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class HabitService {

    private final HabitRepository repository;
    private final HabitRecordRepository recordRepository = new HabitRecordRepository();


    public HabitService(HabitRepository repository) {
        this.repository = repository;
    }

    public void addHabit(String name, String type, boolean important, boolean rewarded) {
        Habit habit = new Habit(name, type, important, rewarded, LocalDate.now());
        repository.insert(habit);
    }

    public List<Habit> getAllHabits() {
        return repository.findAll().stream()
                .map(habit -> {
                    Habit decorated = habit;
                    if (habit.isImportant()) decorated = new ImportantHabitDecorator(decorated);
                    if (habit.isRewarded()) decorated = new RewardedHabitDecorator(decorated);
                    return decorated;
                })
                .collect(Collectors.toList());
    }


    public void completeHabit(Habit habit, LocalDate date) {
        CompletionStrategy strategy = CompletionStrategyFactory.getStrategy(habit.getType());
        List<HabitRecord> records = recordRepository.findByHabitId(habit.getId());

        if (strategy.isCompleted(habit, records, date)) {
            recordRepository.insert(new HabitRecord(habit.getId(), date, true));
        }
    }

    public List<Habit> getPendingHabits(LocalDate date) {
        return repository.findAll().stream()
                .filter(habit -> {
                    var strategy = CompletionStrategyFactory.getStrategy(habit.getType());
                    var records  = recordRepository.findByHabitId(habit.getId());
                    // csak azok jöjjenek, ahol még NEM teljesült a kiválasztott időszakra
                    return !strategy.isCompleted(habit, records, date);
                })
                .map(habit -> {
                    // dekorátorozd is
                    Habit d = new ImportantHabitDecorator(habit);
                    return habit.isRewarded() ? new RewardedHabitDecorator(d) : d;
                })
                .collect(Collectors.toList());
    }


}
