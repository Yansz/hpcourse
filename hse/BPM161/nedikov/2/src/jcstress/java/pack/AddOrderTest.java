package pack;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.ZZ_Result;

import static org.openjdk.jcstress.annotations.Expect.ACCEPTABLE;
import static org.openjdk.jcstress.annotations.Expect.FORBIDDEN;


@JCStressTest
@Outcome(id = "true, true", expect = ACCEPTABLE)
@Outcome(id = "true, false", expect = ACCEPTABLE)
@Outcome(id = "false, false", expect = ACCEPTABLE)
@Outcome(id = "false, true", expect = FORBIDDEN)
@State
public class AddOrderTest {
  private LockFreeSet<Integer> set = new LockFreeSet<>();

  @Actor
  public void actor1() {
    set.add(1);
    set.add(2);
  }

  @Actor
  public void actor2(ZZ_Result r) {
    r.r2 = set.contains(2);
    r.r1 = set.contains(1);
  }
}