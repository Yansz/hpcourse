package ru.hse.lupuleac.lockfree;

import com.devexperts.dxlab.lincheck.LinChecker;
import com.devexperts.dxlab.lincheck.Options;
import com.devexperts.dxlab.lincheck.annotations.Operation;
import com.devexperts.dxlab.lincheck.strategy.stress.StressCTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@StressCTest(actorsAfter = 0, actorsBefore = 0)
public class LinCheckTest {
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
    public String iterator() {
        List<Integer> ints = new ArrayList<>();
        set.iterator().forEachRemaining(ints::add);
        return ints.stream().map(Object::toString).collect(Collectors.joining
                (", "));
    }

    @Test
    public void test() {
        LinChecker.check(LinCheckTest.class);
    }
}
