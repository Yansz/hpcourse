package ru.hse.spb.sharkova.lockfreeset;

import com.devexperts.dxlab.lincheck.LinChecker;
import com.devexperts.dxlab.lincheck.LoggingLevel;
import com.devexperts.dxlab.lincheck.Options;
import com.devexperts.dxlab.lincheck.annotations.Operation;
import com.devexperts.dxlab.lincheck.strategy.stress.StressCTest;
import com.devexperts.dxlab.lincheck.strategy.stress.StressOptions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@StressCTest
public class LockFreeSetLincheckTest {
    private LockFreeSet<Integer> set = new LockFreeSet<>();

    @Operation
    public boolean add(int key) {
        return set.add(key);
    }

    @Operation
    public boolean remove(int key) {
        return set.remove(key);
    }


    @Operation
    public boolean contains(int key) {
        return set.contains(key);
    }

    @Operation
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Operation
    public String iterator() {
        List<Integer> l = new ArrayList<>();
        set.iterator().forEachRemaining(l::add);
        return l.toString();
    }

    @Test
    public void runLinCheck() {
        Options opts = new StressOptions()
                .iterations(10)
                .threads(2)
                .logLevel(LoggingLevel.DEBUG);
        LinChecker.check(this.getClass(), opts);
    }
}